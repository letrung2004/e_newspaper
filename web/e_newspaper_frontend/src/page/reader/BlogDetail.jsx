import React, { useEffect } from 'react';
import { Link } from "react-router-dom";

const BlogDetail = () => {
    const preventDefault = (e) => e.preventDefault();
    useEffect(() => {
        document.title = 'Blog - Detail';
    }, []);

    return (
        <div>
            {/* Breadcrumb */}
            <div className="container">
                <div className="headline bg0 flex-wr-sb-c p-rl-20 p-tb-8">
                    <div className="f2-s-1 p-r-30 m-tb-6">
                        <Link to="/" className="breadcrumb-item f1-s-3 cl9">
                            Home
                        </Link>
                        <Link to="/blog" className="breadcrumb-item f1-s-3 cl9">
                            Blog
                        </Link>
                        <span className="breadcrumb-item f1-s-3 cl9">
                            Detail
                        </span>
                    </div>
                    <div className="pos-relative size-a-2 bo-1-rad-22 of-hidden bocl11 m-tb-6">
                        <input className="f1-s-1 cl6 plh9 s-full p-l-25 p-r-45" type="text" name="search" placeholder="Search" />
                        <button className="flex-c-c size-a-1 ab-t-r fs-20 cl2 hov-cl10 trans-03">
                            <i className="zmdi zmdi-search"></i>
                        </button>
                    </div>
                </div>
            </div>

            {/* Blog Detail Content */}
            <section className="bg0 p-b-140 p-t-10">
                <div className="container">
                    <div className="row justify-content-center">
                        <div className="col-md-10 col-lg-8 p-b-30">
                            <div className="p-r-10 p-r-0-sr991">
                                <div className="p-b-70">
                                    <Link to="#" onClick={preventDefault} className="f1-s-10 cl2 hov-cl10 trans-03 text-uppercase">Technology</Link>
                                    <h3 className="f1-l-3 cl2 p-b-16 p-t-33 respon2">
                                        Nulla non interdum metus non laoreet nisi tellus eget aliquam lorem pellentesque
                                    </h3>
                                    <div className="flex-wr-s-s p-b-40">
                                        <span className="f1-s-3 cl8 m-r-15">
                                            <Link to="#" onClick={preventDefault} className="f1-s-4 cl8 hov-cl10 trans-03">by John Alvarado</Link>
                                            <span className="m-rl-3">-</span>
                                            <span>Feb 18</span>
                                        </span>
                                        <span className="f1-s-3 cl8 m-r-15">5239 Views</span>
                                        <Link to="#" onClick={preventDefault} className="f1-s-3 cl8 hov-cl10 trans-03 m-r-15">0 Comment</Link>
                                    </div>

                                    <audio controls className="w-full my-4">
                                        <source src="/assets/audio/sample-news.mp3" type="audio/mpeg" />
                                        Your browser does not support the audio element.
                                    </audio>


                                    <div className="wrap-pic-max-w p-b-30">
                                        <img src="/assets/images/blog-list-01.jpg" alt="IMG" />
                                    </div>

                                    <p className="f1-s-11 cl6 p-b-25">
                                        Curabitur volutpat bibendum molestie... Maecenas elementum arcu eu convallis rhoncus.
                                    </p>
                                    <p className="f1-s-11 cl6 p-b-25">
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit... Donec ac purus id sapien condimentum feugiat.
                                    </p>
                                    <p className="f1-s-11 cl6 p-b-25">
                                        Praesent vel mi bibendum, finibus leo ac... Vivamus sagittis accumsan felis, quis vulputate.
                                    </p>

                                    <div className="flex-s-s p-t-12 p-b-15">
                                        <span className="f1-s-12 cl5 m-r-8">Tags:</span>
                                        <div className="flex-wr-s-s size-w-0">
                                            <Link to="#" onClick={preventDefault} className="f1-s-12 cl8 hov-link1 m-r-15">Streetstyle</Link>
                                            <Link to="#" onClick={preventDefault} className="f1-s-12 cl8 hov-link1 m-r-15">Crafts</Link>
                                        </div>
                                    </div>

                                    <div className="flex-s-s">
                                        <span className="f1-s-12 cl5 p-t-1 m-r-15">Share:</span>
                                        <div className="flex-wr-s-s size-w-0">
                                            {["facebook", "twitter", "google", "pinterest"].map((net, idx) => (
                                                <a
                                                    key={idx}
                                                    href="#"
                                                    onClick={preventDefault}
                                                    className={`dis-block f1-s-13 cl0 bg-${net} borad-3 p-tb-4 p-rl-18 hov-btn1 m-r-3 m-b-3 trans-03`}
                                                >
                                                    <i className={`fab fa-${net === "google" ? "google-plus-g" : net === "pinterest" ? "pinterest-p" : net} m-r-7`}></i>
                                                    {net.charAt(0).toUpperCase() + net.slice(1)}
                                                </a>
                                            ))}
                                        </div>
                                    </div>
                                </div>

                                <div>
                                    <h4 className="f1-l-4 cl3 p-b-12">Leave a Comment</h4>
                                    <form>
                                        <textarea className="bo-1-rad-3 bocl13 size-a-15 f1-s-13 cl5 plh6 p-rl-18 p-tb-14 m-b-20" name="msg" placeholder="Comment..."></textarea>
                                        <button className="size-a-17 bg2 borad-3 f1-s-12 cl0 hov-btn1 trans-03 p-rl-15 m-t-10">Post Comment</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                        {/* Sidebar */}
                        <div className="col-md-10 col-lg-4 p-b-30">
                            <div className="p-l-10 p-rl-0-sr991 p-t-70">
                                <div className="p-b-30">
                                    <div className="how2 how2-cl4 flex-s-c">
                                        <h3 className="f1-m-2 cl3 tab01-title">Popular Post</h3>
                                    </div>
                                    <ul className="p-t-35">
                                        {[4, 5, 6].map((num, idx) => (
                                            <li className="flex-wr-sb-s p-b-30" key={idx}>
                                                <Link to="#" onClick={preventDefault} className="size-w-10 wrap-pic-w hov1 trans-03">
                                                    <img src={`/assets/images/popular-post-0${num}.jpg`} alt="IMG" />
                                                </Link>
                                                <div className="size-w-11">
                                                    <h6 className="p-b-4">
                                                        <Link to="/blog-detail-02" className="f1-s-5 cl3 hov-cl10 trans-03">
                                                            Donec metus orci, malesuada et lectus vitae
                                                        </Link>
                                                    </h6>
                                                    <span className="cl8 txt-center p-b-24">
                                                        <Link to="#" onClick={preventDefault} className="f1-s-6 cl8 hov-cl10 trans-03">
                                                            {num === 4 ? "Music" : num === 5 ? "Game" : "Celebrity"}
                                                        </Link>
                                                        <span className="f1-s-3 m-rl-3">-</span>
                                                        <span className="f1-s-3">Feb {18 - idx * 2}</span>
                                                    </span>
                                                </div>
                                            </li>
                                        ))}
                                    </ul>
                                </div>

                                <div>
                                    <div className="how2 how2-cl4 flex-s-c m-b-30">
                                        <h3 className="f1-m-2 cl3 tab01-title">Tags</h3>
                                    </div>
                                    <div className="flex-wr-s-s m-rl--5">
                                        {["Fashion", "Lifestyle", "Denim", "Streetstyle", "Crafts", "Magazine", "News", "Blogs"].map((tag, idx) => (
                                            <Link
                                                key={idx}
                                                to="#"
                                                onClick={preventDefault}
                                                className="flex-c-c size-h-2 bo-1-rad-20 bocl12 f1-s-1 cl8 hov-btn2 trans-03 p-rl-20 p-tb-5 m-all-5"
                                            >
                                                {tag}
                                            </Link>
                                        ))}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
};

export default BlogDetail;
