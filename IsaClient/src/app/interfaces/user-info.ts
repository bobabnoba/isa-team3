import { UserRank } from "./user-rank";

export interface UserInfo {
    id: number,
    firstName: string,
    lastName: string,
    email: string,
    role: string,
    headAdmin : boolean,
    points : number,
    rank : UserRank
}
