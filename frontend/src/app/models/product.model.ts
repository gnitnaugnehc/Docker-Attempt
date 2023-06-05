import { Tag } from "./tag.model";

export class Product {
  id: string = '';
  name: string = '';
  description: string = '';
  price: number = 0;
  createdAt: string = '';
  tags: Tag[] = [];
}
