

import DataBase.ConfigDB;
import Controller.PlaneController;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ConfigDB.openConnection();

        String option = "";
        do{
            option = JOptionPane.showInputDialog(
                    """
                       Welcome to the hospital
                       
                       1.Consult planes
                       2.Consult flights     
                       3.Consult passengers  
                       4.Consult reservations
                       5.close
                            """);
            switch (option){
                case"1":
                    String option2 = "";
                    do{
                        option2 = JOptionPane.showInputDialog(
                                """ 
                                      PLANE
                                       
                                       1. Create plane
                                       2. Show plane       
                                       3. Delete plane  
                                       4. Update plane  
                                       5. Back
                                       
                                            """);
                        switch (option2){
                            case"1":
                                PlaneController.create();
                                break;
                            case"2":
                                PlaneController.create();
                                break;
                            case"3":
                                PlaneController.create();
                                break;
                            case"4":
                                PlaneController.create();
                                break;
                        }

                    }while (!option2.equals("5"));
                    break;
                case"2":
                    String option3 = "";
                    do{
                        option3 = JOptionPane.showInputDialog(
                                """
                                   SPECIALTY
                                   
                                   1. Create specialty
                                   2. Show specialty       
                                   3. Delete specialty  
                                   4. Update specialty  
                                   5. Back
                                   
                                        """);
                        switch (option3){
                            case"1":
                                PlaneController.create();
                                break;
                            case"2":
                                PlaneController.create();
                                break;
                            case"3":
                                PlaneController.create();
                                break;
                            case"4":
                                PlaneController.create();
                                break;
                        }

                    }while (!option3.equals("5"));
                    break;
                case"3":
                    String option4 = "";
                    do{
                        option4 = JOptionPane.showInputDialog(
                                """
                                   MEDICO
                                   
                                   1. Create medico
                                   2. Show medico       
                                   3. Delete medico  
                                   4. Update specialty  
                                   5. Back
                                   
                                        """);
                        switch (option4){
                            case"1":
                                PlaneController.create();
                                break;
                            case"2":
                                PlaneController.create();
                                break;
                            case"3":
                                PlaneController.create();
                                break;
                            case"4":
                                PlaneController.create();
                                break;
                        }

                    }while (!option4.equals("5"));
                    break;
                case"4":
                    PlaneController.create();
                    break;
            }

        }while (!option.equals("5"));
    }
}