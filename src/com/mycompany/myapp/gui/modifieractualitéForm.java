/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.News;
import com.mycompany.myapp.services.ServiceNews;

/**
 *
 * @author USER
 */
public class modifieractualitéForm extends Form {
    public modifieractualitéForm(Form re, News r,int id) {         

        TextField bg_img = new TextField(r.getBg_img(), "bg_img" , 20 , TextField.ANY);
        TextField description = new TextField(r.getDescription(), "description" , 20 , TextField.ANY);
               TextField img = new TextField(String.valueOf(r.getImg()) , "img" , 20 , TextField.ANY);
                TextField title = new TextField(String.valueOf(r.getTitle()) , "title" , 20 , TextField.ANY);        
        
        
        
        
        bg_img.setUIID("NewsTopLine");
        description.setUIID("NewsTopLine");
        img.setUIID("NewsTopLine");
         title.setUIID("NewsTopLine");

        title.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
        bg_img.setSingleLineTextArea(true);
                img.setSingleLineTextArea(true);

        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setTitle(title.getText());
           r.setDescription(description.getText());
                      r.setBg_img(bg_img.getText());
r.setImg(img.getText());
             r.setId(r.getId());
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceNews.getInstance().modifierNews(r,id)) { // if true
           new listactualitéForm(re).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new listactualitéForm(re).show();
       });
       
              Label l6 = new Label("");

       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(title),
                createLineSeparator(),
     

                new FloatingHint(description),
                createLineSeparator(),
                 new FloatingHint(img),
createLineSeparator(),
                 new FloatingHint(bg_img),
                createLineSeparator(),//ligne de séparation

                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
}
     public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
}
