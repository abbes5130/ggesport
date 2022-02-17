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
public class Utilisateurs {
    private int Id_utilisateurs,Num_tel,Id_role;
    private String Nom,Prenom,Email,Password;

    public Utilisateurs() {
    }

    public Utilisateurs(int Num_tel, int Id_role, String Nom, String Prenom, String Email, String Password) {
        this.Num_tel = Num_tel;
        this.Id_role = Id_role;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
        this.Password = Password;
    }

    public Utilisateurs(int Id_utilisateurs, int Num_tel, String Nom, String Prenom, String Email) {
        this.Id_utilisateurs = Id_utilisateurs;
        this.Num_tel = Num_tel;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
    }

    public Utilisateurs(int Id_utilisateurs, int Num_tel, int Id_role, String Nom, String Prenom, String Email, String Password) {
        this.Id_utilisateurs = Id_utilisateurs;
        this.Num_tel = Num_tel;
        this.Id_role = Id_role;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
        this.Password = Password;
    }

    public int getId_utilisateurs() {
        return Id_utilisateurs;
    }

    public int getNum_tel() {
        return Num_tel;
    }

    public int getId_role() {
        return Id_role;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setId_utilisateurs(int Id_utilisateurs) {
        this.Id_utilisateurs = Id_utilisateurs;
    }

    public void setNum_tel(int Num_tel) {
        this.Num_tel = Num_tel;
    }

    public void setId_role(int Id_role) {
        this.Id_role = Id_role;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    @Override
    public String toString() {
        return "Utilisateurs{" + "Id_utilisateurs=" + Id_utilisateurs + ", Num_tel=" + Num_tel + ", Id_role=" + Id_role + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Email=" + Email + ", Password=" + Password + '}';
    }
    
    
    
    
    
}
