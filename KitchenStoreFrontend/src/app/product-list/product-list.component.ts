import {Component, OnInit} from '@angular/core';
import {Product} from '../product';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  product: Product = {
    created: 1,
    description: 'some nice milk',
    expiry: 2,
    id: 34,
    name: 'Milk',
    quantity: 2,
  };

  products: Product[] = [this.product];

  constructor() {
  }

  ngOnInit(): void {
    // todo use a productService here to grab products from the backend
  }

}
