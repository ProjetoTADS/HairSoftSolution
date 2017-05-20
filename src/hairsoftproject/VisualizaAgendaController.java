//stLmpp

package hairsoftproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private ObservableList<visualizaAgendaLoader>data;
    
    Connection MyConn = hairsoftproject.DAO.ConexaoMySql.getConexaoMySql();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDataBaseAgen();
    }
    private void loadDataBaseAgen() {
        try {
            data=FXCollections.observableArrayList();
            Statement Stmt = MyConn.createStatement();
            ResultSet myRs = Stmt.executeQuery("SELECT CL.NOME, DATE_FORMAT(AG.DATA_AGENDAMENTO, '%d-%m-%Y'), AG.SERVICO_DES, AG.PRECO"
                    + "                           FROM AGENDAMENTO AG,"
                    + "                                CLIENTE CL"
                    + "                          WHERE CL.CLIENTE_ID = AG.CLIENTE_ID"
                    + "                            AND IFNULL(CL.ATIVO, 'SIM') = 'SIM'");
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
