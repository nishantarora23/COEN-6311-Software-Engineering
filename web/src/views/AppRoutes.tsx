import { BrowserRouter, Routes, Route } from "react-router-dom";
import LandingPage from "./NewLandingPage/NewLandingPage";
import Login from "./Login/Login";
import SignUp from "./SignUp/SignUp";
import Home from "./Student/Home/Home";
import React from "react";
import Header from "../components/Header/Header";
import StudentDetails from "../components/Student/Details/StudentDetails";
import MyJobs from "../components/MyJobs/MyJobs";
import RecommandationJobs from "../components/RecommandationJobs/RecommandationJobs";


const AppRoutes = () => {
  return (
    <BrowserRouter>
    <Header />
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="login" element={<Login />} />
        <Route path="signup" element={<SignUp />} />
        <Route path="student/home" element={<RecommandationJobs />} />  
        <Route path="student/profile" element={<StudentDetails />} />    
        <Route path="student/searchjobs" element={<MyJobs />} />   
        <Route path="student/upload-a-resume" element={<MyJobs />} />             
      </Routes>
    </BrowserRouter>
  );
};

export default AppRoutes;
