import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { useEffect } from 'react';

const CommonTable = ({data}) => {

  const listTostring = (arr) =>{
    return arr?.map((elem)=>elem?.feedback).join(", ");
  }

  useEffect(()=>{
    return () =>{
      data=[];
    }
  },[]);

  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell >Report Name</TableCell>
            <TableCell align="right">Report Type</TableCell>
            <TableCell align="right">Registration Date</TableCell>
            <TableCell align="right">Event End Date</TableCell>
            <TableCell align="right">Event Name</TableCell>
            <TableCell align="right">Feedbacks</TableCell>
            <TableCell align="right">Bookmark Count</TableCell>
            <TableCell align="right">Attendance Count</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
        
          {
          data?.map((row) => (
            <TableRow
              key={row.reportName}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {row.reportName}
              </TableCell>
              <TableCell align="right">{row?.reportType }</TableCell>
              <TableCell align="right">{row?.registrationDate}</TableCell>
              <TableCell align="right">{row?.eventEndDate}</TableCell>
              <TableCell align="right">{row?.eventName}</TableCell>
              <TableCell align="right">{(row?.feedbackList == null || row?.feedbackList == undefined) ? ['-'] : listTostring(row?.feedbackList)}</TableCell>
              <TableCell align="right">{row?.bookMarkedCount}</TableCell>
              <TableCell align="right">{row?.attendance}</TableCell>
              
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}

export default CommonTable;