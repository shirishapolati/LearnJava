package employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTest {
    private static Connection getdbconnection(){
        Connection connection=null;
       try{
           connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/learnjava","root","chinnusiri");

       }catch (SQLException e){
           e.printStackTrace();
       }

        return connection;
    }

    private static List<Employee> readData(){
        List<Employee> list=null;
        Statement statement=null;

        try{

            statement=getdbconnection().createStatement();
            String sql="select * from Employee";
            ResultSet resultSet=statement.executeQuery(sql);
            list =new ArrayList<Employee>();
            while (resultSet.next()){
                Employee employee=new Employee();
                employee.setEmployeeId(resultSet.getInt(1));
                employee.setEmployeeFirstName(resultSet.getString(2));
                employee.setEmployeeMiddleName(resultSet.getString(3));
                employee.setEmployeeLastName(resultSet.getString(4));
                employee.setDeptId(resultSet.getString(5));
                employee.setDesignation(resultSet.getString(6));
                employee.setSalary(resultSet.getDouble(7));
                list.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {

        Connection con=getdbconnection();
        List<Employee> finalList=readData();
        System.out.println(finalList);

    }
}