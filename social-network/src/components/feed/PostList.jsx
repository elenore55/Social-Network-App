import React, { useEffect, useState } from 'react'
import Post from '../feed/Post';
import { useNavigate } from 'react-router-dom';
import CreatePost from './CreatePost'
import PostListService from '../../services/PostListService';
import { toast } from 'react-toastify'

export default function PostList(props) {
    const [posts, setPosts] = useState([]);
    const [newPostContent, setNewPostContent] = useState('');
    const [updatedPostContent, setUpdatedPostContent] = useState('');    
    const navigate = useNavigate();

    useEffect(() => {  
        fetchPosts();
        console.log(JSON.stringify(posts));
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    const likePost = id => {
        PostListService.like(id)
        .then(_res => {
            const updatedPosts = posts.map(p => p.id === id ? {...p, myPoints: 1, points: p.points + 1 - p.myPoints} : p);
            setPosts(updatedPosts);
        })
        .catch(error => {
            if (error.response.status === 401)
                navigate('/unauth');
            else
                toast.error('Something went wrong: unable to like post');
        });
    }

    const dislikePost = id => {
        PostListService.dislike(id)
        .then(_res => {
            const updatedPosts = posts.map(p => p.id === id ? {...p, myPoints: -1, points: p.points - 1 - p.myPoints} : p);
            setPosts(updatedPosts);
        })
        .catch(error => {
            if (error.response.status === 401)
                navigate('/unauth');
            else
                toast.error('Something went wrong: unable to dislike post');
        });
    }

    const removeReaction = id => {
        PostListService.removeReaction(id)
        .then(_res => {
            const updatedPosts = posts.map(p => p.id === id ? {...p, myPoints: 0, points: p.points - p.myPoints} : p);
            setPosts(updatedPosts);
        })
        .catch(error => {
            if (error.response.status === 401)
                navigate('/unauth');
            else
                toast.error('Something went wrong: unable to remove reaction');
        });
    }

    const createPost = () => {
        PostListService.createNewPost(newPostContent)
        .then(res => {
            setPosts(prev => [res.data, ...prev]);
            setNewPostContent('');
        })
        .catch(error => {
            if (error.response.status === 400) 
                toast.error(error.response.data.message)
            else if (error.response.status === 401)
                navigate('/unauth');
            else
                toast.error('Something went wrong: unable to create post');
        });
    }

    const updateNewPostContent = (event) => {
        setNewPostContent(event.target.value);
    }
    
    const deletePost = id => {
        PostListService.deletePost(id)
        .then(_res => {
            setPosts(prev => prev.filter(x => x.id !== id));
        })
        .catch(error => {
            if (error.response.status === 401)
                navigate('/unauth');
            else
                toast.error('Something went wrong: unable to delete post');
        });
    }

    const updateExistingPostContent = (event) => {
        setUpdatedPostContent(event.target.value);
    }

    const updatePost = id => {
        let updatedPost = {...posts.filter(x => x.id === id)[0], content: updatedPostContent};
        PostListService.updatePost(updatedPost)
        .then(res => {
            setUpdatedPostContent('');
            setPosts(prev => [res.data].concat(prev.filter(x => x.id !== id)));
        })
        .catch(error => {
            if (error.response.status === 400) 
                toast.error(error.response.data.message)
            else if (error.response.status === 401)
                navigate('/unauth');
            else
                toast.error('Something went wrong: unable to update post');
        });
    }

    const setInitialUpdatedPostContent = content => {
        setUpdatedPostContent(content);
    }

    const fetchPosts = () => {
        PostListService.fetchPosts(props.path)
        .then(res => {
            setPosts(res.data);
        })
        .catch(error => {
            if (error.response.status === 401) 
                navigate('/unauth');
            else
                toast.error('Something went wrong: unable to fetch posts');
        });
    }
    

  return (
    <div>
        {props.myPosts && <CreatePost postButtonClicked={createPost} content={newPostContent} postContentChanged={updateNewPostContent}/>}
        {posts.map(x => <Post key={x.id} post={x} likePost={likePost} dislikePost={dislikePost} removeReaction={removeReaction} 
            myPost={props.myPosts} deleteButtonClicked={deletePost} updateButtonClicked={updatePost} postContentChanged={updateExistingPostContent}
            updateContent={updatedPostContent} setUpdatedPostContent={setInitialUpdatedPostContent} />)}
        {posts.length === 0 && <h3 className='d-flex justify-content-center my-4'>No posts to show</h3>}
    </div>
  )
}
