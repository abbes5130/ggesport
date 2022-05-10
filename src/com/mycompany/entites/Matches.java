/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;


/**
 *
 * @author Ghassene Badra
 */
public class Matches {
    
    private int idMatch;
    private String time;
    private String date;
    private String location,link;
    private int nbSeats,price;
    private int idTeam1,idTeam2;
    private String teamName1, teamName2;

    public String getTeamName1() {
        return teamName1;
    }

    public void setTeamName1(String teamName1) {
        this.teamName1 = teamName1;
    }

    public String getTeamName2() {
        return teamName2;
    }

    public void setTeamName2(String teamName2) {
        this.teamName2 = teamName2;
    }

    public Matches(int idMatch, String time, String date, String location, String link, int nbSeats, int price, int idTeam1, int idTeam2) {
        this.idMatch = idMatch;
        this.time = time;
        this.date = date;
        this.location = location;
        this.link = link;
        this.nbSeats = nbSeats;
        this.price = price;
        this.idTeam1 = idTeam1;
        this.idTeam2 = idTeam2;
    }

    public Matches(String time, String date, String location, String link, int nbSeats, int price, int idTeam1, int idTeam2) {
        this.time = time;
        this.date = date;
        this.location = location;
        this.link = link;
        this.nbSeats = nbSeats;
        this.price = price;
        this.idTeam1 = idTeam1;
        this.idTeam2 = idTeam2;
    }

    public Matches() {
    }

    public Matches(String time, String date, String location, String link) {
        this.time = time;
        this.date = date;
        this.location = location;
        this.link = link;
    }

    public Matches(String location, String link, int nbSeats) {
        this.location = location;
        this.link = link;
        this.nbSeats = nbSeats;
    }

    public Matches(String time, String date, String location, String link, int nbSeats, int price) {
        this.time = time;
        this.date = date;
        this.location = location;
        this.link = link;
        this.nbSeats = nbSeats;
        this.price = price;
    }

    public Matches(String location, String link, int nbSeats, int price) {
        this.location = location;
        this.link = link;
        this.nbSeats = nbSeats;
        this.price = price;
    }

    public Matches(String location, String link, int nbSeats, int price, int idTeam1, int idTeam2) {
        this.location = location;
        this.link = link;
        this.nbSeats = nbSeats;
        this.price = price;
        this.idTeam1 = idTeam1;
        this.idTeam2 = idTeam2;
    }
    
    
    
    

    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getNbSeats() {
        return nbSeats;
    }

    public void setNbSeats(int nbSeats) {
        this.nbSeats = nbSeats;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIdTeam1() {
        return idTeam1;
    }

    public void setIdTeam1(int idTeam1) {
        this.idTeam1 = idTeam1;
    }

    public int getIdTeam2() {
        return idTeam2;
    }

    public void setIdTeam2(int idTeam2) {
        this.idTeam2 = idTeam2;
    }

    @Override
    public String toString() {
        return "Matches{" + "idMatch=" + idMatch + ", time=" + time + ", date=" + date + ", location=" + location + ", link=" + link + ", nbSeats=" + nbSeats + ", price=" + price + ", idTeam1=" + idTeam1 + ", idTeam2=" + idTeam2 + ", teamName1=" + teamName1 + ", teamName2=" + teamName2 + '}';
    }


    
    
    
     
}
