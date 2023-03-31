import React from 'react'
import { Link } from 'react-router-dom';

export default function ShortUserDisplay(props) {

  return (
    <div className='d-flex justify-content-center'>
      <div className='card container w-50 my-2 py-1'>
        <div className='row'>
          <div className='col-2'>
          <Link to={'/userProfile/' + props.user.id}><img src={`${process.env.REACT_APP_BACKEND_BASE_URL}assets/images/${props.user.profilePicture}`} alt='Profile' className='user-profile-list m-2 rounded-circle' /></Link>
          </div>
          <div className='col container mt-2'>
            <div className='row'>
            <Link to={'/userProfile/' + props.user.id}><label id='lbl-name-surname-md'>{props.user.name} {props.user.surname}</label></Link>
            </div>
            <div>
              <label className='text-muted'>@{props.user.username}</label>
            </div>
          </div>
          <div className='col d-flex justify-content-end align-items-end mb-2'>
            {props.user.friendshipStatus === "Friends" && <span className="badge bg-primary friendship-status-badge px-3 py-2">Friends</span>}

            {props.user.friendshipStatus === "Request sent" && <span className="badge bg-secondary friendship-status-badge px-3 py-2">Request sent</span>}

            {props.user.friendshipStatus === "Not friends" && <button type='button' className='btn' onClick={() => props.requestSent(props.user.id)}>Send request</button>}

            {props.user.friendshipStatus === "Request received" && 
              <div className="dropdown">
                <button className="btn dropdown-toggle" type="button" id="btnRespond" data-bs-toggle="dropdown" aria-expanded="false">
                  Respond
                </button>
                <ul className="dropdown-menu" aria-labelledby="btnRespond">
                  <li><button className="dropdown-item" onClick={() => props.requestAccepted(props.user.id)}>Accept</button></li>
                  <li><button className="dropdown-item" onClick={() => props.requestRejected(props.user.id)}>Reject</button></li>
                </ul>
              </div>
            }
          </div>
        </div>
      </div>
    </div>
  )
}
