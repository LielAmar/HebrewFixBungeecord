package g_o.hefix_team.hebrewfix;

import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class EventManager implements Listener {

	@EventHandler
	public void onPrivateMessage(ChatEvent e) {
		if (!e.isCommand()) return;
		
		String cmd = e.getMessage();
		if (!cmd.contains(" ")) return;

		if (!PrivateMessageCommands.isPrivateMessageCmd(cmd)) return;
		if (e.isCancelled()) return;
		
		String cmdTemplate = PrivateMessageCommands.getTemplate(cmd);
		if (cmdTemplate == null) return;
		String[] cmdArgs = cmd.split(" ");
		
		int msgIndex = PrivateMessageCommands.getArgIndex(cmdTemplate, "message");

		if (msgIndex == -1 || msgIndex >= cmdArgs.length) return;
		
		String preMsg = "";
		for (int i = 0; i < msgIndex; i++)
			preMsg += cmdArgs[i] + " ";

		String msg = "";
		for (int i = msgIndex; i < cmdArgs.length; i++)
			msg += cmdArgs[i] + " ";
		
		e.setMessage(preMsg + Utils.fixMessage(msg));
	}

	@EventHandler
	public void onPlayerChat(ChatEvent e) {
		if (e.isCommand())
			return;

		String msg = e.getMessage();
		e.setMessage(Utils.fixMessage(msg));
	}
}
