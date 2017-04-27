package classes;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.border.*;
import javax.swing.BorderFactory;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

import javafx.embed.swing.JFXPanel;

public class InstallGUI extends JFrame {


	private static final long serialVersionUID = 258299858641866046L;
	private static final int width = 600;
	private static final int height = 300;
	
	//containers
	private JPanel settingsPanel, filePanel, buttonPanel;
	
	//buttons
	private JButton browseB, exitB, okB;
	
	//labels
	private JLabel libFileLabel, warningLabel;
	
	//panes
	private JTextPane libTPane;
	
	//text fields
	private JTextField libTField;
	
	//colors
	private Color bgC, fontC, frameC;
	
	//fonts
	private Font setupFont;
	
	//borders and padding
	private Border windowPadding;
	
	//handlers
	private ExitButtonHandler ebHandler;
	private BrowseButtonHandler bbHandler;
	private OKButtonHandler okHandler;
	
	//constraints
	GridBagConstraints left = new GridBagConstraints();
	
	//layouts
	private GridBagLayout setupLayout;
	
	//booleans
	private boolean setupComplete;
	
	public InstallGUI(){
		
		JFXPanel dumPanel = new JFXPanel();
		
		//set colors
		bgC = new Color(225, 245, 255);
		fontC = new Color(0,0,0);
		frameC = bgC;
		
		//set padding
		windowPadding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		
		//instantiate labels
		libFileLabel = new JLabel(" ", SwingConstants.LEFT);
		libFileLabel.setFont(setupFont); libFileLabel.setForeground(fontC);
		
		warningLabel = new JLabel(" ", SwingConstants.LEFT);
		warningLabel.setFont(setupFont); warningLabel.setForeground(fontC);
		
		//instantiate text panes
		libTField = new JTextField("Please enter your install folder... ", 25);
		
		//instantiate buttons
		exitB = new JButton("exit");
		exitB.setFont(setupFont); exitB.setForeground(fontC);
		ebHandler = new ExitButtonHandler();
		exitB.addActionListener(ebHandler);
		
		browseB = new JButton("Browse...");
		browseB.setFont(setupFont); browseB.setForeground(fontC);
		bbHandler  = new BrowseButtonHandler();
		browseB.addActionListener(bbHandler);
		
		okB = new JButton(" Ok ");
		okB.setFont(setupFont); okB.setForeground(fontC);
		okHandler = new OKButtonHandler();
		okB.addActionListener(okHandler);
		
		//instantiate layouts
		setupLayout = new GridBagLayout();
		
		//instantiate containers
		settingsPanel = new JPanel();
		settingsPanel.setBorder(windowPadding);
		settingsPanel.setBackground(bgC);
		
		filePanel = new JPanel();
		filePanel.setBorder(windowPadding);
		filePanel.setBackground(bgC);
		
		buttonPanel = new JPanel();
		buttonPanel.setBorder(windowPadding);
		buttonPanel.setBackground(bgC);
		
		//instantiate anchors
		left.anchor = GridBagConstraints.WEST;
		
		//instantiate booleans
		setupComplete = false;
		
		//populate containers
		buttonPanel.add(browseB, left);
		buttonPanel.add(okB, left);
		buttonPanel.add(exitB, left);
		buttonPanel.add(warningLabel, left);
		
		
		setTitle("Please Install HollowEarthOpenMediaPlayer");
		setSize(width, height);
		setBackground(frameC);
		setForeground(fontC);
		setFont(setupFont);
		add(filePanel, BorderLayout.NORTH);
		add(settingsPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private class ExitButtonHandler implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent a) {
			setupComplete = true;
			
		}
		
	}
	
	private class BrowseButtonHandler implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent a) {
			JFileChooser installFolder = new JFileChooser();
			installFolder.setCurrentDirectory(new java.io.File("."));
			installFolder.setDialogTitle("Please select install folder... ");
			installFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			installFolder.setAcceptAllFileFilterUsed(false);
			
			if (installFolder.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) { 
			      System.out.println("getCurrentDirectory(): " 
			         +  installFolder.getCurrentDirectory());
			      System.out.println("getSelectedFile() : " 
			         +  installFolder.getSelectedFile());
			      }
			    else {
			      System.out.println("No Selection ");
			      }
		}		
	}
		
	

	private class OKButtonHandler implements ActionListener{
	
		@Override
		public void actionPerformed(ActionEvent a) {
			if (!setupComplete){
				warningLabel.setText("Warning: you have not yet completed the installation procedure... ");
			}

		
		}
	
	}
	
	public boolean SetupComplete(){
		return setupComplete;
	}

}
