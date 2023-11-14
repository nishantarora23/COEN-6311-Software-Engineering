import { injectIntl } from "react-intl";
import { Box, Button, CardContent, Modal, Typography, Pagination } from "@mui/material";

import { TouchApp, Work } from "@mui/icons-material";
import { useState, useEffect } from "react";
import Snackbar from "@mui/material/Snackbar";
import MuiAlert, { AlertColor } from "@mui/material/Alert";
import axios from "axios";
import { API_URL } from "../../constants";
import { getFullName, getUserName, getUserId } from "../../services/userInfoService";


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
        setJobsList(response.data?? []);
        // setJobsList(response?.data ?? []);
        // {
        //   title: "Senior Software Engineer",
        //   description: "Lead the development of our main platform and manage a small team of junior developers.",
        //   name: "Tech Innovations Inc.",
        //   location: "Boston, MA",
        //   qualifications: "Bachelor's degree in Computer Science, 5+ years of experience, leadership skills.",
        //   deadline: "2023-12-15",
        //   id: "job-001"
        // },
        
        // const jobListings = [
        //   {
        //     title: "Senior Software Engineer",
        //     description: "Lead the development of our main platform and manage a small team of junior developers.",
        //     name: "Tech Innovations Inc.",
        //     location: "Boston, MA",
        //     qualifications: "Bachelor's degree in Computer Science, 5+ years of experience, leadership skills.",
        //     deadline: "2023-12-15",
        //     id: "job-001"
        //   },
        //   {
        //     title: "Data Analyst",
        //     description: "Analyze large datasets to extract meaningful insights and support strategic decisions.",
        //     name: "DataWise Analytics",
        //     location: "San Francisco, CA",
        //     qualifications: "Bachelor's degree in Statistics or related field, proficiency in SQL and Python, experience with data visualization tools.",
        //     deadline: "2023-11-30",
        //     id: "job-002"
        //   },
        //   {
        //     title: "UX/UI Designer",
        //     description: "Create user-centric designs for our mobile and web applications.",
        //     name: "Creative Solutions",
        //     location: "Seattle, WA",
        //     qualifications: "Bachelor's degree in Design, portfolio of design projects, experience with Sketch or Figma.",
        //     deadline: "2023-10-31",
        //     id: "job-003"
        //   },
        //   {
        //     title: "Marketing Manager",
        //     description: "Lead our marketing team to drive brand awareness and generate leads through various marketing channels.",
        //     name: "MarketGurus Inc.",
        //     location: "New York, NY",
        //     qualifications: "Bachelor's degree in Marketing, 5+ years of marketing experience, strong communication skills.",
        //     deadline: "2023-11-20",
        //     id: "job-004"
        //   },
        //   {
        //     title: "Network Security Expert",
        //     description: "Protect company's data and infrastructure from cyber threats.",
        //     name: "SecureTech Innovations",
        //     location: "Dallas, TX",
        //     qualifications: "Bachelor's degree in Information Technology, certifications in cybersecurity, 5+ years of experience in network security.",
        //     deadline: "2023-12-01",
        //     id: "job-005"
        //   },
        //   {
        //     title: "Senior Software Engineer",
        //     description: "Lead the development of our main platform and manage a small team of junior developers.",
        //     name: "Tech Innovations Inc.",
        //     location: "Boston, MA",
        //     qualifications: "Bachelor's degree in Computer Science, 5+ years of experience, leadership skills.",
        //     deadline: "2023-12-15",
        //     id: "job-001"
        //   },
        //   {
        //     title: "Data Analyst",
        //     description: "Analyze large datasets to extract meaningful insights and support strategic decisions.",
        //     name: "DataWise Analytics",
        //     location: "San Francisco, CA",
        //     qualifications: "Bachelor's degree in Statistics or related field, proficiency in SQL and Python, experience with data visualization tools.",
        //     deadline: "2023-11-30",
        //     id: "job-002"
        //   },
        //   {
        //     title: "UX/UI Designer",
        //     description: "Create user-centric designs for our mobile and web applications.",
        //     name: "Creative Solutions",
        //     location: "Seattle, WA",
        //     qualifications: "Bachelor's degree in Design, portfolio of design projects, experience with Sketch or Figma.",
        //     deadline: "2023-10-31",
        //     id: "job-003"
        //   },
        //   {
        //     title: "Marketing Manager",
        //     description: "Lead our marketing team to drive brand awareness and generate leads through various marketing channels.",
        //     name: "MarketGurus Inc.",
        //     location: "New York, NY",
        //     qualifications: "Bachelor's degree in Marketing, 5+ years of marketing experience, strong communication skills.",
        //     deadline: "2023-11-20",
        //     id: "job-004"
        //   },
        //   {
        //     title: "Network Security Expert",
        //     description: "Protect company's data and infrastructure from cyber threats.",
        //     name: "SecureTech Innovations",
        //     location: "Dallas, TX",
        //     qualifications: "Bachelor's degree in Information Technology, certifications in cybersecurity, 5+ years of experience in network security.",
        //     deadline: "2023-12-01",
        //     id: "job-005"
        //   },
        // ];
        // setJobsList(jobListings);
        // console.log(jobs);
        
      })
      .catch((error) => {
        setJobsList([]);
        console.log(error);
      });
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
