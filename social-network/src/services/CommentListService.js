import api from "../api";

const fetchComments = id => {
    return api.get(`comments/${id}`, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
}

const createNewComment = (content, postId) => {
    return api.post('comments', {content: content, postId: postId}, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
}

const updateComment = (comment) => {
    return api.patch(`comments`, comment, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
}

const deleteComment = (id) => {
    return api.delete(`comments/${id}`, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
}

const CommentsListService = {
    fetchComments, createNewComment, updateComment, deleteComment
}

export default CommentsListService;