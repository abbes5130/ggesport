/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.gui.entities.Team;

/**
 *
 * @author mohamedabbes
 */
public class TeamDetails extends BaseForm{
    public TeamDetails(BaseForm previous , int id, Team t) {
    setTitle("Checking this team");
        setLayout(BoxLayout.y());
        Container ct = BorderLayout.center(previous);
Label teamN = new Label("Team Name : "+ t.getName());
Label teamL = new Label("Team Logo : "+ t.getLogo());
Label teamNB = new Label("Team Players Count : "+ t.getNb_joueur());
Label teamD = new Label("Team Description : "+ t.getDescription());
    
     ct.add(
  BorderLayout.CENTER,BoxLayout.encloseY(
  BoxLayout.encloseX(teamN),
     BoxLayout.encloseX(teamL),
       BoxLayout.encloseX(teamNB),
         BoxLayout.encloseX(teamD)
  )
  
  );
  addAll(ct);
    }
}
