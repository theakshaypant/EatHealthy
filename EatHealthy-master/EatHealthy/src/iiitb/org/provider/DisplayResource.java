package iiitb.org.provider;

import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;

import iiitb.org.model.ResourceModel;
import jdbc.Connectionutil;

	public class DisplayResource {

		static List <String> n = new ArrayList<String>();
		
		//test file for running the code
/*		public static void main(String args[]) {
			
			n.add("pizza");
			n.add("chese");
			n.add("pizza");
			providerMethod(n);
		}*/

		public  static ResourceModel providerMethod(List<String> nameString)
		{
			
			
			ResourceModel resourceModel = new ResourceModel();

			Connection con = Connectionutil.getConnection();
		    
			for(String s: nameString)
			{
			   String query=  "select itemName,calories,carbohydrates,protein,sodium,totalFat from FoodItems,NutritionValue where NutritionValue.FK= FoodItems.SK AND itemName =?";
			   try {
				   PreparedStatement ps = con.prepareStatement(query);   //ps object represent the precompiled query of database software being from java application
				   ps.setString(1, s);
				   ResultSet rs = ps.executeQuery();  //storing the returned query pointer 
				   if (rs != null && rs.next()) 
				   {
					
					resourceModel.setName(rs.getString(1));
					resourceModel.setCalorie(rs.getDouble(2));
					resourceModel.setCarbohydrate(rs.getDouble(3));
					resourceModel.setProtein(rs.getDouble(4));
					resourceModel.setSodium(rs.getDouble(5));
					resourceModel.setTotalFat(rs.getDouble(6));
					break;
					}
			     }
			   
			   catch (SQLException e) {

					e.printStackTrace();

				}
			}
			
				    
			return resourceModel;
		}
	}
		

