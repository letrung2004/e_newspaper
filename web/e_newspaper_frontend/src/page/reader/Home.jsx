import React from "react";
import { Link } from "react-router-dom";

const Home = () => {
    const preventDefault = (e) => e.preventDefault();
    return (
        <div>

            <div className="container">
                <div className="bg0 flex-sb-c p-rl-20 p-tb-8">
                    {/* Trending Headline */}
                    <div className="f2-s-1 p-r-30 flex-wr-s-c" style={{ minWidth: '0', flex: '1' }}>
                        <span className="cl2 p-r-8">Tin mới:  Bộ Nội vụ đề xuất tăng lương tối thiểu vùng 7,2% từ 1/1/2026</span>
                    </div>

                    {/* Search Box */}
                    <div className="pos-relative size-a-2 bo-1-rad-22 of-hidden bocl11" style={{ flexShrink: '0' }}>
                        <input
                            className="f1-s-1 cl6 plh9 s-full p-l-25 p-r-45"
                            type="text"
                            name="search"
                            placeholder="Tìm kiếm"
                        />
                        <button className="flex-c-c size-a-1 ab-t-r fs-20 cl2 hov-cl10 trans-03">
                            <i className="zmdi zmdi-search"></i>
                        </button>
                    </div>
                </div>
            </div>

            <section className="bg0">

                <div className="container">
                    <div className="row m-rl--1">
                        {/* Bài nổi bật bên trái */}
                        <div className="col-md-6 p-rl-1 p-b-2">
                            <div
                                className="bg-img1 size-a-3 how1 pos-relative"
                                style={{
                                    backgroundImage: `url('/assets/images/post-01.jpg')`,
                                }}
                            >
                                <Link to="/blog-detail-01" className="dis-block how1-child1 trans-03"></Link>

                                <div className="flex-col-e-s s-full p-rl-25 p-tb-20">
                                    <Link
                                        to="/"
                                        className="dis-block how1-child2 f1-s-2 cl0 bo-all-1 bocl0 hov-btn1 trans-03 p-rl-5 p-t-2"
                                    >
                                        Business
                                    </Link>

                                    <h3 className="how1-child2 m-t-14 m-b-10">
                                        <Link
                                            to="/blog-detail-01"
                                            className="how-txt1 size-a-6 f1-l-1 cl0 hov-cl10 trans-03"
                                        >
                                            Microsoft quisque at ipsum vel orci eleifend ultrices
                                        </Link>
                                    </h3>

                                    <span className="how1-child2">
                                        <span className="f1-s-4 cl11">Jack Sims</span>
                                        <span className="f1-s-3 cl11 m-rl-3">-</span>
                                        <span className="f1-s-3 cl11">Feb 16</span>
                                    </span>
                                </div>
                            </div>
                        </div>

                        {/* Bên phải chia 3 post nhỏ */}
                        <div className="col-md-6 p-rl-1">
                            <div className="row m-rl--1">
                                <div className="col-12 p-rl-1 p-b-2">
                                    <div
                                        className="bg-img1 size-a-4 how1 pos-relative"
                                        style={{
                                            backgroundImage: `url('/assets/images/post-02.jpg')`,
                                        }}
                                    >
                                        <Link to="/blog-detail-01" className="dis-block how1-child1 trans-03"></Link>

                                        <div className="flex-col-e-s s-full p-rl-25 p-tb-24">
                                            <Link
                                                to="/"
                                                className="dis-block how1-child2 f1-s-2 cl0 bo-all-1 bocl0 hov-btn1 trans-03 p-rl-5 p-t-2"
                                            >
                                                Culture
                                            </Link>

                                            <h3 className="how1-child2 m-t-14">
                                                <Link
                                                    to="/blog-detail-01"
                                                    className="how-txt1 size-a-7 f1-l-2 cl0 hov-cl10 trans-03"
                                                >
                                                    London ipsum dolor sit amet, consectetur adipiscing elit.
                                                </Link>
                                            </h3>
                                        </div>
                                    </div>
                                </div>

                                <div className="col-sm-6 p-rl-1 p-b-2">
                                    <div
                                        className="bg-img1 size-a-5 how1 pos-relative"
                                        style={{
                                            backgroundImage: `url('/assets/images/post-03.jpg')`,
                                        }}
                                    >
                                        <Link to="/blog-detail-01" className="dis-block how1-child1 trans-03"></Link>

                                        <div className="flex-col-e-s s-full p-rl-25 p-tb-20">
                                            <Link
                                                to="/"
                                                className="dis-block how1-child2 f1-s-2 cl0 bo-all-1 bocl0 hov-btn1 trans-03 p-rl-5 p-t-2"
                                            >
                                                Life Style
                                            </Link>

                                            <h3 className="how1-child2 m-t-14">
                                                <Link
                                                    to="/blog-detail-01"
                                                    className="how-txt1 size-h-1 f1-m-1 cl0 hov-cl10 trans-03"
                                                >
                                                    Pellentesque dui nibh, pellen-tesque ut dapibus ut
                                                </Link>
                                            </h3>
                                        </div>
                                    </div>
                                </div>

                                <div className="col-sm-6 p-rl-1 p-b-2">
                                    <div
                                        className="bg-img1 size-a-5 how1 pos-relative"
                                        style={{
                                            backgroundImage: `url('/assets/images/post-04.jpg')`,
                                        }}
                                    >
                                        <Link to="/blog-detail-01" className="dis-block how1-child1 trans-03"></Link>

                                        <div className="flex-col-e-s s-full p-rl-25 p-tb-20">
                                            <Link
                                                to="/"
                                                className="dis-block how1-child2 f1-s-2 cl0 bo-all-1 bocl0 hov-btn1 trans-03 p-rl-5 p-t-2"
                                            >
                                                Sport
                                            </Link>

                                            <h3 className="how1-child2 m-t-14">
                                                <Link
                                                    to="/blog-detail-01"
                                                    className="how-txt1 size-h-1 f1-m-1 cl0 hov-cl10 trans-03"
                                                >
                                                    Motobike Vestibulum vene-natis purus nec nibh volutpat
                                                </Link>
                                            </h3>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>


            {/* Post section with tabs */}
            <section className="bg0 p-t-70">
                <div className="container">
                    <div className="row justify-content-center">
                        <div className="col-md-10 col-lg-8">
                            <div className="p-b-20">
                                <div className="tab01 p-b-20">
                                    <div className="tab01-head how2 how2-cl1 bocl12 flex-s-c m-r-10 m-r-0-sr991">
                                        <h3 className="f1-m-2 cl12 tab01-title">Entertainment</h3>
                                        <ul className="nav nav-tabs" role="tablist">
                                            <li className="nav-item">
                                                <a className="nav-link active" data-toggle="tab" href="/tab1-1" role="tab">All</a>
                                            </li>
                                            <li className="nav-item">
                                                <a className="nav-link" data-toggle="tab" href="/tab1-2" role="tab">Celebrity</a>
                                            </li>
                                            <li className="nav-item">
                                                <a className="nav-link" data-toggle="tab" href="/tab1-3" role="tab">Movies</a>
                                            </li>
                                            <li className="nav-item">
                                                <a className="nav-link" data-toggle="tab" href="/tab1-4" role="tab">Music</a>
                                            </li>
                                            <li className="nav-item">
                                                <a className="nav-link" data-toggle="tab" href="/tab1-5" role="tab">Games</a>
                                            </li>
                                            <li className="nav-item-more dropdown dis-none">
                                                <a className="nav-link dropdown-toggle" data-toggle="dropdown" href="/" onClick={preventDefault} role="button">
                                                    <i className="fa fa-ellipsis-h"></i>
                                                </a>
                                                <ul className="dropdown-menu"></ul>
                                            </li>
                                        </ul>
                                        <Link to="/category-01" className="tab01-link f1-s-1 cl9 hov-cl10 trans-03">
                                            View all <i className="fs-12 m-l-5 fa fa-caret-right"></i>
                                        </Link>
                                    </div>

                                    <div className="tab-content p-t-35">
                                        <div className="tab-pane fade show active" id="tab1-1" role="tabpanel">
                                            <div className="row">
                                                <div className="col-sm-6 p-r-25 p-r-15-sr991">
                                                    <div className="m-b-30">
                                                        <Link to="/blog-detail-01" className="wrap-pic-w hov1 trans-03">
                                                            <img src="/assets/images/post-05.jpg" alt="IMG" />
                                                        </Link>
                                                        <div className="p-t-20">
                                                            <h5 className="p-b-5">
                                                                <Link to="/blog-detail-01" className="f1-m-3 cl2 hov-cl10 trans-03">
                                                                    American live music lorem ipsum dolor sit amet consectetur
                                                                </Link>
                                                            </h5>
                                                            <span className="cl8">
                                                                <a href="/" onClick={preventDefault} role="button" className="f1-s-4 cl8 hov-cl10 trans-03">Music</a>
                                                                <span className="f1-s-3 m-rl-3">-</span>
                                                                <span className="f1-s-3">Feb 18</span>
                                                            </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className="col-sm-6 p-r-25 p-r-15-sr991">
                                                    {[6, 7, 8].map((img, i) => (
                                                        <div className="flex-wr-sb-s m-b-30" key={i}>
                                                            <Link to="/blog-detail-01" className="size-w-1 wrap-pic-w hov1 trans-03">
                                                                <img src={`/assets/images/post-0${img}.jpg`} alt="IMG" />
                                                            </Link>
                                                            <div className="size-w-2">
                                                                <h5 className="p-b-5">
                                                                    <Link to="/blog-detail-01" className="f1-s-5 cl3 hov-cl10 trans-03">
                                                                        Donec metus orci, malesuada et lectus vitae
                                                                    </Link>
                                                                </h5>
                                                                <span className="cl8">
                                                                    <a href="/" onClick={preventDefault} role="button" className="f1-s-6 cl8 hov-cl10 trans-03">
                                                                        {img === 6 ? 'Music' : img === 7 ? 'Game' : 'Celebrity'}
                                                                    </a>
                                                                    <span className="f1-s-3 m-rl-3">-</span>
                                                                    <span className="f1-s-3">Feb {19 - i}</span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    ))}
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div className="col-md-10 col-lg-4">
                            <div className="p-l-10 p-rl-0-sr991 p-b-20">
                                <div>
                                    <div className="how2 how2-cl4 flex-s-c">
                                        <h3 className="f1-m-2 cl3 tab01-title">Most Popular</h3>
                                    </div>
                                    <ul className="p-t-35">
                                        {[1, 2, 3, 4, 5].map((num, i) => (
                                            <li className="flex-wr-sb-s p-b-22" key={i}>
                                                <div className="size-a-8 flex-c-c borad-3 size-a-8 bg9 f1-m-4 cl0 m-b-6">{num}</div>
                                                <a href="/" onClick={preventDefault} role="button" className="size-w-3 f1-s-7 cl3 hov-cl10 trans-03">
                                                    {[
                                                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                                                        "Proin velit consectetur non neque",
                                                        "Nunc vestibulum, enim vitae condimentum volutpat lobortis ante",
                                                        "Proin velit justo consectetur non neque elementum",
                                                        "Proin velit consectetur non neque"
                                                    ][i]}
                                                </a>
                                            </li>
                                        ))}
                                    </ul>
                                </div>

                                <div className="p-t-50">
                                    <div className="how2 how2-cl4 flex-s-c">
                                        <h3 className="f1-m-2 cl3 tab01-title">Stay Connected</h3>
                                    </div>
                                    <ul className="p-t-35">
                                        {[
                                            { platform: "facebook", label: "6879 Fans", action: "Like" },
                                            { platform: "twitter", label: "568 Followers", action: "Follow" },
                                            { platform: "youtube", label: "5039 Subscribers", action: "Subscribe" },
                                        ].map((item, i) => (
                                            <li className="flex-wr-sb-c p-b-20" key={i}>
                                                <a href="/" onClick={preventDefault} role="button" className={`size-a-8 flex-c-c borad-3 size-a-8 bg-${item.platform} fs-16 cl0 hov-cl0`}>
                                                    <span className={`fab fa-${item.platform}`}></span>
                                                </a>
                                                <div className="size-w-3 flex-wr-sb-c">
                                                    <span className="f1-s-8 cl3 p-r-20">{item.label}</span>
                                                    <a href="/" onClick={preventDefault} role="button" className="f1-s-9 text-uppercase cl3 hov-cl10 trans-03">
                                                        {item.action}
                                                    </a>
                                                </div>
                                            </li>
                                        ))}
                                    </ul>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </section>

        </div>
    );
};

export default Home;