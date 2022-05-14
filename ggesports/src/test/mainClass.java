/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.News;
import entities.Comments;
import java.sql.Date;
import services.NewsCRUD;
import services.commentaireCRUD;
import utils.connexion;

/**
 *
 * @author USER
 */
public class mainClass {
    public static void main (String[] args){
        //connexion mc = connexion.getIstance();
        //connexion mc2 = connexion.getIstance();
        //System.out.println(mc.hashCode()+"_" +mc2.hashCode());
       NewsCRUD acd = new NewsCRUD();
       commentaireCRUD acc = new commentaireCRUD();
String str="2015-03-31";
Date date=Date.valueOf(str);
       News a2;
        a2 = new News("THE ","//:http","//:http","  100 MILLION DOLLAR INBELIEVABLE YOU CAN BE THE WINNER ",date);
        //acd.ajouteractualite2(a2);
        //acd.ajouteractualite();
        //commentaire b2;
       // b2 = new commentaire(3,1,"hhh",date);
        //acc.ajoutercommmentaire2(b2);
                //acc.Updatecommmentaire2(b2);
               // acd.Updateactualite2(a2);
               //acc.ajoutercommentaire();
        System.out.println(acd.afficheractualite());
                System.out.println(acc.affichercommentaire());
//b2=new commentaire(2);
//acc.deletecommmentaire2(b2);
//a2=new News(4);
//acd.deleteactualite2(a2);
    }    
}
