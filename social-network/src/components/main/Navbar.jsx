import React, {useState} from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faSearch, faHome, faUser, faUsers, faSignOut } from '@fortawesome/free-solid-svg-icons'
import { useNavigate } from 'react-router-dom';

export default function Navbar(props) {

  	const [searchTerm, setSearchTerm] = useState('');
  	const navigate = useNavigate();

	const homeBtnClicked = () => {
		navigate('/main');
	}

	const myProfileButtonClicked = () => {
		navigate('/myProfile');
	}

	const friendReqsButtonClicked = () => {
		navigate('/requests');
	}

	const logOut = () => {
		localStorage.removeItem('jwt');
		navigate('/');
	}

  	const searchButtonClicked = () => {
		navigate(`/searchResult/${searchTerm}`);
	}

	const updateSearchTerm = (event) => {
		setSearchTerm(event.target.value);
	}

  return (
    <div style={{marginBottom: '60px'}}>
      <nav className='navbar navbar-expand-lg navbar-light fixed-top' style={{backgroundColor: "#a568b5"}}>
        <div className='container-fluid'>
            <div className="input-group mx-4">
                <input className="form-control rounded-end rounded-pill" type="search" placeholder='Search people' value={searchTerm} onChange={updateSearchTerm}></input>
                <button className="btn bg-white rounded-start rounded-pill py-2" type="button" onClick={searchButtonClicked}>
                    <FontAwesomeIcon icon={faSearch}></FontAwesomeIcon>
                </button>
            </div>
            <button className={!props.selectedNavLink ? 'btn me-2 ms-5 bg-light' : 'btn me-2 ms-5 not-selected'} data-bs-toggle="tooltip" title="Home page" onClick={homeBtnClicked}>
              <FontAwesomeIcon icon={faHome} size='lg' className={!props.selectedNavLink ? '' : 'light-icon'} />
            </button>
            <button className={props.selectedNavLink === 'myProfile' ? 'btn mx-2 bg-light' : 'btn mx-2 not-selected'} data-bs-toggle="tooltip" title="My profile" onClick={myProfileButtonClicked}>
              <FontAwesomeIcon icon={faUser} size='lg' className={props.selectedNavLink === 'myProfile' ? '' : 'light-icon'} />
            </button>
            <button className={props.selectedNavLink === 'friendReqs' ? 'btn mx-2 bg-light' : 'btn mx-2 not-selected'} data-bs-toggle="tooltip" title="Friendship requests" onClick={friendReqsButtonClicked}>
              <FontAwesomeIcon icon={faUsers} size='lg'  className={props.selectedNavLink === 'friendReqs' ? '' : 'light-icon'} />
            </button>
            <button className='btn not-selected ms-2 me-4' data-bs-toggle="tooltip" title="Sign out" onClick={logOut}>
              <FontAwesomeIcon icon={faSignOut} size='lg' className='light-icon' />
            </button>
        </div>
      </nav>
    </div>
  )
}
