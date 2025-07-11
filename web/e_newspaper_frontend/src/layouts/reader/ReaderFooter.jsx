import React from "react";

const ReaderFooter = () => {
    return (
        <>
            <footer>
                <div className="bg2 p-t-40 p-b-25">
                    <div className="container">
                        <div className="row">
                            {/* Logo & Info */}
                            <div className="col-lg-4 p-b-20">
                                <div className="size-h-3 flex-s-c">
                                    <a href="/">
                                        <img
                                            className="max-s-full"
                                            src="/assets/images/icons/logo-02.png"
                                            alt="LOGO"
                                        />
                                    </a>
                                </div>

                                <div>
                                    <p className="f1-s-1 cl11 p-b-16">
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit...
                                    </p>

                                    <p className="f1-s-1 cl11 p-b-16">
                                        Any questions? Call us on (+1) 96 716 6879
                                    </p>

                                    <div className="p-t-15">
                                        <a href="#" className="fs-18 cl11 hov-cl10 trans-03 m-r-8">
                                            <span className="fab fa-facebook-f"></span>
                                        </a>
                                        <a href="#" className="fs-18 cl11 hov-cl10 trans-03 m-r-8">
                                            <span className="fab fa-twitter"></span>
                                        </a>
                                        <a href="#" className="fs-18 cl11 hov-cl10 trans-03 m-r-8">
                                            <span className="fab fa-pinterest-p"></span>
                                        </a>
                                        <a href="#" className="fs-18 cl11 hov-cl10 trans-03 m-r-8">
                                            <span className="fab fa-vimeo-v"></span>
                                        </a>
                                        <a href="#" className="fs-18 cl11 hov-cl10 trans-03 m-r-8">
                                            <span className="fab fa-youtube"></span>
                                        </a>
                                    </div>
                                </div>
                            </div>

                            {/* Popular Posts */}
                            <div className="col-sm-6 col-lg-4 p-b-20">
                                <div className="size-h-3 flex-s-c">
                                    <h5 className="f1-m-7 cl0">Popular Posts</h5>
                                </div>

                                <ul>
                                    {[1, 2, 3].map((item, idx) => (
                                        <li className="flex-wr-sb-s p-b-20" key={idx}>
                                            <a href="#" className="size-w-4 wrap-pic-w hov1 trans-03">
                                                <img
                                                    src={`/assets/images/popular-post-0${item}.jpg`}
                                                    alt="IMG"
                                                />
                                            </a>

                                            <div className="size-w-5">
                                                <h6 className="p-b-5">
                                                    <a href="#" className="f1-s-5 cl11 hov-cl10 trans-03">
                                                        {idx === 0
                                                            ? "Donec metus orci, malesuada et lectus vitae"
                                                            : idx === 1
                                                                ? "Lorem ipsum dolor sit amet, consectetur"
                                                                : "Suspendisse dictum enim quis imperdiet auctor"}
                                                    </a>
                                                </h6>
                                                <span className="f1-s-3 cl6">Feb {17 - idx}</span>
                                            </div>
                                        </li>
                                    ))}
                                </ul>
                            </div>

                            {/* Category */}
                            <div className="col-sm-6 col-lg-4 p-b-20">
                                <div className="size-h-3 flex-s-c">
                                    <h5 className="f1-m-7 cl0">Category</h5>
                                </div>

                                <ul className="m-t--12">
                                    {[
                                        "Fashion (22)",
                                        "Technology (29)",
                                        "Street Style (15)",
                                        "Life Style (28)",
                                        "DIY & Crafts (16)",
                                    ].map((cat, i) => (
                                        <li className="how-bor1 p-rl-5 p-tb-10" key={i}>
                                            <a
                                                href="#"
                                                className="f1-s-5 cl11 hov-cl10 trans-03 p-tb-8"
                                            >
                                                {cat}
                                            </a>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                {/* Copyright */}
                <div className="bg11">
                    <div className="container size-h-4 flex-c-c p-tb-15">
                        <span className="f1-s-1 cl0 txt-center">
                            Copyright © {new Date().getFullYear()}
                        </span>
                    </div>
                </div>

                {/* Back to Top */}
                <div className="btn-back-to-top" id="myBtn">
                    <span className="symbol-btn-back-to-top">
                        <span className="fas fa-angle-up"></span>
                    </span>
                </div>
            </footer>



        </>
    );
};

export default ReaderFooter;
