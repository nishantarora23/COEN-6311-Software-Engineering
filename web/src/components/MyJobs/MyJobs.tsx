import { injectIntl } from "react-intl";
import { Box, Button, CardContent, Modal, Typography, Pagination } from "@mui/material";

import { TouchApp, Work } from "@mui/icons-material";
import { useState, useEffect } from "react";
import Snackbar from "@mui/material/Snackbar";
import MuiAlert, { AlertColor } from "@mui/material/Alert";
import axios from "axios";
import { API_URL } from "../../constants";
import { getUserId } from "../../services/userInfoService";


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
  const [titleSearch, setTitleSearch] = useState("");
const [companySearch, setCompanySearch] = useState("");

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

const handleTitleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTitleSearch(event.target.value);
};

const handleCompanySearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setCompanySearch(event.target.value);
};

const handleSearch = () => {
  if (titleSearch.trim() && companySearch.trim()) {
  axios
  .post(`${API_URL}/search`, {
    id: getUserId(), 
    keywords: titleSearch,
    location: companySearch
  })
    .then((response) => {
      setJobsList(response.data?? []);
    })
    .catch((error) => {
      setJobsList([]);
      console.log(error);
    });
  }else{
    setEasyApplyResponseSnackbar({
      open: true,
      severity: "warning",
      message: "Please fill in both search fields.",
    });
    setTimeout(() => {
      setEasyApplyResponseSnackbar({
        ...easyApplyResponseSnackbar,
        open: false,
      });
    }, 3000); // 5000ms or 5 seconds to hide the message

  }
};
  useEffect(() => {
    // axios
    // .post(`${API_URL}/getRecommendedJobs/search`, {
    //   id: getUserId(),
    // })
    //   .then((response) => {
    //     setJobsList(response.data?? []);
    //   })
    //   .catch((error) => {
    //     setJobsList([]);
    //     console.log(error);
    //   });
  }, []);

  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 3; // You can change this to display more or less items per page

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

      <Box component="div" sx={{ marginBottom: "20px", display: "flex", justifyContent: "space-between" }}>
    <input 
        type="text"
        placeholder="Job title, keywords, or company"
        value={titleSearch}
        onChange={handleTitleSearchChange}
        style={{ width: "40%", padding: "10px", fontSize: "1rem", marginRight: "10px" }}
    />
    <input 
        type="text"
        placeholder="City, province, or remote"
        value={companySearch}
        onChange={handleCompanySearchChange}
        style={{ width: "40%", padding: "10px", fontSize: "1rem", marginRight: "10px" }}
    />
    <Button
        variant="contained"
        color="primary"
        onClick={handleSearch}
    >
        Find Jobs
    </Button>
</Box>

      <Box component="div" sx={{ marginTop: "20px" }}>
        {currentJobs?.length > 0 &&
          currentJobs.map((jobInfo) => {
            return (
              <CardContent
                key={jobInfo.Job_Title}
                sx={{
                  borderBottom: "1px solid #868686",
                }}
              >
                <Typography
                  color="primary"
                  sx={{
                    textTransform: "capitalize",
                    fontWeight: "600",
                    fontSize: "1.5rem",
                  }}
                >
                  {jobInfo.Job_Title}
                </Typography>
                <Typography
                  sx={{
                    textTransform: "capitalize",
                    fontSize: "1.25rem",
                  }}
                >
                  {jobInfo.Location}
                </Typography>
                <Typography
                  sx={{
                    textTransform: "capitalize",
                    fontSize: "1.25rem",
                    color: "#868686",
                  }}
                >
                  {jobInfo.Company}
                </Typography>
                <Box component="div" sx={{ marginTop: "20px" }}>
                  <Button
                    variant="contained"
                    color="primary"
                    sx={{ fontSize: "1.1rem" }}
                    href={ jobInfo.Apply_Link }
                    target="_blank" 
                    // onClick={() => {
                    //   debugger;
                    //   setSelectedJobInfo(jobInfo);
                    //   handleOpen();
                    // }}
                  >
                    <TouchApp sx={{ marginRight: "10px" }} /> Apply
                  </Button>
                </Box>
              </CardContent>
            );
          })}
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
      {/* <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-title"
        aria-describedby="modal-description"
      >
        <Box
          sx={{
            position: "absolute",
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            width: 800,
            bgcolor: "background.paper",
            border: "2px solid #000",
            boxShadow: 24,
            p: 2,
            textAlign: "left",
          }}
        >
          <Typography
            id="modal-title"
            sx={{
              fontSize: "1.75rem",
              fontWeight: "600",
            }}
          >
            {selectedJobInfo?.Job_Title}
          </Typography>
          <p id="modal-description">
            {selectedJobInfo?.Job_Title} . {selectedJobInfo?.Job_Title}
          </p>
          <Typography>
            <Work sx={{ marginTop: "10px", marginRight: "10px" }} />
            {selectedJobInfo?.Job_Title}
          </Typography>
          {selectedJobInfo?.Job_Title && (
            <Typography
              sx={{
                fontSize: "1.1rem",
                marginTop: "10px",
              }}
            >
              Apply before: {selectedJobInfo?.Job_Title}
            </Typography>
          )}
          <Typography
            variant="h5"
            sx={{ marginTop: "20px", marginBottom: "20px" }}
          >
            About the job
          </Typography>
          <Typography>{selectedJobInfo?.Job_Title}</Typography>
          <Box component="div" sx={{ float: "right" }}>
            <Button
              variant="contained"
              onClick={() => {
                // handleEasyApply(selectedJobInfo);
              }}
              sx={{ marginRight: "20px" }}
            >
              Easy Apply
            </Button>
          </Box>
        </Box>
      </Modal> */}
    </>
  );
};

export default injectIntl(JobsList);
