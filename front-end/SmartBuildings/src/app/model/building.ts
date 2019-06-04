import { Product } from './product';

export class Building {
    id: string;
    name: string;
    address: string;
    active: Boolean;
    products: Array<Product>;
}