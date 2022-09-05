import { IAddress } from "./address";

export interface INewUser {
  password: string;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  address: IAddress;
  
}
