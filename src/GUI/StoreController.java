//
//package gui;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.layout.GridPane;
//
//public class StoreController implements Initializable {
//    
//    @FXML
//    private GridPane gridpane;
//
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        ProductCRUD pcrud =new ProductCRUD();
//        List<Product> productList= new ArrayList();
//        productList = pcrud.getAll();
//        ObservableList<Product> observableProducts= FXCollections.observableList(productList);
//        
//        observableProducts.forEach((p)->{
//            FXMLLoader fxml = new FXMLLoader();
//            fxml.setLocation(getClass().getResource("productItem.fxml"));
//            VBox vb;
//            try {
//                vb = fxml.load();
//                if(col==3){
//                    col=0;
//                    row++;
//                }
//                gridpane.add(vb, col++, row);
//                GridPane.setMargin(vb, new Insets(20,20,20,20));
//            } catch (IOException ex) {
//                Logger.getLogger(StoreController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        });
//
//    }    
//    
//}