import { IAddress } from "./address"


export interface IVacationHouseProfile {
    id: number,
    name: string,
    pricePerDay:number,
    address: IAddress,
    description: string,
    rating: number,
    guestLimit: 5,
    images: string[],
    rooms: IRoom[],
    availableReservations: IAvailableReservations[],
    codeOfConduct: string[],
    utilities: IUtility[],
    vacationHomeOwner: IOwnerInfo
}
export interface IOwnerInfo {
    firstName: string,
    lastName: string,
    email: string,
    address: string,
    phone: string
}
export interface IAvailableReservations {
    startDate: Date,
    endDate: Date,
    maxGuests: number,
    price: number

}
export interface IUtility {
    name: string,
    price: number
}
export interface IRoom {
    numberOfBeds: number
}

