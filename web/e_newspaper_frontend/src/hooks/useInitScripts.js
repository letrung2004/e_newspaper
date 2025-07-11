import { useEffect } from 'react';
import { useLocation } from 'react-router-dom';

const useInitScripts = () => {
    const location = useLocation();

    useEffect(() => {
        // Tạo lại thẻ script để chạy lại hiệu ứng
        const script = document.createElement("script");
        script.src = "/assets/js/main.js";
        script.async = true;
        document.body.appendChild(script);

        // Xóa script khi chuyển route để tránh chạy nhiều lần
        return () => {
            document.body.removeChild(script);
        };
    }, [location.pathname]); // chạy lại mỗi lần route đổi
};

export default useInitScripts;
