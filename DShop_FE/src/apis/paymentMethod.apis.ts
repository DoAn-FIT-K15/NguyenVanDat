import { API_URL } from "~/constants/utils";

const paymentMethodApi = {
  createVNPay : (id) => API_URL + `/api/vnpay/create?orderId=${id}`,
  getResultVnPay: () => API_URL + `/api/vnpay/result`,
}
export default paymentMethodApi;