import React, {useState, useEffect} from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faPencil } from '@fortawesome/free-solid-svg-icons'
import EditProfileService from '../../services/EditProfileService'
import Navbar from '../main/Navbar'
import InputField from '../util/InputField'
import InvalidValueMessage from '../util/InvalidValueMessage'
import { Link, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import ProfilePictureModal from './ProfilePictureModal'

export default function EditProfileData() {

	const [profile, setProfile] = useState('default_avatar.png');
	const [userData, setUserData] = useState({name: '', surname: '', email: '', dateOfBirth: '', username: ''});
	const [isValidData, setIsValidData] = useState({name: true, surname: true, email: true, dateOfBirth: true});
	const [file, setFile] = useState();
	const [fileInput, setFileInput] = useState();
	const navigate = useNavigate();

	useEffect(() => {
		fetchUserData();
		// eslint-disable-next-line
	}, []);

	const updateUserData = (event) => {
		setUserData(prev => {
			return {
			  ...prev, 
			  [event.target.name]: event.target.value
			}
		});
		setIsValidData(prev => {
			return {
			...prev,
			[event.target.name]: !!(event.target.value)
			}
		});
	}

	const onFormSubmit = (event) => {
		event.preventDefault();
		validate();
		if (isValidUserData()) {
			EditProfileService.editProfile(userData, file)
			.then(res => {
				toast.success('Profile data updated');
				setUserData({...res.data, dateOfBirth: convertDate(res.data.dateOfBirth)});
				navigate('/myProfile');
			})
			.catch(error => {
				if (error.response.status === 400) 
                	toast.error(error.response.data.message)
				else if (error.response.status === 401)
					navigate('/unauth');
				else
					toast.error('Something went wrong: unable to update profile data');
			})
		}
	}

	const onChange = (event) => {
		setFile(event.target.files[0]);
		setProfile(URL.createObjectURL(event.target.files[0]));
	}

	const isValidUserData = () => {
		return isValidData.name && isValidData.surname && isValidData.email && isValidData.dateOfBirth;
	}

	const validate = () => {
		setIsValidData({
			name: !!(userData.name),
			surname: !!(userData.surname),
			email: !!(userData.email),
			dateOfBirth: !!(userData.dateOfBirth),
			}
		);
    }

	const fetchUserData = () => {
		EditProfileService.fetchUserData()
		.then(res => {
			setUserData({...res.data, dateOfBirth: convertDate(res.data.dateOfBirth)});
			setProfile(`${process.env.REACT_APP_BACKEND_BASE_URL}assets/images/${res.data.profilePicture}`);
		})
		.catch(error => {
			if (error.response.status === 401)
                navigate('/unauth');
            else
                toast.error('Something went wrong: unable to fetch profile data');
		});
	}

	const convertDate = (dateStr) => {
		const tokens = dateStr.split('/');
		return `${tokens[2]}-${tokens[1]}-${tokens[0]}`;
	}
	
  return (
    <div>
		<Navbar selectedNavLink='myProfile' />
		<div className='d-flex justify-content-center profile-bg'>
			<form className='card m-5 p-3' onSubmit={onFormSubmit}>
				<h3 className='d-flex justify-content-center my-4'>Profile picture</h3>
				<div className='d-flex justify-content-center mx-5 my-2'>
					<input type="file" style={{display: 'none'}} onChange={onChange} ref={inp => setFileInput(inp)} />
					<img src={profile} alt="Profile" style={{width: '210px', height: '210px', cursor: 'pointer'}} className='rounded-circle'
						data-bs-toggle="modal" data-bs-target="#profile-picture-modal"/>
					<button type='button' className='btn h-25 btn-sm' onClick={() => fileInput.click()}>
						<FontAwesomeIcon icon={faPencil}></FontAwesomeIcon> Edit
					</button>
				</div>
				<hr />
				<h3 className='d-flex justify-content-center my-3'>Personal data</h3>
				<div className='container'>
					<div className='row'>
						<div className='col'>
							<InputField mt={2} mb={1} id='name-input' type='text' placeholder='Name' name='name' value={userData.name} changeHandler={updateUserData} />
							{ !isValidData.name && <InvalidValueMessage message='Name must not be empty' /> }
						</div>
						<div className='col'>
							<InputField mt={2} mb={1} id='name-input' type='text' placeholder='Surname' name='surname' value={userData.surname} changeHandler={updateUserData} />
							{ !isValidData.surname && <InvalidValueMessage message='Surname must not be empty' /> }
						</div>
					</div>
					<InputField mt={1} mb={2} id='email-input' type='email' placeholder='Email' name='email' value={userData.email} changeHandler={updateUserData} />
					{ !isValidData.email && <InvalidValueMessage message='Email must not be empty' /> }
					<InputField mt={1} mb={2} id='date-of-birth-input' type='date' placeholder='Date of birth' name='dateOfBirth' value={userData.dateOfBirth} changeHandler={updateUserData} />
					{ !isValidData.dateOfBirth && <InvalidValueMessage message='Date of birth must not be empty' /> }
					<div className='d-flex justify-content-between'>
						<Link to='/changePassword' className='mt-3'><u>Change password</u></Link>
						<button type='submit' className='btn btn-lg btn-success my-2'>Save changes</button>
					</div>
				</div>
			</form>
		</div>
		<ProfilePictureModal profilePicture={profile} id="profile-picture-modal" />
    </div>
  )
}
