package jsoft.library;

import net.htmlparser.jericho.*;

public class Utilities_Text {

	public static boolean checkPass(String pass1, String pass2) {
		boolean flag = false;
		if (pass1 != null && !pass1.equalsIgnoreCase("")) {
			if (pass1.length() > 5) {
				if (pass1.equals(pass2)) {
					flag = true;
				}
			}
		}

		return flag;
	}
	
	public static String encode(String str_UNI) {
		return CharacterReference.encode(str_UNI);
	}
	
	public static String decode(String str_HTML) {
		return CharacterReference.decode(str_HTML);
	}

}
