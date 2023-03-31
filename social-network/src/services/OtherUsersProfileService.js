import api from "../api"

const fetchUserData = id => {
    return api.get(`users/${id}/profile`, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
}

const sendFriendReq = id => {
    return api.post(`friends/requests/${id}`, {}, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
}

const OtherUsersProfileService = {fetchUserData, sendFriendReq};

export default OtherUsersProfileService;