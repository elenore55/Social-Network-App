import React, {useState} from 'react'
import InputField from '../util/InputField';
import { Link, useNavigate } from 'react-router-dom';
import InvalidValueMessage from '../util/InvalidValueMessage';
import { toast } from 'react-toastify';
import AuthService from '../../services/AuthService';


export default function Login() {
	const people = 'people.png';
	const navigate = useNavigate();
	const [loginData, setLoginData] = useState({username: '', password: ''});
	const [isValidData, setIsValidData] = useState({username: true, password: true});

	const updateLoginData = (event) => {
	setLoginData(prev => {
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
		if (isValidData.username && isValidData.password) {
			AuthService.login(loginData)
			.then(res => {
				localStorage.setItem('jwt', res['data']['jwt']);
				localStorage.setItem('id', Number(res['data']['userId']));
				navigate('main');
			})
			.catch(_error => {
				toast.error('Invalid credentials');
			});
		}
	}

	const validate = () => {
		setIsValidData({
			username: !!(loginData.username),
			password: !!(loginData.password)
			}
		);
	}

	const test = () => {
		AuthService.test()
		.then(res => {
			alert(JSON.stringify(res));
		})
		.catch(_error => {
			toast.error('Error');
		});
	}

return (
	<div className='login-bg'>
		<div className='content-fluid d-flex justify-content-center'>
			<div className='card my-5 shadow-lg w-75' style={{maxWidth: '1000px'}}>
				<div className='row'>
					<div className='col-5 d-flex justify-content-center align-items-center'>
						<img src={`${process.env.REACT_APP_BACKEND_BASE_URL}assets/images/${people}`} className='w-75 h-75' alt='logo'></img>
					</div>
					<div className='col container d-flex justify-content-center'>
						<div className='w-100 mx-5'>
							<h3 className='d-flex justify-content-left ms-2 mt-5 mb-4'>Sign into your account</h3>
							<InputField mt={5} mb={1} id='username-input' type='text' placeholder='Username' name='username' value={loginData.username} changeHandler={updateLoginData} />
							{ !isValidData.username && <InvalidValueMessage message='Username must not be empty' /> }
							<InputField mt={3} mb={1} id='password-input' type='password' placeholder='Password' name='password' value={loginData.password} changeHandler={updateLoginData} />
							{ !isValidData.password && <InvalidValueMessage message='Password must not be empty' /> }
							<div className="mt-4 mb-2 d-flex flex-column">
									<button type="button" className="btn btn-success btn-lg bg-gradient text-center fw-bold" onClick={submit}>LOGIN</button>                    
							</div>
							<p className="text-center text-muted mt-3 mb-4">Don't have an account? <Link to='/register' className="fw-bold text-body"><u>Register here</u></Link></p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
)
}
