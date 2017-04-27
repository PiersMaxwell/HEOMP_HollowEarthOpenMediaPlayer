package classes;

import java.util.ArrayList;
import org.jaudiotagger.audio.mp3.*;
import javafx.scene.media.Media;

public class HollowEarthLibraryList {
	
	ArrayList<Media> HollowList;
	int entries;
	
	public HollowEarthLibraryList(int entries) {
		this.entries = entries;
		HollowList = new ArrayList<Media>(entries);
	}
	
	public void addSong(Media song){
		HollowList.add(song);
		
	}
	
	public void deleteSong(Media song){
		HollowList.remove(song);
	}
	
	public Media getSong(Media song){
		for (Media n: HollowList){
			if (n.equals(song)){
				return n;
			}
		}
		return null;
	}
	
	public ArrayList<String> toStringList(){
		
		ArrayList<String> stringList = new ArrayList<String>();
		int i = 0;
		for(Media n: HollowList){
			String songName;
			/*if(n.getTag().getFirst("TT2").isEmpty()){
				songName = n.getTag().getFirst("TIT2");
			} else {
				songName = n.getTag().getFirst("TT2");
			}*/
		//	stringList.add(songName);
			i++;
		}
		for (String n: stringList){
			System.out.println(n);
		}
		return stringList;
	}
	
	public ArrayList<Media> getList() {
		return HollowList;
	}
}
