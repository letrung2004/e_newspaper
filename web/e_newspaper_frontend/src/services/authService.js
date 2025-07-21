import { AUTH_REQUEST, API } from '../configs/axios';
import { ENDPOINTS } from '../configs/api';
import { tokenStorage } from '../utils/storage';

export const authService = {

    // Đăng nhập bằng username/password
    login: async (credentials) => {
        const response = await AUTH_REQUEST.post(ENDPOINTS.AUTH.LOGIN, credentials);
        return response.data;
    },

    // Đăng nhập OAuth
    loginWithOAuth: async (authCode) => {
        const response = await AUTH_REQUEST.post(
            `${ENDPOINTS.AUTH.OAUTH}?code=${authCode}`
        );
        return response.data;
    },

    // Lấy thông tin user
    getUserInfo: async () => {
        const response = await API.get(ENDPOINTS.AUTH.ME);
        return response.data;
    },

    // Đăng xuất
    logout: () => {
        tokenStorage.removeToken();
    }
};