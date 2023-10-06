import { useEffect, useState } from "react";
import { apiCall } from "../utils/utils";
import { ENDPOINTS, REPORT_ENDPOINTS } from "../utils/statics";
import HeadlineText from "../components/HeadlineText";
import CommonTable from "../components/CommonTable";
import TablePagination from '@mui/material/TablePagination';
import jsPDF from "jspdf"
import { AiFillCaretDown } from 'react-icons/ai';

function FeedBackPage() {
  const [eventList, setEventList] = useState();
  const [rowPerPage, setRowPerPage] = useState(20);
  const [total, setTotal] = useState(0);
  const [type, setType] = useState(`OVERALL`);
  const [page, setPage] = useState(0);
  const [data, setData] = useState();
  const downloadPdf = async () => {
    //code to download pdf  
    console.log(`clicked`)
  };

  const getReport = async (reportType) => {
    console.log(reportType);
    setData();
    let response = await apiCall(REPORT_ENDPOINTS.getAllEventReport(reportType, page, rowPerPage), `GET`);
    // console.log(response.content);
    setType(reportType);
    setTotal(response.totalElement);
    setEventList(response.content);
    // data updating
    setData(response.content);
  };

  const listTostring = (arr) =>{
    return arr?.map((elem)=>elem?.feedback).join(", ");
  }

  const createPdf = () => {
    console.log("clicked on createPdf");
    const doc = new jsPDF('lanscape', 'px', 'a4', 'false');
    doc.setFontSize(15);
    doc.setFont('Helvertica', 'bold');
    doc.text(200, 60, `${type} Report`);

    data?.map((row) => {
      doc.addPage();
      doc.setFontSize(12);
      doc.setFont('Helvertica', 'bold');
      doc.text(120, 90, 'reportName');
      doc.text(120, 120, 'reportType');
      doc.text(120, 150, 'registrationDate');
      doc.text(120, 180, 'eventEndDate');
      doc.text(120, 210, 'eventName');
      doc.text(120, 240, 'feedbackList');
      doc.text(120, 270, 'bookMarkedCount');
      doc.text(120, 300, 'attendance');

      doc.setFont('Helvertica', 'normal');
      doc.text(200, 90, `: ${row?.reportName}`);
      doc.text(200, 120, `: ${row?.reportType}`);
      doc.text(200, 150, `: ${row?.registrationDate}`);
      doc.text(200, 180, `: ${row?.eventEndDate}`);
      doc.text(200, 210, `: ${row?.eventName}`);
      doc.text(200, 240, `: ${(row?.feedbackList == null || row?.feedbackList == undefined) ? ['-'] : listTostring(row?.feedbackList)}`);
      doc.text(200, 270, `: ${row?.bookMarkedCount}`);
      doc.text(200, 300, `: ${row?.attendance}`);
    });

    doc.save('report.pdf')
  };


  useEffect(() => {
    getReport(type, 0);
  }, []);



  const unMarkEvent = async (bookmark_id) => {
    // todo fix cors issue
    console.log(bookmark_id);
    let response = await apiCall(ENDPOINTS.unMarkEvent(bookmark_id), "DELETE");
    console.log(response);
    setData(response.content);
  };

  return (
    <>
    <br/>
      <div className="col-12 row  d-flex justify-content-between mt-2">
        <div className="col-auto">
          <HeadlineText text="Report" />
        </div>
        <div className="col-auto" style ={{display:'flex',flexDirection:'row', gap:10}}>
          {/* export button----------------- */}
          <div className="dropdown">
            <button
              data-bs-toggle="dropdown"
              className="btn btn-sm btn-info bg-info bg-opacity-10 "
              type="button"
              aria-expanded="false"
            >
                Select Report <AiFillCaretDown/>
            </button>
            <ul className="dropdown-menu">
              <li onClick={event => getReport(`BOOKMARK`)}>
                <a className="dropdown-item" href="#">
                  Bookmark
                </a>
              </li>
              <li onClick={event => getReport(`ATTENDANCE`)}>
                <a className="dropdown-item" href="#">
                  Attendance
                </a>
              </li>
              <li onClick={event => getReport(`OVERALL`)}>
                <a className="dropdown-item" href="#">
                  Overall
                </a>
              </li>
              <li onClick={event => getReport(`FEEDBACK`)}>
                <a className="dropdown-item" href="#">
                  feedback
                </a>
              </li>
            </ul>
          </div>
          {/* export button----------------- */}
        <div className="dropdown">
          <button
            onClick={createPdf}
            className="btn btn-sm btn-info bg-info bg-opacity-10 "
            type="button"
            aria-expanded="false"
          >
            <i className="bi bi-download"></i> Download Report
          </button>

        </div>
        </div>
      </div>

      <br/><br/>

      <div className="row">
        <div className="col-md-12">
          <CommonTable data={data}></CommonTable>
        </div>
      </div>
      <div className="col-auto">
        
      </div>

      <TablePagination
        rowsPerPageOptions={[10, 20]}
        component="div"
        count={total}
        rowsPerPage={rowPerPage}
        page={page}
        onPageChange={(event, newPage) => {
          if (total >= page * rowPerPage) {
            setPage(newPage);
            console.log(newPage);
            getReport(`OVERALL`);
          }
        }}
        onRowsPerPageChange={event => {
          setRowPerPage(event.target.value);
          getReport(type);
        }}
        showFirstButton={true}
        showLastButton={true}
      />

    </>
  );
}

export default FeedBackPage;
