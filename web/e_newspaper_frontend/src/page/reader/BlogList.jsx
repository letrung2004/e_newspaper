import React, { useEffect } from 'react';
import { Link } from "react-router-dom";

const BlogList = () => {
    useEffect(() => {
        document.title = 'Blog - List';
    }, []);
    return (
        <div>

            <div className="container">
                <div className="headline bg0 flex-wr-sb-c p-rl-20 p-tb-8">
                    <div className="f2-s-1 p-r-30 m-tb-6">
                        <Link to="/" className="breadcrumb-item f1-s-3 cl9">
                            Home
                        </Link>
                        <Link to="/blog" className="breadcrumb-item f1-s-3 cl9">
                            Name category Blog
                        </Link>
                    </div>
                    <div className="pos-relative size-a-2 bo-1-rad-22 of-hidden bocl11 m-tb-6">
                        <input className="f1-s-1 cl6 plh9 s-full p-l-25 p-r-45" type="text" name="search" placeholder="Search" />
                        <button className="flex-c-c size-a-1 ab-t-r fs-20 cl2 hov-cl10 trans-03">
                            <i className="zmdi zmdi-search"></i>
                        </button>
                    </div>
                </div>
            </div>

            <section className="bg0 p-b-55">
                <div className="container">
                    <div className="row justify-content-center">
                        <div className="col-md-10 col-lg-8 p-b-80">
                            <div className="p-r-10 p-r-0-sr991">
                                <div className="m-t--40 p-b-40">
                                    {[43, 44].map((num, i) => (
                                        <div className="flex-wr-sb-s p-t-40 p-b-15 how-bor2" key={i}>
                                            <Link to="/blog-detail-02" className="size-w-8 wrap-pic-w hov1 trans-03 w-full-sr575 m-b-25">
                                                <img src={`/assets/images/post-${num}.jpg`} alt="IMG" />
                                            </Link>

                                            <div className="size-w-9 w-full-sr575 m-b-25">
                                                <h5 className="p-b-12">
                                                    <Link to="/blog-detail-02" className="f1-l-1 cl2 hov-cl10 trans-03 respon2">
                                                        {i === 0 ? "Robot lorem ipsum dolor sit amet consectetur" : "Health lorem ipsum dolor sit amet consectetur"}
                                                    </Link>
                                                </h5>

                                                <div className="cl8 p-b-18">
                                                    <a href="/" onClick={(e) => e.preventDefault()} className="f1-s-4 cl8 hov-cl10 trans-03">
                                                        by John Alvarado
                                                    </a>
                                                    <span className="f1-s-3 m-rl-3">-</span>
                                                    <span className="f1-s-3">Feb 18</span>
                                                </div>

                                                <p className="f1-s-1 cl6 p-b-24">
                                                    Duis eu felis id tortor congue consequat. Sed vitae vestibulum enim, et pharetra magna
                                                </p>

                                                <Link to="/blog-detail-02" className="f1-s-1 cl9 hov-cl10 trans-03">
                                                    Read More <i className="m-l-2 fa fa-long-arrow-alt-right"></i>
                                                </Link>
                                            </div>
                                        </div>
                                    ))}
                                </div>

                                <a href="/" onClick={(e) => e.preventDefault()} className="flex-c-c size-a-13 bo-all-1 bocl11 f1-m-6 cl6 hov-btn1 trans-03">
                                    Load More
                                </a>
                            </div>
                        </div>

                        <div className="col-md-10 col-lg-4 p-b-80">
                            <div className="p-l-10 p-rl-0-sr991">
                                <div className="p-b-23">
                                    <div className="how2 how2-cl4 flex-s-c">
                                        <h3 className="f1-m-2 cl3 tab01-title">Most Popular</h3>
                                    </div>
                                    <ul className="p-t-35">
                                        {[1, 2, 3, 4, 5].map((num, i) => (
                                            <li className="flex-wr-sb-s p-b-22" key={i}>
                                                <div className="size-a-8 flex-c-c borad-3 size-a-8 bg9 f1-m-4 cl0 m-b-6">{num}</div>
                                                <a href="/" onClick={(e) => e.preventDefault()} className="size-w-3 f1-s-7 cl3 hov-cl10 trans-03">
                                                    {["Lorem ipsum dolor sit amet, consectetur adipiscing elit", "Proin velit consectetur non neque", "Nunc vestibulum, enim vitae condimentum volutpat lobortis ante", "Proin velit justo consectetur non neque elementum", "Proin velit consectetur non neque"][i]}
                                                </a>
                                            </li>
                                        ))}
                                    </ul>
                                </div>



                                <div>
                                    <div className="how2 how2-cl4 flex-s-c m-b-30">
                                        <h3 className="f1-m-2 cl3 tab01-title">Tags</h3>
                                    </div>

                                    <div className="flex-wr-s-s m-rl--5">
                                        {["Fashion", "Lifestyle", "Denim", "Streetstyle", "Crafts", "Magazine", "News", "Blogs"].map((tag, i) => (
                                            <a
                                                key={i}
                                                href="/"
                                                onClick={(e) => e.preventDefault()}
                                                className="flex-c-c size-h-2 bo-1-rad-20 bocl12 f1-s-1 cl8 hov-btn2 trans-03 p-rl-20 p-tb-5 m-all-5"
                                            >
                                                {tag}
                                            </a>
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

export default BlogList;
