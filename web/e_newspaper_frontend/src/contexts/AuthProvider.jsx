import React, { createContext, useState, useEffect, useContext } from 'react';
import axios from 'axios';
import { authAPIs, endpoints } from '../configs/APIs';
import cookie from "react-cookies"

const AuthContext = createContext();

const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    const login = (userData, token) => {
        cookie.save('jwtToken', token, { path: '/' });
        console.log("Token:", token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
        setUser(userData);
    };

    const logout = () => {

    };

    const loadUser = async () => {

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