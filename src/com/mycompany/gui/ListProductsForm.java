
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.entites.Product;

import com.mycompany.services.ServiceProduct;
import java.util.ArrayList;

public class ListProductsForm extends Form {
     public ListProductsForm(Form previous) {
        ArrayList<Product>ListProduct = ServiceProduct.getInstance().getAllProducts();
        setTitle("Add a new Product");
        setLayout(BoxLayout.y());

        for (Product p : ListProduct){
            productShow(p, previous);
        }
       
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

    private void productShow(Product p, Form previous) {
        SpanLabel sp = new SpanLabel();
        Container ct = BorderLayout.center(previous);
        Label productN = new Label("product Name : "+ p.getName());
        Label productD = new Label("product Description : "+ p.getDescription());
        Label productP = new Label("product Price: "+ p.getPrice());

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
            System.out.println("u clicked me");

            new UpdateProductForm(previous,p.getId(),p).show();

        });
    show.addPointerPressedListener(l->{
        new DetailsProductForm(previous,p.getIdCategory(),p).show();
    });

    supp.addPointerPressedListener(l ->{
        Dialog diag = new Dialog("supp");
        if(diag.show("supp", "You WANT TO DELETE this product ?","No","OK")){
            diag.dispose();
        }else{
            diag.dispose();
        }
        
        if(ServiceProduct.getInstance().deleteProduct(p.getId())){
            new ListProductsForm(previous).show();
        }
        });


    ct.add(
            BorderLayout.CENTER,BoxLayout.encloseY(
            BoxLayout.encloseX(productN),
            BoxLayout.encloseX(productD),
            BoxLayout.encloseX(productP),
            BoxLayout.encloseX(supp),
            BoxLayout.encloseX(update),
            BoxLayout.encloseX(show)
        )
  );
  
    addAll(ct);
  
}
     
}