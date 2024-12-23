package com.JavaCode.Payment.dto;


public class OrderDto {

    private String orderId;
    private String productName;
    private int quantity;
    private double price;
    private String customerName;
    private String shippingAddress;
    private String email;
    private OrderStatus status;

    @Override
    public String toString() {
        return "OrderDto{" +
            "orderId='" + orderId + '\'' +
            ", productName='" + productName + '\'' +
            ", quantity=" + quantity +
            ", price=" + price +
            ", customerName='" + customerName + '\'' +
            ", shippingAddress='" + shippingAddress + '\'' +
            ", email='" + email + '\'' +
            ", status='" + status + '\'' +
            '}';
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
