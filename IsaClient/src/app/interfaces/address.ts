export interface IAddress{
    id: number ,
    country: string,
    city: string,
    street: string,
    zipCode: number,
}
export interface IAddressMap{
    id: number ,
    country: string,
    city: string,
    street: string,
    zipCode: number,
    latitude: number,
    longitude: number,
}

export class IAddressClass implements IAddressMap{
    id: number = 0;
    country: string = '';
    city: string    = '';
    street: string  = '';
    zipCode: number = 0;
    longitude: number   = 19.833549;
    latitude: number    = 45.267136;
}