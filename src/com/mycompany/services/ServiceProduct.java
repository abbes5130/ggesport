
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;

import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

import com.mycompany.entities.Category;
import com.mycompany.utils.Statics;
import com.mycompany.entities.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceProduct {
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Category> categories= new ArrayList<>();
    
    public static ServiceProduct instance=null;
    public boolean resultOK;
    ConnectionRequest req ;

    private ServiceProduct() {
         req = new ConnectionRequest();
    }

    public static ServiceProduct getInstance() {
        if (instance == null) {
            instance = new ServiceProduct();
        }
        return instance;
    }

    int pageNumber = 1;
    
        public ArrayList<Category> parseCategory(String jsonText) {
        try {
            categories = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> categoriesJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) categoriesJson.get("root");
            for (Map<String, Object> obj : list) {
                Category c = new Category();

                float id = Float.parseFloat(obj.get("idCategory").toString());
                c.setIdCategory((int) id);

                if (obj.get("categoryName") == null) {
                    c.setCategoryName("null");
                } else {
                    c.setCategoryName(obj.get("categoryName").toString());
                }

                categories.add(c);
            }

        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return categories;
    }
    public ArrayList<Category> getAllCategories() {
        String url = Statics.BASE_URL + "/product/getListCategories";
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categories = parseCategory(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXx");

        System.out.println(categories);
        return categories;
    }
       
    public boolean addProduct(Product p) {

        String url = Statics.BASE_URL
                + "/PostProduct?name=" + p.getName() 
                + "&price=" + p.getPrice()
                + "&description=" + p.getDescription()
                + "&color=" + p.getColor()
                + "&mark=" + p.getBrand()
                + "&quantity=" + p.getQuantite_stock()
                + "&idCategory=" +p.getIdCategory();
        
        System.out.println(url);
        
                req.setUrl(url);
                req.setPost(true);
                

         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;

   
    }
    

    
    public ArrayList<Product> parseProducts(String jsonText){
        try {
            products=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Product p = new Product();
               
                float id = Float.parseFloat(obj.get("idProduct").toString());
                p.setId((int)id);
                
                float price = Float.parseFloat(obj.get("productPrice").toString());
                p.setPrice((int)price);
                
                 if (obj.get("productName") == null) {
                    p.setName("null");
                } else {
                    p.setName(obj.get("productName").toString());
                }
                 
                 if (obj.get("description") == null) {
                    p.setDescription("null");
                } else {
                    p.setDescription(obj.get("description").toString());
                }


                products.add(p);
            }
            
        } catch (IOException ex) {
            
        }
        return products;
    }
     public ArrayList<Product> getAllProducts(){
        ConnectionRequest r = new ConnectionRequest();
        String url = Statics.BASE_URL+"/productsList";
        r.setUrl(url);
        r.setPost(false);
        r.addResponseListener(new ActionListener<NetworkEvent>() {  
            @Override
            public void actionPerformed(NetworkEvent evt) {
               products = parseProducts(new String(r.getResponseData()));
               r.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(r);
        return products;
    }
     public void detailsProduct(int id, Product p){
     String url =Statics.BASE_URL+ "/DetailProduct" +id;
     req.setUrl(url);
     req.setPost(false);
     req.addResponseListener(new ActionListener<NetworkEvent>(){
             @Override
             public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() ==200;
                req.removeResponseCodeListener(this);
             }
         
         });
         NetworkManager.getInstance().addToQueueAndWait(req);
         
     }
     public boolean deleteProduct(int id){
         String urlDelete =Statics.BASE_URL+ "/DeleteProduct?idProduct="+id;
         req.setUrl(urlDelete);
          req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>(){
             @Override
             public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() ==200;
                req.removeResponseCodeListener(this);
             }
         });
         
         NetworkManager.getInstance().addToQueueAndWait(req);
         
         return resultOK;
     }
     
        public boolean updateProduct(int id, Product p){
            
            String url = Statics.BASE_URL + "/UpdateProduct?id=" + id + 
                "&name=" + p.getName() 
                + "&price=" + p.getPrice()
                + "&description=" + p.getDescription()
                + "&color=" + p.getColor()
                + "&mark=" + p.getBrand()
                + "&quantity=" + p.getQuantite_stock()
                + "&idCategory=" +p.getIdCategory();  
            
            req.setUrl(url);
            req.setPost(true);

            req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
               resultOK = req.getResponseCode() ==200;
               req.removeResponseCodeListener(this);
            }         
         });
         NetworkManager.getInstance().addToQueueAndWait(req);
         return true;
        }
}
