package PropertiesFileDemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class PropertiesDemoExample {
    public static void main(String[] args) throws IOException, SQLException {
        FileInputStream fileInputStream=new FileInputStream("resources/database.properties");
        Properties properties=new Properties();
        properties.load(fileInputStream);

        //get the data from properties file

        String s1=properties.getProperty("sqlurl");
        String s2=properties.getProperty("sqlusername");
        String s3=properties.getProperty("sqlpassword");

        Connection connection= DriverManager.getConnection(s1,s2,s3);
        Statement statement=connection.createStatement();
        String sql="select * from Employee";
        ResultSet resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
            System.out.println(resultSet.getInt(1)+" "+ resultSet.getString(2)+" "+ resultSet.getString(3)+" "+ resultSet.getString(4)+" "+ resultSet.getString(5)+" "+ resultSet.getString(6)+" "+ resultSet.getDouble(7));

        }
    }
}
