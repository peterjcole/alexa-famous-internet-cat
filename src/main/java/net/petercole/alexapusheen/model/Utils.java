package net.petercole.alexapusheen.model;

import java.util.List;
import java.util.StringJoiner;

public class Utils {

	public static String listToCommaString(List<String> list) {
		if (list.size() == 1) {return list.get(0);};
		
		StringJoiner joiner = new StringJoiner(", ");
		for (int x = 0; x < (list.size()-1); x++) {
			joiner.add(list.get(x).toString());			
		}
		
		String joinedString = joiner.toString() + " and " + list.get(list.size()-1).toString();
		return joinedString;
		
	}
	
}

