package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entites.Category;
import com.mycompany.entites.Product;

import com.mycompany.services.ServiceProduct;
import java.util.ArrayList;


public class AddProductForm extends Form {

    public AddProductForm(Form previous) {
        setTitle("Add a new product");
        setLayout(BoxLayout.y());

        TextField name = new TextField("", "name");
        TextField description = new TextField("", "description");
        TextField price = new TextField("", "price");
        TextField color = new TextField("", "color");
        TextField brand = new TextField("", "mark");
        TextField quantity = new TextField("", "quantity");
      
        name.getAllStyles().setFgColor(0xFF2261);
        description.getAllStyles().setFgColor(0xFF2261);
        price.getAllStyles().setFgColor(0xFF2261);
                color.getAllStyles().setFgColor(0xFF2261);

        brand.getAllStyles().setFgColor(0xFF2261);
 quantity.getAllStyles().setFgColor(0xFF2261);


        ArrayList<Category> list = ServiceProduct.getInstance().getAllCategories();

        ComboBox<Category> category = new ComboBox<>();

        for (Category c : list) {
            Category cat = new Category(c.getIdCategory(), c.getCategoryName());
            category.addItem(cat);            

        }


        Button btnInsert = new Button("Add Product");

        btnInsert.addActionListener((ActionListener) (ActionEvent evt) -> {
            if ((name.getText().length() == 0) || (description.getText().length() == 0) || (price.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    Product p = new Product(
                            Integer.parseInt(quantity.getText()),
                            category.getSelectedItem().getIdCategory(),
                            Integer.parseInt(price.getText()),
                            String.valueOf(name.getText()),
                            String.valueOf(color.getText()),
                            String.valueOf(brand.getText()),
                            String.valueOf(description.getText())

                    );
                    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxx");

                    System.out.println(p);
                    System.out.println(ServiceProduct.getInstance().addProduct(p));

                    if (ServiceProduct.getInstance().addProduct(p)) {
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

        addAll(name, description,quantity, price,color,brand,category, btnInsert);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
