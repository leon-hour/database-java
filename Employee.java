import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class Employee
{
    private String name;
    private String lastName;
    private String department;
    private int age;
    Scanner input = new Scanner(System.in);
    public void insertEmployee() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/staff","root","");
        Connection connection = dbmsConnection.getConnection();
        System.out.println("Enter your Name");
        name = input.nextLine();
        System.out.println("Enter your Last Name");
        lastName = input.nextLine();
        System.out.println("Enter your Department");
        department = input.nextLine();
        System.out.println("Enter the age");
        age = input.nextInt();
        String sql = "insert into Employee values (?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, department);
        preparedStatement.setInt(4, age);
        preparedStatement.executeUpdate();
        System.out.println("Record  inserted successfully");
        dbmsConnection.closeConnection(connection, preparedStatement);
    }
    
    public void updateEmployeeDepartment() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/staff","root","");
        Connection connection = dbmsConnection.getConnection();
        System.out.println("Enter Your Name");
        String inputname=input.nextLine();
        System.out.println("Enter the new Department");
        String newDepartment=input.nextLine();
        String sql = "update Employee set department = ? where name = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(2, inputname);
        preparedStatement.setString(1, newDepartment);
        int i = preparedStatement.executeUpdate();
        if(i>0)
        {
            System.out.println("Record updated sucessfully");
        }else
        {
            System.out.println("No Such record in the Database");
        }
        dbmsConnection.closeConnection(connection, preparedStatement);
    }


public void deleteEmployeeRecord() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/staff","root","");
    Connection connection = dbmsConnection.getConnection();
    System.out.println("Enter the Name of the Employee");
    String inputname=input.nextLine();
    String sql = "delete from Employee where name = ?;";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, inputname);
    int i = preparedStatement.executeUpdate();
    if(i>0)
    {
        System.out.println("Record Deleted Successfully");
    }
    else
    {
        System.out.println("No Such Record in the Database");
    }
    dbmsConnection.closeConnection(connection, preparedStatement);
}

public void searchEmployee() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/staff","root","");
    Connection connection = dbmsConnection.getConnection();
    System.out.println("Enter Your Name");
    String inputname=input.nextLine();
    String sql = "select * from Employee where name=?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, inputname);
    ResultSet resultSet = preparedStatement.executeQuery();
    if(resultSet.next()==false)
    {
        System.out.println("No such record found in the database");
    }
    else
    {    
        System.out.println(resultSet.getString(1)+resultSet.getString(2)+resultSet.getString(3)+resultSet.getInt(4));
        
    }
    dbmsConnection.closeConnection(connection, preparedStatement);
}
}