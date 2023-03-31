import axios from 'axios'

export default function jwtInterceptor() {
    axios.interceptors.request.use(request => {
        if (localStorage.getItem('jwt')) {
            console.log('auth');
            request.headers.common.Authorization = `Bearer ${localStorage.getItem('jwt')}`;
        }
        return request;
    })
}
