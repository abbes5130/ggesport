/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Matches;
import com.mycompany.myapp.services.MatchesServices;
import java.util.ArrayList;


/**
 *
 * @author USER
 */
public class ListMatchForm extends Form{
        public ListMatchForm(Form res) {
        setTitle("List Match");
        
        setLayout(BoxLayout.y());
        
         ArrayList<Matches>list = MatchesServices.getInstance().getAllMatches();
         
         for (Matches rec : list)
         {
              String urlImage ="back-logo.jpeg";//image statique pour le moment ba3d taw fi  videos jayin nwarikom image 
            
             Image placeHolder = Image.createImage(120, 90);
             EncodedImage enc =  EncodedImage.createFromImage(placeHolder,false);
            
                addButton(rec,res);
        
         }
    }

    private void addButton(Matches rec, Form res) {

                int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        //Button image = new Button(img.fill(width, height));
       // image.setUIID("Label");
               Container cnt = BorderLayout.center(res);
                
        Label creationdateTxt = new Label("Date : "+rec.getDate(),"NewsTopLine2");
        Label timeTxt = new Label("Time : "+rec.getTime(),"NewsTopLine2");
        Label linkTxt = new Label("Link : "+rec.getLink(),"NewsTopLine2");
        Label loctaionTxt = new Label("Location : "+rec.getLocation(),"NewsTopLine2" );
        Label nbSeatsTxt = new Label("Nb of Seats : "+rec.getNbSeats(),"NewsTopLine2");
        Label priceTxt = new Label("Price : "+rec.getPrice(),"NewsTopLine2");
        Label team1 = new Label("Team 1 : "+rec.getTeamName1(),"NewsTopLine2");
        Label team2 = new Label("Team 2 : "+rec.getTeamName2(),"NewsTopLine2");
        
        

                
                
                
                
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        
        
        lSupprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer ce match ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                //n3ayto l suuprimer men service News
                if(MatchesServices.getInstance().deleteMatch(rec.getIdMatch())) {
                    new ListMatchForm(res).show();
                }
           
        });
        
        
        
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        
        
        lModifier.addPointerPressedListener(l -> {
            //System.out.println("hello update");
            new UpdateMatchForm(res,rec.getIdMatch(),rec).show();
        });
        
        
        
        Label lDetail = new Label(" ");
        lDetail.setUIID("NewsTopLine");
        Style detailStyle = new Style(lDetail.getUnselectedStyle());
        detailStyle.setFgColor(0xf7ad02);
        
        FontImage dFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, detailStyle);
        lDetail.setIcon(dFontImage);
        lDetail.setTextPosition(LEFT);
        
        
        lDetail.addPointerPressedListener(l -> {
            //System.out.println("hello update");
                if(MatchesServices.getInstance().detaileMatch(rec.getIdMatch())) {
                    new MatchDetailForm(res,rec.getIdMatch(),rec).show();
                }
        });


        
        
                cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(
                        
                        
                 BoxLayout.encloseX(team1),
                 BoxLayout.encloseX(team2),
                BoxLayout.encloseX(creationdateTxt),
                BoxLayout.encloseX(timeTxt),
                BoxLayout.encloseX(loctaionTxt),
                BoxLayout.encloseX(linkTxt),
                BoxLayout.encloseX(nbSeatsTxt),

                BoxLayout.encloseX(priceTxt),
               BoxLayout.encloseX(lModifier,lSupprimer,lDetail)));
        
        
        
        add(cnt);
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> res.showBack());


        
        
        





    }
    
    
       /* public ListMatchForm(Form previous) {
        setTitle("List tasks");

        SpanLabel sp = new SpanLabel();
        sp.setText(MatchesServices.getInstance().getAllMatches().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }*/
    
}
