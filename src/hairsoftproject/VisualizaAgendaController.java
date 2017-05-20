//stLmpp

package hairsoftproject;

import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

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
    private TableColumn<visualizaAgendaLoader, String> agenId;
    @FXML
    private Button deletarBtn;
    
    private ObservableList<visualizaAgendaLoader>data;
    
    Connection MyConn = hairsoftproject.DAO.ConexaoMySql.getConexaoMySql();
    
    String agen_id;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaAgenda();
        tableAgen.getSelectionModel().selectedItemProperty().addListener(
         (observable, antigo, novo) -> selecionarItem(novo)
        ); 
    }
    private void carregaAgenda(){
        try {
            data=FXCollections.observableArrayList();
            Statement Stmt = MyConn.createStatement();
            ResultSet myRs = Stmt.executeQuery("SELECT AG.AGENDAMENTO_ID, CL.NOME, DATE_FORMAT(AG.DATA_AGENDAMENTO, '%d-%m-%Y'), AG.SERVICO_DES, AG.PRECO"
                    + "                           FROM AGENDAMENTO AG,"
                    + "                                CLIENTE CL"
                    + "                          WHERE CL.CLIENTE_ID = AG.CLIENTE_ID"
                    + "                            AND IFNULL(CL.ATIVO, 'SIM') = 'SIM'");
            while (myRs.next()){
                data.add(new visualizaAgendaLoader(myRs.getString(1), myRs.getString(2), myRs.getString(3), myRs.getString(4), myRs.getString(5)));
            }
        }
        //Exception bem fuleira, mas ainda vou modificar ela
        catch (Exception exc){
            exc.printStackTrace();
        }
        //Inserindo os dados nas strings da tabela do SB que criei
        agenId.setCellValueFactory(new PropertyValueFactory<>("agen"));
        clienteTbl.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        dataTbl.setCellValueFactory(new PropertyValueFactory<>("data"));
        servicosTbl.setCellValueFactory(new PropertyValueFactory<>("servicos"));
        precoTbl.setCellValueFactory(new PropertyValueFactory<>("preco"));
        
        //Mostrando os dados
        tableAgen.setItems(null);
        tableAgen.setItems(data);
    }
    public void selecionarItem(visualizaAgendaLoader visualizaAgendaLoader){
        agen_id = visualizaAgendaLoader.getAgen();
    }
    @FXML
    private void deletarAgen(ActionEvent event) {
            try{
        Statement stm = MyConn.createStatement();
        
         stm.executeUpdate("DELETE FROM AGENDAMENTO"
                 + "         WHERE AGENDAMENTO_ID = " + agen_id);
         JOptionPane.showMessageDialog(null, "Agendamento Excluido!");
         
         carregaAgenda();
         
        }catch(Exception e){
                       JOptionPane.showMessageDialog(null, "Falha ao excluir agendamento! Contate o suporte.");
	} 
    }
    
}
