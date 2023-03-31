import api from "../api";

const like = id => {
    return api.post(`reactions/like/${id}`, {}, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
};


const dislike = id => {
    return api.post(`reactions/dislike/${id}`, {}, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    })
};

const removeReaction = id => {
    return api.delete(`reactions/${id}`, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
}

const fetchPosts = path  => {
    return api.get(path, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
}

const createNewPost = content => {
    return api.post('posts', {content: content}, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
}
   
const deletePost = id => {
    return api.delete(`posts/${id}`, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
}

const updatePost = (post) => {
    return api.patch(`posts`, post, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
}

const PostListService = {like, dislike, removeReaction, fetchPosts, createNewPost, deletePost, updatePost}

export default PostListService;