package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class ConfigDB {
    //variable que va a contener el estado de la coneccion

    static Connection objConnection = null;

    //metodo para establecer la conexion entre java y la base de datos

    public static Connection OpenConnection() {
        try {
            //for name permite implementar el driver
            Class.forName("com.mysql.jdbc.Driver");


            //creamos variables con las credenciales a la base de datos
            String url = "jdbc:mysql://localhost:3306/primeraDB";
            String user = "root";
            String password = "Rlwl2023.";

            //establecemos la conexion en la base de datos
            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Connection perfectly");

        }
        catch (ClassNotFoundException e) {
            System.out.println("Error Driver no instalado");
        }catch (SQLException e){
            System.out.println("No se pudo establecer la conexion con la base de datos");
        }
        //nos pide que retornemos el objeto de conexion
        return objConnection;

    }

    public static void closeConnection(){
        try {
            if (objConnection != null) objConnection.close();
        }catch (SQLException e){
            System.out.println("ERROR " + e.getMessage());
        }
    }
}