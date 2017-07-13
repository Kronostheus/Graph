import javax.swing.JTextArea;

public class Copy implements Command{
	
	private JTextArea textArea;
	
	public Copy(JTextArea newTextArea){
		textArea = newTextArea;
	}

	@Override
	public void execute() {
		textArea.copy();
	}

}
