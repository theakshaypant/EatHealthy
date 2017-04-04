import java.util.*;
import java.io.*;

	
public class read{
    public static void main(String[] args) throws Exception {
        Map<String,List<String> > map = new HashMap<String, List<String>>();
        BufferedReader in = new BufferedReader(new FileReader("text.txt"));
        String line = "";
        while ((line = in.readLine()) != null) {
            String parts[] = line.split(" ");
            List<String> food_list = new ArrayList();
            food_list.add(parts[1]);
            food_list.add(parts[2]);
            map.put(parts[0], food_list);
          //  map.put(parts[0],"perosn");
        }
        in.close();
        Set set = map.entrySet();
        Iterator k =set.iterator();

        while(k.hasNext()) {
        	Map.Entry me= (Map.Entry)k.next();
        	System.out.print(me.getKey() +" :");
        	System.out.print(me.getValue());
        }
    }
}