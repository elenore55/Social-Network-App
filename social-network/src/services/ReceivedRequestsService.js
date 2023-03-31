import api from "../api";

const fetchRequests = () => {
    return api.get('friends/requests/received', {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
}

const acceptRequest = (id) => {
    return api.post(`/friends/requests/${id}/ACCEPTED`, {}, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
}

const rejectRequest = (id) => {
    return api.post(`/friends/requests/${id}/REJECTED`, {}, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
}

const ReceivedRequestsService = {fetchRequests, acceptRequest, rejectRequest};

export default ReceivedRequestsService;