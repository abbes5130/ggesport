/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
/**
 *
 * @author mohamedabbes
 */
public interface IService<T> {
    public void create(T t);
    public void delete(int t);
    public void update(T t);
   String Retrieve();
}
