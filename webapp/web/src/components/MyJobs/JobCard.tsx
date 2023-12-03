import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import LocationOnIcon from '@mui/icons-material/LocationOn'; // Ensure @mui/icons-material is installed

interface JobCardProps {
  jobTitle: string;
  jobType: string;
  location: string;
  apply: string;
}

const JobCard: React.FC<JobCardProps> = ({ 
  jobTitle, 
  jobType, 
  location,
  apply
}) => {
  return (
    <Box
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
    <Typography variant="h6" component="h3" sx={{ fontWeight: 'bold', color: '#333' }}>
      {jobTitle}
    </Typography>
    <Typography variant="body2" sx={{ color: '#777' }}>
      {jobType}
    </Typography>
  </Box>
  <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }} mt={1}>
    <LocationOnIcon color="error" /> {/* Adjust color if needed */}
    <Typography variant="body2" sx={{ color: '#555' }}>
      {location}
    </Typography>
  </Box>
</Box>

     <Button
        variant="contained"
        sx={{
          backgroundColor: '#4caf50', // Adjust the button color as needed
          '&:hover': {
            backgroundColor: '#43a047', // Adjust button hover color as needed
          }
        }}
        href={apply}
        target="_blank" 
      >
        I'm interested
      </Button>
      
    </Box>
  );
};

export default JobCard;

// Usage example:
// <JobCard
//   jobTitle="Full-stack Engineer (front-end heavy)"
//   jobType="Outside US/Remote"
//   level="Mid to Senior"
//   salaryRange="$30K - $60K"
//   equityRange="1% - 1.5%"
//   location="Remote"
// />
