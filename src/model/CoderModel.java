package model;
//el modelo se encarga de interactuar directamente con la base de datos
//es buena practica establecer un contrato con ka clase que lo hereda, creando una interface, es algo parecido a una clase abastracta
//diferencia, la clase abstract es mas poderosa, solo se puede implementar una vez y de una interfaz varuas veces
import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {
    //IMPLEMENTS PARA IMPLEMENTAR LOS METODOS DE CRUD Y SUS METODOS
    @Override
    public Object insert(Object object) {

        //1. abrir la conexion

        Connection ObjeConnecion = ConfigDB.OpenConnection();

        //2 Castear el objeto
        Coder objCoder = (Coder) object;

        try {
            //3. Escribimos el SQL
            //Cada signo va a ser lo que nosotros ingresemos en el controller
            String sql = "INSERT INTO coder(name,age,clan) VALUES(?,?,?);";

            //4. preparar o utilizar PrepareStatement // y le decimos que retorne las llaves generadas
            PreparedStatement objPrepare = (PreparedStatement) ObjeConnecion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Escribir los valores dentro de la consulta o los ?
            objPrepare.setString(1, objCoder.getName());
            objPrepare.setInt(2, objCoder.getAge());
            objPrepare.setString(3, objCoder.getClan());

            //6. Ejecutar la consulta se usa execute cuando no va a traer nada de la base de datos en este caso solo insertamos
            objPrepare.execute();

            //7. Obtener la consulta
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objCoder.setId(objResult.getInt(1));
            }

            //8. Cerrar el prepare statement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "coder insertion successfully");


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Insert failed" + e.getMessage());
        }
        ConfigDB.closeConnection();
        //retornar todo el usuario que acabamos de crear
        return objCoder;
    }


    //=======================================================================================================================================
    @Override
    public boolean update(Object object) {
        return false;
    }

    //=======================================================================================================================================
    @Override
    public boolean delete(Object object) {

        //1. convertir el objeto a entidad tipo coder
        Coder objCoder = (Coder) object;

        //2. variable booleana para medir el estado de la eliminacion
        boolean isDeleted = false;

        //3. abrir la conexion
        Connection objConnection = ConfigDB.OpenConnection();

        try {

            //4. hacer la consulta SQL
            String sql = "DELETE FROM coder WHERE coder.id = ?;";

            //5. preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Asignar el valor al signo de interrogacion
            objPrepare.setInt(1, objCoder.getId());

            //7. Ejecutar la consulta y devuelve la cantidad de filas afectadas por la consulta SQL
            int totalRowsAff = objPrepare.executeUpdate();

            if (totalRowsAff > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Elimination Successfully.");
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //8. Cerrar la conexion
        ConfigDB.closeConnection();

        return false;
    }


//=========================================================================================================

    @Override
    public List<Object> findAll() {
        //1. abrir la conexion
        Connection ObjeConnecion = ConfigDB.OpenConnection();

        //2. Inicializar la lista donde se guardan los registros que devuelve la BD
        List<Object> ListCoders = new ArrayList<Object>();

        try {
            //3. escribir la sentencia SQL
            //ordenar por id la tabla coder
            //se le pone el ; al final de la consulta
            String sql = "SELECT * FROM coder ORDER BY coder.id ASC;";

            //4. preparar o utilizar PrepareStatement
            //prepara la sentencia para depsues ejecutarla y lo casteamos con los (para volverlo un tipo prepareStatement)
            PreparedStatement objPrepareStatement = (PreparedStatement) ObjeConnecion.prepareStatement(sql);

            //5. Ejecutar el prepare o Query
            objPrepareStatement.executeQuery();

            //6. guardar el resultado de la busqueda
            ResultSet objResultSet = (ResultSet) objPrepareStatement.executeQuery();

            //7. guardar el resultado
            while (objResultSet.next()) {
                Coder objCoder = new Coder();
                //trae el id de la base de datos y lo guarda dentro del id del objeto coder o la instancia del objeto que acabo de crear

                //traemos todos los datos y los guardamos en la instancia del objeto Coder llamada objCoder
                objCoder.setId(objResultSet.getInt("id"));
                objCoder.setName(objResultSet.getString("name"));
                objCoder.setAge(objResultSet.getInt("age"));
                objCoder.setClan(objResultSet.getString("clan"));

                //Finalmente agregamos el coder a la lista
                ListCoders.add(objCoder);
            }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition failed");
        }


        //8. cerrar la conexion a la base de datos
        ConfigDB.closeConnection();
        return ListCoders;
    }
    //============================================================================================================

    @Override
    public Object findById(int id) {
        //1. abrir la conexion
        Connection objConnection = ConfigDB.OpenConnection();
        Coder objCoder = null;

        try {
            //2. generar la consulta

            String sql = "SELECT * FROM coder WHERE id = ?;";

            //3. preparar o utilizar PrepareStatement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);

            //4. Insertar los elementos al ? con el prepare statement
            objPrepare.setInt(1, id);

            //5. Ejecutar la consulta se usa para guardar el resultado
            ResultSet resultSet = objPrepare.executeQuery();

            //Mientras haya un registro siguiente

            while (resultSet.next()) {
                objCoder = new Coder();
                //trae el id de la base de datos y lo guarda dentro del id del objeto coder o la instancia del objeto que acabo de crear
                //traemos todos los datos y los guardamos en la instancia del objeto Coder llamada objCoder
                //guardar lo que la base de datos me trae
                objCoder.setId(resultSet.getInt("id"));
                objCoder.setName(resultSet.getString("name"));
                objCoder.setAge(resultSet.getInt("age"));
                objCoder.setClan(resultSet.getString("clan"));
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //8. cerrar la conexion a la base de datos
        ConfigDB.closeConnection();

        return objCoder;
    }

    @Override
    public ArrayList<Object> findByName(String name) {


        Connection objConection = ConfigDB.OpenConnection();
        ArrayList<Object> objCoder = new ArrayList<Object>();

        try {
            //2. generar la consulta
            String sql = "SELECT * FROM coder WHERE name = ?;";

            //3. Preparar el statement
            PreparedStatement objStatement = (PreparedStatement) objConection.prepareStatement(sql);

            //4 Añadir los elementos
            objStatement.setString(1, name);


            //5. Ejecutar la consulta

            ResultSet resultSet = objStatement.executeQuery();

            while (resultSet.next()){
                Coder coder1 = new Coder();
                coder1.setName(resultSet.getString("name"));
                coder1.setAge(resultSet.getInt("age"));
                coder1.setId(resultSet.getInt("id"));
                coder1.setClan(resultSet.getString("clan"));
                objCoder.add(coder1);

            }



        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        //8. cerrar la conexion a la base de datos
        ConfigDB.closeConnection();

        return objCoder ;

    }


}
