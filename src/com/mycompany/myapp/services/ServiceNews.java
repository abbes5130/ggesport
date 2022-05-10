/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.News;
import com.mycompany.myapp.utils.Statics;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Lenovo
 */
public class ServiceNews {
    
    //singleton 
    public static ServiceNews instance = null ;
    
    public boolean resultOK;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceNews getInstance() {
        if(instance == null )
            instance = new ServiceNews();
        return instance ;
    }
    
    
    
    public ServiceNews() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public boolean ajoutNews(News news) {
        
        String url =Statics.BASE_URL+"/addNewsJson?title="+news.getTitle()+"&description="+news.getDescription()+"&bg_img="+news.getBg_img()+"&img="+news.getImg(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOK;
    }
    
    
    //affichage
    
    /*public ArrayList<News>affichageNews() {
        ArrayList<News> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/listnewsJson";
        req.setUrl(url);
                req.setPost(false);

        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapNews= jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapNews.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        News re = new News();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String Title = obj.get("title").toString();
                        
                        String description = obj.get("description").toString();
                        String img = obj.get("img").toString();
                        String bg_img = obj.get("bg_img").toString();

                        re.setId((int)id);
                        re.setDescription(description);
                        re.setTitle(Title);
                        re.setBg_img(bg_img);
                        re.setImg(img);
                        
                        //Date 
                        String DateConverter =  obj.get("creation_date").toString().substring(obj.get("creation_date").toString().indexOf("timestamp") + 10 , obj.get("creation_date").toString().lastIndexOf("}"));
                        
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);
                        re.setCreation_date(dateString);
                        
                        //insert data into ArrayList result
                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }*/
    public ArrayList<News> newss;
     public ArrayList<News> parseNews(String jsonText){
        try {
            newss=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> NewsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)NewsListJson.get("root");
            for(Map<String,Object> obj : list){
                News t = new News();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                if (obj.get("title")==null)
              t.setTitle("null");
                else
                    t.setTitle(obj.get("title").toString());
                
                if (obj.get("description")==null)
              t.setDescription("null");
                else
                    t.setDescription(obj.get("description").toString());
                
                if (obj.get("img")==null)
              t.setImg("null");
                else
                    t.setImg(obj.get("img").toString());
                
                if (obj.get("bg_img")==null)
              t.setBg_img("null");
                else
                    t.setBg_img(obj.get("bg_img").toString());
         //Date 
                     
                        if (obj.get("creation_date")==null)
              t.setCreation_date("null");
                else  
                      t.setCreation_date(obj.get("creation_date").toString());         
                
                newss.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return newss;
    }
    
    public ArrayList<News> getAllNews(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"/listnewsJson";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");               

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                newss = parseNews(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return newss;
    }
    
    
    
    //Detail News bensba l detail n5alihoa lel5r ba3d delete+update
    
    public News DetailNews( int id , News news) {
        
        String url = Statics.BASE_URL+"/api/NewsJson/?"+id+"/view";
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                news.setTitle(obj.get("title").toString());
                news.setDescription(obj.get("description").toString());
                news.setImg(obj.get("img").toString());
                news.setBg_img(obj.get("bg_img").toString());

            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return news;
        
        
    }
    
    
    //Delete 
    public boolean deleteNews(int id ) {
        String url = Statics.BASE_URL +"/api/deleteNewsJson/"+id+"/view";
        
        req.setUrl(url);
                req.setHttpMethod("DELETE");               

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                            req.setPost(false);

                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }
    
    
    
    //Update 
    public boolean modifierNews(News news,int id) {
        String url = Statics.BASE_URL +"/api/updateJson/"+id+"?bg_img="+news.getBg_img()+"&img="+news.getImg()+"&title="+news.getTitle()+"&description="+news.getDescription();
        req.setUrl(url);
        req.setPost(false);
req.setHttpMethod("PUT");               
req.setReadResponseForErrors(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                resultOK = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOK;
        
    }
    

    
}