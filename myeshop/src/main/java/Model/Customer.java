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
public class Customer {

    private int idcustomer;

    public int getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(int idcustomer) {
        this.idcustomer = idcustomer;
    }
    private String lastName;
    private String firstName;
    private String afm;
    private String telephone;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastNmae) {
        this.lastName = lastNmae;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Customer(String lastName, String firstName, String afm, String telephone, int idcustomer) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.afm = afm;
        this.telephone = telephone;
        this.idcustomer = idcustomer;
    }

    public Customer(String lastName, String firstName, String afm, String telephone) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.afm = afm;
        this.telephone = telephone;
    }

    public Customer(int idcustomer) {
        this.idcustomer = idcustomer;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" + "idcustomer=" + idcustomer + ", lastName=" + lastName + ", firstName=" + firstName + ", afm=" + afm + ", telephone=" + telephone + '}';
    }

}
