// LandingPage.jsx

import React, { useEffect, useState } from 'react';
import {
  Container,
  Typography,
  Button,
  Grid,
  Card,
  CardContent,
  CardMedia,
  List,
  ListItemIcon,
  ListItemText,
  ListItem,
} from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import ExploreIcon from '@mui/icons-material/Explore';
import DescriptionIcon from '@mui/icons-material/Description';
import NotificationsIcon from '@mui/icons-material/Notifications';

import LandingPageImage from '../../assets/LandingPageImage.png';
import Login from '../Login/Login';
import FeatureGrid from '../../components/NewLandingPage/FeatureGrid';




const LandingPage = () => {
    const [text, setText] = useState('');
    const textToType = 'Welcome to JobHive - where opportunities meet talent!';
    const typingSpeed = 50; // Adjust typing speed as needed
  
   
  useEffect(() => {
    let timer: NodeJS.Timeout | null = null;

    if (text.length < textToType.length) {
      timer = setInterval(() => {
        setText(textToType.slice(0, text.length + 1)); // Add one character at a time

        if (text.length === textToType.length) {
          clearInterval(timer as NodeJS.Timeout); // Clear interval when text is fully typed
        }
      }, typingSpeed);
    }

    return () => {
      if (timer) clearInterval(timer as NodeJS.Timeout); // Clear interval on component unmount
    };
  }, [text, textToType, typingSpeed]);
    const scrollToSection = (sectionId: string) => {
        const section = document.getElementById(sectionId);
        if (section) {
          section.scrollIntoView({ behavior: 'smooth' });
        }
      };
  return (
    <Container maxWidth={false} style={{ position: 'relative', width: '100%' }}>
        <Grid justifyContent="center" style={{  position: 'relative' }}>
        <Grid justifyContent="center" style={{ position: 'relative' }}>
        <CardMedia
            component="img"
            src={LandingPageImage} // Replace with the URL of your desired image
            alt="Your Image Description"
            style={{ maxWidth: '100%', height: '80vh', borderRadius: '40px', position: 'relative' }}
        />
      <Typography
        variant="h5"
        style={{
          position: 'absolute',
        top: '50%',
        left: '50%',
        width: '70%',
        height: '50%',
        transform: 'translate(-50%, -50%)',
        textAlign: 'center',
        fontSize: '60px',
        padding: '10px',
        color: 'rgba(255, 255, 255, 0.8)',
        whiteSpace: 'normal', // Change to 'normal' for text to wrap onto new lines
        overflow: 'hidden',
        animation: 'typing 3s steps(40) infinite',
        }}
      >
        {text}
      </Typography>
  <Button
    variant="contained"
    color="primary"
    onClick={() => scrollToSection('LearnMore')}
    style={{ position: 'absolute', bottom: '60px', left: '50%', transform: 'translateX(-50%)' }}
  >
    Learn More
  </Button>
</Grid>

      <Grid container id="LearnMore" spacing={4} justifyContent="center" style={{ marginTop: '50px' }}>
        {/* <Grid item xs={12} sm={6}>
          <Card>
            <CardMedia
              component="img"
              alt="Key Features"
              height="140"
              image="https://via.placeholder.com/300"  // Replace with an actual image URL
            />
            <CardContent>
              <Typography variant="h6" component="div" gutterBottom>
                Key Features
              </Typography>
              <List>
        <ListItem>
          <ListItemIcon>
            <SearchIcon />
          </ListItemIcon>
          <ListItemText primary="Search for jobs based on keywords" />
        </ListItem>
        <ListItem>
          <ListItemIcon>
            <ExploreIcon />
          </ListItemIcon>
          <ListItemText primary="Discover new job opportunities" />
        </ListItem>
        <ListItem>
          <ListItemIcon>
            <DescriptionIcon />
          </ListItemIcon>
          <ListItemText primary="Resume Builder" />
        </ListItem>
        <ListItem>
          <ListItemIcon>
            <NotificationsIcon />
          </ListItemIcon>
          <ListItemText primary="Get Job Recommendations" />
        </ListItem>
        {/* Add more items with icons as needed */}
      {/* </List>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} sm={6}>
          <Card>
            <CardContent>
              <Typography variant="h6" component="div" gutterBottom>
                Get Started
              </Typography>
              <Typography paragraph>
                Ready to find your dream job? Sign in or create an account to get started.
              </Typography>
             <Login></Login>
            </CardContent>
          </Card>
        </Grid>  */}
        <FeatureGrid></FeatureGrid>
      </Grid>
      </Grid>
    </Container>
  );
};

export default LandingPage;
