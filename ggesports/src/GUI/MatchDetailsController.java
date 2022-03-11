/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Match;
import entities.Reservation;
import entities.Users;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import services.ReservationService;
import utils.MyDB;



/**
 * FXML Controller class
 *
 * @author DeadlyDaggerS
 */
public class MatchDetailsController implements Initializable {
    

    Connection cnx2;

    public MatchDetailsController() {
        cnx2 = MyDB.getInstance().getCnx();
    }
    
    private int matchId;
    private int id_user;
    @FXML
    Label AwayTeam;
    @FXML
    Label HomeTeam;
    @FXML
    Label matchTime;
    @FXML
    Label matchDate;
    @FXML
    Label matchPlace;
    @FXML
    Label matchPrice;
    @FXML
    ImageView HomeImage;
    @FXML
    ImageView AwayImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //get List of USers
        
 
        
        
        // TODO
    }
    
    public void getIdmatch(int matchId){
         
        this.matchId = matchId;
    }

    public void loadDetails(int matchId) {
        // Load details from matchId
        PreparedStatement statement;
        String req = "SELECT price,location,nb_place_dispo FROM `matchs` WHERE matchs.id_match=?";

        try {
            statement = cnx2.prepareStatement(req);
            statement.setInt(1, matchId);

            ResultSet rst;
            rst = statement.executeQuery();

            while (rst.next()) {
                int price = rst.getInt("price");
                matchPrice.setText("" + price);
                String location = rst.getString("location");
                matchPlace.setText("" + location);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        // Create pane for player details.
    }

    public void BookTicket(ActionEvent e) throws DocumentException, IOException 
    {
        ReservationService reservationService = new ReservationService();
         id_user = Users.current_user.getId_user();
        
       System.out.println(matchId);

        
        reservationService.CreateRes(id_user, matchId);
        List test = reservationService.RetrieveRes(id_user);
        
        String delim = "\n\n- ";
 
        StringBuilder sb = new StringBuilder();
 
        int i = 0;
        while (i < test.size() - 1)
        {
            sb.append(test.get(i));
            sb.append(delim);
            i++;
        }
        sb.append(test.get(i));
 
        String res = sb.toString();
        System.out.println(res);    
        
        
pdfCreation(res);
        
    }
    
public void pdfCreation(String it) throws DocumentException, IOException{

Document document = new Document();
PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Ghassene\\Desktop\\hello.pdf"));

document.open();
 document.add(new Paragraph(it));

// Save document
document.close();

}
}
