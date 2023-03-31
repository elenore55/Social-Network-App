import api from "../api";

const fetchUserData = () => {
    return api.get(`users/me/profile`, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
}

const MyProfileService = {fetchUserData};

export default MyProfileService;