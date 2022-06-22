package com.example.qlkhachhangrestful.model;

import javax.persistence.*;

@Entity
@Table(name = "orderdetail")
public class OrderDetail {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int quantity;

  @ManyToOne
  private Product product;

  @ManyToOne
//  @JoinColumn(name = "product_orderId")
  private Orderr orderr;

  public OrderDetail() {
  }

  public OrderDetail(Long id, int quantity) {
    this.id = id;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

}
