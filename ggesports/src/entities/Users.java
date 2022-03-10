/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ridha
 */
public class Users {
    private int id_user,phone_number,id_role;
    private String firstname,lastname,email,password;
    public static Users current_user;
    public static Users findpass;
    public Role role;
    public Users() {
        
    }
    public Users(String password){
        this.password = password;
    }

    public Users(int phone_number, int id_role, String firstname, String lastname, String email, String password) {
        this.phone_number = phone_number;
        this.id_role = id_role;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public Users( int phone_number, String firstname, String lastname, String email,String password) {
        
        this.phone_number = phone_number;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        
    }

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
        
    }

    public Users(int id_user, int phone_number, int id_role, String firstname, String lastname, String email, String password) {
        this.id_user = id_user;
        this.phone_number = phone_number;
        this.id_role = id_role;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public int getId_user() {
        return id_user;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public int getId_role() {
        return id_role;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users(int phone_number, String firstname, String lastname, String email) {
        this.phone_number = phone_number;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Users{" + "id_user=" + id_user + ", phone_number=" + phone_number + ", id_role=" + id_role + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password=" + password + ", role="+role.getRolename()+"}";
    }

   

  
    
    
    
    
    
}
