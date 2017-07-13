import javax.swing.JTextArea;
import javax.swing.JOptionPane;

public class Exit implements Command{
	
	private JTextArea textArea;
	
	public Exit(JTextArea newTextArea){
		textArea = newTextArea;
	}

	@Override
	public void execute() {
		int choice = JOptionPane.showConfirmDialog(textArea.getParent(), "Would you like to save before exiting?", "Exit",
				JOptionPane.YES_NO_CANCEL_OPTION);

		if(choice == JOptionPane.YES_OPTION){
			Save save = new Save(textArea);
			save.execute();
		} else if(choice == JOptionPane.NO_OPTION){
			System.exit(0);
		} else
			return;
	}

}
