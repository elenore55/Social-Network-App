import React, {useState, useEffect} from 'react'
import Navbar from '../main/Navbar'
import { faPencil } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useNavigate } from 'react-router-dom';
import About from './About'
import MyProfileService from '../../services/MyProfileService'
import PostList from '../feed/PostList';
import '../../styles/styles.css'
import Friends from './Friends';
import { toast } from 'react-toastify'
import ProfilePictureModal from './ProfilePictureModal';

export default function MyProfile() {
    const cover = 'purple_cover.jpg';

    const [selectedTab, setSelectedTab] = useState('posts');
    const [userData, setUserData] = useState({});
    
    const navigate = useNavigate();

    useEffect(() => {
      fetchUserData();
	  // eslint-disable-next-line
    }, []);

    const editProfileData = () => {
      navigate('/editProfile');
    }

    const postsTabClicked = () => {
      setSelectedTab('posts');
    }

    const friendsTabClicked = () => {
      setSelectedTab('friends');
    }

    const aboutTabClicked = () => {
      setSelectedTab('about');
    }

    const fetchUserData = () => {
      MyProfileService.fetchUserData()
      .then(res => {
        setUserData(res.data);
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
		<Navbar selectedNavLink='myProfile' />
		<div className='container-fluid mt-3'>
			<div className='d-inline-block position-relative w-100'>
				<img src={`${process.env.REACT_APP_BACKEND_BASE_URL}assets/images/${cover}`} alt='Cover' className='opacity-75' id='cover' />
				<img src={`${process.env.REACT_APP_BACKEND_BASE_URL}assets/images/${userData.profilePicture}`} alt='Profile' className='rounded-circle p-1' id='profile-picture' 
					data-bs-toggle="modal" data-bs-target="#profile-picture-modal" style={{cursor: 'pointer'}} />
				<button className='btn bg-light btn-lg' id='btn-edit-profile' onClick={editProfileData}>
				<FontAwesomeIcon icon={faPencil} />&nbsp; Edit profile
				</button>
			</div>
			<div>
				<label className='d-flex justify-content-center fw-bold ms-1' id='lbl-name-surname'>{userData.name} {userData.surname}</label>
				<label className='d-flex justify-content-center fw-bold text-muted' id='lbl-username'>@{userData.username}</label>
			</div>
		</div>
		<div className='d-flex justify-content-center mt-5'>
			<ul className='nav nav-tabs bg-white'>
			<li className='nav-item'>
				<button className={selectedTab === 'posts' ? 'nav-link active' : 'nav-link'} aria-current='page' onClick={postsTabClicked}>Posts</button>
			</li>
			<li className='nav-item'>
				<button className={selectedTab === 'friends' ? 'nav-link active' : 'nav-link'} aria-current='page' onClick={friendsTabClicked}>Friends</button>
			</li>
			<li className='nav-item'>
				<button className={selectedTab === 'about' ? 'nav-link active' : 'nav-link'} aria-current='page' onClick={aboutTabClicked}>About</button>
			</li>
			</ul>
		</div>
		<div className='profile-bg'>
			{selectedTab === 'posts' && <PostList path='posts/me' myPosts={true}/>}
			{selectedTab === 'friends' && <Friends />}
			{selectedTab === 'about' && <About birthday={userData.dateOfBirth} email={userData.email} />}
		</div>
		<ProfilePictureModal profilePicture={`${process.env.REACT_APP_BACKEND_BASE_URL}assets/images/${userData.profilePicture}`} id="profile-picture-modal" />
    </div>
  )
}
