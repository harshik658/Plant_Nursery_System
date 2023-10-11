package com.amdocs.plant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amdocs.plant.exception.userDefinedException;
import com.amdocs.plant.model.Plant;

public class PlantDao extends Plant{
     Connection conn= DatabaseConnection.getConnection();
     public PlantDao() {
    	 
     }
   
    public PlantDao(int pid,String pname,String type,String cname,String wfreq,double cost,int sun){
    	this.plantId=pid;
    	this.plantName=pname;
    	this.plantType=type;
    	this.originCountryName=cname;
    	this.setWaterSupplyFrequency(wfreq);
    	this.cost=cost;
    	this.setSunLightRequired(sun);
    	this.conn = DatabaseConnection.getConnection();
    }

    public int addPlant(Plant p) {
    	String query2;
    	String insertQuery = "INSERT INTO plant (plantId, plantName, originCountryName, sunlightrequired, waterSupplyFrequency, planttype,cost) VALUES (?, ?, ?, ?, ?, ?, ?)";
    	
    	try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
    	   preparedStatement.setInt(1, p.plantId);
    	   preparedStatement.setString(2, p.plantName);
    	   preparedStatement.setString(3, p.originCountryName);
    	   preparedStatement.setInt(4, p.getSunLightRequired());
    	   preparedStatement.setString(5, p.getWaterSupplyFrequency());
    	   preparedStatement.setString(6, p.plantType);
    	   preparedStatement.setDouble(7, p.cost);
    	
    	   // Execute the query
    	   int rowsInserted = preparedStatement.executeUpdate();
    	   
    	   if (rowsInserted > 0) {
    	       System.out.println("Plant record inserted successfully.");
    	   } else {
    	       System.out.println("Failed to insert plant record.");
    	   }
    	} catch (SQLException e) {
    	   e.printStackTrace();
    	   // Handle the SQL exception appropriately
    	}
    	
    	return 0;

      
    }

    public boolean deletePlant(int pid) throws userDefinedException {


		try{
		Statement stmt=conn.createStatement();
		String query2;
		String query1="select * from plant where plantId='"+pid+"'";
		ResultSet rs=stmt.executeQuery(query1);
		if(rs.next()) {
		query2="delete from plant where plantId='"+pid+"'";
		stmt.executeUpdate(query2);
		return true;
		} else {
		throw new userDefinedException("Plant Not Found");
//		System.out.println("Plant not found");
		
//		return false;
		}
		
		} catch(SQLException e) {
		e.printStackTrace();
		}
		return true;
		
		}
		public boolean updatePlantCost(int plid,double newCost) {
		try {
		Statement stmt=conn.createStatement();
		String query2 = "UPDATE plant SET cost=" + newCost + " WHERE plantId='" + plid + "'";
		int rows=stmt.executeUpdate(query2);
		if(rows>0)
		return true;
		return false;
		} catch(SQLException e) {
		
		}
		return true;

       
    }

   

    public List<Plant> showAllPlants() {
    	ArrayList<Plant> arr=new ArrayList<Plant>();
    	try {
    	String query1="select * from plant";
    	Statement stmt=conn.createStatement();
    	ResultSet rs = stmt.executeQuery(query1);
    	       while (rs.next()) {
    	           // Retrieve by column name
    	        int id=rs.getInt("plantId");
    	        String pname=rs.getString("plantName");
    	        String cname=rs.getString("originCountryName");
    	        int sun=rs.getInt("sunlightRequired");
    	        String water=rs.getString("waterSupplyFrequency");
    	        String ptype=rs.getString("plantType");
    	        double cost=rs.getDouble("cost");
    	        Plant p=new Plant(id,pname,ptype,cname,water,cost,sun);
    	           arr.add(p);
    	        }
    	       for(int i=0;i<arr.size();i++) {
    	        Plant t=arr.get(i);
    	        System.out.println();
    	        System.out.print("ID "+ t.plantId+" ");
    	        System.out.print("Plant Name "+ t.plantName+" ");
    	        System.out.print("Origin Country Name "+ t.originCountryName+" ");
    	        System.out.print("Plant Type "+ t.plantType+" ");
    	        System.out.print("Cost "+ t.cost);
    	       }
    	} catch(SQLException e) {

    	}

    	        return arr;
    }

    public List<Plant> searchByOriginCountryName(String cntry) {
    	ArrayList<Plant> arr=new ArrayList<Plant>();
    	try {
    	String query1="select * from plant where originCountryName='"+cntry+"'";
    	PreparedStatement stmt=conn.prepareStatement(query1);
    	stmt.setString(1,cntry);
    	
    	  ResultSet rs = stmt.executeQuery(query1);
    	while (rs.next()) {
    	           // Retrieve by column name
    	        int id=rs.getInt("plantId");
    	        String pname=rs.getString("plantName");
    	        String cname=rs.getString("originCountryName");
    	        int sun=rs.getInt("sunlightRequired");
    	        String water=rs.getString("waterSupplyFrequency");
    	        String ptype=rs.getString("plantType");
    	        double cost=rs.getDouble("cost");
    	        Plant p=new Plant(id,pname,ptype,cname,water,cost,sun);
    	           arr.add(p);
    	        }
    	       for(int i=0;i<arr.size();i++) {
    	        Plant t=arr.get(i);
    	        System.out.println();
    	        System.out.print("ID "+ t.plantId+" ");
    	        System.out.print("Plant Name "+ t.plantName+" ");
    	        System.out.print("Origin Country Name "+ t.originCountryName+" ");
    	        System.out.print("Plant Type "+ t.plantType+" ");
    	        System.out.print("Cost "+ t.cost);
    	       }
    	} catch(SQLException e) {
    		System.out.println("In 5");
    	}

    	        return arr;
    }

    public List<Plant> searchOutdoorPlantsWithSunlight() {
    	ArrayList<Plant> arr=new ArrayList<Plant>();
    	try {
    	Statement stmt=conn.createStatement();
    	String query1="select * from plant where plantType='outdoor' && sunlightRequired='1'";
    	  ResultSet rs = stmt.executeQuery(query1);
    	while (rs.next()) {
    	           // Retrieve by column name
    	        int id=rs.getInt("plantId");
    	        String pname=rs.getString("plantName");
    	        String cname=rs.getString("originCountryName");
    	        int sun=rs.getInt("sunlightRequired");
    	        String water=rs.getString("waterSupplyFrequency");
    	        String ptype=rs.getString("plantType");
    	        double cost=rs.getDouble("cost");
    	        Plant p=new Plant(id,pname,ptype,cname,water,cost,sun);
    	           arr.add(p);
    	        }
    	       for(int i=0;i<arr.size();i++) {
    	        Plant t=arr.get(i);
    	        System.out.println();
    	        System.out.print("ID "+ t.plantId+" ");
    	        System.out.print("Plant Name "+ t.plantName+" ");
    	        System.out.print("Origin Country Name "+ t.originCountryName+" ");
    	        System.out.print("Plant Type "+ t.plantType+" ");
    	        System.out.print("Cost "+ t.cost);
    	       }
    	} catch(SQLException e) {

    	}

    	        return arr;
    }

    public int countPlantsByWaterSupplyFrequency(String waterSupplyFrequency) {
    	String query = "SELECT COUNT(*) FROM plant WHERE waterSupplyFrequency = '" + waterSupplyFrequency + "'";
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
           
        }
        return 0;
        
    }
    
    
}
