import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import { submitApplication } from '../services/api';
import '../styles/SubmitApplicationForm.css';

const SubmitApplicationForm = () => {
    const { offerId } = useParams(); // Extract the offer ID from the URL
    const [application, setApplication] = useState({
        applicantName: '',
        applicantEmail: '',
        cvFile: null, // Add a new state for the CV file
        jobId: offerId, // Use the extracted offer ID for the application
    });

    const handleChange = (e) => {
        if (e.target.name === 'cvFile') {
            // Update the CV file state
            setApplication({ ...application, [e.target.name]: e.target.files[0] });
        } else {
            // Update other application details
            setApplication({ ...application, [e.target.name]: e.target.value });
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        
        // Create a FormData object to store form data including the CV file
        const formData = new FormData();
        formData.append('applicantName', application.applicantName);
        formData.append('applicantEmail', application.applicantEmail);
        formData.append('cvFile', application.cvFile);
        formData.append('jobId', application.jobId);

        // Submit the application with FormData containing the CV file
        await submitApplication(formData);
        alert('Candidature soumise avec succès!');
        // Optional: Reset the form or redirect the user
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" name="applicantName" value={application.applicantName} onChange={handleChange} placeholder="Nom du candidat" required />
            <input type="email" name="applicantEmail" value={application.applicantEmail} onChange={handleChange} placeholder="Email du candidat" required />
            <input type="file" name="cvFile" onChange={handleChange} accept=".pdf,.doc,.docx" required /> {/* File input for CV */}
            <button type="submit">Soumettre Candidature</button>
        </form>
    );
};

export default SubmitApplicationForm;
