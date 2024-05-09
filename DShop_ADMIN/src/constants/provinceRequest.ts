import axios from 'axios';

const provinceRequest = axios.create({
    baseURL: 'https://vapi.vnappmob.com',
    headers: {
        'content-type': 'application/json',
    },
});

export default provinceRequest;