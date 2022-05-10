/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;

import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Users;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.SessionManager;

import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ridha
 */
public class UsersServices {
    public static UsersServices instance = null ;
    
    private ConnectionRequest req;
    public static boolean resultOk = true;
    String json;
   
    public static UsersServices getInstance(){
        if(instance == null)
            instance = new UsersServices();
        return instance;
    }
    public UsersServices(){
       req = new ConnectionRequest();

    }
    
    
    
    
    public void signup(TextField firstname,TextField lastname,TextField email,TextField password,TextField confirmPassword,TextField phone_number ){
         String url = Statics.BASE_URL+"/singupActionJson?firstname="+firstname.getText().toString()+"&lastname="+lastname.getText().toString()+
                "&email="+email.getText().toString()+"&password="+password.getText().toString()+"&phone_number="+phone_number.getText().toString();
        
        req.setUrl(url);
       
        //Control saisi
        if(firstname.getText().equals(" ") && lastname.getText().equals(" ") && password.getText().equals(" ") && email.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }
    
     
    public void edit(TextField idUser,TextField firstname,TextField lastname,TextField email,TextField password,TextField phone_number ){
         String url = Statics.BASE_URL+"/EditProfileJson?id_user="+SessionManager.getId()+"&firstname="+firstname.getText().toString()+"&lastname="+lastname.getText().toString()+
                "&email="+email.getText().toString()+"&password="+password.getText().toString()+"&phone_number="+phone_number.getText().toString();
        
        req.setUrl(url);
       
        //Control saisi
        if(firstname.getText().equals(" ") && lastname.getText().equals(" ") && password.getText().equals(" ") && email.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }
    public void signin(TextField email,TextField password, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/singinActionJson?email="+email.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","email ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
                float id = Float.parseFloat(user.get("idUser").toString());
                SessionManager.setId((int)id);
                 SessionManager.setFirstname(user.get("firstname").toString());
                  SessionManager.setLastname(user.get("lastname").toString());
                SessionManager.setPassowrd(user.get("password").toString());
              
                SessionManager.setEmail(user.get("email").toString());
                float phone = Float.parseFloat(user.get("phoneNumber").toString());
                SessionManager.setPhone_number((int)phone);
                System.out.println("current user is : "+SessionManager.getEmail()+","+SessionManager.getPassowrd());
             
           if(user.size()>0)
               new ProfileForm(rs).show();
                
                
               
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
      public String getPasswordByEmail(String email, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/getPasswordByEmail?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }
      public static void EditUser(String firstname,String lastname,String email, String password ,int phone_number){
          String url = "";
                    MultipartRequest req = new MultipartRequest();
                    
                    req.setUrl(url);
                    req.setPost(true);
                    req.addArgument ("id", String.valueOf (SessionManager.getId() ));
                    
                    req.addArgument ("password", password);
                    req.addArgument ("email", email);
                    req.addArgument ("firstname", firstname);
                    req.addArgument ("lastname", lastname);
                   // req.addArgument ("phone_number",String.valueOf (SessionManager.getPhone_number()));
                    System.out.println (email);
                    req.addResponseListener((response)->{
                    byte[] data=(byte[]) response.getMetaData ();
                    String s =new String (data);
                    System.out.println (s);
                    if (s.equals ("success")){
                    }else{
                            Dialog.show ("Erreur", "Echec de modification", "Ok", null);
                    }
                    });
                        NetworkManager.getInstance ().addToQueueAndWait(req);
      }
      
}