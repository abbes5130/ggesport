/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;

import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.entites.Team;

import com.mycompany.services.ServiceTeam;
import java.util.ArrayList;

/**
 *
 * @author mohamedabbes
 */
public class ListTeamsForm extends BaseForm {
     public ListTeamsForm(BaseForm previous) {
         ArrayList<Team>listTeam = ServiceTeam.getInstance().getAllTasks();
         setTitle("Add a new task");
        setLayout(BoxLayout.y());

for (Team tr : listTeam){
teamShow(tr, previous);
}
  
//        sp.getAllStyles().setBgColor(0xFF2261);
      
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

    private void teamShow(Team tr, BaseForm previous) {
          SpanLabel sp = new SpanLabel();
    //sp.setText(ServiceTeam.getInstance().getAllTasks().toString().intern());
Container ct = BorderLayout.center(previous);
Label teamN = new Label("Team Name : "+ tr.getName());
Label teamL = new Label("Team Logo : "+ tr.getLogo());
Label teamNB = new Label("Team Players Count : "+ tr.getNb_joueur());
Label teamD = new Label("Team Description : "+ tr.getDescription());

Label supp = new Label("");
Label update = new Label("");
Label show = new Label("");
show.setUIID("sh");
update.setUIID("uu");
supp.setUIID("uuid");
Style showStyle = new Style(show.getUnselectedStyle());
Style suppStyle = new Style(supp.getUnselectedStyle());
Style updateStyle = new Style(update.getUnselectedStyle());
showStyle.setFgColor(0xFF0033);
suppStyle.setFgColor(0xFF8291);
suppStyle.setFgColor(0xFF1920);
FontImage showImage = FontImage.createMaterial(FontImage.MATERIAL_OPEN_IN_FULL, showStyle);

FontImage suppImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE_FOREVER, suppStyle);
FontImage updateImage = FontImage.createMaterial(FontImage.MATERIAL_EDIT,updateStyle);
show.setIcon(showImage);
supp.setIcon(suppImage);
update.setIcon(updateImage);
show.setTextPosition(RIGHT);
supp.setTextPosition(RIGHT);
update.setTextPosition(RIGHT);
update.addPointerPressedListener(l->{
if(ServiceTeam.getInstance().updateTeam(tr.getId_team(), tr)){
new UpdateTeamForm(previous,tr.getId_team(),tr).show();
}
});
show.addPointerPressedListener(l->{

new TeamDetails(previous,tr.getId_team(),tr).show();

});

supp.addPointerPressedListener(l ->{
    Dialog diag = new Dialog("supp");
if(diag.show("supp", "You WANT TO DELETE BROTHA ?","La","OK")){
diag.dispose();
}else{
diag.dispose();

}
if(ServiceTeam.getInstance().deleteTeam(tr.getId_team())){
new ListTeamsForm(previous).show();
}
});


  ct.add(
  BorderLayout.CENTER,BoxLayout.encloseY(
  BoxLayout.encloseX(teamN),
     BoxLayout.encloseX(teamL),
       BoxLayout.encloseX(teamNB),
         BoxLayout.encloseX(teamD),
         BoxLayout.encloseX(supp),
         BoxLayout.encloseX(update),
         BoxLayout.encloseX(show)
  )
  
  );
  addAll(ct);
    }
     
}
