import controller.CoderController;
import database.ConfigDB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Main {
    public static void main(String[] args) {
        CoderController objCoderController = new CoderController();

        String option = "";
        do {
            option = JOptionPane.showInputDialog("""
                    MENU
                    1. LIST CODER
                    2. INSERT CODER
                    3. UPDATE CODER
                    4. DELETE CODER
                    5. GET BY ID
                    6. EXIT
                    
                    CHOSE AN OPTION
                    
                    """);
            switch (option) {
                case "1": //List all Coders
                    objCoderController.getAll();
                    break;
                case "2":
                    objCoderController.create();
                    break;
                case "3":
                    objCoderController.update();
                    break;
                case "4":
                    objCoderController.delete();
                    break;
                case "5":
                    objCoderController.findByName();
                    break;
                case "6":
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option");
                    break;
            }
        }while (!option.equals("6"));
    }
}
