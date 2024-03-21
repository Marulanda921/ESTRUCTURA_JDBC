package controller;

import entity.Coder;
import model.CoderModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CoderController {
    //crear una instancia del model
    CoderModel objCoderModel;

    //inicializarlo en el constructor
    public CoderController() {
        this.objCoderModel = new CoderModel();
    }
    //METODO PARA LISTAR LOS CODER
    public void getAll(){
        //Sobreescritura del metodo de la lista, ejecuta el get all de abajo
        String List = this.getAll(this.objCoderModel.findAll());
        JOptionPane.showMessageDialog(null,List);
    }

    public String getAll(List<Object> listObject){
        //El modelo interactua con el controller
        String List = "list coders \n";

        //Recorremos la lista que devuelve findAll
        for (Object obj : listObject){

            //el coder normal esta tipo objeto y lo convierto en clase coder y automaticamente lo que se recorre se convirtio en tipo coder
            Coder objCoder = (Coder) obj;

            //Concatenamos la informacion
            List += objCoder.toString()  + "\n";
        }
        return List;
    }
    //============================================================================================================
    public void create(){
        //1 crear coder
        Coder objCoder = new Coder();

        //2 pedimos los datos
        String name = JOptionPane.showInputDialog("Insert name");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Insert age"));
        String clan = JOptionPane.showInputDialog("Insert clan");

        //3 guardamos los datos en el objeto
        objCoder.setName(name);
        objCoder.setAge(age);
        objCoder.setClan(clan);

        //4 al insertar objcoder como es un object toca convertirlo a un tipo CODER Y INSERTAMOS objcoder
        objCoder = (Coder) this.objCoderModel.insert(objCoder);
        JOptionPane.showMessageDialog(null,objCoder.toString());

    }
    //=============================================================================================================

    public void delete(){
        String listCoderString = this.getAll(this.objCoderModel.findAll());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listCoderString + "Enter the ID of the coder to delete"));

        //buscar quien tiene el id del obj
        Coder objCoder = (Coder) this.objCoderModel.findById(idDelete);
        if(objCoder == null) {
            JOptionPane.showMessageDialog(null,"Coder Not found");
        }else {
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure, want to delete the coder: \n" + objCoder.toString());
            //si el usuario escogio que si entoces eliminamos el coder
            if (confirm == 0){
                this.objCoderModel.delete(objCoder);
            }

        }
    }

    //==============================================================================================================
    public void update(){
        //Listamos
        String list = this.getAll(this.objCoderModel.findAll());
        //pedimos el id
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(list + "\nEmter the ID of the coder to edit"));
        Coder objcoder  = (Coder) this.objCoderModel.findById(idUpdate);

        if (objcoder == null){
            JOptionPane.showMessageDialog(null,"Coder not found");

        }else {
            //PEDIR LOS DATOS AL USUARIO
            String name = JOptionPane.showInputDialog(null, "Enter  new Name", objcoder.getName());
            String clan = JOptionPane.showInputDialog(null, "Enter new clan", objcoder.getClan());
            int age = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new age", String.valueOf(objcoder.getAge())));

            objcoder.setName(name);
            objcoder.setAge(age);
            objcoder.setClan(clan);

            this.objCoderModel.update(objcoder);

        }
    }

    public void findByName() {


    }
}
