/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import static com.codename1.io.Log.e;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Users;
import com.mycompany.services.UsersServices;
import java.util.ArrayList;



/**
 *
 * @author ridha
 */
public class ListUsersForm extends BaseForm {
    Form current;
     public ListUsersForm (Resources res){
        Toolbar tb=new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("");
        super.addSideMenu(res);
        getContentPane().setScrollVisible(false);
        
        tb.addSearchCommand((e)->{
        
        
        
        });
        
        Tabs swipe=new Tabs();
        Label s1= new Label();
        Label s2= new Label();
        
        //addTab(swipe,s1,res.getImage("back-logo.jpeg"),"","",res);
        
        //
        
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Reclamations", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Reclamer", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        //  ListReclamationForm a = new ListReclamationForm(res);
          //  a.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));
        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        mesListes.addActionListener((e) -> {
           
            //   ArrayList<Users>list = UsersServices.getInstance().AffichageUsers();
           
               
        
  
            
        });
          

    }
    
   private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }
     public void bindButtonSelection(Button btn, Label l){
    btn.addActionListener(e->{
    if(btn.isSelected()){
    updateArrowPosition(btn,l);
    }
    
    });
    }
      private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT, btn.getX()+btn.getWidth()/2-l.getWidth()/2);
        l.getParent().repaint();
    }

    private void addbutton(Image img, String firstname,String lastname,String email, Users user){
        int height = Display.getInstance().convertToPixels(11.5f);
        int widht = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(widht, height));
        image.setUIID("Label");
                
        Container cnt = BorderLayout.west(image);
        TextArea ta = new TextArea(firstname);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);
        
        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(ta));
        add(cnt);
        
    }
}
     
   

