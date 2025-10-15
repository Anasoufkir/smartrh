// SignupForm.js
import React, { useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/SignupForm.css';

const SignupForm = () => {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/auth/signup', {
        username,
        email,
        password,
        role
      });
      console.log('Signup successful:', response.data);
      navigate('/login'); // Redirigez l'utilisateur après une inscription réussie
    } catch (error) {
      console.error('Signup error:', error.response.data);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="signup-form">
      <h2 className="form-header">Sign Up for Smart RH</h2>
      <div>
        <label>Username:</label>
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Email:</label>
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Password:</label>
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Role:</label>
        <select value={role} onChange={(e) => setRole(e.target.value)} required>
          <option value="">Select your role</option>
          <option value="USER">User</option>
          <option value="RH">HR</option>
        </select>
      </div>
      <button type="submit">Sign Up</button>
      <p className="signup-link">Already have an account? <Link to="/login">Login</Link></p>
    </form>
  );
};

export default SignupForm;
