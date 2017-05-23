/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hairsoftproject;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Eduardo
 */
public class TelaInicialController implements Initializable {

    
    
    
    @FXML
    public AnchorPane stack;
    public ImageView img;
    
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    void listaClientes(){
        stack.getChildren().clear();
        stack.getChildren().add(getNode("listaClientes.fxml"));

    }
    
    @FXML
    void cadastraCliente(){
        stack.getChildren().clear();
        stack.getChildren().add(getNode("cadastrarCliente.fxml"));
    }
    
    @FXML
    void verGrafico(){
    stack.getChildren().clear();
    stack.getChildren().add(getNode("grafico.fxml"));
    }
    
    @FXML
    void agendaCliente(){
    stack.getChildren().clear();
    stack.getChildren().add(getNode("agendarCliente.fxml"));
    }
    
    @FXML
    void visualizaAgenda(){
    stack.getChildren().clear();
    stack.getChildren().add(getNode("visualizaAgenda.fxml"));
    }
    

    @FXML
    void ajuda(){
    stack.getChildren().clear();
    stack.getChildren().add(getNode("ajuda.fxml"));
    }
    
    @FXML
    void home() throws FileNotFoundException{
        stack.getChildren().clear();
    }
    
  
    public Node getNode(String node){
        Node no = null;
        try {
            no = FXMLLoader.load(getClass().getResource(node));
        } catch (Exception e) {
        }
        return no;
        
    }
   
}
