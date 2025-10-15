import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { fetchJobOffers } from '../services/api'; // Assurez-vous que le chemin d'importation est correct
import '../styles/JobOffersList.css';

const JobOffersList = () => {
    const [offers, setOffers] = useState([]);
    const [error, setError] = useState(null); // Add state for error handling

    useEffect(() => {
        const loadOffers = async () => {
            try {
                const response = await fetchJobOffers();
                setOffers(response.data);
            } catch (error) {
                setError(error); // Set error state if request fails
            }
        };
        loadOffers();
    }, []);

    if (error) {
        return <div>Error: {error.message}</div>; // Render error message if request fails
    }

    return (
        <div>
            <h1 className="job-offers-heading">Offres d'Emploi</h1>
            <div className="job-offers-container">
                {offers.map((offer) => (
                    <div key={offer.id} className="job-offer">
                        <h2 className="job-offer-title">{offer.title}</h2>
                        <p className="job-offer-details"><h3>Entreprise:</h3>{offer.company}</p>
                        <p className="job-offer-details"><h3>Lieu:</h3>{offer.location}</p>
                        <p className="job-offer-details"><h3>Description:</h3>{offer.description}</p>
                        <Link to={`/submit-application/${offer.id}`} className="apply-button">Postuler</Link>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default JobOffersList;
