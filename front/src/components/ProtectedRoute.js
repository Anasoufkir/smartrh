// components/ProtectedRoute.js
import React from 'react';
import { Navigate } from 'react-router-dom';

const ProtectedRoute = ({ children }) => {
  const isAuthenticated = localStorage.getItem('authToken'); // Vérifiez si l'utilisateur est authentifié

  if (!isAuthenticated) {
    // Rediriger vers la page de connexion si non authentifié
    return <Navigate to="/login" />;
  }

  return children; // Afficher le composant enfant si authentifié
};

export default ProtectedRoute;
