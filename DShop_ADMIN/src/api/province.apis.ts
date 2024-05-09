import provinceRequest from "~/constants/provinceRequest";


const provinceApi = {
  cityApi: () => provinceRequest.get('/api/province'),
  districtApi: (cityId) => provinceRequest.get(`/api/province/district/${cityId} `),
  wardApi: (districtId) => provinceRequest.get(`/api/province/ward/${districtId}`),
} 
export default provinceApi;