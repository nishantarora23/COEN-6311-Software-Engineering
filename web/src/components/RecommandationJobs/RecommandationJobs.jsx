
import { injectIntl } from "react-intl";
import { Box, Button, CardContent, Modal, Typography, Pagination, IconButton } from "@mui/material";
import JobsList from "../Jobs/Jobs";
import JobCard from "../MyJobs/JobCard";

const RecommandationJobs = () => {  
  return (
    <Box component="div" sx={{ margin: "20px" }}>
        <>
                <CardContent>
                  <Typography
                    sx={{
                      fontSize: "1.75rem",
                      textTransform: "capitalize",
                      fontWeight: "600",
                    }}
                  >
                    Jobs recommendations
                  </Typography>
                  <Typography sx={{ color: "#868686" }}>
                    based on your Interest
                  </Typography>
                  <JobsList></JobsList>
                   
                </CardContent>
              </>
  </Box>
  );
};

export default injectIntl(RecommandationJobs);
