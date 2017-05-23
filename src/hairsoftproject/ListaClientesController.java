package hairsoftproject;

import hairsoftproject.DAO.ConexaoMySql;
import static hairsoftproject.DAO.ConexaoMySql.fecharConexao;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;


public class ListaClientesController implements Initializable {

    
    int cdCliente;
    
//INSTANCIANDO OBJETOS DA TELA  
    @FXML
   private TableView<Cliente> tableViewClientes;
   
   @FXML
   private TableColumn<Cliente, String> tableColumnName;

   
   @FXML
   private TableColumn<Cliente, String> tableColumnCPF;
   
   @FXML
   private Button btnAlterar;
   
   @FXML
   private Button btnDeletar;
   
   @FXML
   private Label lblCodigo; 
   
   @FXML
   private Label lblNome;
   
   @FXML 
   private Label lblCPF;      

   @FXML
   private Label lblEndereco;

   @FXML
   private Label lblCelular;
   
   private List<Cliente> listClientes;
   
   private ObservableList<Cliente> observableListClientes;
 
// FIM INSTANCIANDO OBJETOS DA TELA
   
   
//INSTANCIANDO O OBJETO connection
        Connection connection = hairsoftproject.DAO.ConexaoMySql.getConexaoMySql();
        
        
        
        
        
   @Override
   //METODO INICIALIZADOR DA TELA
    public void initialize(URL url, ResourceBundle rb) {
       //INICIA A CONEXÃO
       ConexaoMySql.getConexaoMySql();
       
      //CHAMA O METODO QUE CARREGA LISTA
       CarregarListViewClientes();
      
        
        
        //ALTERA LABEL AO CLICAR NA CELULA
        tableViewClientes.getSelectionModel().selectedItemProperty().addListener(
         (observable, oldvalue, newValue) -> selecionarItemTableViewClientes(newValue)
        ); 
    }
    
    //METODO QUE CARREGA LISTA DE CLIENTES
    private void CarregarListViewClientes() {
        try {
            
            //CRIA OBSERVABLE LIST 
            observableListClientes=FXCollections.observableArrayList();
            Statement sttmt = connection.createStatement();
            //CRIA QUERY QUE BUSCARÁ DADOS NO BANCO
           ResultSet query = sttmt.executeQuery("SELECT * FROM CLIENTE WHERE ATIVO = 'SIM'");
            //BUSCA OS DADOS DO BANCO E INSERE NO CONSTRUTOR CLIENTE
            while (query.next()){
        observableListClientes.add(new Cliente(query.getInt(1), query.getString(2), query.getString(3),query.getString(4), query.getString(5)));
          
            }
        }//FIM DO TRY
        //NOTIFICA EXCEÇÕES
        catch (Exception e){
           e.notify();
        }
        //SETA O NOME NA CELULA NOME
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        //SETA O CPF NA CELULA CPF
        tableColumnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
            
        //INSERE NOME E CPF NA TABLEVIEW
        tableViewClientes.setItems(observableListClientes);
        
             fecharConexao();
    }
    
    public void selecionarItemTableViewClientes(Cliente cliente){
//CONDIÇÃO PARA LIMPAR LABELS CASO NÃO TENHA CLIENTE 'CLICADO'
        if(cliente != null){
        lblCodigo.setText(String.valueOf(cliente.getCdCliente()));
        lblNome.setText(cliente.getNome());
        lblCPF.setText(cliente.getCpf());
        lblCelular.setText(String.valueOf(cliente.getCelular()));
        lblEndereco.setText(cliente.getEndereco());
        }else{
        lblCodigo.setText("");
        lblNome.setText("");
        lblCPF.setText("");
        lblCelular.setText("");
        lblEndereco.setText("");
        }
        
        cdCliente = cliente.getCdCliente();
    }
    @FXML
    private void apagarCliente() throws SQLException{
      
        try{
        Statement stm = connection.createStatement();
        

         stm.executeUpdate("UPDATE CLIENTE SET ATIVO = 'NAO' WHERE CLIENTE_ID = " + cdCliente);
         stm.executeUpdate("UPDATE AGENDAMENTO SET ATIVO = 'NAO' WHERE CLIENTE_ID = " + cdCliente);
         JOptionPane.showMessageDialog(null, "Cliente Excluido!");
         
         CarregarListViewClientes();
         
        }catch(Exception e){
                       JOptionPane.showMessageDialog(null, "Falha ao excluir cliente! Contate o suporte.");
	}        
    }
}
