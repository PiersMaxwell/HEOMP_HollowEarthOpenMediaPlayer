package classes;

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
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import javafx.embed.swing.JFXPanel;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.regex.*;

import java.awt.image.*;
import java.io.IOException;
import java.awt.Image;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.CopyOption;
import java.nio.file.StandardCopyOption;




public class PlayerGUI extends JFrame {

	private static final long serialVersionUID = 7570631844673220616L;
	private static final int width = 1200;
	private static final int height = 650;
	
	private JPanel MPpane, LIBpane, INFOpane, FUNpane;
	
	//Labels for Media Player Container
	private JLabel trackL, invTrackL, fileL, artistL, invArtistL, dateL, 
	invDateL, lengthL, invLengthL, albumL, invAlbumL, albumArtL, formatL, 
	invFormatL, encodingL, invEncodingL;
	
	//Labels for Library Container
	private JLabel libL, playL;
	
	private JTextPane libTP;
	private JScrollPane libSP;
	private JList libJlist;
	private DefaultListModel<String> stringModel;
	
	private JTextPane playTP;
	private JScrollPane playSP;
	
	private JTextField trackJF, fileJF, libTextBox;
	private JButton fileBrowseb, exitb, playb, pauseb, stopb, rewindb, 
	fforwardb, previousb, nextb, muteb, addfilelibb, removefilelibb ;
	
	private Color bgC, fontC, frameBGColor;
	
	private Font bgFont;
	private Border padding;
	
	private ExitButtonHandler ebHandler;
	private BrowseButtonHandler bbHandler;
	private PlayButtonHandler pbHandler;
	private PauseButtonHandler pabHandler;
	private StopButtonHandler sbHandler;
	private FastForwardButtonHandler ffHandler;
	private RewindButtonHandler rbHandler;
	private NextButtonHandler nbHandler;
	private PreviousButtonHandler prbHandler;
	private MuteButtonHandler mbHandler;
	
	private LibraryJSPHandler libJSPHandler;
	
	
	private HollowEarthMediaPlayer mp;
	
	GridBagConstraints left = new GridBagConstraints();
	GridBagConstraints left2 = new GridBagConstraints();
	GridBagConstraints middle = new GridBagConstraints();
	GridBagConstraints right = new GridBagConstraints();
	GridBagConstraints right2 = new GridBagConstraints();
	GridBagConstraints bottomRight = new GridBagConstraints();

	public PlayerGUI(){
		
		//Required to use JFX classes later on, do not use
		JFXPanel fxPanel = new JFXPanel();
		
		//set colors
		bgC = new Color(225,245,255);
		fontC = new Color(0,0,0);
		padding = BorderFactory.createEmptyBorder(20,20,20,20);
		frameBGColor = bgC;
		
		//set font
		bgFont = new Font("Courier", Font.BOLD, 12);
		
		//instantiate MP labels
		trackL = new JLabel(" ", SwingConstants.LEFT);
		trackL.setPreferredSize(new Dimension(200, 100));
		trackL.setFont(bgFont); trackL.setForeground(fontC);
		
		invTrackL = new JLabel(" ", SwingConstants.LEFT);
		invTrackL.setFont(bgFont); invTrackL.setForeground(fontC);
		
		fileL = new JLabel("File Name: ", SwingConstants.LEFT);
		fileL.setFont(bgFont); fileL.setForeground(fontC);
		
		artistL = new JLabel("Artist:              ", SwingConstants.LEFT);
		artistL.setFont(bgFont); artistL.setForeground(fontC);
		
		invArtistL = new JLabel(" ", SwingConstants.LEFT);
		invArtistL.setFont(bgFont); invArtistL.setForeground(fontC);
		
		albumL = new JLabel("Album: ", SwingConstants.LEFT);
		albumL.setFont(bgFont); albumL.setForeground(fontC);
		
		invAlbumL = new JLabel(" ", SwingConstants.LEFT);
		invAlbumL.setFont(bgFont); invAlbumL.setForeground(fontC);
		
		dateL = new JLabel("Date: ", SwingConstants.LEFT);
		dateL.setFont(bgFont); dateL.setForeground(fontC);
		
		invDateL = new JLabel(" ", SwingConstants.LEFT);
		invDateL.setFont(bgFont); invDateL.setForeground(fontC);
		
		lengthL = new JLabel("Song Length: ", SwingConstants.LEFT);
		lengthL.setFont(bgFont); lengthL.setForeground(fontC);
		
		invLengthL = new JLabel(" ", SwingConstants.LEFT);
		invLengthL.setFont(bgFont); invLengthL.setForeground(fontC);
		
		albumArtL = new JLabel(" ", SwingConstants.LEFT);
		albumArtL.setFont(bgFont); albumArtL.setForeground(fontC);
		
		formatL = new JLabel("File Format: ", SwingConstants.LEFT);
		formatL.setFont(bgFont); formatL.setForeground(fontC);
		
		invFormatL = new JLabel(" ", SwingConstants.LEFT);
		invFormatL.setFont(bgFont); invFormatL.setForeground(fontC);
		
		encodingL = new JLabel("File Encoding: ", SwingConstants.LEFT);
		encodingL.setFont(bgFont);encodingL.setForeground(fontC);
		
		invEncodingL = new JLabel(" ", SwingConstants.LEFT);
		invEncodingL.setFont(bgFont); invEncodingL.setForeground(fontC);
		
		
		//instantiate LIB labels
		libL = new JLabel("Library: ", SwingConstants.LEFT);
		libL.setFont(bgFont); libL.setForeground(fontC);
		playL = new JLabel("          Playlist: ", SwingConstants.LEFT);
		playL.setFont(bgFont); playL.setForeground(fontC);
		
		//instantiate Text Fields
		fileJF = new JTextField("Please input file here...", 25);
		fileJF.setFont(bgFont); fileJF.setForeground(fontC);
		
		
		stringModel = new DefaultListModel<>();
		libJlist = new JList<String>(stringModel);
		libJlist.setBorder(padding);
		libJSPHandler = new LibraryJSPHandler();
		libJlist.addMouseListener(libJSPHandler);
		//libJlist.addListSelectionListener(libJSPHandler);
		libSP = new JScrollPane(libJlist);
		libSP.setFont(bgFont); libSP.setForeground(fontC);
		libSP.setPreferredSize(new Dimension(400, 350));
		libSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		//playTP = new JTextPane();
		//playSP = new JScrollPane(playTP);
		//playSP.setFont(bgFont); playSP.setForeground(fontC);
		//playSP.setPreferredSize(new Dimension(200,350));
		//playSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		//instantiate Buttons
		fileBrowseb = new JButton("Browse...");
		fileBrowseb.setFont(bgFont); fileBrowseb.setForeground(fontC);
		bbHandler = new BrowseButtonHandler();
		fileBrowseb.addActionListener(bbHandler);
		
		exitb = new JButton("exit");
		exitb.setFont(bgFont); exitb.setForeground(fontC);
		ebHandler = new ExitButtonHandler();
		exitb.addActionListener(ebHandler);
		
		playb = new JButton("play");
		playb.setFont(bgFont); playb.setForeground(fontC);
		pbHandler = new PlayButtonHandler();
		playb.addActionListener(pbHandler);
		
		pauseb = new JButton(" II ");
		pauseb.setFont(bgFont); pauseb.setForeground(fontC);
		pabHandler = new PauseButtonHandler();
		pauseb.addActionListener(pabHandler);
		
		stopb = new JButton("stop");
		stopb.setFont(bgFont); stopb.setForeground(fontC);
		sbHandler = new StopButtonHandler();
		stopb.addActionListener(sbHandler);
		
		fforwardb = new JButton(" >> ");
		fforwardb.setFont(bgFont); fforwardb.setForeground(fontC);
		ffHandler = new FastForwardButtonHandler();
		fforwardb.addActionListener(ffHandler);
		
		rewindb = new JButton(" << ");
		rewindb.setFont(bgFont); rewindb.setForeground(fontC);
		rbHandler = new RewindButtonHandler();
		rewindb.addActionListener(rbHandler);
		
		previousb = new JButton(" < ");
		previousb.setFont(bgFont); previousb.setForeground(fontC);
		prbHandler = new PreviousButtonHandler();
		previousb.addActionListener(prbHandler);
		
		nextb = new JButton(" > ");
		nextb.setFont(bgFont); nextb.setForeground(fontC);
		nbHandler = new NextButtonHandler();
		nextb.addActionListener(nbHandler);
		
		muteb = new JButton("MUTE");
		muteb.setFont(bgFont); muteb.setForeground(fontC);
		mbHandler = new MuteButtonHandler();
		muteb.addActionListener(mbHandler);
		
		addfilelibb = new JButton("Add File to Library... ");
		addfilelibb.setFont(bgFont); addfilelibb.setForeground(fontC);
		addfilelibb.addActionListener(bbHandler);
		
		removefilelibb = new JButton("Remove File from Library... ");
		removefilelibb.setFont(bgFont); removefilelibb.setForeground(fontC);
		
		
		
		
		
		
		//get Container
		//MPpane// = getContentPane();
		MPpane = new JPanel();
		
		//Set title		
		setTitle("Hollow Earth Media Player");
		
		//Set Layout of Pane
		
		MPpane.setBorder(padding);
		GridBagLayout layout = new GridBagLayout(); 
		MPpane.setLayout(layout);
		MPpane.setBackground(bgC);
		//MPpane.setFont(bgFont);
		
        left.anchor = GridBagConstraints.WEST;
        
        left2.anchor = GridBagConstraints.WEST;
        left2.gridwidth  = GridBagConstraints.REMAINDER;
        left2.weightx = 3.0;
        
        middle.weightx = 3.0;
        middle.fill = GridBagConstraints.HORIZONTAL;
        middle.gridwidth = GridBagConstraints.RELATIVE;
        
        right.anchor = GridBagConstraints.EAST;
        right.gridwidth = GridBagConstraints.REMAINDER;
        
        right2.weightx = 3.0;
        right2.gridwidth = GridBagConstraints.REMAINDER;
        
        bottomRight.gridwidth = GridBagConstraints.REMAINDER;
        bottomRight.anchor = GridBagConstraints.EAST;
        
        
		//Add components to pane
		MPpane.add(fileL, left);
		MPpane.add(fileJF, middle);
		MPpane.add(fileBrowseb, right);
		
		addFreeSpace(1, MPpane);
		
		MPpane.add(muteb, left);
		MPpane.add(previousb, left);
		MPpane.add(rewindb, left);
		MPpane.add(playb, left);
		MPpane.add(pauseb, left);
		MPpane.add(fforwardb, left);
		MPpane.add(nextb, left);
		MPpane.add(stopb, right);
		
		INFOpane = new JPanel();
		INFOpane.setBorder(padding);
		INFOpane.setLayout(layout);
		INFOpane.setBackground(bgC);
		//INFOpane.setFont(MPpane.getFont());
		
		addFreeSpace(1, INFOpane);
		INFOpane.add(trackL, left);
		INFOpane.add(invTrackL, middle);
		INFOpane.add(albumArtL, right);

		INFOpane.add(artistL, left);
		INFOpane.add(invArtistL, left2);
		
		INFOpane.add(albumL, left);
		INFOpane.add(invAlbumL, left2);
		
		INFOpane.add(dateL, left);
		INFOpane.add(invDateL, left2);

		INFOpane.add(lengthL, left);
		INFOpane.add(invLengthL, left2);
		
		INFOpane.add(formatL, left);
		INFOpane.add(invFormatL, left2);
		
		INFOpane.add(encodingL, left);
		INFOpane.add(invEncodingL, left2);

		LIBpane = new JPanel();
		LIBpane.setBorder(padding);
		LIBpane.setLayout(layout);
		LIBpane.setBackground(bgC);
		//LIBpane.setFont(MPpane.getFont());
		
		addFreeSpace(1, LIBpane);
		LIBpane.add(libL, left);
		LIBpane.add(libSP, left);
		//LIBpane.add(playL, left);
		//LIBpane.add(playSP, left);
		
		FUNpane = new JPanel();
		FUNpane.setBorder(padding);
		FUNpane.setLayout(layout);
		FUNpane.setBackground(bgC);
		//FUNpane.setFont(MPpane.getFont());
		
		FUNpane.add(addfilelibb, left);
		FUNpane.add(removefilelibb, left);
		FUNpane.add(exitb, left);

		setSize(width, height);
		setBackground(frameBGColor);
		setForeground(fontC);
		setFont(bgFont);
		add(MPpane, BorderLayout.NORTH);
		add(INFOpane, BorderLayout.CENTER);
		add(LIBpane, BorderLayout.AFTER_LINE_ENDS);
		add(FUNpane, BorderLayout.SOUTH);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public class ExitButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent t){
			System.exit(getDefaultCloseOperation());
		}
		
	}
	
	public class BrowseButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent c){
			JFileChooser trackChooser = new JFileChooser();
			//FileNameExtensionFilter extFilter = new FileNameExtensionFilter("mp3");
			//trackChooser.setFileFilter(extFilter);
			trackChooser.setCurrentDirectory(trackChooser.getSelectedFile());
			int returnValue = trackChooser.showOpenDialog(getParent());
			String trackString = trackChooser.getSelectedFile().getName();	
			String trackLocation = trackChooser.getSelectedFile().getAbsolutePath();
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				System.out.println("You have chosen to play: " + trackString);
			}
			fileJF.setText(trackChooser.getSelectedFile().getName());
			Path fromFile  = Paths.get(trackLocation);
			Path toFile = Paths.get("F:\\MEDIA\\CODE Files\\Eclipse\\Play Projects\\HollowEarthMediaPlayer_DEC06\\01" + trackString);
			CopyOption[] fileCopyOptions = new CopyOption[]{
					StandardCopyOption.REPLACE_EXISTING,
					StandardCopyOption.COPY_ATTRIBUTES};
			try {
				Files.copy(fromFile, toFile, fileCopyOptions);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println("File Copied from " + fromFile + " to " + toFile);
			
			String filePathString = toFile.toString();
			mp = new HollowEarthMediaPlayer(filePathString);
			
			String songName = mp.getSongName();
			String artistName = mp.getArtist();
			String albumName = mp.getAlbum();
			String songDate = mp.getDate();
			String songLength = mp.getLength();
			String formatType = mp.getFormat();
			String encodingType = mp.getEncoding();
			
			//StringBuilder trackBuilder = new StringBuilder(
					//songName + " - " + artistName + ", " 
					//+ albumName + ", " + songDate);
			
			
			stringModel.addElement(filePathString);
			trackL.setText(songName);
			invArtistL.setText(artistName);
			invAlbumL.setText(albumName);
			invDateL.setText(songDate);
			invLengthL.setText(songLength);
			invFormatL.setText(formatType);
			invEncodingL.setText(encodingType);
			
			ImageIcon icon;
			try {
				icon = new ImageIcon(mp.getAlbumArt());
				Image x = icon.getImage();
				Image y = x.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(y);
				albumArtL.setIcon(icon);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public class PlayButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent h){
			mp.play();
		}
	}
	
	public class PauseButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent q){
			mp.pause();
		}
	}
	
	public class StopButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent z){
			mp.stop();
		}
	}
	
	public class FastForwardButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent r){
			mp.fforward();
		}
	}
	
	public class RewindButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent f){
			
		}
	}
	
	public class NextButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent n){
			
		}
	}
	
	public class PreviousButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent p){
			
		}
	}
	
	public class MuteButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent m){
			if (mp.ismute()){
				mp.unmute();
			} else {
				mp.mute();
			}
		}
	}
	public class LibraryJSPHandler extends MouseAdapter{			
			public void mouseClicked(MouseEvent x) {
				int index = libJlist.locationToIndex(x.getPoint());
				if (index >= 0) {
					Object o = libJlist.getModel().getElementAt(index);
					System.out.println(o.toString());
					mp.playFile(o.toString());
				}
			}
		
	}
	public void addFreeSpace(int numLines, JPanel panel){
		for(int i = 0; i < numLines; i++)	{
			panel.add(new JLabel("    ", SwingConstants.LEFT), left);
			panel.add(new JLabel("    ", SwingConstants.LEFT), middle);
			panel.add(new JLabel("    ", SwingConstants.LEFT), right);
			}
	}
	
}
