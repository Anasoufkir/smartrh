import { render, screen, waitFor } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import JobOffersList from './JobOffersList';
import * as api from '../services/api';

jest.mock('../services/api');

describe('JobOffersList', () => {
  test('renders job offers fetched from API', async () => {
    api.fetchJobOffers.mockResolvedValue({
      data: [
        { id: '1', title: 'Developpeur Java', company: 'Acme', location: 'Paris', description: 'Backend Java 17' },
        { id: '2', title: 'Developpeur React', company: 'Beta', location: 'Lyon', description: 'Frontend React 18' },
      ],
    });

    render(<MemoryRouter><JobOffersList /></MemoryRouter>);

    await waitFor(() => {
      expect(screen.getByText('Developpeur Java')).toBeInTheDocument();
      expect(screen.getByText('Developpeur React')).toBeInTheDocument();
    });
  });

  test('shows error message on fetch failure', async () => {
    api.fetchJobOffers.mockRejectedValue(new Error('Network error'));

    render(<MemoryRouter><JobOffersList /></MemoryRouter>);

    await waitFor(() => {
      expect(screen.getByText(/Error/i)).toBeInTheDocument();
    });
  });

  test('renders empty list when no offers returned', async () => {
    api.fetchJobOffers.mockResolvedValue({ data: [] });

    render(<MemoryRouter><JobOffersList /></MemoryRouter>);

    await waitFor(() => {
      expect(screen.queryAllByRole('heading', { level: 2 })).toHaveLength(0);
    });
  });
});
