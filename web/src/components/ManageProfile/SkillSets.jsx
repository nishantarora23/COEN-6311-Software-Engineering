import React from 'react'
import Card from '../../shared/Card'
import {FaTimes,FaEdit} from 'react-icons/fa'
import {useState} from 'react'

function SkillSets() {
    const [SkillSets,setSkillSet] = useState([
        'Python',
        'Java',
        'ReactJS',
    ])
    const [newSkillSet,setNewSkillSet] = useState('')
    const [isEditing,setIsEditing] = useState(false)

    const handleSkillSetsChange = (e) => {
        setNewSkillSet(e.target.value)
    }

    const handleEdit = (e) => {
        setIsEditing(true)
    }

    const handleUpdate = (e) => {
        if (newSkillSet.trim() !== '') {
            setSkillSet([...SkillSets,newSkillSet])
            setNewSkillSet('')
        }
    }
    const handleCloseButton = (e) => {
        setIsEditing(false)
    }
    const handleSubmit = (e) => {
        e.preventDefault();
    }
    const handleDelete = (indexToDelete) => {
        const updatedSkillSet = SkillSets.filter((item,index) => index !== indexToDelete)
        setSkillSet(updatedSkillSet)
    }

  return (
    isEditing ? (
    <Card>
        <h3>Edit SkillSets</h3>
        <form onSubmit={handleSubmit}>
            <button className='close' onClick={handleCloseButton}>
                <FaTimes color='rgba(70,130,180)'/>
            </button> 
            <ul>
                {SkillSets.map((text, index) => (
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
                    onChange={handleSkillSetsChange} 
                    type='text'  
                    value={newSkillSet}
                />
            </div>
            <button className='button-style-submit' onClick={handleUpdate}>
                Add SkillSet
            </button>
        </form>
    </Card>
  ) : (
    <Card>
        <h3>SkillSets</h3>
        <button onClick={handleEdit}  className='edit'>
            <FaEdit color='rgba(70,130,180)'/>
        </button>
        <ul>
            {SkillSets.map((text, index) => (
                <li className='li' key={index} > 
                    <button className='button-style-profile'>{text}</button> 
                </li>
            ))}
        </ul>
    </Card>
  )
  )
}

export default SkillSets
