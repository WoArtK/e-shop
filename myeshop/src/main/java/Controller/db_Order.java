/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Viewer.viewer;
import Model.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author aggelos
 */
public class db_Order {

    public  static void InsertOrder(Order order) throws SQLException {
        String insertQuery = "INSERT INTO orders (custid, invid , quantity , price)"
                + "VALUES(?,?,?,?)";
        PreparedStatement preparedStmt = viewer.conn.prepareStatement(insertQuery);
        preparedStmt.setInt(1, order.getCustomerID());
        preparedStmt.setInt(2, order.getInvid());
        preparedStmt.setInt(3, order.getQuantity());
        preparedStmt.setInt(4, order.getPrice());
        System.out.println("--> "+preparedStmt.executeUpdate());
        System.out.println("Record inserted successfully");
    }

    public static void deleteOrderWithCustomerID(int customerID) throws SQLException{
        ArrayList<Order> orders=selectOrders();
        for(Order order:orders){
            if(order.getCustomerID()==customerID){
                deleteOrder(order.getOrderID());
            }
        }
    }
    
    public static void deleteOrderWithInventoryID(int invertoryID) throws SQLException{
        ArrayList<Order> orders=selectOrders();
        for(Order order:orders){
            if(order.getInvid()==invertoryID){
                deleteOrder(order.getOrderID());
            }
        }
    }
    public  static void deleteOrder(int orderID) throws SQLException {
        //DELETE FROM table_name WHERE condition;
        String deleteQuery = "DELETE FROM orders WHERE idOrder= ?";
        PreparedStatement preparedStmt = viewer.conn.prepareStatement(deleteQuery);
        preparedStmt.setInt(1, orderID);
        preparedStmt.execute();

    }

    public static  Order selectOrder(int orderID) throws SQLException {
        String selectQuery = "SELECT * FROM orders WHERE idOrder=" + orderID;
        Statement st = viewer.conn.createStatement();
        ResultSet rs = st.executeQuery(selectQuery);
        Order to_return = new Order();
        while (rs.next()) {
            if (rs.getInt("idOrder") == orderID) {
                to_return.setOrderID(rs.getInt("idOrder"));
                to_return.setInvid(rs.getInt("invid"));
                to_return.setCustomerID(rs.getInt("custid"));
                to_return.setQuantity(rs.getInt("quantity"));
                to_return.setPrice(rs.getInt("price"));
                break;
            }

        }
        return to_return;
    }

    public static ArrayList<Order> selectOrders() throws SQLException {
        ArrayList orderslist = new ArrayList();
        String selectQuery = "SELECT * FROM orders";
        Statement st = viewer.conn.createStatement();
        ResultSet rs = st.executeQuery(selectQuery);
        while (rs.next()) {
            Order order = new Order();
            order.setOrderID(rs.getInt("idOrder"));
            order.setInvid(rs.getInt("invid"));
            order.setCustomerID(rs.getInt("custid"));
            order.setQuantity(rs.getInt("quantity"));
            order.setPrice(rs.getInt("price"));

            orderslist.add(order);

        }
        return orderslist;
    }

    public static void updateOrder(int orderID, String column, String value) throws SQLException {

        String updateQuery = "update orders set " + column + "=? where idOrder=?";
        PreparedStatement preparedStmt = viewer.conn.prepareStatement(updateQuery);
//            preparedStmt.setString(1, column);
        preparedStmt.setString(1, value);
        preparedStmt.setInt(2, orderID);
        preparedStmt.executeUpdate();
    }
}
