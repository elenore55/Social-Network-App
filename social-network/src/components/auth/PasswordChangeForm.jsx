import React, {useState} from 'react'
import Navbar from '../main/Navbar';
import InputField from '../util/InputField';
import InvalidValueMessage from '../util/InvalidValueMessage';
import AuthService from '../../services/AuthService';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

export default function PasswordChangeForm() {
    const [passwordData, setPasswordData] = useState({oldPassword: '', newPassword: '', confirmPassword: ''});
    const [isValidData, setIsValidData] = useState({oldPassword: true, newPassword: true, confirmPassword: true});
	const navigate = useNavigate();

    const updateData = (event) => {
        setPasswordData(prev => {
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

	const changePassword = () => {
		validate();
		if (isValidPasswordData()) {
			AuthService.changePassword(passwordData)
			.then(_res => {
				toast.success('Password successfully changed');
				navigate('/myProfile');
			})
			.catch(error => {
				if (error.response.status === 400)
					toast.error('Incorrect password');
				else if (error.response.status === 401)
					navigate('/unauth');
				else
					toast.error('Something went wrong: unable to change password')
			});
		} 
	}

	const isValidPasswordData = () => {
		return isValidData.oldPassword && isValidData.newPassword && isValidData.confirmPassword; 
	}

	const validate = () => {
		setIsValidData({
			oldPassword: !!(passwordData.oldPassword),
			newPassword: !!(passwordData.newPassword),
			confirmPassword: !!(passwordData.confirmPassword) && (passwordData.newPassword === passwordData.confirmPassword)
		});
    }


  return (
    <div className='profile-bg'>
		<Navbar selectedNavLink='myProfile' />
		<div className='d-flex justify-content-center'>
			<div className='card m-5 p-4 shadow w-50'>
				<h2 className='d-flex justify-content-center mt-2 mb-4'>Change password</h2>
				<div>
					<InputField mt={3} mb={3} id='old-password-input' type='password' placeholder='Current password' name='oldPassword' value={passwordData.oldPassword} changeHandler={updateData} />
					{ !isValidData.oldPassword && <InvalidValueMessage message='Password must not be empty' /> }
					<InputField mt={3} mb={3} id='new-password-input' type='password' placeholder='New password' name='newPassword' value={passwordData.newPassword} changeHandler={updateData} />
					{ !isValidData.newPassword && <InvalidValueMessage message='Password must not be empty' /> }
					<InputField mt={3} mb={3} id='confirm-password-input' type='password' placeholder='Confirm password' name='confirmPassword' value={passwordData.confirmPassword} changeHandler={updateData} />
					{ !isValidData.confirmPassword && <InvalidValueMessage message='Passwords must match' /> }
				</div>
				<div className='d-flex justify-content-end'>
					<button type='button' className='btn btn-lg btn-success my-2' onClick={changePassword}>Save changes</button>
				</div>
			</div>
		</div>
    </div>
  )
}
