/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author ridha
 */
public class Role {
    private int id_role;
    private String rolename;

    public Role() {
    }

    public Role(String rolename) {
        this.rolename = rolename;
    }

    public Role(int id_role, String rolename) {
        this.id_role = id_role;
        this.rolename = rolename;
    }

    public int getId_role() {
        return id_role;
    }

    public String getRolename() {
        return rolename;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "Role{" + "id_role=" + id_role + ", role_name=" + rolename + '}';
    }
   

    
    

   
    
    
    
}
