package hairsoftproject.DAO;

import java.sql.Connection;
 
import java.sql.DriverManager;
 
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoMySql {
    
    
    
    public static String status = "Não conectou..";
    
    //CONSTRUTOR DA CLASSE
    public ConexaoMySql(){
    
    }
    
    //CONEXÃO COM BANCO
    public static java.sql.Connection getConexaoMySql(){
    
    Connection connection = null;

    try{
        //Carregamento do driver
        String driverName = "com.mysql.jdbc.Driver";
        
        Class.forName(driverName);
        
        
        //CONFIGURAÇÃO DA CONEXÃO
        
        String servername = "hairsoft";
        String mydatabase ="mysql";
        String url="jdbc:mysql://hairsoft.mysql.uhserver.com/hairsoft";
        String username = "hairsoft";
        String password = "uninove+1";
        
        connection = DriverManager.getConnection(url,username,password);
        
        
        //TESTE CONEXÃO
        
        if(connection != null ){
          status = ("CONECTADO COM SUCESSO!");  
        }else{
          status = ("STATUS -- > NÃO FOI POSSÍVEL REALIZAR A CONEXÃO!");
        }
        
        return connection;
    
    }catch(ClassNotFoundException e){
    
        System.out.println("O driver não foi encontrado");
        return null;
        }catch(SQLException e){
            //SE NÃO CONECTAR AO BANCO
             JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados! Verifique sua conexão.");
            System.out.println("Não foi possivel conectar.");
            
            return null;
        }  
    }
    
    
    public static String statusconnection(){
        return status;
    }
    
    //FECHANDO A CONEXÃO
    
    public static boolean fecharConexao(){
        try{
            ConexaoMySql.getConexaoMySql().close();
            
            return true;
        }catch(SQLException e){
            return false;
        }
    }
    
    //INICIA A CONEXÃO
    
    public static java.sql.Connection ReiniciarConexao(){
        fecharConexao();
        
        return ConexaoMySql.getConexaoMySql();
    }
}
