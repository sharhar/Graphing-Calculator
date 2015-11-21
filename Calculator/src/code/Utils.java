package code;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	public static String[] split(String text, char[] spliters) {
		
		List<String> strings = new ArrayList<String>();
		
		String current = "";
		
		for(int i =0;i < text.length();i++) {
			char c = text.charAt(i);
			
			if(inCharArray(c, spliters)) {
				strings.add(new String(current));
				current = "";
			} else {
				current += c;
			}
		}
		
		strings.add(current);
		
		String[] result = new String[strings.size()];
		
		for(int i =0;i < result.length;i++) {
			result[i] = strings.get(i);
		}
		
		return result;
	}
	
	public static boolean inCharArray(char c, char[] ca) {
		for(int i =0;i < ca.length;i++) {
			if(c == ca[i]) {
				return true;
			}
		}
		
		return false;
	}
	
	public static String stripSpaces(String text) {
		String result = "";
		
		for(int i = 0;i < text.length();i++) {
			char c = text.charAt(i);
			
			if(c != ' ' && c != '\n') {
				result += c;
			}
		}
		
		return result;
	}
}
