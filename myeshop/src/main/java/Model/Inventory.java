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
public class Inventory {

    private int idinv;
    private String category;
    private String productName;
    private int price;
    private int quantity;

    public Inventory(String category, String productName, int price, int quantity, int idinv) {
        this.idinv = idinv;
        this.category = category;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public Inventory(String category, String productName, int price, int quantity) {
        this.category = category;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public Inventory() {
    }

    public int getIdinv() {
        return idinv;
    }

    public void setIdinv(int idinv) {
        this.idinv = idinv;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Inventory{" + "idinv=" + idinv + ", category=" + category + ", productName=" + productName + ", price=" + price + ", quantity=" + quantity + '}';
    }

}
