export interface User {
  id: number;
  username: string;
  password: string;
  fullName: string;
  email: string;
  phone: string;
  image: string;
  createdDate: string;
  modifiedDate: string;
  roles: {
    name: string;
  }[];
  status: number;
  addresses: Address[];
}
export interface Address {
  addressDetail: string;
  createdDate: string;
  district: string;
  fullName: string;
  focus: number;
  id: number;
  modifiedDate: string;
  phone: string;
  province: string;
  status: number;
  wards: string;
}
