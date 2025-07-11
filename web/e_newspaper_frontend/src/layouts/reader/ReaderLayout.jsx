import React from "react";
import { Outlet } from "react-router-dom";
import ReaderHeader from "./ReaderHeader";
import ReaderFooter from "./ReaderFooter";
import useInitScripts from "../../hooks/useInitScripts";

const ReaderLayout = () => {
    useInitScripts();

    return (

        <div className="flex flex-col min-h-screen">
            <ReaderHeader />

            {/* Phần nội dung có thể co giãn */}
            <main className="flex-grow flex">
                <Outlet />
            </main>

            <ReaderFooter />
        </div>

    );
};

export default ReaderLayout;
