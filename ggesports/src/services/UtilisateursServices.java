/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;
import entities.Role;
import entities.Users;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import utils.MyConnexion;

/**
 *
 * @author ridha
 */
public class UtilisateursServices implements UserServices<Users>{

    
    Connection cnx;
    public UtilisateursServices() {
        cnx = MyConnexion.getInstance().getConncetion();
    }

    @Override
    public void ajouter(Users t) {
        
            try {
          
           String query="INSERT INTO users(firstname,lastname,email,password,phone_number,id_role,check_account) values(?,?,?,?,?,?,'Not_Blocked')";
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setString(1, t.getFirstname());
                smt.setString(2, t.getLastname());
                smt.setString(3, t.getEmail());
                smt.setString(4, encrypt(t.getPassword()));
                smt.setInt(5, t.getPhone_number());
                smt.setInt(6, t.getId_role());
                smt.executeUpdate();
                System.out.println("ajout avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       } catch (Exception ex) {
            Logger.getLogger(UtilisateursServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public void Sign_in(Users t) {
        
            try {
                
           String query="INSERT INTO users(firstname,lastname,email,password,phone_number,id_role,check_account) values(?,?,?,?,?,3,'Not_Blocked')";
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setString(1, t.getFirstname());
                smt.setString(2, t.getLastname());
                smt.setString(3, t.getEmail());
                smt.setString(4, encrypt(t.getPassword()));
                smt.setInt(5, t.getPhone_number());
               
                smt.executeUpdate();
                System.out.println("ajout avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       } catch (Exception ex) {
            Logger.getLogger(UtilisateursServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void modifier(Users t) {
         try {
       String query2="update users set firstname=?, lastname=?, email=?, password=?,phone_number=? where id_user=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                
                
                smt.setString(1, t.getFirstname());
                smt.setString(2, t.getLastname());
                smt.setString(3, t.getEmail());
                smt.setString(4, encrypt(t.getPassword()));
                smt.setInt(5, t.getPhone_number());
                smt.setInt(6, t.getId_user());
                smt.executeUpdate();
                System.out.println("modification avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }   catch (Exception ex) {
            Logger.getLogger(UtilisateursServices.class.getName()).log(Level.SEVERE, null, ex);
        }}

    @Override
    public void supprimer(Users t) {
        
     try {
       String query2="delete from users where id_user=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                smt.setInt(1, t.getId_user());
                smt.executeUpdate();
                System.out.println("suppression avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}

    @Override
    public List<Users> find() {
    
        ObservableList<Users> List = FXCollections.observableArrayList();
        try {
       String query2="select * from users join role on users.id_role=role.id_role";
                PreparedStatement smt = cnx.prepareStatement(query2);
                Users p;
               Role r;
               
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                   p=new Users(rs.getInt("id_user"),rs.getInt("phone_number"),rs.getInt("id_role"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"),rs.getString("password"),rs.getString("check_account"));
                   r=new Role(rs.getString("rolename"));
                  // p.current_user=p;
                  p.role = r;
                    
                   List.add(p);
                   //l.add(r);
                }
                System.out.println(List);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }

        return List;
    
}
    
    @Override
    
    public void userLoign(Users t){
        
    }
    public String hashagePWD(String pwd){
        String hashPWD="";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pwd.getBytes());
           
            byte byteData[] = md.digest();
           
            //convertir le tableau de bits en une format hexadécimal
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashPWD = sb.toString();
           
            //System.out.println("En format hexa : " + sb.toString());
           
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UtilisateursServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hashPWD;
    }
    private static final String ALGORITHM = "AES";
private static final byte[] keyValue = 
    new byte[] { 'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

 public String encrypt(String valueToEnc) throws Exception {
    Key key = generateKey();
    Cipher c = Cipher.getInstance(ALGORITHM);
    c.init(Cipher.ENCRYPT_MODE, key);
    byte[] encValue = c.doFinal(valueToEnc.getBytes());
    String encryptedValue = new BASE64Encoder().encode(encValue);
    return encryptedValue;
}

public String decrypt(String encryptedValue) throws Exception {
    Key key = generateKey();
    Cipher c = Cipher.getInstance(ALGORITHM);
    c.init(Cipher.DECRYPT_MODE, key);
    byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedValue);
    byte[] decValue = c.doFinal(decordedValue);
    String decryptedValue = new String(decValue);
    return decryptedValue;
}

private static Key generateKey() throws Exception {
    Key key = new SecretKeySpec(keyValue, ALGORITHM);
    return key;
}
public void blocker (Users t) {
    
    try {
       String query2="update users set check_account= 'Blocked' where id_user=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                smt.setInt(1, t.getId_user());
                smt.executeUpdate();
                System.out.println("Blockage avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    } 
       
}
public void deblocker (Users t) {
    
    try {
       String query2="update users set check_account= 'Not_Blocked' where id_user=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                smt.setInt(1, t.getId_user());
                smt.executeUpdate();
                System.out.println("Deblockage avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    } 
       
}
public long calculStat() {

        List<Users> vehicule = find();
        return vehicule.stream().filter(b -> b.role.getRolename().equals("Administrateur")).count();
    }
public long calculStat1() {

        List<Users> vehicule = find();
        return vehicule.stream().filter(b -> b.role.getRolename().equals("Responsables")).count();
    }
public long calculStat2() {

        List<Users> vehicule = find();
        return vehicule.stream().filter(b -> b.role.getRolename().equals("Membre")).count();
    }
}