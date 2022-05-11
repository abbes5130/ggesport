
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class storeForm extends BaseForm{
    Form current;
    public storeForm() {
        current=this;  
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add Product");
        Button btnListTasks = new Button("List Product");

        btnAddTask.addActionListener(e-> new AddProductForm(current).show());
        btnListTasks.addActionListener(e-> new ListProductsForm((BaseForm)current).show());
        addAll(btnAddTask,btnListTasks);
    }
}