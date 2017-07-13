import javax.swing.JTextArea;

public class Paste implements Command{
	
	private JTextArea textArea;
	
	public Paste(JTextArea newTextArea){
		textArea = newTextArea;
	}

	@Override
	public void execute() {
		textArea.paste();	
	}

}