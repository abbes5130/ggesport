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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entites.Team;
import com.mycompany.services.ServiceTeam;

/**
 *
 * @author mohamedabbes
 */
public class UpdateTeamForm extends BaseForm{
     public UpdateTeamForm(BaseForm previous , int id, Team t) {
        setTitle("update this team");
        setLayout(BoxLayout.y());
        
        TextField tfTeamName = new TextField(t.getName(),"Team Name");
        TextField tfLogo= new TextField(t.getLogo(), "Team Logo");
        TextField tfNbPlayer= new TextField(String.valueOf(t.getNb_joueur()), "Team players Count");
        TextField tfDescription= new TextField(t.getDescription(), "Team Description");
      tfTeamName.getAllStyles().setFgColor(0xFF2261);
          tfLogo.getAllStyles().setFgColor(0xFF2261);
        tfNbPlayer.getAllStyles().setFgColor(0xFF2261);
          tfDescription.getAllStyles().setFgColor(0xFF2261);
        Button btnValider = new Button("Add task");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
                        Team t = new Team(tfTeamName.getText(),tfLogo.getText(),Integer.parseInt(tfNbPlayer.getText()),tfDescription.getText());
                              if( ServiceTeam.getInstance().updateTeam(id, t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                    
                
                
                
            }
        });
        
        addAll(tfTeamName,tfLogo,tfNbPlayer,tfDescription,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
