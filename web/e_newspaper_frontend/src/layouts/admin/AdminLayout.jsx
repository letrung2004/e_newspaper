import React from "react";
import { Outlet } from "react-router-dom";
import AdminHeader from "./AdminHeader";
import AdminFooter from "./AdminFooter";

const AdminLayout = () => {

    return (

        <div className="flex flex-col min-h-screen">
            <AdminHeader />

            {/* Phần nội dung có thể co giãn */}
            <main className="flex-grow flex">
                <Outlet />
            </main>

            <AdminFooter />
        </div>

    );
};

export default AdminLayout;
