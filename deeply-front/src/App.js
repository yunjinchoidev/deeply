import './App.css';
import {Route, Routes} from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import WritePage from "./pages/WritePage";
import RegisterPage from "./pages/RegisterPage";
import PostPage from "./pages/PostPage";
import Layout from "./components/common/Layout";
import LogoutPage from "./pages/LogoutPage";
import MyPage from "./pages/MyPage";
import CustomerCenterPage from "./pages/CustomerCenterPage";
import Home from "./pages/Home";
import BoardListPage from "./pages/BoardListPage";
import MatchListPage from "./pages/MatchListPage";
import ProfilePage from "./pages/ProfilePage";
import PostListPage from "./pages/PostListPage";
import PolicyPage from "./pages/PolicyPage";
import BoardPage from "./pages/BoardPage";
import AdminPage from "./pages/AdminPage";
import AdminLayout from "./components/common/AdminLayout";
import SocialLoginPage from "./pages/SocialLoginPage";
import MyFeedPage from "./pages/MyFeedPage";
import NoticePage from "./pages/NoticePage";
import BasketPage from "./pages/BasketPage";
import OrderPage from "./pages/OrderPage";
import MapServicePage from "./pages/MapServicePage";
import ChatPage from "./pages/ChatPage";
import BoardGet from "./components/common/BoardGet";
import BoardGetPage from "./pages/BoardGetPage";


function App() {
    return (
        <div className="App">
            <Routes>
                <Route element={<Layout/>}>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/login" element={<LoginPage/>}/>
                    <Route path="/sociallogin" element={<SocialLoginPage/>}/>
                    <Route path="/logout" element={<LogoutPage/>}/>
                    <Route path="/register" element={<RegisterPage/>}/>
                    <Route path="/mypage" element={<MyPage/>}/>
                    <Route path="/myfeed" element={<MyFeedPage/>}/>
                    <Route path="/customercenter" element={<CustomerCenterPage/>}/>
                    <Route path="/write" element={<WritePage/>}/>
                    <Route path="/boardList" element={<BoardListPage/>}/>
                    <Route path="/notice" element={<NoticePage/>}/>
                    <Route path="/basket" element={<BasketPage/>}/>
                    <Route path="/order" element={<OrderPage/>}/>
                    <Route path="/matchList" element={<MatchListPage/>}/>
                    <Route path="/profile" element={<ProfilePage/>}/>
                    <Route path="/policy" element={<PolicyPage/>}/>
                    <Route path="/mapservice" element={<MapServicePage/>}/>
                    <Route path="/chat" element={<ChatPage/>}/>

                    <Route element={<AdminLayout/>}>
                        <Route path="/admin" element={<AdminPage/>}/>
                    </Route>
                    <Route path="/board">
                        <Route index element={<BoardListPage/>}/>
                        <Route path="write" element={<BoardPage/>}/>
                        <Route path="id/:id" element={<BoardGetPage/>}/>
                    </Route>
                </Route>

                <Route path="/@:username">
                    <Route index element={<PostListPage/>}/>
                    <Route path=":postId" element={<PostPage/>}/>
                </Route>
            </Routes>
        </div>
    );
}

export default App;
