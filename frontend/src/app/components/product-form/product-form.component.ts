import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product.model';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss'],
})
export class ProductFormComponent implements OnInit {
  product: Product = new Product();

  tagsString = '';

  constructor(private productService: ProductService) {}

  ngOnInit() {}

  submitForm() {
    const tags = this.tagsString.split(',');

    this.productService
      .createProduct(
        this.product.name,
        this.product.description,
        this.product.price,
        tags
      )
      .subscribe({
        next: (response) => {
          console.log('Product created:', response);
          this.resetForm();
        },
        error: (error) => {
          console.error('Error creating product:', error);
        },
      });
  }

  private resetForm() {
    this.product = new Product();
    this.tagsString = '';
  }
}
