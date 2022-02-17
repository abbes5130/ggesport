/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Role;
import entities.Users;
import services.RoleServices;
import services.UtilisateursServices;

/**
 *
 * @author ridha
 */
public class Main {
    public static void main(String[] args) {
        UtilisateursServices sp = new UtilisateursServices(); 
        RoleServices kr = new RoleServices();
        
        
        
        
        Role tt = new Role(2, "Responsables");
        //kr.ajouterRole(tt);
        Users trt = new Users(26548965, "Jean", "Paul", "Jeanpaul@gmail.com","test2");
        Users trrr = new Users(1, 58249569, 0, "Boulares", "mohamedridha", "mohmamedridha.boulares@esprit.tn", "Test");
     sp.find();
       //kr.modifierRole(tt);
       
        
        //sp.find();
        
        
    }
}
