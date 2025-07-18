import './App.css';
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import ReaderLayout from "./layouts/reader/ReaderLayout";
import EditorLayout from "./layouts/editor/EditorLayout";
import AdminLayout from "./layouts/admin/AdminLayout";
import Home from './page/reader/Home';
import BlogList from './page/reader/BlogList';
import BlogDetail from './page/reader/BlogDetail';
import AdminHome from './page/admin/AdminHome';
import Login from './page/auth/Login';
import Register from './page/auth/Register';
import { AuthProvider, useAuth } from './contexts/AuthProvider';



function App() {

  const PrivateRoute = ({ element }) => {
    const { user, loading } = useAuth();
    if (loading) return <div>Loading...</div>;
    return user ? element : <Navigate to="/login" />;
  };

  return (
    <>
      <AuthProvider>
        <BrowserRouter>
          <Routes>
            {/* Auth route */}
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />

            {/* Reader route */}
            <Route path="/" element={< ReaderLayout />}>
              <Route index element={<Home />} />
              <Route path=":categoryName" element={<BlogList />} />
              <Route path=":categoryName/:blogName" element={<BlogDetail />} />
            </Route>

            {/* Editor route */}
            <Route path="/editor" element={< EditorLayout />}>

            </Route>


            {/* Admin route */}
            <Route path="/admin" element={< AdminLayout />}>
              <Route index element={<AdminHome />} />
            </Route>

          </Routes>
        </BrowserRouter>
      </AuthProvider>
    </>
  );
}

export default App;
