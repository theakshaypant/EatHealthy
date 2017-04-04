import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.Runtime;
//import java.io.InputStreamReader;
//import java.io.BufferedReader;

class client{
	
	private static boolean netIsAvailable() 
	{
    try {                                                                            
        final URL url = new URL("http://www.google.com");                       
        final URLConnection conn = url.openConnection();                           
        conn.connect();                                                 
        return true;
		} 
	catch (MalformedURLException e) {                                        
		throw new RuntimeException(e);                                         
		} 
	catch (IOException e) {                              
		return false;                                                                 
		}  
		}                                                         
    
		
	public static void main(String args[]){
		
		if(!netIsAvailable()){
			System.out.println("Maa Chuda. Net to chala le bhikaari!");
			return;
			}
		
		String address = "http://nitzapizza.com/wp-content/uploads/2011/07/maxresdefault-1.jpg";
		String c2a = "Authorization: Bearer 9xXZbLHwxeVxsm3CKDQ1vdId9BqkY2";
		String cmd = "https://api.clarifai.com/v1/tag/?model=food-items-v1.0&url="+address;
		String[] commands = {"curl", cmd, "-H",c2a};
		StringBuilder test = new StringBuilder();
	    try { 		
		Process	p = Runtime.getRuntime().exec(commands); 			
		p.waitFor(); 
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		String line = null;
		
		while ((line = reader.readLine())!= null) 
		{
		//	System.out.println(line);
			test.append(line); 
		}
		while ((line = error.readLine()) != null) {
		}
	}
		catch (Exception ex){
			ex.printStackTrace();
			}
		String test1 = test.toString();	
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
		ArrayList<String> list = new ArrayList();
		int i;
		for(i=0;i<resultProb.length;i++){
			double pc = Double.parseDouble(resultProb[i]);
			if (pc>0.9) list.add(result[i]);
			}
		for(i=0;i<list.size();i++) System.out.println(list.get(i));
		/*HashMap hm=new HashMap();
		int i;
		for(i=0;i<resultProb.length;i++){
			double pc = Double.parseDouble(resultProb[i]);
			hm.put(result[i],pc);
			//System.out.println(result[i]+ "     "+resultProb[i]);
			}
			
		Set set = hm.entrySet();
		Iterator k = set.iterator();
      
		while(k.hasNext()) {
			Map.Entry me = (Map.Entry)k.next();
			System.out.print(me.getKey() + ": ");
			System.out.println(me.getValue());
			}*/
}
}

