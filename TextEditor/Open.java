import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Open implements Command{
	
	private JTextArea textArea;
	private JFileChooser chooser;
	private File file;
	private FileReader reader;
	
	public Open(JTextArea newTextArea){
		textArea = newTextArea;
		chooser = new JFileChooser();
	}

	@Override
	public void execute() {

		int choice = JOptionPane.showConfirmDialog(textArea.getParent(), "Would you like to save before opening file?", "Open",
				JOptionPane.YES_NO_CANCEL_OPTION);
		if(choice == JOptionPane.YES_OPTION){
			Save save = new Save(textArea);
			save.execute();
		} else if(choice == JOptionPane.CANCEL_OPTION)
			return;

		
		if(chooser.showOpenDialog(textArea.getParent()) != JFileChooser.APPROVE_OPTION)
			return;
		
		file = chooser.getSelectedFile();
	    if (file == null)
	    	return;
	    
	    reader = null;
	    try {
	        reader = new FileReader(file);
	        textArea.read(reader, null);
	        Editor.setFile(file);
	        
	    } catch (IOException ex) {
	        JOptionPane.showMessageDialog(textArea.getParent(), "File Not Found", "ERROR", JOptionPane.ERROR_MESSAGE);
	        
	    } finally {
	        if (reader != null) {
	          try {
	            reader.close();
	          } catch (IOException x) {}
	        }
	    }
	}

}
