import { injectIntl } from 'react-intl';
import { AppBar, Grid, Box, Toolbar, Button, Typography, IconButton, Avatar } from '@mui/material';
import styled from 'styled-components';
import {
  LogoutOutlined,
  Work,
  // SchoolIcon
} from '@mui/icons-material';
import { Link } from 'react-router-dom';
import { getUserName, getUserRole, getFullName, getIsLoggedIn, getUserId } from '../../services/userInfoService';
import './MenuBar.scss';
import { indigo } from '@mui/material/colors';
import Logo from '../../assets/JobHive-logo.png';
import React from 'react';
import { useNavigate } from 'react-router-dom';
import { QUICK_CV_URL } from '../../constants';


type Props = {
  intl: any;
  title: string;
  noBtn: boolean;
};
const StyledLink = styled(Link)`
  margin: 0 10px;
  text-decoration: none;
  color: inherit;
  padding:10px;
  border-radius:5px;
  &:hover {
    background-color: black;
    color: white;
  }
`;
const MenuBar = ({ intl, title, noBtn }: Props) => {
  let isLoggedIn = false;
  const userRole = getUserRole();
  isLoggedIn = getIsLoggedIn();
  const navigate = useNavigate();
  // let userId = "-1";
  let userId = getUserId();
  console.log(isLoggedIn)

  const MENU_ITEMS = {
    Student: [
      {
        id: 'dashboard',
        link: 'student/home'
      }
    ],
  };

 

  const doLogout = () => {
    localStorage.clear();
    navigate('/login');
  };
  
  
  
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar component="nav" position="sticky" style={{boxShadow : "none"}}>
      <Toolbar sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
      {/* Left side - Logo */}
      <Link to="/">
        <img src={Logo} alt="Logo" height="70" style={{ margin: '10px' }} />
      </Link>

      {/* Right side - Navigation Links and Buttons */}
      <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
  {userId ? (
    <nav style={{ boxShadow:'none' }}>
      <StyledLink to="/">Home</StyledLink>
      <StyledLink to="student/home">My Jobs</StyledLink>
      <StyledLink to="student/profile">Profile</StyledLink>
      <StyledLink to="student/searchjobs">Search A Job</StyledLink>
      <StyledLink to="" onClick={() => {
                  window.open(QUICK_CV_URL, "_blank");
                }}>
       Resume Builder
      </StyledLink>
      <StyledLink to="/login" onClick={doLogout}>
        Log Out
      </StyledLink>
      
    </nav>
  ) : (
    <>
      <Button component={Link} to="/login" color="inherit" style={{ margin: '0 10px' }}>
        Login
      </Button>
      <Button component={Link} to="/signup" color="inherit" style={{ margin: '0 10px' }}>
        Sign Up
      </Button>
    </>
  )}
</Box>
    </Toolbar>      
      </AppBar>
    </Box>
  );
};

export default injectIntl(MenuBar);