import React, { useState } from 'react';
import axios from 'axios';
import './classes.css';
import { useNavigate } from 'react-router-dom';


const UserForm = () => {
    const navigate = useNavigate();
    const [user, setUser] = useState({
        email: '',
        password: '',
        name: '',
        dateOfBirth: '',
        phoneNumber: '',
    });

    const handleChange = (event) => {
        setUser({ ...user, [event.target.name]: event.target.value });
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        axios.post('http://localhost:8080/users', user)
            .then(response => {
                console.log(response.data);
                // Check if the account was successfully created
                if (response.status === 200) {
                    navigate('/login');
                }
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
    }

    const loginButtonStyle = {
        color: 'black'
    };
    
    const createUserStyle = {
        color: 'black'
    };

    const handleLogin = () => {
        navigate('/Login'); 
    }


    return (
        <div className="form-container">
            <form onSubmit={handleSubmit}>
                <div className="form-field">
                    <label>Email:</label>
                    <input type="email" name="email" value={user.email} onChange={handleChange} required />
                </div>
                <div className="form-field">
                    <label>Password:</label>
                    <input type="password" name="password" value={user.password} onChange={handleChange} required />
                </div>
                <div className="form-field">
                    <label>Name:</label>
                    <input type="text" name="name" value={user.name} onChange={handleChange} required />
                </div>
                <div className="form-field">
                    <label>Date of Birth:</label>
                    <input type="date" name="dateOfBirth" value={user.dateOfBirth} onChange={handleChange} required />
                </div>
                <div className="form-field">
                    <label>Phone Number:</label>
                    <input type="text" name="phoneNumber" value={user.phoneNumber} onChange={handleChange} required />
                </div>
                <div className='buttons'>
                    <button type="submit" className="button" style={createUserStyle}>Create User</button>
                    <button type="button" className='button' style={loginButtonStyle} onClick={handleLogin}>Log in</button>
                </div>
            </form>
        </div>
    );
}

export default UserForm;
