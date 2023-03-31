import './App.css';
import Login from './components/auth/Login';
import Registration from './components/auth/Registration';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import MainPage from './components/main/MainPage';
import MyProfile from './components/users/MyProfile';
import Unauthorized from './components/auth/Unauthorized';
import EditProfileData from './components/users/EditProfileData'
import OtherUsersProfile from './components/users/OtherUsersProfile';
import ReceivedRequests from './components/friend_reqs/ReceivedRequests';
import SearchResult from './components/users/SearchResult';
import { ToastContainer } from 'react-toastify';
import PasswordChangeForm from './components/auth/PasswordChangeForm';

function App() {
  return (
    <div className="App">
      <ToastContainer
        position="bottom-right"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnHover
      />
      <BrowserRouter>
      <Routes>
        <Route path="register" element={<Registration />} />
        <Route index element={<Login />} />
        <Route path='main' element={<MainPage />} />
        <Route path='myProfile' element={<MyProfile />} />
        <Route path='unauth' element={<Unauthorized />} />
        <Route path='editProfile' element={<EditProfileData />} />
        <Route path='userProfile/:id' element={<OtherUsersProfile />} />
        <Route path='requests' element={<ReceivedRequests />} />
        <Route path='searchResult/:term' element={<SearchResult />} />
        <Route path='searchResult' element={<SearchResult />} />
        <Route path='changePassword' element={<PasswordChangeForm />} /> 
      </Routes>
    </BrowserRouter>
    </div>
  );
}

export default App;
