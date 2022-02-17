/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.logging.Logger;

/**
 *
 * @author ridha
 */
public class Role {
    private int Id_role;
    private String Nom_role;

    public Role() {
    }

    public Role(String Nom_role) {
        this.Nom_role = Nom_role;
    }
    

    public Role(int Id_role, String Nom_role) {
        this.Id_role = Id_role;
        this.Nom_role = Nom_role;
    }

    public int getId_role() {
        return Id_role;
    }

    public String getNom_role() {
        return Nom_role;
    }

    public void setId_role(int Id_role) {
        this.Id_role = Id_role;
    }

    public void setNom_role(String Nom_role) {
        this.Nom_role = Nom_role;
    }

    @Override
    public String toString() {
        return "Role{" + "Id_role=" + Id_role + ", Nom_role=" + Nom_role + '}';
    }
    
    
    
    
}
