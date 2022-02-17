/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Role;
import entities.Utilisateurs;
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
        Role tt = new Role("Responsable");
        //kr.ajouterRole(tt);
        Utilisateurs trrr = new Utilisateurs(12456321, 1, "Boulares", "Ridha", "boulares.ridha@gmail.com", "Facebook1");
        //sp.ajouter(trrr);
       kr.ajouterRole(tt);
        
        //sp.find();
        
        
    }
}
