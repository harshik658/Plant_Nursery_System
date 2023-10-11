package com.amdocs.plant.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
	
import com.amdocs.plant.dao.DatabaseConnection;
import com.amdocs.plant.dao.PlantDao;
import com.amdocs.plant.exception.userDefinedException;
import com.amdocs.plant.model.Plant;






public class Main {
public static void main(String args[]) throws userDefinedException {
Connection conn;

       String query1;
       String query2;
       ResultSet rs;
try {
        // Load the MySQL JDBC driver
Class.forName("oracle.jdbc.driver.OracleDriver"); //registration
System.out.println("Inside try after class.forname");
conn=DriverManager.getConnection("Jdbc:Oracle:thin:@LAPTOP-J257HKO2:1521:XE","system","admin"); //connection

        // Check if the connection is successful
        if (conn != null) {
            System.out.println("Connected to the MySQL database!");
            // You can perform database operations here
        } else {
            System.out.println("Failed to connect to the database.");
        }
       
       
       
        Statement stmt = conn.createStatement();
        Scanner sc=new Scanner(System.in);
        int choice;
       
        do {
        System.out.println("Press 1 to add new plant");
        System.out.println("Press 2 to update plant cost");
        System.out.println("Press 3 to delete plant");
        System.out.println("Press 4 to view all plants");
        System.out.println("Press 5 to find plant by origin country name");
        System.out.println("Press 6 to find outdoor plants which requires sunlight");
        System.out.println("Press 7 to count plants by water supply frequency");
        System.out.println("Press 8 to exit");
        System.out.println("Please enter your choice");
        
        choice=sc.nextInt();
        
        
        switch(choice) {
        case 1:
        System.out.println("Enter all details");
        System.out.print("Enter Plant ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        System.out.print("Enter Plant Name: ");
        String pname = sc.nextLine();

        System.out.print("Enter Origin Country Name: ");
        String cname = sc.nextLine();

        System.out.print("Enter Sunlight Required (1 for true, 0 for false): ");
        int sun = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        System.out.print("Enter Water Supply Frequency: (Daily/Alternate Days/Weekly) ");
        String water = sc.nextLine();

        System.out.print("Enter Plant Type (indoor/outdoor): ");
        String type = sc.nextLine();

        System.out.print("Enter Cost: ");
        double cost = sc.nextDouble();
        if(cost <= 0) {
        	throw new userDefinedException("Negative cost is not allowed");
        }
        sc.nextLine(); // Consume the newline character

        PlantDao p=new PlantDao(id,pname,type,cname,water,cost,sun);
        
        p.addPlant(p);
//         query2="insert into plant values('103','neem','india',1,'5','outdoor',100.78)";
//         stmt.executeUpdate(query2);
        break;
        case 2:
        System.out.println("Enter updated cost");
        double ucost=sc.nextDouble();
        if(ucost <= 0) {
        	throw new userDefinedException("Negative cost is not allowed");
        }
        sc.nextLine();
        System.out.println("Enter plant id where updation needs to be done");
        int plid=sc.nextInt();
       
        PlantDao p3=new PlantDao();
        boolean b= p3.updatePlantCost(plid, ucost);
//         query2 = "UPDATE plant SET cost=" + ucost + " WHERE plantId='" + plid + "'";
//         int rows=stmt.executeUpdate(query2);
        break;
        case 3:
        System.out.println("Enter the id of plant which has to be deleted");
        int plid2=sc.nextInt();
        PlantDao p1=new PlantDao();
        p1.deletePlant(plid2);
//         query2="delete from plant where plantId='"+plid+"'";
//         stmt.executeUpdate(query2);
        break;
        case 4:
       // 	query1="select * from plant";
//          rs = stmt.executeQuery(query1);
//                     while (rs.next()) {
//                         // Retrieve by column name
//                         
//                         System.out.print("Name: " + rs.getString("plantName"));
//                         System.out.println(" Cost: " + rs.getDouble("cost"));
//                      }
//                    }
        PlantDao p2=new PlantDao();
        List<Plant> arr=p2.showAllPlants();
        break;
        case 5:
        System.out.println("Enter origin country name");
        sc.nextLine();
        String cntry=sc.nextLine();
        PlantDao p5=new PlantDao();
        List<Plant> arr2=p5.searchByOriginCountryName(cntry);
        
       
//         query1="select * from plant where originCountryName='"+cntry+"'";
//         rs = stmt.executeQuery(query1);
//                    while (rs.next()) {
//                        // Retrieve by column name
//                        
//                        System.out.print("Name: " + rs.getString("plantName"));
//                        System.out.println(" Cost: " + rs.getDouble("cost"));
//                     }
        break;
        case 6:
        PlantDao p4=new PlantDao();
        List<Plant> arr3=p4.searchOutdoorPlantsWithSunlight();
        System.out.println("Enter outdoor plants which requires sunlight");
        sc.nextLine();

         query1="select * from plant where plantType='outdoor' AND sunlightRequired='1'";
         rs = stmt.executeQuery(query1);
                    while (rs.next()) {
                        // Retrieve by column name
                        
                        System.out.print("Name: " + rs.getString("plantName"));
                        System.out.println(" Cost: " + rs.getDouble("cost"));
                     }
        break;
        case 7:
        PlantDao p6=new PlantDao();
        System.out.println("Enter whether search is to be daily, weekly or monthly ");
        sc.nextLine();
        String water1=sc.nextLine();
        int ans=p6.countPlantsByWaterSupplyFrequency(water1);
        System.out.println("Total plants with your condition "+water1+" is "+ans);
        break;
        case 8:
        System.out.println("Exiting from system");
        break;
        default:
        System.out.println("Invalid choice");
        break;
        }
        } while(choice!=8);
       
       
        // Close the connection when done
        conn.close();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
}

   
}
