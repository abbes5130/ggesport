/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author ridha
 */
public interface RServices<T>{
   public void ajouterRole(T t);
    public void modifierRole(T t);
    public void supprimerRole(T t);
    public List<T> findRole(); 
    
}
