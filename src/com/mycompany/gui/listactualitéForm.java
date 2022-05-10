/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.contacts.Contact;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.BaseForm;
import com.mycompany.entites.News;
import com.mycompany.services.ServiceNews;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Map;
/**
 *
 * @author USER
 */
public class listactualitéForm extends Form{
    
   
         
    public listactualitéForm(Form res   ) {         
 setLayout(BoxLayout.y());
 ArrayList<News>list = ServiceNews.getInstance().getAllNews();
        
        for(News rec : list ) {
           
              String urlImage =rec.getBg_img();//image statique pour le moment ba3d taw fi  videos jayin nwarikom image 
            
             Image placeHolder = Image.createImage(120, 90);
             EncodedImage enc =  EncodedImage.createFromImage(placeHolder,false);
             URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
                addButton(urlim,rec,res);
        
                
                
        }
        
        
        
    }
     private void addButton(Image img,News rec , Form res) {
        
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
                Container cnt = BorderLayout.west(image);

        
        //kif nzidouh  ly3endo date mathbih fi codenamone y3adih string w y5alih f symfony dateTime w ytab3ni cha3mlt taw yjih
        Label creationdateTxt = new Label("Date : "+rec.getCreation_date(),"NewsTopLine2");
        Label descriptionTxt = new Label("description : "+rec.getDescription(),"NewsTopLine2");
        Label titleTxt = new Label("title : "+rec.getTitle(),"NewsTopLine3");
                Label ttleTxt = new Label("title : "+rec.getTitle(),"NewsTopLine3");
                String urlImage =rec.getBg_img();//image statique pour le moment ba3d taw fi  videos jayin nwarikom image 
            
             Image placeHolder = Image.createImage(120, 90);
             EncodedImage enc =  EncodedImage.createFromImage(placeHolder,false);
          ImageViewer iv = new ImageViewer(enc);
  
        if(rec.getId()== 0 ) {
            ttleTxt.setText("non Traitée");
        }
        else 
            ttleTxt.setText("Traitée");
       
        
        //supprimer button
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        //click delete icon
        lSupprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer ce reclamation ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                //n3ayto l suuprimer men service News
                if(ServiceNews.getInstance().deleteNews(rec.getId())) {
                    new listactualitéForm(res).show();
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
            new modifieractualitéForm(res,rec,rec.getId()).show();
        });
        
        
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(
                
                BoxLayout.encloseX(titleTxt),
                BoxLayout.encloseX(creationdateTxt),
                                BoxLayout.encloseX(iv),

                BoxLayout.encloseX(descriptionTxt,lModifier,lSupprimer)));
        
        
        
        add(cnt);
        
    
 /*

 Form hi=new Form ("Contacts List", new BorderLayout ());
final FontImage placeholderImage=FontImage.createMaterial (FontImage.MATERIAL_PERSON, "Label", 6);
Container list=new InfiniteContainer (){
  private Contact[] contacts;
  public Component[] fetchComponents (int index, int amount){
    if (index == 0) // for pull for refresh by fetching the contacts over again
      contacts=Display.getInstance ().getAllContacts(true, true, false, false, false, false);
    if (index+amount>contacts.length){
      amount=contacts.length-index;
      if (amount <= 0){// we reached the end of the infinite contacts
        return null;
      }
    }
    Component[] more=new Component[amount];
for (int iter=0;iter<amount;iter++){
  int offset=index+iter;
  MultiButton mb=new MultiButton (contacts[offset].getDisplayName ());
  String contactId=contacts[iter].getId();
  mb.setIcon (placeholderImage);
Display.getInstance ().callSeriallyOnIdle( () ->{
  Contact cnt=Display.getInstance ().getContactById(contactId,true,true,false,false,false);
  Image i=cnt.getPhoto ();
  if (i != null) mb.setIcon (i.fill(placeholderImage.getWidth (), placeholderImage.getHeight()));
});
   more[iter]=mb;
}
return more;
}
};
add(list);
    
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    */
 /*public listactualitéForm(Form previous) {
Container list=new Container (BoxLayout.y());
list.setScrollableY(true);



for (int iter=0;iter<1000;iter++){
    
    MultiButton mb=new MultiButton ("List entry");
            //mb.setText(ServiceNews.getInstance().getAllNews().toString());

   mb.setTextLine2 ("Further details....");
    list.add(mb);
    
}
add(list);

getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());

           }
           */
 
           }
           
           }

    

        /*public listactualitéForm(Form previous) {
        setTitle("List actualité");
        int deviceWidth = Display.getInstance().getDisplayWidth();

SpanLabel sp = new SpanLabel();
        sp.setText(ServiceNews.getInstance().getAllNews().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }*/

    

