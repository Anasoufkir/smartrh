import React, { useState } from 'react';
import axios from 'axios';
import { Link,useNavigate } from 'react-router-dom'; // Si vous souhaitez rediriger après la connexion
import '../styles/LoginForm.css';

const LoginForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate(); // Pour la redirection après la connexion

  const handleSubmit = async (e) => {
    e.preventDefault(); // Pour empêcher le rechargement de la page
    try {
      const response = await axios.post('http://localhost:8080/api/auth/login', {
        username,
        password
      });
      console.log('Login successful:', response.data);
      // Stocker le token ici si nécessaire, par exemple dans localStorage
      localStorage.setItem('authToken', response.data.token); // Adaptez selon la structure de votre réponse
      // Rediriger l'utilisateur
      navigate('/joboffers');
    } catch (error) {
      console.error('Login error:', error.response.data);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
            <h2 className="login-header">Sign-up Smart RH</h2>

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
        <label>Password:</label>
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
      </div>
      <button type="submit">Login</button>
      <p className="signup-link">Don't have an account? <Link to="/signup">Sign up</Link></p>

    </form>
  );
};

export default LoginForm;
