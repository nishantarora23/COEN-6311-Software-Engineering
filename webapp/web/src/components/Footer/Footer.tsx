import { injectIntl } from 'react-intl';
import React from 'react';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Link from '@mui/material/Link';
import IconButton from '@mui/material/IconButton';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import FacebookIcon from '@mui/icons-material/Facebook';
import YouTubeIcon from '@mui/icons-material/YouTube';
import TwitterIcon from '@mui/icons-material/Twitter';
import PinterestIcon from '@mui/icons-material/Pinterest';
import InstagramIcon from '@mui/icons-material/Instagram';
type Props = {
  intl: any;
};



const Footer = ({ intl }: Props) => {
  return (
    <Box
      sx={{
        width: '100%',
        backgroundColor: 'hsl(232.5deg 12.12% 12.94%)', // Customize the color using theme
        color: 'white',
        py: 8, // Padding top & bottom
      }}
    >
      <Grid container spacing={2} justifyContent="space-around" alignItems="center">
        <Grid item xs={12} sm={4}>
          <Typography variant="h6" color="inherit" gutterBottom>
            JobHive
          </Typography>
          <IconButton color="inherit" aria-label="Facebook">
            <FacebookIcon />
          </IconButton>
          <IconButton color="inherit" aria-label="YouTube">
            <YouTubeIcon />
          </IconButton>
          <IconButton color="inherit" aria-label="Twitter">
            <TwitterIcon />
          </IconButton>
          <IconButton color="inherit" aria-label="Pinterest">
            <PinterestIcon />
          </IconButton>
          <IconButton color="inherit" aria-label="Instagram">
            <InstagramIcon />
          </IconButton>
        </Grid>
        <Grid item xs={12} sm={4} container spacing={1}>
          {/* {['Home', 'Pricing', 'Products', 'About Us'].map((text) => (
            <Grid item xs={6} key={text}>
              <Link href="#" color="inherit" underline="hover">
                {text}
              </Link>
            </Grid>
          ))} */}
          {/* {['Careers', 'Community', 'Privacy Policy'].map((text) => (
            <Grid item xs={6} key={text}>
              <Link href="#" color="inherit" underline="hover">
                {text}
              </Link>
            </Grid>
          ))} */}
        </Grid>
        <Grid item xs={12} sm={4} container>
        Copyright 2023. All Rights Reserved
        </Grid>
      </Grid>
    </Box>
  );
};
export default injectIntl(Footer);
