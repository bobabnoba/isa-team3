import { IUtility } from "./vacation-house-profile"

export interface INewReservation {
    id: number,
    startDate:Date,
    endDate: Date,
    guests:number,
    price:number,
    utilities: IUtility[],
    duration: number,
}