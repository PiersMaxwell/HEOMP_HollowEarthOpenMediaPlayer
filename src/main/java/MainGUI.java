package classes;

public class MainGUI {

	public static void main(String[] args) {
		// 
		
		InstallGUI sg = new InstallGUI();
		sg.setResizable(false);
		sg.setVisible(false);
		
		while(!sg.SetupComplete()){
			sg.setVisible(true);
		}
		
		if (sg.SetupComplete() == true){
			PlayerGUI pg = new PlayerGUI();
			pg.setResizable(false);
			sg.setVisible(false);
		}
		

	}

}
