/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

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
import com.mycompany.myapp.entities.ComboTeam;
import com.mycompany.myapp.entities.Matches;
import com.mycompany.myapp.services.MatchesServices;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ghassene Badra
 */
public class UpdateMatchForm extends Form{
    
    public UpdateMatchForm(Form previous , int id, Matches m)
    {
        setLayout(BoxLayout.y());

        TextField location = new TextField( m.getLocation(),"location");
        TextField link = new TextField(m.getLink(),"link" );
        TextField nbSeats = new TextField((String.valueOf(m.getNbSeats())),"nomber of seats" );
        TextField price = new TextField((String.valueOf(m.getPrice())), "Price");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        Picker timePicker = new Picker();
        timePicker.setType(Display.PICKER_TYPE_TIME);

        ArrayList<ComboTeam> list = MatchesServices.getInstance().getAllTeams();

        ComboBox<ComboTeam> comboTeam1 = new ComboBox<>();
        ComboBox<ComboTeam> comboTeam2 = new ComboBox<>();

        for (ComboTeam rec : list) {
            ComboTeam team = new ComboTeam(rec.getIdTeam(), rec.getTeamName());
            comboTeam1.addItem(team);
            comboTeam2.addItem(team);
            

        }

        datePicker.setDate(new Date());
        timePicker.setTime(10 * 60);

        //Time time = timePicker.getTime();
        Date date = datePicker.getDate();

        Button btnInsert = new Button("Update Match");

        btnInsert.addActionListener((ActionListener) (ActionEvent evt) -> {
            if ((location.getText().length() == 0) || (price.getText().length() == 0) || (datePicker.getText().length() == 0) || (timePicker.getText().length() == 0) || (link.getText().length() == 0) || (nbSeats.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    Matches t = new Matches(
                            String.valueOf(location.getText()),
                            String.valueOf(link.getText()),
                            Integer.parseInt(nbSeats.getText()),
                            Integer.parseInt(price.getText()),
                            comboTeam1.getSelectedItem().getIdTeam(),
                            comboTeam2.getSelectedItem().getIdTeam()
                    );

                    if (MatchesServices.getInstance().UpdateMatch(t,id)) {
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

        addAll(location, link, nbSeats, price, datePicker, timePicker, comboTeam1, comboTeam2, btnInsert);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    }
    
