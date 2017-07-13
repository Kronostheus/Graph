import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class Editor extends JFrame implements ActionListener{
	
	private JTextArea area;	//multi-line area that displays plain text
	private JMenuBar menus;	//allows the creation of a menu bar with JMenu objects
	
	private JMenu file;
	private JMenu edit;
	
	private JMenuItem exit, cut, copy, paste, select, save, open;
	
	private JScrollPane sp;
	private JToolBar tb;
	
	private CommandFactory cf;
	
	private static File filename;
	
	public Editor(){
		super("Editor");
		setSize(600, 600);
		setLocationRelativeTo(null);	//center window
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Required for custom exit method for upper-right corner exit
		
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		
		area = new JTextArea();
		menus = new JMenuBar();
		
		sp = new JScrollPane(area); //associates text area with scroll pane
		tb = new JToolBar();
		
		createItems();
		
		area.setLineWrap(true);
		area.setWrapStyleWord(true); //wrap lines at word boundries (whitespace)
		
		createMenus();
		createShortcuts();
		
		pane.add(sp, BorderLayout.CENTER);
		pane.add(tb, BorderLayout.SOUTH);
		
		createListeners();
		
		setVisible(true);
		
		cf = new CommandFactory();	//creation of Command Factory to distinguish different menu commands
		
		// New listener for window closing that asks for confirmation
		WindowListener exitListener = new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        Exit windowClosing = new Exit(area);
		        windowClosing.execute();
		    }
		};
		addWindowListener(exitListener);
	}
	
	private void createItems(){
		file = new JMenu("File");
		edit = new JMenu("Edit");
		
		exit = new JMenuItem("Exit");
		cut = new JMenuItem("Cut");
		copy = new JMenuItem("Copy");
		paste = new JMenuItem("Paste");
		
		select = new JMenuItem("Select");
		save = new JMenuItem("Save");
		open = new JMenuItem("Open");

	}
	
	private void createMenus(){
		setJMenuBar(menus);
		menus.add(file);
		menus.add(edit);
		
		file.add(save);
		file.add(open);
		file.add(exit);
		
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(select);
		
	}
	
	private void createShortcuts(){
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK)); 	//CTRL+S save
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK)); 	//CTRL+O open
		
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));   //CTRL+X cut
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));	//CTRL+C copy
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK)); //CTRL+V paste
		
		select.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));//CTRL+X select
	}
	
	private void createListeners(){
		save.addActionListener(this);
		open.addActionListener(this);
		exit.addActionListener(this);
		
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		
		select.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action =  e.getActionCommand();
		
		// I will use the Command and Factory design patterns to reduce code repetition and increase modularity
		Command command = cf.getCommand(action, area);
		command.execute();
	}
	
	public static void setFile(File newFile){
		filename = newFile;
	}
	public static File getFile(){
		return filename;
	}
	
	public static void main(String[] args){
		new Editor();
	}

	
}
