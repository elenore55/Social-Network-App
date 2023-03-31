import React, {useState} from 'react'
import InputField from '../util/InputField'
import { Link } from 'react-router-dom'
import AuthService from '../../services/AuthService';
import InvalidValueMessage from '../util/InvalidValueMessage';
import { toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';

export default function Registration() {
	const [userData, setUserData] = useState({name: '', surname: '', email: '', dateOfBirth: '', username: '', password: '', confirmPassword: ''});
	const [isValidData, setIsValidData] = useState({name: true, surname: true, email: true, dateOfBirth: true, username: true, password: true, confirmPassword: true});
	const navigate = useNavigate();

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

	const submit = () => {
		validate();
		if (isValidUserData()) {
			AuthService.register(userData)
			.then(_res => {
				toast.success('Registration successful');
				navigate('/');
			})
			.catch(error => {
				if (error.response.status === 400)
					toast.error(error.response.data.message);
				else
					toast.error('Something went wrong: unable to register');
			});
		}
	}

	const isValidUserData = () => {
		return isValidData.name && isValidData.surname && isValidData.email && isValidData.dateOfBirth && isValidData.username
			&& isValidData.password && isValidData.confirmPassword;
	}

	const validate = () => {
		setIsValidData({
			name: !!(userData.name),
			surname: !!(userData.surname),
			email: !!(userData.email),
			dateOfBirth: !!(userData.dateOfBirth),
			username: !!(userData.username),
			password: !!(userData.password),
			confirmPassword: !!(userData.confirmPassword) && (userData.password === userData.confirmPassword)
			}
		);
    }

  return (
    
    <div className='d-flex justify-content-center login-bg'>
      <div className='card w-50 my-4 shadow-lg py-1' style={{maxWidth: '700px'}}>
        <h3 className='card-title d-flex justify-content-center mb-3 mt-4'>Create a new account</h3>
        <div className='card-body container'>
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
            <hr />
            <InputField mt={2} mb={2} id='username-input' type='text' placeholder='Username' name='username' value={userData.username} changeHandler={updateUserData} />
			{ !isValidData.username && <InvalidValueMessage message='Username must not be empty' /> }
            <InputField mt={2} mb={2} id='password-input' type='password' placeholder='Password' name='password' value={userData.password} changeHandler={updateUserData} />
			{ !isValidData.password && <InvalidValueMessage message='Password must not be empty' /> }
            <InputField mt={2} mb={2} id='confirm-password-input' type='password' placeholder='Confirm password' name='confirmPassword' value={userData.confirmPassword} changeHandler={updateUserData} />
            { !isValidData.confirmPassword && <InvalidValueMessage message='Passwords must match' /> }
			<div className="mt-4 mb-2 d-flex flex-column">
                <button type="button" className="btn btn-success btn-lg bg-gradient text-center fw-bold" onClick={submit}>REGISTER</button>                    
            </div>
            <p className="text-center text-muted mt-3 mb-3">Already have an account? <Link to="/" className="fw-bold text-body"><u>Login here</u></Link></p>
        </div>
      </div>
    </div>
  )
}
