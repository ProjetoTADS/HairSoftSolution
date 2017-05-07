/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hairsoftproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Dani
 */
public class VisualizaAgendaController implements Initializable {
    
    @FXML
    private TableView<visualizaAgendaLoader> tableAgen;
    @FXML
    private TableColumn<visualizaAgendaLoader, String> clienteTbl;
    @FXML
    private TableColumn<visualizaAgendaLoader, String> dataTbl;
    @FXML
    private TableColumn<visualizaAgendaLoader, String> servicosTbl;
    @FXML
    private TableColumn<visualizaAgendaLoader, String> precoTbl;
    @FXML
    private Button carregaBtn;

    private ObservableList<visualizaAgendaLoader>data;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    //Ação do botão Carregar
    @FXML
    private void loadDataBaseAgen(ActionEvent event) {
        try {
            //Connection MyConn = DriverManager.getConnection("jdbc:mysql://hairsoft.mysql.uhserver.com/hairsoft", "hairsoft", "uninove+1");
            //Conexão com BD através da Classe ConexaoMySql
            Connection MyConn = hairsoftproject.DAO.ConexaoMySql.getConexaoMySql();
            //Criando um ArrayList para armazenar os dados do banco
            data=FXCollections.observableArrayList();
            Statement Stmt = MyConn.createStatement();
            //Armazenando o resultado dentro do ArrayList
            ResultSet myRs = Stmt.executeQuery("SELECT CL.NOME, DATE_FORMAT(AG.DATA_AGENDAMENTO, '%d-%m-%Y'), AG.SERVICO_DES, AG.PRECO"
                    + "                           FROM AGENDAMENTO AG,"
                    + "                                CLIENTE CL"
                    + "                          WHERE CL.CLIENTE_ID = AG.CLIENTE_ID");
            while (myRs.next()){
                data.add(new visualizaAgendaLoader(myRs.getString(1), myRs.getString(2), myRs.getString(3), myRs.getString(4)));
            }
        }
        //Exception bem fuleira, mas ainda vou modificar ela
        catch (Exception exc){
            exc.printStackTrace();
        }
        //Inserindo os dados nas strings da tabela do SB que criei
        clienteTbl.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        dataTbl.setCellValueFactory(new PropertyValueFactory<>("data"));
        servicosTbl.setCellValueFactory(new PropertyValueFactory<>("servicos"));
        precoTbl.setCellValueFactory(new PropertyValueFactory<>("preco"));
        
        //Mostrando os dados
        tableAgen.setItems(null);
        tableAgen.setItems(data);
    }
    
}
