/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import javaapplication1.SQLHelpermethod;
import java.sql.*;

/**
 *
 * @author Rong
 */
public class sqlConnector 
{
   protected static Connection conn;
   static private SQLHelpermethod test;
     /**
    * method to connect the database
    * @throws SQLException 
    * @throws ClassNotFoundException 
    */
   static private boolean connect() 
      throws ClassNotFoundException, SQLException {
      test = new SQLHelpermethod("5432", "Book", "postgres","920307");
      if (test.getConnection() == false)
      {
         test = new SQLHelpermethod("5432", "Book", "postgres","920307");
         return test.getConnection();
      }
      else if  (test.getConnection() == false)
      {
         test = new SQLHelpermethod("5432", "Book", "postgres","920307");
         return test.getConnection();
      }
      return test.getConnection();
   }
   /**
    * method to check if name is in the database
    * @param fName   first name
    * @param lName   last Name
    * @return            true or false
    * @throws SQLException 
    * @throws ClassNotFoundException 
    */
   static public boolean testNewUserName(String fName,String lName) 
      throws ClassNotFoundException, SQLException {
      boolean exist = false;
      if (connect() == false)
         System.out.println("Fail to connect");
   
      exist = test. confirmName(fName,lName);
      return exist;
   }
    /**
    * method to add name in the database
    * @param fName   first name
    * @param lName   last Name
    * @return            true or false
    * @throws SQLException 
    * @throws ClassNotFoundException 
    */
   static public boolean addNewUserName(String fName,String lName) 
      throws ClassNotFoundException, SQLException {
      boolean exist = false;
      if (connect() == false)
         System.out.println("Fail to connect");
   
      exist = test. insertName(fName,lName);
      return exist;
   }
   /**
    * method to check title in the database
    * @param title  first name
    * @return            true or false
    * @throws SQLException 
    * @throws ClassNotFoundException 
    */
   static public boolean checkTitle(String title) 
      throws ClassNotFoundException, SQLException {
      boolean exist = false;
      if (connect() == false)
         System.out.println("Fail to connect");
   
      exist = test.confirmTitle(title);
      return exist;
   }
}
