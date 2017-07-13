import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Save implements Command{
	
	private JTextArea textArea;
	private JFileChooser chooser;
	private File file;
	private FileWriter writer;
	
	public Save(JTextArea newTextArea){
		textArea = newTextArea;
	}

	@Override
	public void execute() {
		
		if(Editor.getFile() == null){
			chooser = new JFileChooser();
			if(chooser.showSaveDialog(textArea.getParent()) != JFileChooser.APPROVE_OPTION)
				return;
		
			file = chooser.getSelectedFile();
		} else{
			file = Editor.getFile();
		}
		
		if (file == null)
	    	return;
		
	    writer = null;
	    try {
	    	writer = new FileWriter(file);
	        textArea.write(writer);
	        Editor.setFile(file);
	        
	    } catch (IOException ex) {
	        JOptionPane.showMessageDialog(textArea.getParent(), "File Not Saved", "ERROR", JOptionPane.ERROR_MESSAGE);
	        
	    } finally {
	        if (writer != null) {
	          try {
	            writer.close();
	          } catch (IOException x) {}
	        }
	    }
	}

}
