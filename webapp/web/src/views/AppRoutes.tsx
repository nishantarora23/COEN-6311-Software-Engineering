import { BrowserRouter, Routes, Route } from "react-router-dom";
import LandingPage from "./NewLandingPage/NewLandingPage";
import Login from "./Login/Login";
import SignUp from "./SignUp/SignUp";
import Home from "./Student/Home/Home";
import React from "react";
import Header from "../components/Header/Header";

const AppRoutes = () => {
  return (
    <BrowserRouter>
    <Header />
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="login" element={<Login />} />
        <Route path="signup" element={<SignUp />} />
        {/* <Route path="student/home" element={<Home />} />  
        <Route path="student/profile" element={<Home />} />    
        <Route path="student/searchjobs" element={<Home />} />              */}
      </Routes>
    </BrowserRouter>
  );
};

export default AppRoutes;
