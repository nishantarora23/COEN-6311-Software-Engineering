import React from 'react'
import Card from '../../shared/Card'
import {FaTimes,FaEdit} from 'react-icons/fa'
import {useState} from 'react'

function Education() {
    const [Education,setEducation] = useState([
        'B.Tech CSE',
        'M.Tech CSE',
        'PhD CSE'
    ])
    const [newEducation, setNewEducation] = useState('')
    const [isEditing,setIsEditing] = useState(false)

    const handleEducationChange = (e) => {
        setNewEducation(e.target.value)
    }

    const handleEdit = (e) => {
        setIsEditing(true)
    }

    const handleUpdate = (e) => {
        if (newEducation.trim() !== '') {
            setEducation([...Education,newEducation])
            setNewEducation('')
        }
        
    }
    const handleCloseButton = (e) => {
        setIsEditing(false)
    }
    const handleSubmit = (e) => {
        e.preventDefault();
    }

    const handleDelete = (indexToDelete) => {
        const updatedEducation = Education.filter((item,index) => index !== indexToDelete)
        setEducation(updatedEducation)
    }
  return (
    isEditing ? (
    <Card>
        <h3>Edit Education Details</h3>
        <form onSubmit={handleSubmit}>
            <button className='close' onClick={handleCloseButton}>
                <FaTimes color='rgba(70,130,180)'/>
            </button> 
            <ul>
                {Education.map((text, index) => (
                    <li className='li' key={index} > 
                        <button 
                            className='button-style-profile'  
                            onClick={() => handleDelete(index)}>
                                {text}
                        </button>
                    </li>
                ))}
            </ul>
            <div>
                <input className='input-form' 
                    onChange={handleEducationChange} 
                    type='text'  
                    value={newEducation}
                />
            </div>
            <button className='button-style-submit' onClick={handleUpdate}>
                Add Education
            </button>
        </form>
    </Card>
  ) : (
    <Card>
        <h3>Education Details</h3>
        <button onClick={handleEdit}  className='edit'>
            <FaEdit color='rgba(70,130,180)'/>
        </button>
        <ul>
            {Education.map((text, index) => (
                <li  className='li' key={index} > 
                    <button className='button-style-profile'> {text} </button>
                </li>
            ))}
        </ul>
    </Card>
  )
  )
}

export default Education
