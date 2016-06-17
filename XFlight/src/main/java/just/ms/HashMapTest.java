package just.ms;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

	public static void main(String[] args) {
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		for(int i = 0;i<16;i++){
			map.put(""+i, i);
		}
		
		map.put(null, "1");
		map.put(null, "2");
		map.put(null, "3");
		Object p1 = map.put("1", "2");
		Object p2 = map.put("1", "3");
		
		System.out.println("p1:"+p1);
		System.out.println("p2:"+p2);
		//map.put(null, "1");
		
		System.out.println(map.size());
		System.out.println(map.get(null));
		System.out.println(map.get("1"));
		
	}
	
}
