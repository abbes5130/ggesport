package com.mycompany.gui.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.ComboTeam;
import com.mycompany.myapp.entities.Matches;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Ghassene Badra
 */
public class MatchesServices {

    public static MatchesServices instance = null;

    public boolean resultOK;

    private ConnectionRequest req;

    public static MatchesServices getInstance() {
        if (instance == null) {
            instance = new MatchesServices();
        }
        return instance;
    }

    public MatchesServices() {
        req = new ConnectionRequest();
    }

    public boolean addMatch(Matches match) {
        String url = Statics.BASE_URL + "/matches/newMatchMobile?location="
                + match.getLocation()
                + "&link=" + match.getLink()
                + "&nb_seats=" + match.getNbSeats()
                + "&price=" + match.getPrice()
                + "&idTeam1=" + match.getIdTeam1()
                + "&idTeam2=" + match.getIdTeam2();

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }

    //affichage matches
    public ArrayList<Matches> matchs;
    public ArrayList<ComboTeam> teams;

    public ArrayList<Matches> parseMatch(String jsonText) {
        try {
            matchs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> MatchListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa");
            System.out.println(jsonText);

            List<Map<String, Object>> list = (List<Map<String, Object>>) MatchListJson.get("root");
            for (Map<String, Object> obj : list) {
                Matches t = new Matches();

                float id = Float.parseFloat(obj.get("idMatch").toString());
                t.setIdMatch((int) id);
                float price = Float.parseFloat(obj.get("price").toString());
                t.setPrice((int) price);

                float nbSeats = Float.parseFloat(obj.get("nbSeats").toString());
                t.setNbSeats((int) nbSeats);

                if (obj.get("location") == null) {
                    t.setLocation("null");
                } else {
                    t.setLocation(obj.get("location").toString());
                }

                if (obj.get("link") == null) {
                    t.setLink("null");
                } else {
                    t.setLink(obj.get("link").toString());
                }

                if (obj.get("date") == null) {
                    t.setDate("null");
                } else {
                    t.setDate(obj.get("date").toString());
                }
                //Date 

                if (obj.get("time") == null) {
                    t.setTime("null");
                } else {
                    t.setTime(obj.get("time").toString());
                }

                

                Map<String, Object> team1 =(Map<String, Object>) obj.get("idTeam1");
                       

                float idTeam1 = Float.parseFloat(team1.get("idTeam").toString());
                t.setIdTeam1((int) idTeam1);

                String teamName = team1.get("teamName").toString();

                t.setTeamName1(teamName);
                
                
                
                
              

                Map<String, Object> team2 = (Map<String, Object>) obj.get("idTeam2");

                float idTeam2 = Float.parseFloat(team2.get("idTeam").toString());
                t.setIdTeam2((int) idTeam2);

                teamName = team2.get("teamName").toString();

                t.setTeamName2(teamName);


                matchs.add(t);
            }

        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return matchs;
    }

    public ArrayList<Matches> getAllMatches() {
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "/matches/allMatchMobile";
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                matchs = parseMatch(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return matchs;
    }

    public ArrayList<ComboTeam> parseTeam(String jsonText) {
        try {
            teams = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> TeamListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) TeamListJson.get("root");
            for (Map<String, Object> obj : list) {
                ComboTeam t = new ComboTeam();

                float id = Float.parseFloat(obj.get("idTeam").toString());
                t.setIdTeam((int) id);

                if (obj.get("teamName") == null) {
                    t.setTeamName("null");
                } else {
                    t.setTeamName(obj.get("teamName").toString());
                }

                teams.add(t);
            }

        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return teams;
    }

    public ArrayList<ComboTeam> getAllTeams() {
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "/matches/getListTeam";
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                teams = parseTeam(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return teams;
    }

    public boolean deleteMatch(int id) {
        String url = Statics.BASE_URL + "/matches/matchDeleteMobile/" + id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;  // Code response Http 200 ok

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public Matches parseSingleMatch(String jsonText) {
        try {
            matchs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> obj
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa");
            System.out.println(jsonText);

            Matches t = new Matches();

            float id = Float.parseFloat(obj.get("idMatch").toString());
            t.setIdMatch((int) id);
            float price = Float.parseFloat(obj.get("price").toString());
            t.setPrice((int) price);

            float nbSeats = Float.parseFloat(obj.get("nbSeats").toString());
            t.setNbSeats((int) nbSeats);

            if (obj.get("location") == null) {
                t.setLocation("null");
            } else {
                t.setLocation(obj.get("location").toString());
            }

            if (obj.get("link") == null) {
                t.setLink("null");
            } else {
                t.setLink(obj.get("link").toString());
            }

            if (obj.get("date") == null) {
                t.setDate("null");
            } else {
                t.setDate(obj.get("date").toString());
            }
            //Date 

            if (obj.get("time") == null) {
                t.setTime("null");
            } else {
                t.setTime(obj.get("time").toString());
            }

            return t;

        } catch (IOException ex) {
            ex.printStackTrace();

        }

        return null;
    }

    public boolean detaileMatch(int id) {

        System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
        String url = Statics.BASE_URL + "/matches/matchDetailMobile/" + id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;  // Code response Http 200 ok

                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
        return resultOK;

    }

    public Matches DetailMatch(int id, Matches match) {

        String url = Statics.BASE_URL + "/matches/matchDetailMobile/" + id;
        req.setUrl(url);

        String str = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {

            JSONParser jsonp = new JSONParser();
            try {

                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));

                match.setDate(obj.get("date").toString());
                match.setTime(obj.get("time").toString());
                match.setLocation(obj.get("location").toString());
                match.setLink(obj.get("link").toString());

                
                Map<String, Object> team1 =(Map<String, Object>) obj.get("idTeam1");
                       

                float idTeam1 = Float.parseFloat(team1.get("idTeam").toString());
                match.setIdTeam1((int) idTeam1);

                String teamName = team1.get("teamName").toString();

                match.setTeamName1(teamName);
                
                
                
                
              

                Map<String, Object> team2 = (Map<String, Object>) obj.get("idTeam2");

                float idTeam2 = Float.parseFloat(team2.get("idTeam").toString());
                match.setIdTeam2((int) idTeam2);

                teamName = team2.get("teamName").toString();

                match.setTeamName2(teamName);
               
            } catch (IOException ex) {
                System.out.println("error related to sql :( " + ex.getMessage());
            }

            System.out.println("data === " + str);

        }));

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return match;

    }

    Matches t = new Matches();

    public Matches getDetailMatch(int id) {
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "/matches/matchDetailMobile/" + id;
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                t = parseSingleMatch(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return t;
    }
    
    
    
    
    
        public boolean UpdateMatch(Matches match, int id) {
        
                String url = Statics.BASE_URL + "/matches/matchUpdateMobile/"+id+
                 "?location="+ match.getLocation()
                + "&link=" + match.getLink()
                + "&nb_seats=" + match.getNbSeats()
                + "&price=" + match.getPrice()
                + "&idTeam1=" + match.getIdTeam1()
                + "&idTeam2=" + match.getIdTeam2();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOK;
        
    }

}
