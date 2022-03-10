/*
<<<<<<< HEAD
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

=======
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Team;
>>>>>>> origin/Gestion_team
import java.util.List;

/**
 *
<<<<<<< HEAD
 * @author Ghassene
 */
public interface IService<T> {
    
    public void Create(T t);
    public void Update(T t);
    public void Delete(T t);
    public List<T> Retrieve ();
}
=======
 * @author mohamedabbes
 */
public interface IService<T> {
    public void create(T t);
    public void delete(int t);
    public void update(T t);
   public List<?> Retrieve();
}
>>>>>>> origin/Gestion_team
