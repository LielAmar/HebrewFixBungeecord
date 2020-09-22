/**
 * 
 * This class was originally written by g_o, later updated by LielAmar & PvPNick.
 * LielAmar updated it to support BungeeCord specifically.
 * 
 */

package g_o.hefix_team.hebrewfix;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	
	/**
	 * Fix an array of messages
	 * @param msgs - the messages to fix & concatenate
	 * @return String - result of the operation
	 */
	public static String fixAll(String[] msgs) {
		String result = "";
		for (String msg : msgs) {
			result += fixMessage(msg);
		}
		
		return result;
	}
	
	/**
	 * Fix Hebrew reversed message if needed to.
	 * @param msg - the message to fix.
	 * @return String - the fixed message. 
	 */
	public static String fixMessage(String msg) {
		if (!isHebrew(msg)) return msg;
		return fixFromWordsArray(msg.split(" "));
	}
	
	/**
	 * Fix message for incompatible clients
	 * @param msg - the message to fix
	 * @param isSenderHeb - is sender client in hebrew
	 * @return String - the fixed message
	 */
	public static String fixMessageIncompatible(String msg, boolean isSenderHeb) {
		if (!isHebrew(msg)) return msg;	// Check messag is in hebrew
		String[] words = msg.split(" ");
		
		if (isSenderHeb)
			return fixFromWordsArray(words);
		else
			return fixWordsIncompatible(words);
	}
	
	/**
	 * isHebrew - a function that retursn true if a String has hebrew letters in it.
	 * @author: nikita (AKA PvPNiK)
	 * @param str - the string to check if it have a hebrew letter in it.
	 * @return true if it has hebrew letters in it, otherwise false.
	 */
	public static boolean isHebrew(String str) {
		if (str == null) return false;
		Pattern p = Pattern.compile("\\p{InHebrew}");
		Matcher m = p.matcher(str);
		return m.find();
	}
	
	/**
	 * Reverse a string
	 * @param s - the string to revese
	 * @return String - the reversed string
	 */
	public static String reverseString(String s) {
		String result = "";
		for (int i = s.length()-1; i > -1; i--)
			result += s.charAt(i);
		return result;
	}
	
	/**
	 * fixFromWordsArray - a function that reverse hebrew letters (avoiding non hebrew letters).
	 * @param words - the words to reverse.
	 * @return String - the fixed reversed string.
	 */
	public static String fixFromWordsArray(String[] words) {
		String result = "";
		for (int i = words.length - 1; i > -1; i--) {
			if (isHebrew(words[i])) {
				for (int j = words[i].length() - 1; j > -1; j--)
					result += words[i].charAt(j);
			} else {
				for (int j = 0; j < words[i].length(); j++)
					result += words[i].charAt(j);
			}
			result += " ";
		}
		return result.trim();
	}
	
	/**
	 * fixWordsIncomatible - fixFromWordsArray for incompatible clients
	 * Changes rtl <--> ltr
	 * @param words - the words of the message
	 * @return String - fixed string
	 */
	public static String fixWordsIncompatible(String[] words) {
		String result = "";
		for (int i = words.length - 1; i > -1; i--) {
			result += words[i] + " ";
		}
		return result.trim();
	}
}
