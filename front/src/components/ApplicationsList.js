import React, { useEffect, useState } from 'react';
import { fetchApplications } from '../services/api';
import '../styles/ApplicationsList.css'; // Make sure to create this CSS file

const ApplicationsList = () => {
    const [applications, setApplications] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const loadApplications = async () => {
            try {
                const response = await fetchApplications();
                setApplications(response.data);
                setLoading(false);
            } catch (error) {
                setError(error.message);
                setLoading(false);
            }
        };
        loadApplications();
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div className="applications-container">
            <h1>Candidatures</h1>
            <div className="applications-list">
                {applications.map(application => (
                    <div key={application.id} className="application-item">
                        <p><strong>Nom:</strong> {application.applicantName}</p>
                        <p><strong>Email:</strong> {application.applicantEmail}</p>
                        <p><strong>CV:</strong> <a href={application.cvLink}>Lien</a></p>
                        <p><strong>ID Offre:</strong> {application.jobId}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ApplicationsList;
