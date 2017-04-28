package hairsoftproject.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class MyConnection {
    private Connection connection;

    
    public MyConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://hairsoft.mysql.uhserver.com/hairsoft", "hairsoft", "uninove+1");
        String conectado = "Conectado com sucesso.";
        System.out.println(conectado);
    }
    
    
    
    public static void main(String[] args) throws SQLException {
        try{
            new MyConnection();
        }
        catch(ClassNotFoundException e){
         System.out.println("Não conectado ao banco de dados.");
        }
    }    
}
