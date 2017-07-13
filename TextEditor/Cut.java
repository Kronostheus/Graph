import javax.swing.JTextArea;

public class Cut implements Command{
	
	private JTextArea textArea;
	
	public Cut(JTextArea newTextArea){
		textArea = newTextArea;
	}

	@Override
	public void execute() {
		textArea.cut();
	}

}