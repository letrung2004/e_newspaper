import React, { createContext, useState, useEffect, useContext } from 'react';
import cookie from "react-cookies"
import { tokenStorage } from '../utils/storage';
import { authService } from '../services/authService';

const AuthContext = createContext();

const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    const login = (userData, token) => {
        tokenStorage.saveToken(token);
        setUser(userData);
    };

    const logout = () => {
        authService.logout();
        setUser(null);
    };

    const loadUser = async () => {
        try {
            const token = tokenStorage.getToken();
            if (token) {
                const response = await authService.getUserInfo();
                setUser(response.data);
            }
        } catch (error) {
            console.error('Error loading user:', error);
            logout();
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        loadUser();
    }, []);


    return (
        <AuthContext.Provider value={{ user, setUser, loading, logout, login, loadUser }}>
            {children}
        </AuthContext.Provider>
    );
};

const useAuth = () => useContext(AuthContext);

export { AuthProvider, useAuth };