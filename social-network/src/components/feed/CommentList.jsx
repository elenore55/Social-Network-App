import React, { useEffect, useState } from 'react'
import Comment from './Comment';
import CommentListService from "../../services/CommentListService";
import { toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';

export default function CommentList(props) {

    const [comments, setComments] = useState([]);
    const [updatedCommentContent, setUpdatedCommentContent] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        fetchComments();
    }, []);

    const updateExistingCommentContent = (event) => {
      setUpdatedCommentContent(event.target.value);
    }

	const updateComment = (id) => {
		let updatedComment = {...comments.filter(x => x.id === id)[0], content: updatedCommentContent};
        CommentListService.updateComment(updatedComment)
        .then(res => {
            setUpdatedCommentContent('');
            setComments(prev => [res['data']].concat(prev.filter(x => x.id !== id)));
        })
        .catch(error => {
            if (error.response.status === 400)
                toast.error(error.response.data.message);
            else if (error.response.status === 401)
                navigate('/unauth');
            else 
                toast('Something went wrong');
        });
	}

	const deleteComment = id => {
        CommentListService.deleteComment(id)
        .then(_res => {
            setComments(prev => prev.filter(x => x.id !== id));
        })
        .catch(error => {
            if (error.response.status === 401)
                navigate('/unauth');
            else 
                toast('Something went wrong');
        });
    }

	const setInitialUpdatedCommentContent = content => {
        setUpdatedCommentContent(content);
    }

    const fetchComments = () => {
        CommentListService.fetchComments(props.postId)
        .then(res => {
            setComments(res['data']);
        })
        .catch(error => {
            if (error.response.status === 401)
                navigate('/unauth');
            else 
                toast('Something went wrong');
        });
    }

  return (
    <div className='w-50 px-2'>
        {comments.map(x => <Comment key={x.id} comment={x} updateButtonClicked={updateComment} commentContentChanged={updateExistingCommentContent} deleteButtonClicked={deleteComment}
            updateContent={updatedCommentContent} setUpdatedCommentContent={setInitialUpdatedCommentContent} myComment={x.user.id == localStorage.getItem('id')} />)}
    </div>
  )
}
