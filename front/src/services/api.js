import axios from 'axios';

const API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080/api';

const apiClient = axios.create({ baseURL: API_BASE_URL });

apiClient.interceptors.request.use((config) => {
  const token = localStorage.getItem('authToken');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Offres d'emploi
export const fetchJobOffers  = ()         => apiClient.get('/job-offers');
export const createJobOffer  = (data)     => apiClient.post('/job-offers', data);
export const getJobOfferById = (id)       => apiClient.get(`/job-offers/${id}`);
export const updateJobOffer  = (id, data) => apiClient.put(`/job-offers/${id}`, data);
export const deleteJobOffer  = (id)       => apiClient.delete(`/job-offers/${id}`);

// Candidatures
export const fetchApplications  = ()         => apiClient.get('/applications');
export const submitApplication  = (data)     => apiClient.post('/applications', data);
export const getApplicationById = (id)       => apiClient.get(`/applications/${id}`);
export const updateApplication  = (id, data) => apiClient.put(`/applications/${id}`, data);
export const deleteApplication  = (id)       => apiClient.delete(`/applications/${id}`);

// Authentification
export const login  = (data) => apiClient.post('/auth/login', data);
export const signup = (data) => apiClient.post('/auth/signup', data);
