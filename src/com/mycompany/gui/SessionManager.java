/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author Lenovo
 */
public class SessionManager {
    
    public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id_user ; 
   private static String firstname;
   private static String lastname;
    private static String email; 
    private static String passowrd ;
    private static int phone_number;
    

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id_user",id_user);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id_user) {
        pref.set("id_user",id_user);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    

    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }

    public static String getPassowrd() {
        return pref.get("passowrd",passowrd);
    }

    public static void setPassowrd(String passowrd) {
         pref.set("passowrd",passowrd);
    }

    public static String getFirstname() {
        return pref.get("firstname",firstname) ;
    }

    public static void setFirstname(String firstname) {
       pref.set("firstname",firstname);
    }

    public static String getLastname() {
        return pref.get("lastname",lastname);
    }

    public static void setLastname(String lastname) {
        pref.set("lastname",lastname);
    }

    public static int getPhone_number() {
       return pref.get("phone_number",phone_number);
    }

    public static void setPhone_number(int phone_number) {
        pref.set("phone_number",phone_number);
    }

    

   
    
    
    
}