import React from 'react'
import Card from '../../shared/Card'
import {FaTimes,FaEdit} from 'react-icons/fa'
import {useState} from 'react'

function ProfileDetails() {
    const [Name,setName] = useState('Lee')
    const [Email,setEmail] = useState('barua.nishant97@gmail.com')
    const [isEditing,setIsEditing] = useState(false)

    const handleNameChange = (e) => {
        setName(e.target.value)
    }

    const handleEmailChange = (e) => {
        setEmail(e.target.value)
    }

    const handleEdit = (e) => {
        setIsEditing(true)
    }

    const handleUpdate = (e) => {
        setIsEditing(false)
    }

    const handleSubmit = (e) => {
        e.preventDefault();
    }

  return (
    isEditing ? (
    <Card>
        <h2>Edit Profile</h2>
        <form onSubmit={handleSubmit}>
        <h4>Name</h4>
            <div>
                <input className='input-form'
                    onChange={handleNameChange} 
                    type='text' 
                    placeholder={Name} 
                    value={Name}
                />
                <button className='close' onClick={handleUpdate}>
                    <FaTimes color='rgba(70,130,180)'/>
                </button> 
            </div>
        </form>
        <form onSubmit={handleSubmit}>
        <h4>Email Address</h4>
            <div>
                <input className='input-form'
                    onChange={handleEmailChange} 
                    type='text' 
                    placeholder={Email} 
                    value={Email}
                />
            </div>
            <button className='button-style-submit' onClick={handleUpdate}>
                Update
            </button>
        </form>
    </Card>
  ) : (
    <Card>
        <h3>Profile Details</h3>
        <button onClick={handleEdit}  className='edit'>
            <FaEdit color='rgba(70,130,180)'/>
        </button>
        <h4>Name</h4>
        <p1>{Name}</p1>
        <h4>Email</h4>
        <p1>{Email}</p1>
    </Card>
  )
  )
}

export default ProfileDetails
