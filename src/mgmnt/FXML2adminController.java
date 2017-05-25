/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgmnt;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ajay
 */
public class FXML2adminController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    public Button logb;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public static void centerOnScreen(Stage stage) {
        stage.centerOnScreen();
        stage.setY(stage.getY() * 3f / 2f);
    }
    public void addemp() throws Exception{
        URL url = getClass().getResource("adminaddemp.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Pane pan = (Pane) fxmlLoader.load(url.openStream());
        pan.getStylesheets().add(getClass().getResource("adminaddemp.css").toString());
        pane.getChildren().clear();
        pane.getChildren().add(pan);
    }
    public void rememp() throws Exception{
        URL url = getClass().getResource("adminremoveemp.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Pane pan = (Pane) fxmlLoader.load(url.openStream());
        pan.getStylesheets().add(getClass().getResource("adminremoveemp.css").toString());
        pane.getChildren().clear();
        pane.getChildren().add(pan);
    }
    public void addcust() throws Exception{
        URL url = getClass().getResource("adminaddcustomer.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Pane pan = (Pane) fxmlLoader.load(url.openStream());
        pan.getStylesheets().add(getClass().getResource("adminaddcustomer.css").toString());
        pane.getChildren().clear();
        pane.getChildren().add(pan);
    }
    public void remcust() throws Exception{
        URL url = getClass().getResource("adminremovecustomer.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Pane pan = (Pane) fxmlLoader.load(url.openStream()); 
        pan.getStylesheets().add(getClass().getResource("adminremovecustomer.css").toString());
        pane.getChildren().clear();
        pane.getChildren().add(pan);
    }
    public void plans() throws Exception{
        URL url = getClass().getResource("adminplans.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Pane pan = (Pane) fxmlLoader.load(url.openStream()); 
        pan.getStylesheets().add(getClass().getResource("adminplans.css").toString());
        pane.getChildren().clear();
        pane.getChildren().add(pan);
}
    public void logout() throws Exception{
        Stage stage; 
        Parent root;
        stage=(Stage) logb.getScene().getWindow();
        stage.setTitle("WELCOME");
        root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene sc = new Scene(root,600,400);
        sc.getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
        stage.setScene(sc);
        stage.centerOnScreen();
    }

}
