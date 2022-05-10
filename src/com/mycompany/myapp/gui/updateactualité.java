/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.News;
import com.mycompany.myapp.services.ServiceNews;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
   /* 
public class updateactualité extends Form {
   public updateactualité(Form previous , int id, News m)
    {
        setLayout(BoxLayout.y());

        TextField bg_img = new TextField( m.getBg_img(),"bg_img");
        TextField img = new TextField(m.getImg(),"img" );
          TextField title = new TextField(m.getTitle(),"title" );
        TextField description = new TextField(m.getTitle(),"description" );

       

        ArrayList<News> list = ServiceNews.getInstance().getAllNews();

        ComboBox<News> comboTeam1 = new ComboBox<>();
        ComboBox<News> comboTeam2 = new ComboBox<>();

        for (News rec : list) {
            News team = new News(rec.getId(), rec.getTitle());
            comboTeam1.addItem(team);
            comboTeam2.addItem(team);
            

        }

   


        Button btnInsert = new Button("Update Match");

        btnInsert.addActionListener((ActionListener) (ActionEvent evt) -> {
            if ((bg_img.getText().length() == 0) || (img.getText().length() == 0) ) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    
                    
                   News t = new News(
                        String.valueOf(bg_img.getText()),
                            String.valueOf(img.getText()),
                             String.valueOf(title.getText()),
                            String.valueOf(description.getText()),

                            comboTeam1.getSelectedItem().getId(),
                            comboTeam2.getSelectedItem().getId()
                    );

                    if (ServiceNews.getInstance().modifierNews(t,id)) {
                        Dialog.show("Success", "Connection accepted", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                }

            }
        });

        addAll(img, bg_img, title, description, comboTeam1, comboTeam2, btnInsert);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
 
}
*/