/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.List;

/**
 *
 * @author Ghassene
 */
public interface IService<T> {
    
    public void Create(T t);
    public void Update(T t);
    public void Delete(T t);
    public List<T> Retrieve ();
}