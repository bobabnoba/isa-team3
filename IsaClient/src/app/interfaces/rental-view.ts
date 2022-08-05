export interface IProfileView {
    id:string;
    name: string,
    address: IAddress,
    description: string,
    rating: number,
    rentalType: string,
}

export interface IAddress{
    id: number,
    country: string,
    city: string,
    street: string,
    zipCode: number
}