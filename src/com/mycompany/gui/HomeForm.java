/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends BaseForm{
Form current;
    public HomeForm() {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add Match");
        Button btnListTasks = new Button("List Match");
        
        btnAddTask.addActionListener(e-> new AddMatchForm(current).show());
        btnListTasks.addActionListener(e-> new ListMatchForm(current).show());
        addAll(btnAddTask,btnListTasks);
        
        
    }
    
    
}
