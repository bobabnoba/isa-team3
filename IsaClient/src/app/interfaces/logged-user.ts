import { IAddress } from "./address"

export interface LoggedUser {
    activated: boolean,
    address:IAddress,
    blocked: boolean,
    email:string,
    firstName:string,
    id: number,
    lastName:string,
    password:string,
    phone:string
}
