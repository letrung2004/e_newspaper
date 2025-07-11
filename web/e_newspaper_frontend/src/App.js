import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import ReaderLayout from "./layouts/reader/ReaderLayout";
import EditorLayout from "./layouts/editor/EditorLayout";
import AdminLayout from "./layouts/admin/AdminLayout";
import Home from './page/reader/Home';
import BlogList from './page/reader/BlogList';
import BlogDetail from './page/reader/BlogDetail';



function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Auth route */}

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

        </Route>

      </Routes>
    </BrowserRouter>
  );
}

export default App;
