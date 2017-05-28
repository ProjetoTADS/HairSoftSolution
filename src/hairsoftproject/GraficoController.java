package hairsoftproject;

import static hairsoftproject.DAO.ConexaoMySql.fecharConexao;

import java.sql.Connection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


/**
 * FXML Controller class
 *
 * @author Eduardo Dias
 */
public class GraficoController implements Initializable {
    
    @FXML
    private PieChart pieChart;
    @FXML
    private Button btnAuto;
    
    private Label label;
    
    private ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

    //INICIANDO CONEXAO COM BANCO DE DADOS
    Connection conexao = hairsoftproject.DAO.ConexaoMySql.getConexaoMySql();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*PieChart pieChart = new PieChart();*/
        try {
            carregaDadosBanco();
           pieChart.getData().addAll(data);
        } catch (SQLException e) {
            e.getErrorCode();
            System.out.println("Erro no banco de dados.");
        }
       

    }
    
    @FXML
    public void atualizaAuto() throws SQLException{
        /*SE O BOTÃO FOR ACIONADO*/
            try {
      
                
                data.clear();
                pieChart.getData().clear();
                
                carregaDadosBanco();
                
                pieChart.getData().addAll(data);      	
        
                
            } catch (SQLException ex) {
                Logger.getLogger(GraficoController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void carregaDadosBanco() throws SQLException{
        try{
        Statement stt = conexao.createStatement();
        stt.executeUpdate("SET lc_time_names = 'pt_BR';");
        ResultSet query = stt.executeQuery("SELECT UPPER (DATE_FORMAT(DATA_AGENDAMENTO, '%M')) as MES, COUNT(MONTH(DATA_AGENDAMENTO)) AS OCORRENCIAS FROM AGENDAMENTO WHERE ATIVO = 'SIM' GROUP BY MONTH(DATA_AGENDAMENTO);");
           while (query.next()){
        data.add(new PieChart.Data(query.getString(1), query.getInt(2)));
           }fecharConexao();
        }catch(SQLException e){
           System.out.println("ERRO NO BANCO DE DADOS");
        }
    
    }
}
