import { IUtility } from "./vacation-house-profile"

export interface IReservation {
    id: number,
    startDate:Date,
    endDate: Date,
    guests:number,
    price:number,
    utilities: IUtility[],
    duration: number,
}