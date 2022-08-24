import { IUtility } from "./vacation-house-profile"

export interface IReservation {
    id: number,
    startDate:string,
    endDate: string,
    guests:number,
    price:number,
    utilities: IUtility[],
    duration: number,
}