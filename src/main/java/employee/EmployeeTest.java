package employee;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class EmployeeTest {
    private static Connection getdbconnection(){
        Connection connection=null;
       try{
           FileInputStream fileInputStream=new FileInputStream("resources/database.properties");
           Properties properties=new Properties();
           properties.load(fileInputStream);

           String s1=properties.getProperty("sqlurl");
           String s2=properties.getProperty("sqlusername");
           String s3=properties.getProperty("sqlpassword");

           connection= DriverManager.getConnection(s1,s2,s3);

       }catch (SQLException e){
           e.printStackTrace();
       }//finally {
       catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
//           try {
//               connection.close();
//           } catch (SQLException throwables) {
//               throwables.printStackTrace();
//           }
//       }

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

    private static List<Employee> sortList(List<Employee> finallist){
        List<Employee> sortedlist=null;
        sortedlist=finallist.stream().sorted(Comparator.comparing(Employee::getSalary)).collect(Collectors.toList());

        return sortedlist;

    }

    public static void main(String[] args) {

        Connection con=getdbconnection();
        List<Employee> finalList=readData();
        System.out.println(finalList);

        List<Employee> finalsortedlist=sortList(finalList);
        System.out.println(finalsortedlist);

    }
}
