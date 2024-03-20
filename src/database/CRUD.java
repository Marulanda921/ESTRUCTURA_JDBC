package database;

import java.util.List;

public interface CRUD {
    //Lleva los metodos que va a heredar a los demas, los metodos no tienen logica solo se declaran

    //si lo ponemos object puede llegar a ser cualquier cosa
    //object es una clase generica para los objetos donde cualquier cosa

    //Insertar
    public Object insert(Object object);

    //Actualizar
    public boolean update(Object object);

    //Eliminar
    public boolean delete(Object object);

    //Listar
    public List<Object> findAll();


    //Listar por ID

    Object findById(int id);

    //Listar por nombre

    Object findByName(String name);
}
