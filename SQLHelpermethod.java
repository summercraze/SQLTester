/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.sql.*;
import java.util.Map;
/**
 *
 * @author Rong
 */
public class SQLHelpermethod 
{
    public static Connection connection = null;
   private static String host;
   private static String dbName;
   private static String user;
   private static String password;
   /**
    * constructor
    * @param host
    * @param dbName    database name
    * @param user      database username
    * @param password      database password
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
    * 
    */

    public SQLHelpermethod (String host, String dbName, String user, 
      String password)throws ClassNotFoundException, SQLException 
    {
      this.host = host;
      this.dbName = dbName;
      this.user = user;
      this.password = password;
   }
 /**
    * this checks for whether the code has a JDBC drive
    * @return true or false
    * @throws ClassNotFoundException
    */
   private static boolean checkJDBCConnetion() throws ClassNotFoundException {
      System.out.println("-------- PostgreSQL "
         + "JDBC Connection Testing ------------");

      try {
         Class.forName("org.postgresql.Driver");
      } catch (ClassNotFoundException e) {
         System.out.println("Where is your PostgreSQL JDBC Driver? "
               + "Include in your library path!");
         e.printStackTrace();
         return false;
      }

      System.out.println("PostgreSQL JDBC Driver Registered!");
      return true;
   }
   
   /**
    * this connect to the actual database after checking for drive
    * @return Connection
    * @throws ClassNotFoundException,SQLException
    */
   private static Connection connect() 
      throws SQLException, ClassNotFoundException { 
      if (checkJDBCConnetion() == true) {
         try {
            connection = DriverManager.getConnection(
               "jdbc:postgresql://localhost:" + 
                host + "/" + dbName,user, password);
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return connection;
   }
   
   /**
    * this tells the SQL Connector method if the database is connected
    * @return boolean
    * @throws SQLException 
    * @throws ClassNotFoundException 
    * 
    */
   public boolean getConnection() throws ClassNotFoundException, SQLException
   {
      return !(connect() == null);
   }
  
/**
    * this method insert name into database
    * @param lastName
    * @param firstName
    * @return status
    * @throws SQLException
    * @throws ClassNotFoundException 
    */
   private static int addName(String firstName,String lastName)
      throws SQLException, ClassNotFoundException {
      Connection dbConnection = null;
      CallableStatement addNameStatement = null;
      int status = 2;

      String insertNameSql = "{call insertname(?,?,?)}";
      try {
         //connect and call function
         dbConnection = connect();
         addNameStatement = dbConnection.prepareCall(insertNameSql);

         //put the variable into the function to execute
         addNameStatement.setString(1,lastName);
         addNameStatement.setString(2, firstName);
         //get the output from the function
          addNameStatement.registerOutParameter(3, java.sql.Types.INTEGER);
         // execute insertloginSqlstore procedure
          addNameStatement.executeUpdate();
         //get the output and return it
         status =  addNameStatement.getInt(3);

      } catch (SQLException e) {
         System.out.println(e.getMessage());

      } finally {
         if (addNameStatement != null) {
            addNameStatement.close();
         }

         if (dbConnection != null) {
            dbConnection.close();
         }
      }
      return status;
   }
    /**
    * this method let SQLConnector add name to database
    * @param fName
    * @param lName
    * @return boolean
    * @throws SQLException
    * @throws ClassNotFoundException 
    */
   public boolean insertName(String fName,String lName) 
      throws ClassNotFoundException, SQLException {
      int status;
      status = addName(fName,lName);
      boolean result = false;
      
      if (status == 0)
         result = false;
      
      if (status == 1)
         result = true;

      return result;
   }
   /**
    * this method check whether the last name and first name exist in database
    * @param lastName
    * @param firstName
    * @return status
    * @throws SQLException
    * @throws ClassNotFoundException 
    */
   private static int checkName(String lastName,String firstName)
      throws SQLException, ClassNotFoundException {
      Connection dbConnection = null;
      CallableStatement checkNameStatement = null;
      int status = 2;

      String insertNameSql = "{call checkname(?,?,?)}";
      try {
         //connect and call function
         dbConnection = connect();
         checkNameStatement = dbConnection.prepareCall(insertNameSql);

         //put the variable into the function to execute
         checkNameStatement.setString(1, firstName);
         checkNameStatement.setString(2, lastName);
         //get the output from the function
          checkNameStatement.registerOutParameter(3, java.sql.Types.INTEGER);
         // execute insertloginSqlstore procedure
          checkNameStatement.executeUpdate();
         //get the output and return it
         status =  checkNameStatement.getInt(3);

      } catch (SQLException e) {
         System.out.println(e.getMessage());

      } finally {
         if (checkNameStatement != null) {
            checkNameStatement.close();
         }

         if (dbConnection != null) {
            dbConnection.close();
         }
      }
      return status;
   }
    /**
    * this method let SQLConnector add user to database
    * @param fName
    * @param lName
    * @return boolean
    * @throws SQLException
    * @throws ClassNotFoundException 
    */
   public boolean confirmName(String fName,String lName) 
      throws ClassNotFoundException, SQLException {
      int status;
      status = checkName(fName,lName);
      boolean result = false;
      
      if (status == 0)
         result = false;
      
      if (status == 1)
         result = true;

      return result;
   }
     /**
    * this method check whether the title exist in database
    * @param title
    * @return status
    * @throws SQLException
    * @throws ClassNotFoundException 
    */
   private static int checkBook(String title)
      throws SQLException, ClassNotFoundException {
      Connection dbConnection = null;
      CallableStatement checkBookStatement = null;
      int status = 2;

      String checkTitlesql = "{call checktitle(?,?)}";
      try {
         //connect and call function
         dbConnection = connect();
        checkBookStatement = dbConnection.prepareCall(checkTitlesql);

         //put the variable into the function to execute
         checkBookStatement.setString(1, title);
         //get the output from the function
          checkBookStatement.registerOutParameter(2, java.sql.Types.INTEGER);
         // execute insertloginSqlstore procedure
          checkBookStatement.executeUpdate();
         //get the output and return it
         status =  checkBookStatement.getInt(2);

      } catch (SQLException e) {
         System.out.println(e.getMessage());

      } finally {
         if (checkBookStatement != null) {
            checkBookStatement.close();
         }

         if (dbConnection != null) {
            dbConnection.close();
         }
      }
      return status;
   }
    /**
    * this method let SQLConnector check title in database
    * @param title
    * @return boolean
    * @throws SQLException
    * @throws ClassNotFoundException 
    */
   public boolean confirmTitle(String title) 
      throws ClassNotFoundException, SQLException {
      int status;
      status = checkBook(title);
      boolean result = false;
      
      if (status == 0)
         result = false;
      
      if (status == 1)
         result = true;

      return result;
   }
}
