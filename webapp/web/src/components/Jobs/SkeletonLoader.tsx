import { Box, Skeleton, Typography, Button } from "@mui/material";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import React from "react";

interface JobCardSkeletonProps {
  count: number;
}

const SkeletonLoader: React.FC<JobCardSkeletonProps> = ({ count }) => {
  const skeletonComponents = Array.from(Array(count).keys()).map((index) => (
    <Box
      key={index}
      sx={{
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'space-between',
        padding: '12px 16px',
        backgroundColor: '#f5f5f5', // Adjust the color as needed
        borderRadius: '4px', // Rounded corners
        marginBottom: '8px', // Add space below the card
      }}
    >
      <Box sx={{ display: 'flex', flexDirection: 'column', gap: 0.5 }}>
        <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
          <Skeleton variant="text" width={150} height={24} />
          <Skeleton variant="text" width={80} height={18} />
        </Box>
        <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }} mt={1}>
          <Skeleton variant="text" width={100} height={18} />
        </Box>
      </Box>

      <Skeleton variant="text" width={100} height={18} />
    </Box>
  ));

  return <>{skeletonComponents}</>;
};

export default SkeletonLoader;
