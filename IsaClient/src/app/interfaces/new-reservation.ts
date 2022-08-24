import { IUtility } from "./vacation-house-profile"

export interface INewReservation {
    id: number,
    startDate:Date | string,
    endDate: Date | string,
    guests:number,
    price:number,
    utilities: IUtility[],
    duration: number,
}