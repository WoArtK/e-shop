/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Viewer.viewer;
import Model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author aggelos
 */
public class db_Customer {
//    static int upperbound = 1000;

    public static void InsertCustomer(Customer customer) throws SQLException {
        String insertQuery = "INSERT INTO customer ( firstname, lastname , afm , telephone)"
                + "VALUES(?,?,?,?)";
        PreparedStatement preparedStmt = viewer.conn.prepareStatement(insertQuery);
        preparedStmt.setString(1, customer.getFirstName());
        preparedStmt.setString(2, customer.getLastName());
        preparedStmt.setString(3, customer.getAfm());
        preparedStmt.setString(4, customer.getTelephone());
        
        System.out.println("Record inserted successfully "+preparedStmt.executeUpdate());
    }

    public static void deleteCustomer(int customerID) throws SQLException {
        //DELETE FROM table_name WHERE condition;
        String deleteQuery = "DELETE FROM customer WHERE idCustomer= ?";
        PreparedStatement preparedStmt = viewer.conn.prepareStatement(deleteQuery);
        preparedStmt.setInt(1, customerID);
        preparedStmt.execute();

    }

    public static Customer selectCustomer(int customerID) throws SQLException {
        String selectQuery = "SELECT * FROM customer WHERE idCustomer=" + customerID;
        Statement st = viewer.conn.createStatement();
        ResultSet rs = st.executeQuery(selectQuery);
        Customer to_return = new Customer();
        while (rs.next()) {
            if (rs.getInt("idCustomer") == customerID) {
                to_return.setIdcustomer(rs.getInt("idCustomer"));
                to_return.setFirstName(rs.getString("firstname"));
                to_return.setLastName(rs.getString("lastname"));
                to_return.setAfm(rs.getString("afm"));
                to_return.setTelephone(rs.getString("telephone"));
                break;
            }

        }
        return to_return;
    }

    public  static ArrayList<Customer> selectCustomers() throws SQLException {
        ArrayList<Customer> customers = new ArrayList();
        String selectQuery = "SELECT * FROM customer";
        Statement st = viewer.conn.createStatement();
        ResultSet rs = st.executeQuery(selectQuery);
        while (rs.next()) {
            Customer cust = new Customer();
            cust.setIdcustomer(rs.getInt("idCustomer"));
            cust.setFirstName(rs.getString("firstname"));
            cust.setLastName(rs.getString("lastname"));
            cust.setAfm(rs.getString("afm"));
            cust.setTelephone(rs.getString("telephone"));

            customers.add(cust);

        }
        return customers;
    }

    public static void updateCustomer(Customer customer) throws SQLException {
        String updateQuery = "update customer set lastname=? , firstname=? , afm=? ,telephone=? where idCustomer=?";
        PreparedStatement preparedStmt =viewer.conn.prepareStatement(updateQuery);
//            preparedStmt.setString(1, column);
        preparedStmt.setString(1, customer.getLastName());
        preparedStmt.setString(2, customer.getFirstName());
        preparedStmt.setString(3, customer.getAfm());
        preparedStmt.setString(4, customer.getTelephone());
        preparedStmt.setInt(5, customer.getIdcustomer());
        preparedStmt.executeUpdate();
    }     
    
    
    
    public static Customer getfirstCustomer() throws SQLException {
        ArrayList<Customer> customers = new ArrayList();
        String selectQuery = "SELECT * FROM customer";
        Statement st = viewer.conn.createStatement();
        ResultSet rs = st.executeQuery(selectQuery);
        while (rs.next()) {
            Customer cust = new Customer();
            cust.setIdcustomer(rs.getInt("idCustomer"));
            cust.setFirstName(rs.getString("firstname"));
            cust.setLastName(rs.getString("lastname"));
            cust.setAfm(rs.getString("afm"));
            cust.setTelephone(rs.getString("telephone"));

            customers.add(cust);
            break;
        }
        
        if(customers.get(0)!=null){
            return customers.get(0);
        }
        else{
            return null;
        }
        
    }
    
    
    
    
    

}
