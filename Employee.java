import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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
        System.out.println("Name");
        name = input.nextLine();
        System.out.println("Last Name");
        lastName = input.nextLine();
        System.out.println("Department");
        department = input.nextLine();
        System.out.println("Age");
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
        System.out.println("Name: ");
        String inputname=input.nextLine();
        System.out.println("New Department: ");
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
    System.out.println("Enter the Name of the Employee: ");
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
        System.out.println(resultSet.getString(1)+", "+resultSet.getString(2)+", "+resultSet.getString(3)+", " + resultSet.getInt(4));
        
    }
    dbmsConnection.closeConnection(connection, preparedStatement);
}
public void importEmloyee(String filePath)
        throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException,
        URISyntaxException {
    DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/staff", "root", "");
    Connection connection = dbmsConnection.getConnection();
    String sql = "insert into Employee values (?,?,?,?);";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    try {
        List<String> fileLines = Files.readAllLines(Paths.get(getClass().getResource(filePath).toURI()));
        for (String menuItemsAsString : fileLines) {
            String[] employeesInfo = menuItemsAsString.split(",");
            String name = employeesInfo[0];
            String lastName = employeesInfo[1];
            String department = employeesInfo[2];
            int age = Integer.valueOf(employeesInfo[3]);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, department);
            preparedStatement.setInt(4, age);
            preparedStatement.executeUpdate();
            System.out.println("Record  inserted successfully");
        }
    } finally {

    }
    dbmsConnection.closeConnection(connection, preparedStatement);
}
}
