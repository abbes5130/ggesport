
package com.mycompany.myapp;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Product;

public class DetailsProductForm extends Form{
    public DetailsProductForm(Form previous , int id, Product p) {
        setTitle("Checking this Product");
        setLayout(BoxLayout.y());
        Container ct = BorderLayout.center(previous);
        Label productN = new Label("Product Name : "+ p.getName());
        Label productD = new Label("Product Description : "+ p.getDescription());
        Label productP = new Label("Product Price : "+ p.getPrice());
        Label productc = new Label("Product Id : "+ id);
        ct.add(
            BorderLayout.CENTER,BoxLayout.encloseY(
            BoxLayout.encloseX(productN),
            BoxLayout.encloseX(productD),
            BoxLayout.encloseX(productP),
            BoxLayout.encloseX(productc)
        )

    );
    
    addAll(ct);
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
}