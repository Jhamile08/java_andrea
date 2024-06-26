package model;

import database.CRUD;
import database.ConfigDB;
import entity.Patient;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        //Open connection
        Connection objConnection = ConfigDB.openConnection();
        Patient objPatient = (Patient) obj;

        try{
            //Query sql
            String sql = "INSERT INTO patient (name,surname,birth_date,identity) VALUES (?,?,?,?)\n";
            //Prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, objPatient.getName());
            objPrepare.setString(2, objPatient.getSurname());
            objPrepare.setString(3, objPatient.getBirth_date());
            objPrepare.setString(4, objPatient.getIdentity());
            objPrepare.execute();
            //Get the result with the id
            ResultSet objRest = objPrepare.getGeneratedKeys();
            while(objRest.next()){
                //get the value with the index
                objPatient.setId_patient(objRest.getInt(1));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return objPatient;
    }

    @Override
    public List<Object> findAll() {
        //Open connection
        Connection objConnection = ConfigDB.openConnection();
        //Create list
        List<Object> listPatient = new ArrayList<>();


        try{
            String sql = "SELECT * FROM patient";

            //Prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            //While exists a patient
            while(objResult.next()){
                Patient objPatient = new Patient();
                objPatient.setId_patient(objResult.getInt("id_patient"));
                objPatient.setName(objResult.getString("name"));
                objPatient.setSurname(objResult.getString("surname"));
                objPatient.setBirth_date(objResult.getString("birth_date"));
                objPatient.setIdentity(objResult.getString("identity"));
                listPatient.add(objPatient);

            }
            objPrepare.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        //Close the connection
        ConfigDB.closeConnection();
        return listPatient;
    }

    @Override
    public boolean upDate(Object obj) {
        //Open connection
        Connection objConnection = ConfigDB.openConnection();
        //Convert to author
        Patient objPatient = (Patient) obj;
        //Create a varible for know the state
        boolean isUpdate = false;
        try {
            //SQL
            String sql = "UPDATE patient SET name = ?, surname = ?, birth_date = ?, identity = ? WHERE id_patient = ?;";
            //Create prepared statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //Asign value to the query

            objPrepare.setString(1,objPatient.getName());
            objPrepare.setString(2,objPatient.getSurname());
            objPrepare.setString(3,objPatient.getBirth_date());
            objPrepare.setString(4,objPatient.getIdentity());
            objPrepare.setInt(5,objPatient.getId_patient());
            //Execute query
            int totalRowAffected = objPrepare.executeUpdate();
            if(totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null,"the update was succesful");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return isUpdate;
    }


    @Override
    public boolean delete(Object obj) {
        //Convert the object
        Patient objPatient = (Patient) obj;
        //Open connection
        Connection objConnection = ConfigDB.openConnection();
        //Create a state variable
        boolean idDelete = false;

        try {
            //Sql
            String sql  = "DELETE FROM patient WHERE id_patient = ?;";
            //Create the prepared statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //Asign value
            objPrepare.setInt(1,objPatient.getId_patient());
            //Execute the query
            int totalRowAffected = objPrepare.executeUpdate();
            if(totalRowAffected > 0){
                idDelete = true;
                JOptionPane.showMessageDialog(null, "The delete was succesful");
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //Close the connection
        ConfigDB.closeConnection();
        return idDelete;
    }

    public Object findById(int id) {
        //Open the connection
        Connection objConnection = ConfigDB.openConnection();
        //create book
        Patient objPatient = null;

        try {
            //Sql
            String sql = "SELECT * FROM patient WHERE id_patient = ?;";
            //Create prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //Asign value
            objPrepare.setInt(1, id);
            //Execute query
            ResultSet objResult = objPrepare.executeQuery();
            if(objResult.next()){
                objPatient = new Patient();
                objPatient.setName(objResult.getString("name"));
                objPatient.setSurname(objResult.getString("surname"));
                objPatient.setBirth_date(objResult.getString("birth_date"));
                objPatient.setIdentity(objResult.getString("identity"));
                objPatient.setId_patient(objResult.getInt("id_patient"));
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //Close connection
        ConfigDB.closeConnection();

        return objPatient;
    }

}
