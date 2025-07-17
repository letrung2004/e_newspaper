import axios from "axios"
import cookie from "react-cookies"

export const BASE_URL = 'http://localhost:8080'

export const endpoints = {
    // APIs for auth
    'login': '/identity/auth/token',



    // APIs for reader/guess




    // APIs for admin/editor


}


export const authAPIs = () => {
    const token = cookie.load("jwtToken");
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
}



export default axios.create({
    baseURL: BASE_URL
});