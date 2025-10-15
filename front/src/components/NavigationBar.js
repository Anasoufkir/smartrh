import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/NavigationBar.css';const NavigationBar = () => {
  return (
    <nav>
      <Link to="/">Home</Link> 
      <Link to="/joboffers">Job Offers</Link> 
      <Link to="/submit-application">Submit Application</Link> 
      <Link to="/applications">Applications</Link> 
      <Link to="/create-joboffer">Create Job Offer</Link>
    </nav>
  );
};

export default NavigationBar;
