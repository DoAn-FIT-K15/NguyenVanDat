export interface Category {
  banners: Banner;
  categoryParent: null;
  childCategories: Category[];
  createdDate: string;
  description: string;
  id: number;
  modifiedDate: string;
  status: number;
  title: string;
  type: number;
  urlImage: null;
}
export interface Banner {
  categoryId: number;
  createdDate: string;
  id: number;
  modifiedDate: string;
  name: string;
  src: string;
}
