package iiitb.org.provider;
import java.util.*;
//import java.net.*;
import java.io.*;
import java.lang.Runtime;

public class UrlClient{
	
	//class for getting 'chunk' of data and then extracting useful tags from it
	//server information
	private final String authorize = "Authorization: Bearer B1bWBo8obGSudIF8FKlzQSg9E9SdVr";
	private final String server = "https://api.clarifai.com/v1/tag/?model=food-items-v1.0&url=";
	public StringBuilder chunk = new StringBuilder();
	
	public StringBuilder getData(String address){
		//getting chunk of data
		//commands in a separate console
		String command1 = server+address;
		String[] commands = {"curl", command1, "-H",authorize};
		
	    try { 
	    	//starting console in another process
			Process	p = Runtime.getRuntime().exec(commands); 	
			p.waitFor(); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			String line = null;	
			while ((line = reader.readLine())!= null) 
			{
			//	System.out.println(line);
				chunk.append(line);
			}
			while ((line = error.readLine()) != null) {
			}
		}
		catch (Exception ex){
			ex.printStackTrace();
			}
		return chunk;
		}
		
	public ArrayList<String> findTags(StringBuilder chunk){
		//extracting useful tags by string operations
		String test1 = chunk.toString();	
		//start of string operations
		String new1[] = test1.split("classes");
		String new2[] = new1[1].split("concept_ids");
		String new3[] = new2[1].split("probs");
		String new4[] = new3[1].split("docid_str");
		String x = new4[0];
		x=x.replace("[","").replace("\"","").replace(",","").replace("]","").replace(": ","").replace("}","");
		String[] resultProb = x.split(" ");
		String y = new2[0];
		y=y.replace("[","").replace(",","").replace("]","").replace(": ","");
		String[] result = y.split("\" \"");
		result[0]=result[0].replace("\"","");
		//end of string operations
		ArrayList<String> list = new ArrayList<String>();
		int i;
		//adding to a new array list

		for(i=0;i<resultProb.length;i++){
			double pc = Double.parseDouble(resultProb[i]);
			if (pc>0.9) list.add(result[i]);
			}
		return list;
		}
}

