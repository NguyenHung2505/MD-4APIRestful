package com.example.qlkhachhangrestful.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orderr")
public class Orderr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createAt;
    private int totalPrice;
    private String customerName;
    private long amount;
    public Orderr() {
    }

    public Orderr(Long id, LocalDateTime createAt, int totalPrice, String customerName, long amount) {
        this.id = id;
        this.createAt = createAt;
        this.totalPrice = totalPrice;
        this.customerName = customerName;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
