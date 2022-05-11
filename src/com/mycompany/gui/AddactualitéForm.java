/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.News;
import com.mycompany.services.ServiceNews;

/**
 *
 * @author bhk
 */
public class AddactualitéForm extends Form{

    public AddactualitéForm( Form pre) {
        setTitle("Add a new actualité");
        setLayout(BoxLayout.y());
                TextField tfimg = new TextField("","img");
        TextField tfimg2 = new TextField("","bg_img");

        TextField tfName = new TextField("","title");
        TextField tfStatus= new TextField("", "description");
        Button btnValider = new Button("Add actualité");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfStatus.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        News t = new News(tfimg.getText() ,tfStatus.getText(), tfName.getText().toString(), tfimg2.getText().toString());
                        if(ServiceNews.getInstance().ajoutNews(t)) {
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfName,tfStatus,tfimg2,tfimg,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> pre.showBack());
                
    }
    
    
}
