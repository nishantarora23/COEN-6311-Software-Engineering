import { injectIntl } from "react-intl";
import { Box, Button, CardContent, Modal, Typography, Pagination } from "@mui/material";

import { TouchApp, Work } from "@mui/icons-material";
import { useState, useEffect } from "react";
import Snackbar from "@mui/material/Snackbar";
import MuiAlert, { AlertColor } from "@mui/material/Alert";
import axios from "axios";
import { API_URL } from "../../constants";
import { getFullName, getUserName, getUserId } from "../../services/userInfoService";
import React from "react";
import JobCard from "../MyJobs/JobCard";
import SkeletonLoader from "./SkeletonLoader";


// Define interface for the Snackbar state
export interface EasyApplyResponseSnackbar {
  open: boolean;
  severity: AlertColor;
  message: string;
}
// Define interface for the job information
export interface JobInfo {
  Job_Title: string;
  Company: string;
  Location: string;
  Apply_Link: string;
}
// Define the JobsList component
const JobsList = () => {
  const [open, setOpen] = useState(false);
  const [loading, setLoading] = useState(true);
  const [selectedJobInfo, setSelectedJobInfo] = useState<JobInfo>({
    Job_Title: "",
    Company: "",
    Location: "",
    Apply_Link: "",
  });
  const [jobsList, setJobsList] = useState<Array<JobInfo>>([]);
  const [easyApplyResponseSnackbar, setEasyApplyResponseSnackbar] =
    useState<EasyApplyResponseSnackbar>({
      open: false,
      severity: "info",
      message: "",
    });
  // Fetch jobs list from API on component mount
  useEffect(() => {
    axios
    .post(`${API_URL}/getRecommendedJobs `, {
      id: getUserId(),
    })
      .then((response) => {
        debugger;
        setJobsList(response.data?? []);
        setLoading(false); 
        
      })
      .catch((error) => {
        setJobsList([]);
        setLoading(false); 
        console.log(error);
      });
  }, []);

  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 5; // You can change this to display more or less items per page

  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentJobs = jobsList.slice(indexOfFirstItem, indexOfLastItem);

  // Change page handler
  const handleChangePage = (event: React.ChangeEvent<unknown>, value: number) => {
    setCurrentPage(value);
  };

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };
  // Handle easy apply for a job
  // const handleEasyApply = (payload: JobInfo) => {
  //   const objectPayload = {
  //     jobid: payload.id,
  //     username: getUserName(),
  //     applicant: getFullName(),
  //     ACTION: "ADD",
  //   };
  //   axios
  //     .post(`${API_URL}/application`, objectPayload)
  //     .then(() => {
  //       setEasyApplyResponseSnackbar({
  //         open: true,
  //         severity: "success",
  //         message: "Applied for job sucessfully.",
  //       });
  //     })
  //     .catch(() => {
  //       setEasyApplyResponseSnackbar({
  //         open: true,
  //         severity: "error",
  //         message: "Upload your resume to apply for jobs!",
  //       });
  //     });
  // };
  // Render the JobsList component UI
  return (
    <>
      <Snackbar open={easyApplyResponseSnackbar.open} autoHideDuration={3000}>
        <MuiAlert severity={easyApplyResponseSnackbar.severity}>
          {easyApplyResponseSnackbar.message}
        </MuiAlert>
      </Snackbar>
      <Box component="div" sx={{ marginTop: "20px" }}>
      {loading ? (
        /* Display the skeleton loader while loading is true */
        // Your skeleton loader UI goes here
        <SkeletonLoader count={5} />
// Replace with your skeleton loader component
      ) : (
        /* Display the job list once loading is false */
        currentJobs?.length > 0 &&
        currentJobs.map((jobInfo) => {
          return (
            <JobCard
              jobTitle={jobInfo.Job_Title}
              jobType={jobInfo.Company}
              location={jobInfo.Location}
              apply={jobInfo.Apply_Link}
              key={jobInfo.Job_Title} // Add a unique key for each job card
            />
          );
        })
      )}
    </Box>
      {/* Pagination Controls */}
      {jobsList.length > 0 && (
        <Box component="div" sx={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
          <Pagination
            count={Math.ceil(jobsList.length / itemsPerPage)}
            page={currentPage}
            onChange={handleChangePage}
            color="primary"
            showFirstButton
            showLastButton
          />
        </Box>
      )}
    </>
  );
};

export default injectIntl(JobsList);
