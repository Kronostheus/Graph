import javax.swing.JTextArea;

public class CommandFactory {
	public CommandFactory(){
		
	}
	
	public Command getCommand(String action, JTextArea textArea){
		Command theCommand = null;
		switch(action){
		case "Copy":
			theCommand = new Copy(textArea);
			break;
		case "Cut":
			theCommand = new Cut(textArea);
			break;
		case "Paste":
			theCommand = new Paste(textArea);
			break;
		case "Select":
			theCommand = new Select(textArea);
			break;
		case "Exit":
			theCommand = new Exit(textArea);
			break;
		case "Open":
			theCommand = new Open(textArea);
			break;
		case "Save":
			theCommand = new Save(textArea);
			break;
		}
		return theCommand;
	}
}
