import React, {useState} from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faPencil, faTrash } from '@fortawesome/free-solid-svg-icons'
import { Link } from 'react-router-dom';

export default function Comment(props) {

    const [beingUpdated, setBeingUpdated] = useState(false);

	const toggleBeingUpdated = () => {
        setBeingUpdated(prev => !prev);
    }

  return (
    <div className='d-flex justify-content-center my-1'>
      <div className='card container px-3 py-2'>
        <div className='row mb-1'>
            <div className='col-1'>
				<Link to={'/userProfile/' + props.comment.user.id}>
					<img src={`${process.env.REACT_APP_BACKEND_BASE_URL}assets/images/${props.comment.user.profilePicture}`} alt='Profile' style={{width: '50px', height: '50px'}} 
						className='rounded-circle' />
				</Link>
			</div>
			<div className='col container ms-2'>
				<Link to={'/userProfile/' + props.comment.user.id}>
					<div className='row'>
						<label className='profile-link'>{props.comment.user.name} {props.comment.user.surname} <label className='text-muted'>(@{props.comment.user.username})</label></label>
					</div>
				</Link>
				<div className='row'>
					<label className='small'>{props.comment.lastUpdated}h { (props.comment.lastUpdated !== props.comment.created) && 
					<label className='text-muted'>(edited)</label> }</label>
				</div>
			</div>
			{props.myComment &&
			<div className='col d-flex justify-content-end'>
				<button type='button' className={beingUpdated ? 'btn mx-1 btn-secondary' : 'btn mx-1'} 
					onClick={() => {props.setUpdatedCommentContent(props.comment.content); toggleBeingUpdated();}}>
					<FontAwesomeIcon icon={faPencil} />
				</button>	
				<button type='button' className='btn mx-1' onClick={() => props.deleteButtonClicked(props.comment.id)}>
					<FontAwesomeIcon icon={faTrash} />
				</button>
			</div>
			}
		</div>
        <div className='row'>
			{beingUpdated ? 
			<div className='container-fluid'>
				<textarea className='w-100' style={{height: '100px'}} value={props.updateContent} onChange={props.commentContentChanged} />
				<div className='d-flex justify-content-end'>
					<button type='button' className='btn btn-secondary mx-1' onClick={() => {toggleBeingUpdated(); props.updateButtonClicked(props.comment.id)}}>Update</button>
					<button type='button' className='btn mx-1' onClick={toggleBeingUpdated}>Cancel</button>
				</div>
			</div> 
			:
            <label className='my-3 ms-2'>{props.comment.content}</label>}
        </div>
      </div>
    </div>
  )
}
