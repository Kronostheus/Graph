import javax.swing.JTextArea;

public class Select implements Command{
	
	private JTextArea textArea;
	
	public Select(JTextArea newTextArea){
		textArea = newTextArea;
	}

	@Override
	public void execute() {
		textArea.selectAll();
	}

}