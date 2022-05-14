//
//package GUI;
//
//import com.jfoenix.controls.JFXSlider;
//import entities.Product;
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.geometry.Insets;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.control.Button;
//import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.VBox;
//import services.ProductCRUD;
//import java.util.regex.*;
//import javafx.scene.input.DragEvent;
//import javafx.stage.FileChooser;
//
//
//public class MainStoreController implements Initializable {
//    
//    private int col;
//    private int row;
//    final FileChooser fc = new FileChooser();
//    private String[] Categories= {"Tshirts","Sweatshirts","Pants","Accessories","Hardwares"};
//    ProductCRUD pcrud =new ProductCRUD();
//    List<Product> productList= pcrud.getAll();
//    //productList = pcrud.getAll();
//    ObservableList<Product> observableProducts= FXCollections.observableList(productList);
//
//    
//    @FXML
//    private ScrollPane scrollpane;
//    @FXML
//    private GridPane gridpane;
//    @FXML
//    private AnchorPane updateProductScene;
//    @FXML
//    private ImageView U_productImg;
//    @FXML
//    private TextField U_productCategoryLabel;
//    @FXML
//    private TextField U_productNameLabel;
//    @FXML
//    private TextField U_productPriceLabel;
//    @FXML
//    private TextField U_productDescriptionLabel;
//    @FXML
//    private Button U_updateBtn;
//    @FXML
//    private AnchorPane productDetailsScene;
//    @FXML
//    private Label D_productNameLabel;
//    @FXML
//    private Label D_productPriceLabel;
//    @FXML
//    private Label D_productPriceReducedLabel;
//    @FXML
//    private Pane D_productRatingPane;
//    @FXML
//    private Label D_productDescriptionLabel;
//    @FXML
//    private Label D_productQuantityLabel;
//    @FXML
//    private Button D_updateBtn;
//    @FXML
//    private Button D_deleteBtn;
//    @FXML
//    private Label D_productBrandLabel;
//    @FXML
//    private Label D_productColorLabel;
//    @FXML
//    private Label D_productCategoryLabel;
//    @FXML
//    private AnchorPane addProductScene;
//    @FXML
//    private TextField A_productPriceField;
//    @FXML
//    private TextArea A_productDescriptionField;
//    @FXML
//    private TextField A_productBrandField;
//    @FXML
//    private TextField A_productColorField;
//    @FXML
//    private TextField A_productQuantityField;
//    @FXML
//    private TextField A_productDiscountField;
//    @FXML
//    private TextField A_productNameField;
//    @FXML
//    private Button A_AddBtn;
//    @FXML
//    private Label A_productImgField;
//    @FXML
//    private Button L_addProductBtn;
//    @FXML
//    private Button F_sweatshirtsBtn;
//    @FXML
//    private Button F_pantsBtn;
//    @FXML
//    private Button F_accessoriesBtn;
//    @FXML
//    private TextField searchField;
//    @FXML
//    private ComboBox<String> A_categoriesCbx;
//    @FXML
//    private Label A_nameErrorLabel;
//    @FXML
//    private Label A_brandErrorLabel;
//    @FXML
//    private Label A_imgErrorLabel;
//    @FXML
//    private Label A_priceErrorLabel;
//    @FXML
//    private Label A_quantityErrorLabel;
//    @FXML
//    private Label A_discountErrorLabel;
//    @FXML
//    private Label A_filePath;
//    @FXML
//    private AnchorPane productListScene;
//    @FXML
//    private Button F_tshirtsBtn;
//    @FXML
//    private Button F_HardwaresBtn;
//    @FXML
//    private JFXSlider F_minPrice;
//    @FXML
//    private JFXSlider F_maxPrice;
//    
//    
//    
//    
//    @Override
//    public void initialize(URL url, ResourceBundle rb){
//        A_categoriesCbx.getItems().addAll(Categories);
//        A_categoriesCbx.setOnAction(this::getCategory);
//    }  
//    
//    public void getCategory(ActionEvent event){
//        String Category = A_categoriesCbx.getValue();
//        System.out.println(Category);
//    }
//    
//    @FXML
//    public void home(ActionEvent actionEvent)throws IOException{
//        Parent fxml = FXMLLoader.load(getClass().getResource("homePage.fxml"));
//        scrollpane.setContent(fxml);
//    }
//
//    @FXML
//    private void teams(ActionEvent event) throws IOException{
//        Parent fxml = FXMLLoader.load(getClass().getResource("teams.fxml"));
//        scrollpane.setContent(fxml);
//    }
//
//    @FXML
//    private void matchs(ActionEvent event) throws IOException{
//        Parent fxml = FXMLLoader.load(getClass().getResource("matchs.fxml"));
//        scrollpane.setContent(fxml);
//    }
//
//    @FXML
//    private void news(ActionEvent event) throws IOException{
//        Parent fxml = FXMLLoader.load(getClass().getResource("news.fxml"));
//        scrollpane.setContent(fxml);        
//    }
//    
//        
//    private void populateGridpane(ObservableList<Product> observablelst){
//        col=0;
//        row=0;
//        gridpane.getChildren().removeAll(gridpane.getChildren());
//        observablelst.forEach((p)->{
//            try {
//                FXMLLoader fxmlloader = new FXMLLoader();
//                fxmlloader.setLocation(getClass().getResource("productItem.fxml"));
//                VBox vb;
//                vb = fxmlloader.load();
//                ProductItemController productItemController= fxmlloader.getController();
//                productItemController.setValues(p);
//                gridpane.add(vb, col++, row);
//                GridPane.setMargin(vb, new Insets(90,90,10,90));
//                
//                if(col==4){
//                    col=0;
//                    row++;
//                }
//
//            } catch (IOException ex) {
//                Logger.getLogger(StoreController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        });
//        
//        scrollpane.setContent(gridpane);
//    }
//    
//    @FXML
//    private void store(ActionEvent event) throws IOException{
//        productListScene.setManaged(true);
//        productListScene.setVisible(true);
//        updateProductScene.setManaged(false);
//        updateProductScene.setVisible(false);
//        addProductScene.setManaged(false);
//        addProductScene.setVisible(false);
//        productDetailsScene.setManaged(false);
//        productDetailsScene.setVisible(false);
//        populateGridpane(observableProducts);
//        
//    }
//    
//
//    public void ShowProductDetails(MouseEvent event){
//        productDetailsScene.setManaged(true);
//        productDetailsScene.setVisible(true);
//        updateProductScene.setManaged(false);
//        updateProductScene.setVisible(false);
//        productListScene.setManaged(false);
//        productListScene.setVisible(false);
//        addProductScene.setManaged(false);
//        addProductScene.setVisible(false);
//    }
//
//    public void setProductDetailsView(Product p) {
////        System.out.println(p);
////        D_productNameLabel.setText(p.getName());
////        D_productPriceLabel.setText(Float.toString(p.getPrice()));
//        //D_productPriceReducedLabel.setText(value);
//        //D_productRatingPane.setText(value);
////        D_productDescriptionLabel.setText(p.getDescription());
////        D_productQuantityLabel.setText(Integer.toString(p.getQuantite_stock()));
////        D_productBrandLabel.setText(p.getBrand());
////        D_productColorLabel.setText(p.getColor());
//        //D_productCategoryLabel.setText();
//        
//        
////        productDetailsScene.setManaged(true);
////        productDetailsScene.setVisible(true);
//        updateProductScene.setManaged(false);
//        updateProductScene.setVisible(false);
//        productListScene.setManaged(false);
//        productListScene.setVisible(false);
//        addProductScene.setManaged(false);
//        addProductScene.setVisible(false);
//        }
//    
////        gridpane.getChildren().clear();
////        gridpane.add(a, 0, 0);
////        Node n= (Node)a;
////        gridpane.add(n, 1, 1);
//
//
//    @FXML
//    private void Label(MouseEvent event) {
//    }
//
//
//    @FXML
//    private void getTshirtsOnclick(MouseEvent event) {
//        List<Product> TshirtsList=pcrud.getProductsByCategory("TShirts");
//        ObservableList observableTshirts= FXCollections.observableList(TshirtsList);
//        populateGridpane(observableTshirts);
//    }
//
//    @FXML
//    private void getSweatshirtsOnclick(MouseEvent event) {
//        List<Product> SweatshirtsList=pcrud.getProductsByCategory("Sweatshirts");
//        ObservableList observableTshirts= FXCollections.observableList(SweatshirtsList);
//        populateGridpane(observableTshirts);
//    }
//
//    @FXML
//    private void getPantsOnclick(MouseEvent event) {
//        List<Product> PantssList=pcrud.getProductsByCategory("Pants");
//        ObservableList observableTshirts= FXCollections.observableList(PantssList);
//        populateGridpane(observableTshirts);
//    }
//
//    @FXML
//    private void getAccessoriesOnclick(MouseEvent event) {
//        List<Product> AccessoriesList=pcrud.getProductsByCategory("Accessories");
//        ObservableList observableTshirts= FXCollections.observableList(AccessoriesList);
//        populateGridpane(observableTshirts);
//    }
//
//    @FXML
//    private void getHardwaresOnclick(MouseEvent event) {
//        List<Product> HardwaresList=pcrud.getProductsByCategory("Hardwares");
//        ObservableList observableTshirts= FXCollections.observableList(HardwaresList);
//        populateGridpane(observableTshirts);
//    }
//
//    @FXML
//    private void D_goToUpdateProductPageOnclick(MouseEvent event) {
//        updateProductScene.setManaged(true);
//        updateProductScene.setVisible(true);
//        productListScene.setManaged(false);
//        productListScene.setVisible(false);
//        addProductScene.setManaged(false);
//        addProductScene.setVisible(false);
//        productDetailsScene.setManaged(false);
//        productDetailsScene.setVisible(false);
//        
//    }
//
//    @FXML
//    private void D_goToDeleteProductPageOnClick(MouseEvent event) {
//        updateProductScene.setManaged(true);
//        updateProductScene.setVisible(true);
//        productListScene.setManaged(false);
//        productListScene.setVisible(false);
//        addProductScene.setManaged(false);
//        addProductScene.setVisible(false);
//        productDetailsScene.setManaged(false);
//        productDetailsScene.setVisible(false);
//    }
//
//    @FXML
//    private void U_updateProductOnClick(MouseEvent event) {
//    }
//
//    @FXML
//    private void A_addProductOnClick(MouseEvent event) {
//        if(A_productNameField.getText().length()==0){
//            A_nameErrorLabel.setText("this field is required!");
//            A_productNameField.setStyle("-fx-border-color:red;"+
//                    "-fx-border-radius:10;"+
//                    "-fx-background-color:#242933;"+
//                    "-fx-text-fill: #ffffff;");
//        }
//        else if(A_productBrandField.getText().length()==0){
//            A_brandErrorLabel.setText("this field is required!");
//            A_productBrandField.setStyle("-fx-border-color:red;"+
//                    "-fx-border-radius:10;"+
//                    "-fx-background-color:#242933;"+
//                    "-fx-text-fill: #ffffff;");
//        }
//        else if(A_productPriceField.getText().length()==0){
//            A_priceErrorLabel.setText("this field is required!");
//            A_productPriceField.setStyle("-fx-border-color:red;"+
//                    "-fx-border-radius:10;"+
//                    "-fx-background-color:#242933;"+
//                    "-fx-text-fill: #ffffff;");
//        }
//        else if(A_productQuantityField.getText().length()==0){
//            A_quantityErrorLabel.setText("this field is required!");
//            A_productQuantityField.setStyle("-fx-border-color:red;"+
//                    "-fx-border-radius:10;"+
//                    "-fx-background-color:#242933;"+
//                    "-fx-text-fill: #ffffff;");
//        }
//        else{
//            String name=A_productNameField.getText();
//            float price=Float.valueOf(A_productPriceField.getText());
//            String description=A_productDescriptionField.getText();
//            String color=A_productColorField.getText();
//            String brand=A_productBrandField.getText();
//            int discount=Integer.valueOf(A_productDiscountField.getText());
//            Boolean available= true;
//            String categorie= A_categoriesCbx.getValue();
//            int quantite_stock=Integer.valueOf(A_productQuantityField.getText());
//            String filePath= A_filePath.getText();
//            Product p=new Product(name,price,description,color,brand,discount,available,categorie,quantite_stock,filePath);
//            pcrud.add(p);
//            System.out.println("hhhhh");
//        }
//    }
//
//
//    @FXML
//    private void L_goToAddProductOnclick(MouseEvent event) {
//        
//        addProductScene.setManaged(true);
//        addProductScene.setVisible(true);
//        updateProductScene.setManaged(false);
//        updateProductScene.setVisible(false);
//        productListScene.setManaged(false);
//        productListScene.setVisible(false);
//        productDetailsScene.setManaged(false);
//        productDetailsScene.setVisible(false);
//    }
//    
//
//    @FXML
//    private void A_verifNameOnKeyReleased(KeyEvent event) {
//        if(!Pattern.matches("^[a-zA-Z]*$", A_productNameField.getText())){
//            A_nameErrorLabel.setText("the product name must contain only letters");
//            A_productNameField.setStyle("-fx-border-color:red;"+
//                    "-fx-border-radius:10;"+
//                    "-fx-background-color:#242933;"+
//                    "-fx-text-fill: #ffffff;");
//        }
//        else{
//            A_nameErrorLabel.setText("");
//            A_productNameField.setStyle("-fx-border-color:#ffffff;"+
//                    "-fx-border-radius:10;"+
//                    "-fx-background-color:#242933;"+
//                    "-fx-text-fill: #ffffff;");
//        }
//    }
//
//    @FXML
//    private void A_verifPriceOnKeyReleased(KeyEvent event) {
//        if(!Pattern.matches("^[0-9]*$", A_productPriceField.getText())){
//            A_priceErrorLabel.setText("the product price must contain only digits");
//            A_productPriceField.setStyle("-fx-border-color:red;"+
//                    "-fx-border-radius:10;"+
//                    "-fx-background-color:#242933;"+
//                    "-fx-text-fill: #ffffff;");
//        }
//        else{
//            A_priceErrorLabel.setText("");
//            A_productPriceField.setStyle("-fx-border-color:#ffffff;"+
//                    "-fx-border-radius:10;"+
//                    "-fx-background-color:#242933;"+
//                    "-fx-text-fill: #ffffff;");
//        }
//    }
//
//    @FXML
//    private void A_verifBrandOnKeyReleased(KeyEvent event) {
//        if(!Pattern.matches("^[a-zA-Z]*$", A_productBrandField.getText())){
//            A_brandErrorLabel.setText("the product brand must contain only letters");
//            A_productBrandField.setStyle("-fx-border-color:red;"+
//                    "-fx-border-radius:10;"+
//                    "-fx-background-color:#242933;"+
//                    "-fx-text-fill: #ffffff;");
//        }
//        else{
//            A_brandErrorLabel.setText("");
//            A_productBrandField.setStyle("-fx-border-color:#ffffff;"+
//                    "-fx-border-radius:10;"+
//                    "-fx-background-color:#242933;"+
//                    "-fx-text-fill: #ffffff;");
//        }
//    }
//
//    @FXML
//    private void A_verifQuantityOnKeyReleased(KeyEvent event) {
//        if(!Pattern.matches("^[0-9]*$", A_productQuantityField.getText())){
//            A_quantityErrorLabel.setText("the product quantity must contain only digits");
//            A_productQuantityField.setStyle("-fx-border-color:red;"+
//                    "-fx-border-radius:10;"+
//                    "-fx-background-color:#242933;"+
//                    "-fx-text-fill: #ffffff;");
//        }
//        else{
//            A_quantityErrorLabel.setText("");
//            A_productQuantityField.setStyle("-fx-border-color:#ffffff;"+
//                    "-fx-border-radius:10;"+
//                    "-fx-background-color:#242933;"+
//                    "-fx-text-fill: #ffffff;");
//        }
//    }
//
//    @FXML
//    private void A_verifDiscountOnKeyReleased(KeyEvent event) {
//        if(A_productNameField.getText().length()>0&&A_productBrandField.getText().length()>0&&A_productPriceField.getText().length()>0&&A_productQuantityField.getText().length()>0){
//            A_AddBtn.setDisable(false);
//        }
//        if(!Pattern.matches("^[0-9]*$", A_productDiscountField.getText())){
//            A_discountErrorLabel.setText("the product discount must contain only digits");
//            A_productDiscountField.setStyle("-fx-border-color:red;"+
//                    "-fx-border-radius:10;"+
//                    "-fx-background-color:#242933;"+
//                    "-fx-text-fill: #ffffff;");
//        }
//        else if(!Pattern.matches("^[0-9]{1,2}$", A_productDiscountField.getText())){
//            A_discountErrorLabel.setText("the product discount must not exceed 2 digits");
//            A_productDiscountField.setStyle("-fx-border-color:red;"+
//                    "-fx-border-radius:10;"+
//                    "-fx-background-color:#242933;"+
//                    "-fx-text-fill: #ffffff;");
//        }
//        else{
//            A_discountErrorLabel.setText("");
//            A_productDiscountField.setStyle("-fx-border-color:#ffffff;"+
//                    "-fx-border-radius:10;"+
//                    "-fx-background-color:#242933;"+
//                    "-fx-text-fill: #ffffff;");
//        }
//    }
//
//    @FXML
//    private void A_addProductImg(MouseEvent event) {
//        fc.setInitialDirectory(new File(System.getProperty("user.home")));
//        fc.getExtensionFilters().clear();
//        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image files", "*.png","*.jpg"));
//        File file = fc.showOpenDialog(null);
//        if(file!=null){
//            A_productImgField.setText(file.toURI().toString());
//            File sourcefile= new File(A_productImgField.getText());
//            String sourcefilename= sourcefile.getName();
//            String destinationPath = new File("src/deployImg").getAbsolutePath();
//            String absolutePath=destinationPath+"\\"+sourcefilename;
//            
//            System.out.println(absolutePath);
//            boolean b= file.renameTo(new File(absolutePath));
//            System.out.println(b);
//            A_filePath.setText(absolutePath);
//            
//        }
//    }
//
//    @FXML
//    private void searchOnKeyReleased(KeyEvent event) {
//        List<Product> productSearchList=pcrud.searchProducts(searchField.getText());
//        ObservableList observableSearchProducts= FXCollections.observableList(productSearchList);
//        populateGridpane(observableSearchProducts);
//    }
//
//    @FXML
//    private void getMinPrice(MouseEvent event) {
//        double minPrice= F_minPrice.getValue();
//        double maxPrice= F_maxPrice.getValue();
//        List<Product> listMinMax= pcrud.getProductsByPrice((float)minPrice, (float)maxPrice);
//        ObservableList observableMinMax= FXCollections.observableList(listMinMax);
//        populateGridpane(observableMinMax);
//    }
//    
//}
