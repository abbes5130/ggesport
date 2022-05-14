
package test;


import entities.Category;
import entities.Order;
import entities.Product;
import entities.ProductOrder;
import java.util.ArrayList;
import java.util.List;
import services.CategoryCRUD;
import services.OrderCRUD;
import services.ProductCRUD;
import services.ProductOrderCRUD;



import entities.Match;
import utils.MyDB;

import services.Match_Team_Service;


public class Ggesports {


    public static void main(String[] args) {

//        Category c = new Category(3, "software");
//        Category c1= new Category(2, "accessories");
//        CategoryCRUD ccrud = new CategoryCRUD();
//        int i=ccrud.getIdCategoryByName("Pants");
//        System.out.println(i);
//        ccrud.add(c);
//        ccrud.update(c1);
//        ccrud.delete(c1);
//        System.out.println(ccrud.getAll());
//
//            java.util.Date date=new java.util.Date();
//            java.sql.Date sqlDate=new java.sql.Date(date.getTime());
//            java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
//
//            
//          Order o = new Order(1,sqlDate,sqlTime,true);
//          Order o1 = new Order(1,sqlDate,sqlTime,true);
//          OrderCRUD ord = new OrderCRUD();
//          ord.add(o);         
//          ord.update(o1);
//          ord.delete(o1);
//          System.out.println(ord.getAll());
//          
//          Product p = new Product("capuche", 130.990f, "blue CSGO capuche ", "blue", "CSGO",20,true, 1, 100);
//          Product p1= new Product(3,"capuche", 130.990f, "blue CSGO capuche ", "blue", "CSGO",20,true, 1, 100);
            Product p= new Product();
          ProductCRUD prd= new ProductCRUD();
          List<Product> resultat=new ArrayList();
          resultat=prd.getProductsByPrice(60, 120);
          System.out.println(resultat);
          
          
//          Product p= prd.getProductById(1);
//          System.out.println(p);
//          prd.add(p);
//          prd.update(p1);
//          prd.delete(p1);
//          System.out.println(prd.getAll());
////          
          
//          ProductOrder po = new ProductOrder(1,1,5);
//          ProductOrder po1= new ProductOrder(1,1,10);
//          ProductOrderCRUD pord = new ProductOrderCRUD();
//          pord.add(po);
//          pord.update(po1);
//          pord.delete(po1);
//          System.out.println(pord.getAll());
          



        MyDB db = MyDB.getInstance();
        Match match = new Match();
        Match_Team_Service mts = new Match_Team_Service();
        
        mts.Update(3,2,1);
        System.out.println("reservation added");

    }

    /**
     *
     * @param args
     */

    
    
}
