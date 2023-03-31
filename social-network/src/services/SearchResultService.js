import api from "../api";

const fetchResults = (term) => {
    if (term) {
        return api.get(`users/search/${String(term)}`, {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('jwt')
            }
        });
    }
    else {
        return api.get(`users/all`, {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('jwt')
            }
        });
    }
}

const SearchResultService = {fetchResults};

export default SearchResultService;