import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

// Offres d'emploi
export const fetchJobOffers = () => axios.get(`${API_BASE_URL}/job-offers`);
export const createJobOffer = (data) => axios.post(`${API_BASE_URL}/job-offers`, data);
export const getJobOfferById = (id) => axios.get(`${API_BASE_URL}/job-offers/${id}`);
export const updateJobOffer = (id, data) => axios.put(`${API_BASE_URL}/job-offers/${id}`, data);
export const deleteJobOffer = (id) => axios.delete(`${API_BASE_URL}/job-offers/${id}`);

// Candidatures
export const fetchApplications = () => axios.get(`${API_BASE_URL}/applications`);

// Modify submitApplication to accept FormData
export const submitApplication = (formData) => axios.post(`${API_BASE_URL}/applications`, formData, {
  headers: {
    'Content-Type': 'multipart/form-data' // Set content type to multipart/form-data for file upload
  }
});

export const getApplicationById = (id) => axios.get(`${API_BASE_URL}/applications/${id}`);
export const updateApplication = (id, data) => axios.put(`${API_BASE_URL}/applications/${id}`, data);
export const deleteApplication = (id) => axios.delete(`${API_BASE_URL}/applications/${id}`);

export const login = (data) => axios.post(`${API_BASE_URL}/auth/login`, data);
export const signup = (data) => axios.post(`${API_BASE_URL}/auth/signup`, data);
