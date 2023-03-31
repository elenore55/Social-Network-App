import api from "../api";

const fetchFriends = () => {
    return api.get('users/friends', {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
}

const FriendsService = {fetchFriends};

export default FriendsService;