/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author bhk
 */
public class NewsForm extends BaseForm{
Form current;

    public NewsForm() {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add actualité");
        Button btnListTasks = new Button("List actualité");
        
        btnAddTask.addActionListener(e-> new AddactualitéForm(current).show());
        btnListTasks.addActionListener(e-> new listactualitéForm(current).show());
        addAll(btnAddTask,btnListTasks);
        
        
    }
      
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
}
