import {Link} from 'react-router-dom'
import ProfileDetails from "../components/ManageProfile/ProfileDetails"
import Education from "../components/ManageProfile/Education"
import SkillSets from "../components/ManageProfile/SkillSets"
import WorkExperience from "../components/ManageProfile/WorkExperience"

function ManageProfile() {
  return (
    <>
    <div className="card_header">
      <Link className='Link' to='/'>Home</Link>
      <span className='margin'> / </span> 
      <Link className='Link' to='/'>User</Link>
      <span className='margin'> / </span>
      <span>Profile</span>
    </div>
    <ProfileDetails></ProfileDetails>
    <Education></Education>
    <SkillSets></SkillSets>
    <WorkExperience></WorkExperience>
    </>
    )
  }
export default ManageProfile
