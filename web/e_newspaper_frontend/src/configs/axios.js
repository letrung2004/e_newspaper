import axios from 'axios';
import { BASE_URL } from '../utils/constants';
import { tokenStorage } from '../utils/storage';

// API instance cho các request cần authentication
export const API = axios.create({
    baseURL: BASE_URL,
});

// API instance cho auth requests
export const AUTH_REQUEST = axios.create({
    baseURL: BASE_URL,
});

// Interceptor để tự động thêm token
API.interceptors.request.use((config) => {
    const token = tokenStorage.getToken();
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

// Interceptor để handle response errors
API.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response?.status === 401) {
            tokenStorage.removeToken();
            window.location.href = '/login';
        }
        return Promise.reject(error);
    }
);