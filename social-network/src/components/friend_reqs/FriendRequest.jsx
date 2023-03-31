import React from 'react'
import { Link } from 'react-router-dom';

export default function FriendRequest(props) {

  return (
    <div className='d-flex justify-content-center'>
      <div className='card container w-50 my-3 py-1 shadow'>
        <div className='row'>
          <div className='col-2'>
          <Link to={'/userProfile/' + props.req.otherUser.id}><img src={`${process.env.REACT_APP_BACKEND_BASE_URL}assets/images/${props.req.otherUser.profilePicture}`} alt='Profile' 
            className='user-profile-list m-2 rounded-circle' /></Link>
          </div>
          <div className='col container mt-2'>
            <div className='row'>
            <Link to={'/userProfile/' + props.req.otherUser.id}><label id='lbl-name-surname-md'>{props.req.otherUser.name} {props.req.otherUser.surname}</label></Link>
            </div>
            <div>
              <label className='text-muted'>@{props.req.otherUser.username}</label>
            </div>
            <div>
              <label className='small mt-2'>Sent on {props.req.dateTime}h</label>
            </div>
          </div>
          <div className='col d-flex justify-content-end align-items-end mb-2'>
            {props.req.status === "PENDING" && 
              <div className="dropdown">
                <button className="btn btn-primary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Respond
                </button>
                <ul className="dropdown-menu">
                    <li><button type='button' className="dropdown-item" onClick={() => props.requestAccepted(props.req.otherUser.id)}>Accept</button></li>
                    <li><button type='button' className="dropdown-item" onClick={() => props.requestRejected(props.req.otherUser.id)}>Reject</button></li>
                </ul>
              </div>
            }
          </div>
        </div>
      </div>
    </div>
  )
}
