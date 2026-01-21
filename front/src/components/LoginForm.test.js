import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import LoginForm from './LoginForm';
import * as api from '../services/api';

jest.mock('../services/api');

describe('LoginForm', () => {
  beforeEach(() => {
    localStorage.clear();
  });

  test('renders form fields and submit button', () => {
    render(<MemoryRouter><LoginForm /></MemoryRouter>);
    expect(screen.getByLabelText(/nom d'utilisateur/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/mot de passe/i)).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /se connecter/i })).toBeInTheDocument();
  });

  test('stores token in localStorage on success', async () => {
    api.login.mockResolvedValue({ data: { token: 'fake-jwt' } });
    render(<MemoryRouter><LoginForm /></MemoryRouter>);

    fireEvent.change(screen.getByLabelText(/nom d'utilisateur/i), {
      target: { value: 'alice' },
    });
    fireEvent.change(screen.getByLabelText(/mot de passe/i), {
      target: { value: 'secret' },
    });
    fireEvent.click(screen.getByRole('button', { name: /se connecter/i }));

    await waitFor(() => {
      expect(localStorage.getItem('authToken')).toBe('fake-jwt');
    });
  });

  test('shows error message on failed login', async () => {
    api.login.mockRejectedValue({
      response: { data: { error: 'Identifiants invalides' } },
    });
    render(<MemoryRouter><LoginForm /></MemoryRouter>);

    fireEvent.change(screen.getByLabelText(/nom d'utilisateur/i), {
      target: { value: 'wrong' },
    });
    fireEvent.change(screen.getByLabelText(/mot de passe/i), {
      target: { value: 'wrong' },
    });
    fireEvent.click(screen.getByRole('button', { name: /se connecter/i }));

    await waitFor(() => {
      expect(screen.getByText(/Identifiants invalides/i)).toBeInTheDocument();
    });
  });
});
