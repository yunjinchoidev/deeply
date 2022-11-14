import './App.css';
import {Route, Routes} from "react-router-dom";
import PostListPage from "./pages/PostListPage";
import LoginPage from "./pages/LoginPage";
import WritePage from "./pages/WritePage";
import RegisterPage from "./pages/RegisterPage";
import PostPage from "./pages/PostPage";
import Layout from "./components/common/Layout";
import LogoutPage from "./pages/LogoutPage";
import MyPage from "./pages/MyPage";
import CustomerCenterPage from "./pages/CustomerCenterPage";


function App() {
  return (
      <>
          <Routes>
              <Route element={<Layout/>}>
                  <Route path="/" element={<PostListPage />} />
                  <Route path="/login" element={<LoginPage />} />
                  <Route path="/logout" element={<LogoutPage />} />
                  <Route path="/register" element={<RegisterPage />} />
                  <Route path="/mypage" element={<MyPage />} />
                  <Route path="/customercenter" element={<CustomerCenterPage />} />
                  <Route path="/write" element={<WritePage />} />
              </Route>
                  <Route path="/@:username">
                      <Route index element={<PostListPage />} />
                      <Route path=":postId" element={<PostPage />} />
                  </Route>
          </Routes>
      </>
  );
}

export default App;
