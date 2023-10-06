import { useEffect, useState } from "react";
import CardEvent from "../components/CardEvent";
import Empty from "../components/Empty";
import { apiCall } from "../utils/utils";
import { ENDPOINTS, EXPORT_ENDPOINTS } from "../utils/statics";
import HeadlineText from "../components/HeadlineText";
import { toast } from "react-toastify";
import { Link } from "react-router-dom"

function Export() {
  const [eventList, setEventList] = useState();
  const toastMessage = (title) => toast("Event removed from bookmark list");
  const [format, setFormat] = useState();
  const [tag, setTag] = useState();
  const [dynButton, setDynButton] = useState("View Data");
  const [data, setData] = useState();

  const formatSelect = (formatSelect) => {
    setFormat(formatSelect);
    console.log(format);
    setDynButton("download data");
  };

  const downlodFile = async () => {
    setData();
    let response = await apiCall(EXPORT_ENDPOINTS.exportReport(1, tag, format, 0), `GET`); // userId hardcoded
    setData(response.content)
    console.log(response);
  }
  useEffect(() => {
    fetchEvents();
  }, []);

  useEffect(() => {
    downlodFile();
  }, [tag]);

  const fetchEvents = async () => {
    let user_id = 1;
    let response = await apiCall(ENDPOINTS.bookmarks(user_id), "GET");
    console.log(response);
    setEventList(response.data);
  };
  const unMarkEvent = async (bookmark_id) => {
    // todo fix cors issue
    console.log(bookmark_id);
    let response = await apiCall(ENDPOINTS.unMarkEvent(bookmark_id), "DELETE");
    console.log(response);
    fetchEvents();
    toastMessage();
  };

  return (
    <>
    <br/>
      <div className="col-12 row  d-flex justify-content-between mt-2">
        <div className="col-auto">
          <HeadlineText text="Export Calendar" />
        </div>
        <div className="col-auto" style= {{display:'flex',flexDirection:'row', gap:10}}>
          {/* tag select */}
          <div className="dropdown" >
            <button style={{width:175}}
              className="btn btn-sm btn-info bg-info bg-opacity-10 dropdown-toggle"
              type="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
               {tag == null ? ` Select Tag` : tag}
            </button>
            <ul className="dropdown-menu">
              <li onClick={event => {
                setTag('tagged');
                }}>
                <a className="dropdown-item" href="#">
                  tagged
                </a>
              </li>
              <li onClick={event => {
                setTag('confirmed_attendance');
                }}>
                <a className="dropdown-item" href="#">
                  confirmed_attendance
                </a>
              </li>
              <li onClick={event => {
                setTag('marked');
                }}>
                <a className="dropdown-item" href="#">
                  marked
                </a>
              </li>
            </ul>
          </div>
          
          
          {/* Format select */}
          <div className="dropdown" >
            <button style={{width:175}}
              className="btn btn-sm btn-info bg-info bg-opacity-10 dropdown-toggle"
              type="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
               {format == null ? ` Select Format` : format}
            </button>
            <ul className="dropdown-menu">
              <li onClick={event => formatSelect('JSON')}>
                <a className="dropdown-item" href="#">
                  JSON
                </a>
              </li>
              <li onClick={event => formatSelect('ICS')}>
                <a className="dropdown-item" href="#">
                  ICS
                </a>
              </li>
              <li onClick={event => formatSelect('XML')}>
                <a className="dropdown-item" href="#">
                  XML
                </a>
              </li>
            </ul>
          </div>

          <div>
            
              
              <button
                style={{width:175}}
                className="btn btn-sm btn-info bg-info bg-opacity-10"
                type="button"
                disabled={((tag === null || tag === undefined) && (format === null || format === undefined)) ? true : false}
                aria-expanded="false"
                onClick={downlodFile}
                href={EXPORT_ENDPOINTS.exportReport(1, tag, format, 0)}
              >
                <i className="bi bi-download"></i> <Link style={{textDecoration:'none'}} to={EXPORT_ENDPOINTS.exportReport(1, tag, format, 0)}> Download </Link>
              </button>

        </div>
          
        </div>
      </div>

      <br/><br/>


      

      <div className="row">
        <div className="col-md-12">
          {(data?.length ?? 0) < 1 && <Empty />}
          <div className="row">
            {data?.map((item) => {
              return (
                <CardEvent
                  key={item?.eventId}
                  event={item}
                  onClickMark={() => unMarkEvent(item?.id)}
                  isMarked={true}
                  isCompact={true}
                />
              );
            })}
          </div>
        </div>
      </div>
    </>
  );
}

export default Export;
