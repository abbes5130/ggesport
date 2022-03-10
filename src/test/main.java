/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import entites.Commentaire;
import entites.actualité;
import services.actualiteCRUD;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import services.commentaireCRUD;

import utils.Mydb;
/**
 *
 * @author khale
 */
public class main {
    public static void main(String[] args) {
        Connection cnx= Mydb.getInstance().getConncetion();
        actualité pub0 = new actualité("1st pub published");
        actualité ac1 = new actualité(32);
        actualiteCRUD spub = new actualiteCRUD();
        String nbreComs = String.valueOf(spub.nombreCommentaires(ac1));
        
        /*User u = new User("kjh", "dsfsd", "sdfsdf", 0, "dsfsdf");
        SUser su = new SUser();
        
        System.out.println(su.find());*/
        
        System.out.println(nbreComs);
        String nbreComms =""+spub.nombreCommentaires(ac1);
        System.out.println(nbreComms);
        List<actualité> lpubs = spub.find();
        for (int i=0;i< lpubs.size();i++){
            System.out.println("nombre de commentaires sur cette pub :"+spub.nombreCommentaires(lpubs.get(i)));
        }
        
        //spub.ajouter(pub1);
        //spub.modifier(pub1);
        //spub.supprimer(pub1);
        System.out.println(spub.find());
      //  Commentaire c=new Commentaire(3);
      String str="2015-03-31";
Date date=Date.valueOf(str);
        Commentaire c=new Commentaire(31,"qqqqqs");
        commentaireCRUD sc=new commentaireCRUD();
        sc.ajouter(c);
        //sc.modifier(c1);
        //sc.supprimer(c);
        System.out.println(sc.find(31));
                
    }
}
