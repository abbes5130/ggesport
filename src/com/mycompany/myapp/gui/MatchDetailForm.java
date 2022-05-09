/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Matches;
import com.mycompany.myapp.services.MatchesServices;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ghassene Badra
 */
public class MatchDetailForm extends Form{
    
    public MatchDetailForm(Form previous, int id,Matches m) {
        
        
        
        
        setTitle("Detail Match");
        
        setLayout(BoxLayout.y());
        
       Matches singleMatch = MatchesServices.getInstance().DetailMatch(id,m);
       
         
         System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBbb");
         
         System.out.println(singleMatch);
         
        

         String urlImage ="back-logo.jpeg";//image statique pour le moment ba3d taw fi  videos jayin nwarikom image
         
         Image placeHolder = Image.createImage(120, 90);
         EncodedImage enc =  EncodedImage.createFromImage(placeHolder,false);
         
         addButton(singleMatch,previous);
         



        
         

        
        
        
        
        
        
       /* setTitle("List tasks");

        SpanLabel sp = new SpanLabel();
        sp.setText(MatchesServices.getInstance().getDetailMatch(id).toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());*/
    }
    
    
    
    
    
    
                 private void addButton(Matches m,Form res) {

                int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        //Button image = new Button(img.fill(width, height));
       // image.setUIID("Label");
               Container cnt = BorderLayout.center(res);
                
        Label creationdateTxt = new Label("Date : "+m.getDate(),"NewsTopLine2");
        Label timeTxt = new Label("Time : "+m.getTime(),"NewsTopLine2");
        Label linkTxt = new Label("Link : "+m.getLink(),"NewsTopLine2");
        Label loctaionTxt = new Label("Location : "+m.getLocation(),"NewsTopLine2" );
        Label nbSeatsTxt = new Label("Nb of Seats : "+m.getNbSeats(),"NewsTopLine2");
        Label priceTxt = new Label("Price : "+m.getPrice(),"NewsTopLine2");
        Label team1 = new Label("Team 1 : "+m.getTeamName1(),"NewsTopLine2");
        Label team2 = new Label("Team 2 : "+m.getTeamName2(),"NewsTopLine2");
        
        

                
                
                
                

     


        
        
                cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(
                        
                        
                 BoxLayout.encloseX(team1),
                 BoxLayout.encloseX(team2),
                BoxLayout.encloseX(creationdateTxt),
                BoxLayout.encloseX(timeTxt),
                BoxLayout.encloseX(loctaionTxt),
                BoxLayout.encloseX(linkTxt),
                BoxLayout.encloseX(nbSeatsTxt),

                BoxLayout.encloseX(priceTxt),
               BoxLayout.encloseX()));
        
        
        
        add(cnt);
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> res.showBack());


        
        
        





    }
    
}
