// CreateJobOfferForm.js
import React, { useState } from 'react';
import { createJobOffer } from '../services/api'; // Assurez-vous que le chemin d'importation est correct

const CreateJobOfferForm = () => {
    const [offer, setOffer] = useState({
        title: '',
        company: '',
        location: '',
        description: '',
    });

    const handleChange = (e) => {
        setOffer({ ...offer, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        await createJobOffer(offer);
        alert('Offre d\'emploi créée avec succès!');
        // Optionnel: Réinitialiser le formulaire ou rediriger l'utilisateur
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" name="title" value={offer.title} onChange={handleChange} placeholder="Titre" required />
            <input type="text" name="company" value={offer.company} onChange={handleChange} placeholder="Entreprise" required />
            <input type="text" name="location" value={offer.location} onChange={handleChange} placeholder="Lieu" required />
            <textarea name="description" value={offer.description} onChange={handleChange} placeholder="Description" required />
            <button type="submit">Créer Offre</button>
        </form>
    );
};

export default CreateJobOfferForm;
