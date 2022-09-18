/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Viewer.viewer;
import Model.Customer;
import Model.Inventory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author aggelos
 */
public class db_Inventory {

    public static void InsertInventory(Inventory inventory) throws SQLException {
        String insertQuery = "INSERT INTO inventory ( category, description , price , quantity)"
                + "VALUES(?,?,?,?)";
        PreparedStatement preparedStmt = viewer.conn.prepareStatement(insertQuery);
        preparedStmt.setString(1, inventory.getCategory());
        preparedStmt.setString(2, inventory.getProductName());
        preparedStmt.setInt(3, inventory.getPrice());
        preparedStmt.setInt(4, inventory.getQuantity());
        preparedStmt.executeUpdate();
        System.out.println("Record inserted successfully");
    }

    public static void deleteInventory(int inventoryID) throws SQLException {
        //DELETE FROM table_name WHERE condition;
        String deleteQuery = "DELETE FROM inventory WHERE idinv= ?";
        PreparedStatement preparedStmt = viewer.conn.prepareStatement(deleteQuery);
        preparedStmt.setInt(1, inventoryID);
        preparedStmt.execute();

    }

    public static Inventory selectInventory(int InventoryID) throws SQLException {
        String selectQuery = "SELECT * FROM inventory WHERE idinv=" + InventoryID;
        Statement st = viewer.conn.createStatement();
        ResultSet rs = st.executeQuery(selectQuery);
        Inventory to_return = new Inventory();
        while (rs.next()) {
            if (rs.getInt("idinv") == InventoryID) {
                to_return.setIdinv(rs.getInt("idinv"));
                to_return.setCategory(rs.getString("category"));
                to_return.setProductName(rs.getString("description"));
                to_return.setPrice(rs.getInt("price"));
                to_return.setQuantity(rs.getInt("quantity"));
                break;
            }

        }
        return to_return;
    }

    public static ArrayList<Inventory> selectInventories() throws SQLException {
        ArrayList<Inventory> inventories = new ArrayList();
        String selectQuery = "SELECT * FROM inventory";
        Statement st = viewer.conn.createStatement();
        ResultSet rs = st.executeQuery(selectQuery);
        while (rs.next()) {
            Inventory inventory = new Inventory();
            inventory.setIdinv(rs.getInt("idinv"));
            inventory.setCategory(rs.getString("category"));
            inventory.setProductName(rs.getString("description"));
            inventory.setPrice(rs.getInt("price"));
            inventory.setQuantity(rs.getInt("quantity"));

            inventories.add(inventory);

        }
        return inventories;
    }

    public static void updateInventory(Inventory inventory) throws SQLException {
   
        String updateQuery = "update inventory set category=? , description = ? , price =? , quantity = ? where idinv=?";
        PreparedStatement preparedStmt =viewer.conn.prepareStatement(updateQuery);
        preparedStmt.setString(1, inventory.getCategory());
        preparedStmt.setString(2, inventory.getProductName());
        preparedStmt.setInt(3, inventory.getPrice());
        preparedStmt.setInt(4, inventory.getQuantity());
        preparedStmt.setInt(5, inventory.getIdinv());
        preparedStmt.executeUpdate();
    }

    
    public static Inventory getfirstInventory() throws SQLException {
        ArrayList<Inventory> inventories = new ArrayList();
        String selectQuery = "SELECT * FROM inventory";
        Statement st = viewer.conn.createStatement();
        ResultSet rs = st.executeQuery(selectQuery);
        while (rs.next()) {
           Inventory inventory = new Inventory();
            inventory.setIdinv(rs.getInt("idinv"));
            inventory.setCategory(rs.getString("category"));
            inventory.setProductName(rs.getString("description"));
            inventory.setPrice(rs.getInt("price"));
            inventory.setQuantity(rs.getInt("quantity"));
            inventories.add(inventory);
            break;
        }
        
        if(inventories.get(0)!=null){
            return inventories.get(0);
        }
        else{
            return null;
        }
        
    }
    
    
    
}
