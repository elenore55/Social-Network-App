import api from "../api";

const fetchUserData = () => {
    return api.get(`users/me/profile`, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
}

const saveImage = file => {
    const formData = new FormData();
    formData.append('profilePicture', file);
    return api.patch('users/me/profilePicture', formData, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt'),
            'Content-Type': 'multipart/form-data'
        }
    });
}

const editProfile = (userData, file) => {
    const formData = new FormData();
    formData.append('profilePicture', file);
    formData.append('name', userData.name);
    formData.append('surname', userData.surname);
    formData.append('email', userData.email);
    formData.append('dateOfBirth', convertDate(userData.dateOfBirth));
    return api.patch('users/me/profile', formData, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt'),
            'Content-Type': 'multipart/form-data'
        }
    });
}

const convertDate = (dateStr) => {
    const tokens = dateStr.split('-');
    return `${tokens[2]}/${tokens[1]}/${tokens[0]}`;
}

const EditProfileData = {fetchUserData, editProfile, saveImage};

export default EditProfileData;