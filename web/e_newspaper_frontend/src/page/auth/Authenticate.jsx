import { useNavigate } from "react-router-dom";
import { useState, useEffect, useRef } from "react";
import { Box, CircularProgress, Typography } from "@mui/material";
import cookie from "react-cookies"
import { BASE_URL, endpoints } from "../../configs/APIs"; // Thêm import endpoints
import { useAuth } from "../../contexts/AuthProvider";

export default function Authenticate() {
    const hasProcessed = useRef(false);
    const navigate = useNavigate();
    const [isLogin, setIsLogin] = useState(false);
    const { login } = useAuth();

    useEffect(() => {
        if (hasProcessed.current) return;
        const authCodeRegex = /code=([^&]+)/;
        const isMatch = window.location.href.match(authCodeRegex);

        if (isMatch) {
            hasProcessed.current = true;
            const authCode = isMatch[1];
            console.log("Auth code received:", authCode);

            // Sử dụng BASE_URL thay vì hardcode localhost
            fetch(`${BASE_URL}/identity/auth/outbound/authentication?code=${authCode}`, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => {
                    console.log("Response status:", response.status);
                    if (!response.ok) {
                        throw new Error(`HTTP error! status: ${response.status}`);
                    }
                    return response.json();
                })
                .then(data => {
                    console.log("OAuth response:", data);

                    if (data.result?.token) {
                        cookie.save("jwtToken", data.result.token, { path: '/' });
                        fetchUserInfo(data.result.token);
                    } else {
                        console.error("No token received from server");
                        navigate("/login?error=oauth_failed");
                    }
                })
                .catch(error => {
                    console.error('OAuth authentication error:', error);
                    navigate("/login?error=oauth_failed");
                });
        } else {
            console.log("No auth code found in URL");
            navigate("/login?error=no_code");
        }
    }, [navigate]);

    const fetchUserInfo = async (token) => {
        try {

            const userResponse = await fetch(`${BASE_URL}${endpoints['my-info']}`, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });

            console.log("User info response status:", userResponse.status);

            if (userResponse.ok) {
                const userData = await userResponse.json();
                console.log("User data:", userData);

                // Gọi login từ AuthProvider
                login(userData, token);
                setIsLogin(true);
            } else {
                console.error("Failed to fetch user info");
                navigate("/login?error=fetch_user_failed");
            }
        } catch (error) {
            console.error('Error fetching user info:', error);
            navigate("/login?error=fetch_user_failed");
        }
    };

    useEffect(() => {
        if (isLogin) {
            navigate("/");
        }
    }, [isLogin, navigate]);

    return (
        <Box
            sx={{
                display: "flex",
                flexDirection: "column",
                gap: "30px",
                justifyContent: "center",
                alignItems: "center",
                height: "100vh",
            }}
        >
            <CircularProgress />
            <Typography>Đang xác thực...</Typography>
        </Box>
    );
}