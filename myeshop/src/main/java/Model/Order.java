/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author aggelos
 */
public class Order {

    private int orderID;
    private int customerID;
    private int Quantity;
    private int price;
    private int invid;

    public Order() {
    }

    public Order(int orderID) {
        this.orderID = orderID;
    }

    public Order(int orderID, int customerID, int invid, int Quantity, int price) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.invid = invid;
        this.Quantity = Quantity;
        this.price = price;
    }

    public Order( int customerID, int invid, int Quantity, int price) {
        this.customerID = customerID;
        this.invid = invid;
        this.Quantity = Quantity;
        this.price = price;
    }

    public int getInvid() {
        return invid;
    }

    public void setInvid(int invid) {
        this.invid = invid;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", customerID=" + customerID + ", inventoryID=" + invid + ", Quantity=" + Quantity + ", price=" + price + '}';
    }

}
