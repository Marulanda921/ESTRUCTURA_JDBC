package controller;

import entity.Coder;
import model.CoderModel;

import javax.swing.*;

public class CoderController {
    //crear una instancia del model
    CoderModel objCoderModel;

    //inicializarlo en el constructor
    public CoderController() {
        this.objCoderModel = new CoderModel();
    }
    //METODO PARA LISTAR LOS CODER
    public void getAll(){
        //El modelo interactua con el controller
        String List = "list coders \n";

        //Recorremos la lista que devuelve findAll
        for (Object obj : this.objCoderModel.findAll()){

            //el coder normal esta tipo objeto y lo convierto en clase coder y automaticamente lo que se recorre se convirtio en tipo coder
            Coder objCoder = (Coder) obj;

            //Concatenamos la informacion
            List += objCoder.toString()  + "\n";
        }
        JOptionPane.showMessageDialog(null,List);

    }

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
}
