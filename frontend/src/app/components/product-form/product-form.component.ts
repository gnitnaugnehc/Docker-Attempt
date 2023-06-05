import { Component, OnInit, Input } from '@angular/core';
import { Product } from 'src/app/models/product.model';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss'],
})
export class ProductFormComponent implements OnInit {
  @Input() product: Product = new Product();

  tagsString = '';

  constructor(private productService: ProductService) {}

  ngOnInit() {
    if (this.product) {
      this.tagsString = this.product.tags.join(',');
    }
  }

  submitForm() {
    const tags = this.tagsString.split(',');

    if (this.product.id) {
      this.productService
        .updateProduct(
          this.product.id,
          this.product.name,
          this.product.description,
          this.product.price,
          tags
        )
        .subscribe({
          next: (response) => {
            console.log('Product updated:', response);
            this.resetForm();
          },
          error: (error) => {
            console.error('Error updating product:', error);
          },
        });
    } else {
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
  }

  private resetForm() {
    this.product = new Product();
    this.tagsString = '';
  }
}
