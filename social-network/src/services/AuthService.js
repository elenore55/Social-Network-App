import api from "../api";

const login = (loginData) => {
    return api.post('auth/login', loginData);
}

const register = (userData) => {
    return api.post('auth/register', {...userData, profilePicture: 'default_avatar.png', dateOfBirth: convertDate(userData.dateOfBirth)});
}

const changePassword = (passwordData) => {
    return api.post('auth/changePassword', passwordData, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
}

const convertDate = (dateStr) => {
    const tokens = dateStr.split('-');
    return `${tokens[2]}/${tokens[1]}/${tokens[0]}`;
}

const test = () => {
    return api.get('authentication/test');
}

const AuthService = {login, register, changePassword, test};

export default AuthService;