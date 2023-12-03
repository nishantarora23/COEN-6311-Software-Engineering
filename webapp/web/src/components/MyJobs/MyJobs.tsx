import { injectIntl } from "react-intl";
import { Box, Button, CardContent, Modal, Typography, Pagination, IconButton } from "@mui/material";

import { TouchApp, Work } from "@mui/icons-material";
import { useState, useEffect } from "react";
import Snackbar from "@mui/material/Snackbar";
import MuiAlert, { AlertColor } from "@mui/material/Alert";
import InfoIcon from '@mui/icons-material/Info';
import SearchIcon from '@mui/icons-material/Search';

import axios from "axios";
import { API_URL } from "../../constants";
import { getUserId } from "../../services/userInfoService";
import React from "react";
import WarningIcon from '@mui/icons-material/Warning';
import JobCard from "./JobCard";
import SkeletonLoader from "../Jobs/SkeletonLoader";


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
  const [isResponseReceived, setIsisResponseReceived] = useState(false);
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
    setLoading(true)
  axios
  .post(`${API_URL}/search`, {
    id: getUserId(), 
    keywords: titleSearch,
    location: companySearch
  })
    .then((response) => {
      setJobsList(response.data?? []);
      setLoading(false)
      setIsisResponseReceived(true)
    })
    .catch((error) => {
      setJobsList([]);
      setLoading(false)
      setIsisResponseReceived(true)
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
  const [loading, setLoading] = useState(false);
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 5; // You can change this to display more or less items per page

  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentJobs = jobsList.slice(indexOfFirstItem, indexOfLastItem);

  // Change page handler
  const handleChangePage = (event: React.ChangeEvent<unknown>, value: number) => {
    setCurrentPage(value);
  };
  
  return (
    <Box component="div" sx={{ margin: "20px" }}>
      <Snackbar open={easyApplyResponseSnackbar.open} autoHideDuration={3000}>
        <MuiAlert severity={easyApplyResponseSnackbar.severity}>
          {easyApplyResponseSnackbar.message}
        </MuiAlert>
      </Snackbar>

      <Box component="div" sx={{
    marginBottom: "20px", 
    display: "flex", 
    justifyContent: "space-between", 
    alignItems: "center", // Align items vertically
    background: "#e8f4f8", // Adjust the background color to match your theme
    padding: "20px" // Add some padding around the box
}}>
    <input 
        type="text"
        placeholder="Job title, company, or keywords"
        value={titleSearch}
        onChange={handleTitleSearchChange}
        style={{ 
            flexGrow: 1, // Allow the input to grow
            padding: "20px", 
            fontSize: "1rem", 
            border: "1px solid #ccc", // Add a subtle border
            borderRight:"none",
            boxShadow: "inset 0 1px 3px rgba(0, 0, 0, 0.1)" // Inner shadow for inset effect
        }}
    />
    <input 
        type="text"
        placeholder="City or Province"
        value={companySearch}
        onChange={handleCompanySearchChange}
        style={{ 
            flexGrow: 1, // Allow the input to grow
            marginRight: "10px",
            padding: "20px",
            fontSize: "1rem", 
            border: "1px solid #ccc", // Add a subtle border
            boxShadow: "inset 0 1px 3px rgba(0, 0, 0, 0.1)" // Inner shadow for inset effect
        }}
    />
    <Button
        variant="contained"
        style={{
            backgroundColor: "#007bff", // Match the button color with your theme
            color: "white", // Text color
            textTransform: "none", // Avoid capitalizing all letters
            padding: "10px 20px", // Increase padding
            fontSize: "1rem", // Match font size with inputs
            boxShadow: "0 2px 4px rgba(0, 0, 0, 0.2)" // Add a subtle shadow
        }}
        onClick={handleSearch}
    >
        Search Jobs
    </Button>
</Box>


<Box component="div" sx={{ marginTop: "20px", minHeight:"400px" }}>
{loading ? (
  // Your skeleton loader UI goes here
  // Replace with your skeleton loader component
  <SkeletonLoader count={5} />
) : (
  <>
    {currentJobs?.length > 0 ? (
      currentJobs.map((jobInfo) => (
        <JobCard
          jobTitle={jobInfo.Job_Title}
          jobType={jobInfo.Company}
          location={jobInfo.Location}
          apply={jobInfo.Apply_Link}
        />
      ))
    ) : (
      <Box
  sx={{
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    minHeight: '450px', // Set the desired height
    borderRadius: '8px', // Rounded corners
    padding: '20px', // Add some padding
  }}
>
  {isResponseReceived ? (
    <>
      <>
    <IconButton color="info" aria-label="info">
      <InfoIcon fontSize="large" />
    </IconButton>
    {/* Error message */}
  </>
      <Typography variant="h6" color="textPrimary" align="center" mt={2}>
        There are no jobs matching your search criteria. Please refine your search.
      </Typography>
    </>
  ) : (
    <>
       <IconButton color="primary" aria-label="search">
      <SearchIcon fontSize="large" />
    </IconButton>
      <Typography variant="h6" color="textPrimary" align="center" mt={2}>
        You have not performed a search yet. Please fill in the details to see the available jobs.
      </Typography>
    </>
  )}
</Box>

    )}
  </>
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
  </Box>
  );
};

export default injectIntl(JobsList);
