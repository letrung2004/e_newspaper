import React, { useState, useEffect, useRef } from "react";
import { Link } from "react-router-dom";
import { useAuth } from "../../contexts/AuthProvider";

const ReaderHeader = () => {
    const { user, logout } = useAuth();
    const [isDropdownOpen, setIsDropdownOpen] = useState(false);
    const dropdownRef = useRef(null);

    // Debug: kiểm tra giá trị user
    console.log("User trong ReaderHeader:", user);

    const handleLogout = () => {
        logout();
        setIsDropdownOpen(false);
    };

    const toggleDropdown = () => {
        setIsDropdownOpen(!isDropdownOpen);
    };

    // Đóng dropdown khi click ra ngoài
    useEffect(() => {
        const handleClickOutside = (event) => {
            if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
                setIsDropdownOpen(false);
            }
        };

        document.addEventListener('mousedown', handleClickOutside);
        return () => {
            document.removeEventListener('mousedown', handleClickOutside);
        };
    }, []);

    return (
        <div>
            {/* Header */}
            <header>
                {/* Header desktop */}
                <div className="container-menu-desktop">
                    <div className="topbar">
                        <div className="content-topbar container h-100">
                            <div className="left-topbar">
                                <span className="left-topbar-item flex-wr-s-c">
                                    <span>Việt Nam</span>
                                    <span className="left-topbar-item">{new Date().toLocaleDateString("vi-VN", { weekday: 'long', year: 'numeric', month: '2-digit', day: '2-digit' })}</span>
                                </span>

                                <Link to="/" className="left-topbar-item">Về chúng tôi</Link>
                                <Link to="/" className="left-topbar-item">Liên Hệ</Link>

                            </div>

                            <div className="right-topbar" style={{ display: 'flex', alignItems: 'center', gap: '16px' }}>
                                {/* Điều kiện hiển thị dựa vào trạng thái đăng nhập */}
                                {user ? (
                                    <div className="left-topbar-item" style={{ position: 'relative' }} ref={dropdownRef}>
                                        <button
                                            onClick={toggleDropdown}
                                            style={{
                                                background: 'none',
                                                border: 'none',
                                                color: 'inherit',
                                                cursor: 'pointer',
                                                padding: 0,
                                                font: 'inherit',
                                                display: 'flex',
                                                alignItems: 'center',
                                                gap: '8px'
                                            }}
                                        >
                                            <div style={{
                                                width: '30px',
                                                height: '30px',
                                                backgroundColor: '#007bff',
                                                borderRadius: '50%',
                                                display: 'flex',
                                                alignItems: 'center',
                                                justifyContent: 'center',
                                                color: 'white',
                                                fontSize: '14px',
                                                fontWeight: 'bold'
                                            }}>
                                                {(user.username || 'U').charAt(0).toUpperCase()}
                                            </div>
                                            <span>{user.username || 'User'}</span>
                                            <i className="fas fa-chevron-down" style={{ fontSize: '12px' }}></i>
                                        </button>

                                        {isDropdownOpen && (
                                            <div style={{
                                                position: 'absolute',
                                                top: '100%',
                                                right: 0,
                                                backgroundColor: 'white',
                                                border: '1px solid #ddd',
                                                borderRadius: '6px',
                                                boxShadow: '0 2px 8px rgba(0,0,0,0.1)',
                                                minWidth: '160px',
                                                zIndex: 1000,
                                                marginTop: '4px',
                                                fontSize: '13px'
                                            }}>
                                                <Link
                                                    to="/profile"
                                                    style={{
                                                        display: 'flex',
                                                        alignItems: 'center',
                                                        padding: '12px 16px',
                                                        textDecoration: 'none',
                                                        color: '#333',
                                                        borderBottom: '1px solid #f0f0f0',
                                                        gap: '12px'
                                                    }}
                                                    onMouseEnter={(e) => e.target.style.backgroundColor = '#f8f9fa'}
                                                    onMouseLeave={(e) => e.target.style.backgroundColor = 'transparent'}
                                                >
                                                    <i className="fas fa-user" style={{ color: '#666', width: '16px' }}></i>
                                                    <span style={{ fontSize: '13px' }}>Hồ sơ của bạn</span>
                                                </Link>


                                                <button
                                                    onClick={handleLogout}
                                                    style={{
                                                        display: 'flex',
                                                        alignItems: 'center',
                                                        padding: '12px 16px',
                                                        background: 'none',
                                                        border: 'none',
                                                        color: '#333',
                                                        cursor: 'pointer',
                                                        width: '100%',
                                                        textAlign: 'left',
                                                        gap: '12px'
                                                    }}
                                                    onMouseEnter={(e) => e.target.style.backgroundColor = '#f8f9fa'}
                                                    onMouseLeave={(e) => e.target.style.backgroundColor = 'transparent'}
                                                >
                                                    <i className="fas fa-sign-out-alt" style={{ color: '#666', width: '16px' }}></i>
                                                    <span>Đăng xuất</span>
                                                </button>
                                            </div>
                                        )}
                                    </div>
                                ) : (
                                    <>
                                        <Link to="/register" className="left-topbar-item" style={{ fontSize: '13px' }}>Đăng ký</Link>
                                        <Link to="/login" className="left-topbar-item" style={{ fontSize: '13px' }}>Đăng nhập</Link>
                                    </>
                                )}
                            </div>

                        </div>
                    </div>

                    <div className="wrap-logo no-banner container">
                        <div className="logo">
                            <Link to="/" ><img src="/assets/images/icons/logo-01.png" alt="LOGO" /></Link>

                        </div>
                    </div>

                    {/* Navigation */}
                    <div className="wrap-main-nav">
                        <div className="main-nav">
                            <nav className="menu-desktop">
                                <Link to="/" className="logo-stick">
                                    <img src="/assets/images/icons/logo-01.png" alt="LOGO" />
                                </Link>

                                <div className="scrollable-menu">
                                    <ul className="main-menu">
                                        <li className="main-menu-active"><Link to="/">Trang chủ</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Tin mới</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Giải trí</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Kinh doanh</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Du lịch</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Đời sống</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Pháp luật</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Kinh tế</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Gia đình</Link></li>
                                    </ul>
                                </div>

                            </nav>
                        </div>
                    </div>
                </div>
            </header>
        </div>
    );
};

export default ReaderHeader;