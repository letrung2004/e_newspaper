import React from "react";
import { Outlet } from "react-router-dom";
import EditorHeader from "./EditorHeader";
import EditorFooter from "./EditorFooter";

const EditorLayout = () => {

    return (

        <div className="flex flex-col min-h-screen">
            <EditorHeader />

            {/* Phần nội dung có thể co giãn */}
            <main className="flex-grow flex">
                <Outlet />
            </main>

            <EditorFooter />
        </div>

    );
};

export default EditorLayout;
