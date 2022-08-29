import { IAddress } from "./address"
import { IAvailability } from "./availability"

export interface IInstructorInfo {
    availability: IAvailability[],
    instructor: {
        id: number,
        address: IAddress
        biography: string,
        email: string,
        firstName: string,
        lastName: string,
        rating: number
    }
}

