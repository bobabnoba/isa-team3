import { IAddress } from "./address";
import { IOwnerInfo } from "./owner-info";
import {  IUtility } from "./vacation-house-profile";

export interface IProfileView {
    id:number;
    name: string,
    duration:number,
    address: IAddress,
    description: string,
    utilities: IUtility[],
    pricePerDay: number,
    rating: number,
    rentalType: string,
    owner: IOwnerInfo
}

