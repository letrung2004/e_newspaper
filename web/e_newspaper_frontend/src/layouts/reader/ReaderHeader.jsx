import React from "react";
import { Link } from "react-router-dom";

const ReaderHeader = () => {
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
                                <Link to="/register" className="left-topbar-item">Đăng ký</Link>
                                <Link to="/login" className="left-topbar-item">Đăng nhập</Link>
                            </div>

                            <div className="right-topbar">
                                <a href="/"><span className="fab fa-facebook-f "></span></a>
                                <a href="/"><span className="fab fa-youtube "></span></a>
                            </div>
                        </div>
                    </div>

                    {/* Logo + Banner */}
                    {/* <div className="wrap-logo container">
                        <div className="logo">
                            <Link to="/">
                                <img src="/assets/images/icons/logo-01.png" alt="LOGO" />
                            </Link>
                        </div>

                        <div className="banner-header">
                            <a href="https://themewagon.com/themes/free-bootstrap-4-html5-news-website-template-magnews2/">
                                <img src="/assets/images/banner-01.jpg" alt="IMG" />
                            </a>
                        </div>
                    </div> */}


                    <div className="wrap-logo no-banner container">
                        <div className="logo">
                            <a href="/"><img src="/assets/images/icons/logo-01.png" alt="LOGO" /></a>
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
                                        <li className="main-menu-active">
                                            <Link to="/">Home</Link>
                                            <ul className="sub-menu">
                                                <li><Link to="/">Homepage v1</Link></li>
                                                <li><Link to="/">Homepage v2</Link></li>
                                                <li><Link to="/">Homepage v3</Link></li>
                                            </ul>
                                        </li>

                                        <li className="mega-menu-item"><Link to="/">News</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Entertainment</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Business</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Travel</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Life Style</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Video</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Video</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Video</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Video</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Video</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Video</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Video</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Video</Link></li>
                                        <li className="mega-menu-item"><Link to="/">Video</Link></li>

                                        <li>
                                            <Link to="/">Features</Link>
                                            <ul className="sub-menu">
                                                <li><Link to="/">Category Page v1</Link></li>
                                                <li><Link to="/">Category Page v2</Link></li>
                                                <li><Link to="/">Blog Grid Sidebar</Link></li>
                                                <li><Link to="/">Blog List Sidebar v1</Link></li>
                                                <li><Link to="/">Blog List Sidebar v2</Link></li>
                                                <li><Link to="/">Blog Detail Sidebar</Link></li>
                                                <li><Link to="/">Blog Detail No Sidebar</Link></li>
                                                <li><Link to="/">About Us</Link></li>
                                                <li><Link to="/">Contact Us</Link></li>
                                            </ul>
                                        </li>
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
