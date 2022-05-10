/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import static com.codename1.charts.util.ColorUtil.BLUE;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.gui.entities.Team;
import com.mycompany.gui.services.ServiceTeam;

/**
 *
 * @author mohamedabbes
 */
public class AddTeamForm extends BaseForm{
    
    public AddTeamForm(BaseForm previous) {
        setTitle("Add a new task");
        setLayout(BoxLayout.y());
        TextField tfTeamName = new TextField("","Team Name");
        TextField tfLogo= new TextField("", "Team Logo");
        TextField tfNbPlayer= new TextField("", "Team players Count");
        TextField tfDescription= new TextField("", "Team Description");
      tfTeamName.getAllStyles().setFgColor(0xFF2261);
          tfLogo.getAllStyles().setFgColor(0xFF2261);
        tfNbPlayer.getAllStyles().setFgColor(0xFF2261);
          tfDescription.getAllStyles().setFgColor(0xFF2261);
        Button btnValider = new Button("Add task");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
                        Team t = new Team(tfTeamName.getText().toString(),tfLogo.getText().toString(),Integer.parseInt(tfNbPlayer.getText()),tfDescription.getText().toString());
                              if( ServiceTeam.getInstance().addTask(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Team Added Successfully");
  status.show();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                    
                
                
                
            }
        });
        
        addAll(tfTeamName,tfLogo,tfNbPlayer,tfDescription,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
