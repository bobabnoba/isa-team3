import { IUtility } from "./vacation-house-profile"

export interface IReservation {
    id: number,
    startDate:Date | string,
    endDate: Date | string,
    guests:number,
    price:number,
    utilities: IUtility[],
    duration: number,
    cancelingPercentage : number
}