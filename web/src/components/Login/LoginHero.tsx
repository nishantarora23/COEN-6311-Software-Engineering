import React from 'react';
import { Grid, Typography, Button, List, ListItem } from '@mui/material';
import { makeStyles, styled } from '@mui/material/styles';
import LoginSide from '../../assets/loginSide.png'
import { Link } from 'react-router-dom';



const StyledImageWrapper = styled('div')(({ theme }) => ({
    width: '400px', // Fixed width
    height: '400px', 
}));

const StyledImage = styled('img')(({ theme }) => ({
  width: '100%', // Adjust the width as needed
  height: '100%', // Adjust the height as needed
  objectFit: 'cover', // Maintain aspect ratio and cover the container
}));


const LoginHero = () => {
    const dotStyle = {
        listStyleType: 'disc', // Use 'disc' to show dot as bullet point
      };
  return (
    <Grid container sx={{ }}>
        <Grid xs={12} sm={2}></Grid>
      <Grid xs={12} sm={4}>
      <StyledImageWrapper>
          <StyledImage src={LoginSide} alt="Login Side" />
        </StyledImageWrapper>
      </Grid>
      <Grid item xs={12} sm={4} sx={{ display: 'flex', flexDirection: 'column', justifyContent: 'center', padding: 4 }}>
      <div>
      <h1 style={{ textAlign: 'left' }}>
      This search is all about you. We're here to simplify it.Your Search, Your Way
      </h1>
      <h4 style={{ textAlign: 'left' }}>
      Welcome to Your Personalized Job Search Journey!
      </h4>
    </div>
    <List sx={{ listStyleType: 'disc', padding: 0, ml: 3 }}>
          {['Verified Jobs', 'Resume Builder', 'Up to date listing'].map((text, index) => (
            <ListItem key={index} sx={{ display: 'list-item' }}>{text}</ListItem>
          ))}
        </List>
        <Typography variant="body1" sx={{ m: 2 }}>
          Don't have an account yet? It's easy! 
          <Button color="primary">
            <Link style={{ color:"white", textDecoration:"none"}}to="/signup">Create an account now</Link>
          </Button>
          
        </Typography>
      </Grid>
      <Grid xs={12} sm={2}></Grid>
    </Grid>
  );
};

export default LoginHero;
