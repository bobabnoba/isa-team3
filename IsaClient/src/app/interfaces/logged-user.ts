import { IAddress } from "./address"
import { IRank } from "./rank"

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
    biography:string,
    rank: IRank,
    points: number
}
