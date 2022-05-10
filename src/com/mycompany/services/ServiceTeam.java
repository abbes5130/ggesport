/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.Team;
import com.mycompany.utils.Statics;
import java.io.IOException;
import static java.lang.Double.parseDouble;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author mohamedabbes
 */
public class ServiceTeam {
       ArrayList<Team> teams = new ArrayList<>();
    
    public static ServiceTeam instance=null;
    public boolean resultOK;
    ConnectionRequest req = new ConnectionRequest();

    private ServiceTeam() {
         req = new ConnectionRequest();
    }

    public static ServiceTeam getInstance() {
        if (instance == null) {
            instance = new ServiceTeam();
        }
        return instance;
    }

   
       int pageNumber = 1;
    public boolean addTask(Team t) {
    try {
        ConnectionRequest r = new ConnectionRequest();
        r.setPost(false);
        String url = Statics.BASE_URL +"/team"+ "/new?team_name=" + t.getName() + "&logo=" + t.getLogo()+ "&players_number=" + t.getNb_joueur() + "&description=" + t.getDescription();
                r.setUrl(url);

         r.addResponseListener(new ActionListener<NetworkEvent>() {
 @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = r.getResponseCode() == 200; //Code HTTP 200 OK
                r.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(r);
        return resultOK;

    } catch(Exception err) {
     
    return resultOK;
    }
   
    }
    

    
    public ArrayList<Team> parseTasks(String jsonText){
        try {
            teams=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Team t = new Team();
               
                
                float id = Float.parseFloat(obj.get("idTeam").toString());
               t.setId_team((int)id);
                    t.setName(obj.get("teamName").toString());
                    t.setLogo(obj.get("logo").toString());
                    t.setNb_joueur(parseDouble( obj.get("playersNumber").toString()));
                    t.setDescription(obj.get("description").toString());
                teams.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return teams;
    }
     public ArrayList<Team> getAllTasks(){
        //String url = Statics.BASE_URL+"/tasks/";
         ConnectionRequest r = new ConnectionRequest();
        String url = Statics.BASE_URL+"/team";
        r.setUrl(url);
        r.setPost(false);
        r.addResponseListener(new ActionListener<NetworkEvent>() {  
            @Override
            public void actionPerformed(NetworkEvent evt) {
               teams = parseTasks(new String(r.getResponseData()));
                r.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(r);
        return teams;
    }
     public void detailsTeam(int id, Team t){
     String url =Statics.BASE_URL+"/team"+ "/" +id;
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
     public boolean deleteTeam(int id){
         String urlDelete =Statics.BASE_URL+"/team"+ "/" +id+ "/delete";
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
          public boolean updateTeam(int id, Team t){
        String url = Statics.BASE_URL +"/team"+ "/" + id + "/edit?team_name=" + t.getName() + "&logo=" + t.getLogo()+ "&players_number=" + t.getNb_joueur() + "&description=" + t.getDescription();
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
         return resultOK;
                 }
}
