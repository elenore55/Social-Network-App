import React, { useState } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faThumbsUp, faThumbsDown, faPencil, faTrash } from '@fortawesome/free-solid-svg-icons'
import CommentList from './CommentList';
import { Link } from 'react-router-dom';
import CreateComment from './CreateComment';
import CommentListService from '../../services/CommentListService';
import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom';

export default function Post(props) {
    const [commentsShown, setCommentsShown] = useState(false);
    const [beingUpdated, setBeingUpdated] = useState(false);
    const [commentBeingCreated, setCommentBeingCreated] = useState(false);
    const [newCommentContent, setNewCommentContent] = useState('');
    const navigate = useNavigate();

    function toggleComments() {
        setCommentsShown(prev => !prev);
        if (commentBeingCreated) {
            setCommentBeingCreated(false);
            setNewCommentContent('');
        }
    }

    const toggleBeingUpdated = () => {
        setBeingUpdated(prev => !prev);
    }

    const toggleCommentBeingCreated = () => {
        setCommentBeingCreated(prev => !prev);
        setCommentsShown(false);
    }

    const updateNewCommentContent = (event) => {
        setNewCommentContent(event.target.value);
    }
    
    const createComment = () => {
        CommentListService.createNewComment(newCommentContent, props.post.id)
        .then(_res => {
            setCommentsShown(true);
            setCommentBeingCreated(false);
            setNewCommentContent('');
        })
        .catch(error => {
            if (error.response.status === 400)
                toast.error(error.response.data.message)
            else if (error.response.status === 401)
                navigate('/unauth');
            else 
                toast('Something went wrong');
        });
    }

  return (
    <div>
        <div className="modal" tabIndex="-1" id="confirm-post-delete">
			<div className="modal-dialog">
				<div className="modal-content p-4">
                    <h4 className='d-flex justify-content-center my-3'>Are you sure you want to delete the post?</h4>
					<div className="modal-body d-flex justify-content-center my-3">
						<button type='button' className='btn btn-danger btn-lg mx-3' onClick={() => props.deleteButtonClicked(props.post.id)} data-bs-dismiss="modal">Yes</button>
                        <button type='button' className='btn btn-lg mx-3' data-bs-dismiss="modal">No</button>
					</div>
				</div>
			</div>
		</div>
        <div className='d-flex justify-content-center mb-1 pt-4'>
            <div className='card shadow container w-50 px-3 py-2'>
                <div className='row'>
                    <div className='col-1 mx-1 d-flex align-items-center'>
                    <Link to={'/userProfile/' + props.post.user.id}>
                        <img className='user-profile-post rounded-circle' src={`${process.env.REACT_APP_BACKEND_BASE_URL}assets/images/${props.post.user.profilePicture}`} alt='Profile' />
                    </Link>
                    </div>
                    <div className='col container'>
                        <div className='row'>
                            <Link to={'/userProfile/' + props.post.user.id}>
                                <label className='profile-link'>{props.post.user.name} {props.post.user.surname} <label className='text-muted'>(@{props.post.user.username})</label></label>
                            </Link>
                        </div>
                        <div className='row'>
                            <label className='small'>{props.post.lastUpdated}h { (props.post.lastUpdated !== props.post.created) && <label className='text-muted'>(edited)</label> }</label>
                        </div>
                    </div>
                    {props.myPost &&
                    <div className='col d-flex justify-content-end'>
                        <button type='button' className={beingUpdated ? 'btn mx-1 btn-secondary' : 'btn mx-1'} onClick={() => {props.setUpdatedPostContent(props.post.content); toggleBeingUpdated();}}>
                            <FontAwesomeIcon icon={faPencil} size='lg'/>
                        </button>
                        <button type='button' className='btn mx-1' data-bs-toggle="modal" data-bs-target="#confirm-post-delete">
                            <FontAwesomeIcon icon={faTrash} size='lg'/>
                        </button>
                    </div>}
                </div>
                <hr></hr>
                <div className='row'>
                    {beingUpdated ? 
                    <div className='container-fluid'>
                        <textarea className='w-100' style={{height: '100px'}} value={props.updateContent} onChange={props.postContentChanged} />
                        <div className='d-flex justify-content-end'>
                            <button type='button' className='btn btn-secondary mx-1' onClick={() => {toggleBeingUpdated(); props.updateButtonClicked(props.post.id)}}>Update</button>
                            <button type='button' className='btn mx-1' onClick={toggleBeingUpdated}>Cancel</button>
                        </div>
                    </div> 
                    : <label className='my-1'>{props.post.content}</label>}
                </div>
                <hr></hr>
                <div className='row mb-1'>
                    <div className='col d-flex justify-content-start'>
                        <label>{props.post.points} {Math.abs(props.post.points) === 1 ? 'like' : 'likes'}</label>
                    </div>
                    <div className='col d-flex justify-content-end me-1'>
                        <button onClick={toggleComments} type="button" className="btn btn-link">
                            <label className='small'>{commentsShown ? 'Hide' : 'Display'} comments</label>
                        </button>
                    </div>
                </div>
                <div className='row my-1'>
                    <div className='col-8 d-flex justify-content-start'>
                        <button type='button' className={props.post.myPoints === 1? 'btn btn-secondary me-2' : 'btn me-2'} 
                            onClick={() => {
                                props.post.myPoints === 1 ? props.removeReaction(props.post.id) : props.likePost(props.post.id);
                            }}>
                            <FontAwesomeIcon icon={faThumbsUp}></FontAwesomeIcon> like
                        </button>
                        <button type='button' className={props.post.myPoints === -1? 'btn btn-secondary me-2' : 'btn me-2'} 
                            onClick={() => {
                                props.post.myPoints === -1? props.removeReaction(props.post.id) : props.dislikePost(props.post.id);
                            }}>
                            <FontAwesomeIcon icon={faThumbsDown}></FontAwesomeIcon> Dislike
                        </button>
                    </div>

                    <div className='col d-flex flex-column'>
                        <button type='button' className='btn' onClick={toggleCommentBeingCreated}>Comment</button>
                    </div>
                </div>
            </div>
        </div>
        <div>
            {commentBeingCreated && <CreateComment postButtonClicked={createComment} content={newCommentContent} commentContentChanged={updateNewCommentContent} />}
        </div>
        <div className='d-flex justify-content-center'>
            {commentsShown && <CommentList postId={props.post.id}/>}
        </div>
    </div>
  )
}
