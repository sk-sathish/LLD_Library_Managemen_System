package helper;

import java.util.HashMap;
import java.util.Map;

public class UniqueIDGenerator {
	private int startIndex;
	private String prefix;
	private static Map<String, UniqueIDGenerator> instancesMap;
	private UniqueIDGenerator(String prefix) {
		this.prefix = prefix;
		this.startIndex = 1;
	}
	public static UniqueIDGenerator getInstance(String prefix) {
		if(instancesMap == null) {
			instancesMap = new HashMap<String, UniqueIDGenerator>();
		}
		if(!instancesMap.containsKey(prefix)) {
			instancesMap.put(prefix, new UniqueIDGenerator(prefix));
		}
		return instancesMap.get(prefix);
	}
	public String generateUUID() {
		String uniqueID = prefix+""+startIndex;
		startIndex++;
		return uniqueID;
	}
}
