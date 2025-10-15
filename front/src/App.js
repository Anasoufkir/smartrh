// App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import NavigationBar from './components/NavigationBar';
import HomePage from './components/HomePage';
import JobOffersList from './components/JobOffersList';
import SubmitApplicationForm from './components/SubmitApplicationForm';
import ApplicationsList from './components/ApplicationsList';
import CreateJobOfferForm from './components/CreateJobOfferForm';
import Footer from './components/Footer';
import LoginForm from './components/LoginForm';
import SignupForm from './components/SignupForm';
import ProtectedRoute from './components/ProtectedRoute'; // Assurez-vous d'importer ProtectedRoute

function App() {
  return (
    <Router>
      <div>
        <NavigationBar />
        <Routes>
          <Route path="/" element={<Navigate replace to="/login" />} />
          <Route path="/login" element={<LoginForm />} />
          <Route path="/signup" element={<SignupForm />} />
          <Route path="/home" element={<ProtectedRoute><HomePage /></ProtectedRoute>} />
          <Route path="/joboffers" element={<ProtectedRoute><JobOffersList /></ProtectedRoute>} />
          <Route path="/submit-application" element={<ProtectedRoute><SubmitApplicationForm /></ProtectedRoute>} />
          <Route path="/applications" element={<ProtectedRoute><ApplicationsList /></ProtectedRoute>} />
          <Route path="/create-joboffer" element={<ProtectedRoute><CreateJobOfferForm /></ProtectedRoute>} />
          <Route path="/submit-application/:offerId" element={<ProtectedRoute><SubmitApplicationForm /></ProtectedRoute>} />
        </Routes>
        <Footer />
      </div>
    </Router>
  );
}

export default App;
