import java.sql.*;
import java.util.Scanner;
public class DataBaseJava {
    
     @SuppressWarnings("resource")
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
        
        try
        {    int option=0;
            Employee employee = new Employee();
            do
            {
                System.out.println("Select an operation \n 1->Insert new Employee \n 2->Update password \n 3->Delete a record \n 4- Search for a Employee \n 5- Exit");
                Scanner optionin = new Scanner(System.in);
                option=optionin.nextInt();
                switch(validateInputOption(option))
                {
                    case INSERT:
                    	employee.insertEmployee();
                        break;
                    case UPDATE:
                    	employee.updateEmployeeDepartment();
                        break;
                    case DELETE:
                    	employee.deleteEmployeeRecord();
                        break;
                    case SEARCH:
                    	employee.searchEmployee();
                        break;
                    case EXIT:
                        break;
                    default:
                    	throw new IllegalArgumentException("Invalid Input");
                        
                }
            }while(option!=5);
            System.out.println("Thanks for using our app.");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }        
     }
     public static Options validateInputOption(int optionId) {
    	 switch(optionId)
         {
             case 1:
                return Options.INSERT;
             case 2:
                return Options.UPDATE;
             case 3:
            	 return Options.DELETE;
             case 4:
            	 return Options.SEARCH;
             case 5:
            	 return Options.EXIT;
             default:
                 throw new IllegalArgumentException("Invalid Input");
         }
    	 
     }
}

