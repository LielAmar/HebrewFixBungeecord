/**
 * 
 * This class was originally written by g_o, LielAmar and PvPNick.
 * LielAmar updated it to support BungeeCord specifically.
 * 
 */

package g_o.hefix_team.hebrewfix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrivateMessageCommands {

	public static final String ARG_DELIMITER = "%";
	private static List<String> pmTemplates = null;
	
	/**
	 * getArgIndex - returns the cmd template arg index
	 * @param template - the command template
	 * @param argName - the argument to search for
	 * @return int - the index; -1 if fails
	 */
	public static int getArgIndex(String template, String argName) {
		if (!template.contains(" ")) return -1;	// Check if command has arguments
		
		// Search for argument
		String arg = ARG_DELIMITER + argName + ARG_DELIMITER;
		return Arrays.asList(template.split(" ")).indexOf(arg);
	}
	
	/**
	 * isPrivateMessageCmd - determine if a templated command
	 * @param command - the command to check
	 * @return - true if it is; false if isn't
	 */
	public static boolean isPrivateMessageCmd(String command) {
		for (String str : getCommands())
			if (command.toLowerCase().startsWith(str))
				return true;
		return false;
	}
	
	/**
	 * getTemplate - get the template for command
	 * @param command - command to get tempalte for
	 * @return String - the template
	 */
	public static String getTemplate(String command) {
		if (!isPrivateMessageCmd(command)) return null;
		for (String template : getTemplates())
			if (command.startsWith(template.split(" ")[0]))
				return template;
		return null;
	}
	
	/**
	 * getCommands - get the commands which have a template
	 * @return List<String> - list of commands
	 */
	public static List<String> getCommands() {
		List<String> cmds = new ArrayList<>();
		for (String str : getTemplates())
			cmds.add(str.split(" ")[0]);
		return cmds;
	}
	
	/**
	 * getTemplates - get the templates saved
	 * @return List<String> - the saved templates.
	 */
	public static List<String> getTemplates() {
		return pmTemplates;
	}

	/**
	 * setTemplates - set the templates.
	 * @param templates - the templates to set.
	 */
	public static void setTemplates(List<String> templates) {
		pmTemplates = templates;
	}
}
