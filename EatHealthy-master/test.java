import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.Runtime;

class test{
	public static void main(String args[]){
		localClient a = new localClient();
		int i;
		String url = "/home/skirmish/pizza.jpg";
		StringBuilder s = a.getData(url);
		ArrayList<String> array = a.findTags(s);
		for(i=0;i<array.size();i++) System.out.println(array.get(i));
		}
}
