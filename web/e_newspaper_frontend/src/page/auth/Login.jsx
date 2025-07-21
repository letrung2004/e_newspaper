import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from '../../contexts/AuthProvider';
import { oauth } from '../../configs/oauth';
import { authService } from '../../services/authService';
import { tokenStorage } from '../../utils/storage';
import { buildOAuthUrl } from '../../utils/helpers';


const Login = () => {
    const [user, setUser] = useState({
        username: "",
        password: ""
    });
    const [errors, setErrors] = useState({});
    const [loading, setLoading] = useState(false);
    const nav = useNavigate();
    const { login } = useAuth();

    const info = [
        {
            label: "Tên đăng nhập",
            type: "text",
            field: "username"
        }, {
            label: "Mật khẩu",
            type: "password",
            field: "password"
        }

    ];

    const setState = (value, field) => {
        setUser({ ...user, [field]: value });
    }



    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        setErrors({});

        try {
            const loginResponse = await authService.login(user);

            tokenStorage.saveToken(loginResponse.result.token);

            const userResponse = await authService.getUserInfo();

            console.log(loginResponse);
            console.log(userResponse);

            login(userResponse, loginResponse.result.token);
            nav("/");

        } catch (error) {
            console.error('Login error:', error);
            setErrors({ general: 'Đăng nhập thất bại. Vui lòng thử lại.' });
        } finally {
            setLoading(false);
        }
    };

    const handleOutBoundLogin = () => {
        const targetUrl = buildOAuthUrl(oauth);
        window.location.href = targetUrl;
    }


    useEffect(() => {
        const urlParams = new URLSearchParams(window.location.search);
        const error = urlParams.get('error');

        if (error) {
            let errorMessage = '';
            switch (error) {
                case 'oauth_failed':
                    errorMessage = 'Đăng nhập Google thất bại. Vui lòng thử lại.';
                    break;
                case 'no_code':
                    errorMessage = 'Không nhận được mã xác thực từ Google.';
                    break;
                case 'fetch_user_failed':
                    errorMessage = 'Không thể lấy thông tin người dùng.';
                    break;
                default:
                    errorMessage = 'Có lỗi xảy ra trong quá trình đăng nhập.';
            }
            setErrors({ general: errorMessage });
        }
    }, []);

    return (
        <div className="magnews-auth-page">

            {/* Main Content */}
            <div className="magnews-auth-container">
                <div className="auth-brand-section">
                    <h1 className="magnews-logo">
                        <span className="mag-text">MAG</span>
                        <span className="news-text">NEWS</span>
                    </h1>
                    <p className="auth-welcome">Chào mừng bạn trở lại với MAGNEWS</p>
                </div>

                <div className="auth-form-container">
                    <div className="auth-form-card">
                        <h2>Đăng nhập</h2>
                        <p className="auth-subtitle">Nhập thông tin để tiếp tục</p>

                        {errors.general && (
                            <div className="error-alert">
                                <i className="error-icon">⚠</i>
                                {errors.general}
                            </div>
                        )}

                        <form onSubmit={handleSubmit} className="magnews-auth-form">
                            {info.map(f =>
                                <div className="input-group" key={f.field}>
                                    <label >{f.label}</label>
                                    <input
                                        type={f.type}
                                        name={f.label}
                                        autoComplete={f.field === 'password' ? 'current-password' : 'username'}
                                        value={user[f.field]}
                                        onChange={e => setState(e.target.value, f.field)}
                                        placeholder={f.label}
                                    />
                                    {errors[f.field] && (
                                        <span className="error-text">{errors[f.field]}</span>
                                    )}
                                </div>
                            )}


                            <button
                                type="submit"
                                className={`magnews-btn-primary ${loading ? 'loading' : ''}`}
                                disabled={loading}
                            >
                                {loading ? (
                                    <span>
                                        <i className="loading-spinner"></i>
                                        Đang đăng nhập...
                                    </span>
                                ) : (
                                    'Đăng nhập'
                                )}
                            </button>
                        </form>

                        <div className="auth-divider">
                            <span>Hoặc đăng nhập với</span>
                        </div>

                        <div className="social-login">
                            <button onClick={handleOutBoundLogin} className="social-button google">
                                <svg width="20" height="20" viewBox="0 0 24 24">
                                    <path fill="#4285f4" d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z" />
                                    <path fill="#34a853" d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z" />
                                    <path fill="#fbbc05" d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z" />
                                    <path fill="#ea4335" d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z" />
                                </svg>
                                Google
                            </button>
                            <button className="social-button facebook">
                                <svg width="20" height="20" viewBox="0 0 24 24">
                                    <path fill="#1877f2" d="M24 12.073c0-6.627-5.373-12-12-12s-12 5.373-12 12c0 5.99 4.388 10.954 10.125 11.854v-8.385H7.078v-3.47h3.047V9.43c0-3.007 1.792-4.669 4.533-4.669 1.312 0 2.686.235 2.686.235v2.953H15.83c-1.491 0-1.956.925-1.956 1.874v2.25h3.328l-.532 3.47h-2.796v8.385C19.612 23.027 24 18.062 24 12.073z" />
                                </svg>
                                Facebook
                            </button>
                        </div>

                        <div className="auth-footer">
                            <p>Chưa có tài khoản? <Link to="/register">Đăng ký ngay</Link></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Login;