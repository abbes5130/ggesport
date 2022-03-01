/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author Firas
 */
public interface IService <T>{
     void add(T obj);
     void delete(T obj);
     void update(T obj);
     List<T> getAll();
}
