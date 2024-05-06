export interface City {
  province_id: number;
  province_name: string;
  province_type: string;
}
export interface District {
  district_id: number;
  district_name: string;
  district_type: string;
  province_id: number;
}
export interface Ward {
  district_id: number;
  ward_id: number;
  ward_name: string;
  ward_type: string;
}