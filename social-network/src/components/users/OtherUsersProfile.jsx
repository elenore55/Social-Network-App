import React, {useState, useEffect} from 'react'
import { useParams } from 'react-router-dom'
import Navbar from '../main/Navbar';
import OtherUsersProfileService from '../../services/OtherUsersProfileService';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCheck, faPlus } from '@fortawesome/free-solid-svg-icons'
import About from './About';
import PostList from '../feed/PostList';
import ReceivedRequestsService from '../../services/ReceivedRequestsService';
import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom';
import ProfilePictureModal from './ProfilePictureModal';

export default function OtherUsersProfile() {
    const cover = 'purple_cover.jpg';
    const {id} = useParams();
    const [userData, setUserData] = useState({});
    const [selectedTab, setSelectedTab] = useState('posts');
    const navigate = useNavigate();

    useEffect(() => {
        fetchUserData();
        // eslint-disable-next-line
    }, []);

    const sendFriendReq = () => {
        OtherUsersProfileService.sendFriendReq(id)
        .then(_res => {
            setUserData(prev => ({...prev, friendshipStatus: 'Request sent'}));
        })
        .catch(error => {
            if (error.response.status === 400) 
                toast.error(error.response.data.message)
            else if (error.response.status === 401)
                navigate('/unauth');
            else
                toast.error('Something went wrong: unable to send request');
        });
    }

    const acceptRequest = () => {
        ReceivedRequestsService.acceptRequest(userData.id)
        .then(_res => {
            setUserData(prev => ({...prev, friendshipStatus: 'Friends'}));
        })
        .catch(error => {
            if (error.response.status === 400) 
                toast.error(error.response.data.message)
            else if (error.response.status === 401)
                navigate('/unauth');
            else
                toast.error('Something went wrong: unable to accept request');
        });
    }

    const rejectRequest = () => {
        ReceivedRequestsService.rejectRequest(userData.id)
        .then(_res => {
            setUserData(prev => ({...prev, friendshipStatus: 'Not friends'}));
        })
        .catch(error => {
            if (error.response.status === 400) 
                toast.error(error.response.data.message)
            else if (error.response.status === 401)
                navigate('/unauth');
            else
                toast.error('Something went wrong: unable to reject request');
        });
    }

    const postsTabClicked = () => {
        setSelectedTab('posts');
    }


    const aboutTabClicked = () => {
        setSelectedTab('about');
    }

    const fetchUserData = () => {
        OtherUsersProfileService.fetchUserData(id)
        .then(res => {
            setUserData({...res['data']['userData'], friendshipStatus: res['data']['friendshipStatus']});
        })
        .catch(error => {
            if (error.response.status === 401)
			    navigate('/unauth');
		    else
			    toast.error('Something went wrong: unable to fetch user');
        });
    }
  return (
    <div className='profile-bg'>
        <Navbar />
        <div className='container-fluid mt-3'>
            <div className='d-inline-block position-relative w-100'>
                <img src={`${process.env.REACT_APP_BACKEND_BASE_URL}assets/images/${cover}`} alt='Cover' className='opacity-75' id='cover' />
                <img src={`${process.env.REACT_APP_BACKEND_BASE_URL}assets/images/${userData.profilePicture}`} alt='Profile' className='rounded-circle p-1' id='profile-picture'
                    data-bs-toggle="modal" data-bs-target="#profile-picture-modal" style={{cursor: 'pointer'}} />
            </div>
            <div>
                <label className='d-flex justify-content-center fw-bold ms-1' id='lbl-name-surname'>{userData.name} {userData.surname}</label>
                <label className='d-flex justify-content-center fw-bold text-muted' id='lbl-username'>@{userData.username}</label>
                {userData.friendshipStatus === "Friends" && 
                <span className="badge bg-light h1" id='friends-badge'>
                    <FontAwesomeIcon icon={faCheck} size='lg'/>&nbsp; Friends
                </span>}
                {userData.friendshipStatus === "Not friends" && 
                <button type='button' className='btn btn-lg bg-light' id='btn-add-friend' onClick={sendFriendReq}>
                    <FontAwesomeIcon icon={faPlus} size='lg'/>&nbsp; Add friend
                </button>}
                {userData.friendshipStatus === "Request sent" && 
                <span className="badge bg-light h1" id='req-sent-badge'>
                    <FontAwesomeIcon icon={faCheck} size='lg'/>&nbsp; Request sent
                </span>}
                {userData.friendshipStatus === "Request received" &&
                <div className="dropdown" id="respond">
                    <button className="btn btn-lg btn-light dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false" id='btn-respond'>
                        Respond
                    </button>
                    <ul className="dropdown-menu">
                        <li><button type='button' className="dropdown-item" onClick={acceptRequest}>Accept</button></li>
                        <li><button type='button' className="dropdown-item" onClick={rejectRequest}>Reject</button></li>
                    </ul>
                </div>
                }
            </div>
        </div>
        {userData.friendshipStatus === "Friends" && 
        <div className='profile-bg'>
            <div className='d-flex justify-content-center mt-5'>
                <ul className='nav nav-tabs bg-white'>
                    <li className='nav-item'>
                        <button className={selectedTab === 'posts' ? 'nav-link active' : 'nav-link'} aria-current='page' onClick={postsTabClicked}>Posts</button>
                    </li>
                    <li className='nav-item'>
                        <button className={selectedTab === 'about' ? 'nav-link active' : 'nav-link'} aria-current='page' onClick={aboutTabClicked}>About</button>
                    </li>
                </ul>
            </div>
            <div>
                {selectedTab === 'posts' && <PostList path={`posts/${id}`}/>}
                {selectedTab === 'about' && <About birthday={userData.dateOfBirth} email={userData.email} />}
            </div>
        </div>}
        <ProfilePictureModal profilePicture={`${process.env.REACT_APP_BACKEND_BASE_URL}assets/images/${userData.profilePicture}`} id="profile-picture-modal" />
    </div>
  )
}
