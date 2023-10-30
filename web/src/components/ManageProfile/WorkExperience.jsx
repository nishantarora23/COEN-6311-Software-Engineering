import React from 'react'
import Card from '../../shared/Card'
import {FaTimes,FaEdit} from 'react-icons/fa'
import {useState} from 'react'

function WorkExperience() {
    const [WorkEX,setWorkEX] = useState([
        'Python Developer',
        'SoftWare Developer',
    ])
    const [isEditing,setIsEditing] = useState(false)
    
    const [newWorkEX,setNewWorkEX] = useState('')

    const handleWorkEXChange = (e) => {
        setNewWorkEX(e.target.value)
    }

    const handleEdit = (e) => {
        setIsEditing(true)
    }

    const handleUpdate = (e) => {
        if (newWorkEX.trim() !== '') {
            setWorkEX([...WorkEX,newWorkEX])
            setNewWorkEX('')
        }
    }
    const handleCloseButton = (e) => {
        setIsEditing(false)
    }
    const handleSubmit = (e) => {
        e.preventDefault();
    }

    const handleDelete = (indexToDelete) => {
        const updatedWorkEX = WorkEX.filter((item,index) => index !== indexToDelete)
        setWorkEX(updatedWorkEX)
    }
  return (
    isEditing ? (
    <Card>
        <h3>Edit Work Experiences</h3>
        <form onSubmit={handleSubmit}>
            <button className='close' onClick={handleCloseButton}>
                <FaTimes color='rgba(70,130,180)'/>
            </button> 
            <ul>
                {WorkEX.map((text, index) => (
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
                    onChange={handleWorkEXChange} 
                    type='text'  
                    value={newWorkEX}
                />
            </div>
            <button className='button-style-submit' onClick={handleUpdate}>
                Add Work Experience
            </button>
        </form>
    </Card>
  ) : (
    <Card>
        <h3>Work Experiences</h3>
        <button onClick={handleEdit}  className='edit'>
            <FaEdit color='rgba(70,130,180)'/>
        </button>
        <ul>
            {WorkEX.map((text, index) => (
                <li className='li' key={index} > 
                    <button className='button-style-profile'>{text}</button> 
                </li>
            ))}
        </ul>
    </Card>
  )
  )
}

export default WorkExperience
