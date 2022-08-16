import { IAddress } from "./address";

export interface RegisterOwner {
  password: string;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  address: IAddress;
  city: string;
  country: string;
  motivation: string;
  registrationType: String;
}
