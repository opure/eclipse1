package zz;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
public class MapTest {
	public static void main(String[] args) { 
   Map<String,String> m=new HashMap<String,String>();
   m.put("a","b");
   m.put("a","c");
   m.put("a","d");
   m.put("f","e");
   Set<Map.Entry<String,String>> s=m.entrySet();
   Iterator<Map.Entry<String,String>> i=null;
    i=s.iterator();
    while(i.hasNext()){
    	Map.Entry<String, String> me=i.next();
    	System.out.println(me.getKey());
    }
    
   
 }
}