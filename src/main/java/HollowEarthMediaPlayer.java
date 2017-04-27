package classes;

import java.io.File;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.CopyOption;
import java.nio.file.StandardCopyOption;

import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.*;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.images.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.Image;
import java.awt.image.*;


public final class HollowEarthMediaPlayer {
	public String track;
	public String trackName;
	public Media song;
	public String artist, playFile;
	public String genre;
	public Double length; 
	public File songFile;
	public File outFile;
	public MP3File mp3SongFile;
	public MediaPlayer mediaPlayer;
	public HollowEarthLibraryList hwLibList;
	
	public HollowEarthMediaPlayer(String track){
		this.track = track;	
		hwLibList = new HollowEarthLibraryList(10);
		System.out.println(track + " 11");
		try {
			
			songFile = new File(track);
			mp3SongFile = new MP3File(songFile);
			
			
			song = new Media(songFile.toURI().toString());
			hwLibList.addSong(song);
			//mediaPlayer = new MediaPlayer(song);
			//mediaPlayer.stop();
			//mediaPlayer.play();
			System.out.println(mp3SongFile.getTag().toString());
			

			
		} catch(Exception ex){	
		}
		
		try {
			MP3FileReader fileReader = new MP3FileReader();
			fileReader.readMustBeWritable(new File(track));			
		} catch(Exception ex){
		}
	}
	
	public void play(){
		//mediaPlayer.getStartTime();
		//mediaPlayer.stop();
		mediaPlayer = new MediaPlayer(hwLibList.getSong(song));
		mediaPlayer.setRate(1.0);
		mediaPlayer.play();
	}
	public void playFile(String File) {
		this.playFile = File;
		songFile = new File(playFile);
		try {
			mp3SongFile = new MP3File(songFile);
			song = new Media(songFile.toURI().toString());
			hwLibList.addSong(song);
			mediaPlayer = new MediaPlayer(hwLibList.getSong(song));
			mediaPlayer.setRate(1.0);
			mediaPlayer.play();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

			
			
		

	}
	
	public void stop(){
		mediaPlayer.setStartTime(javafx.util.Duration.ZERO);
		mediaPlayer.stop();	
	}
	
	public void pause(){
		mediaPlayer.pause();
	}
	
	public void fforward(){
		mediaPlayer.setRate(3.0);
	}  
	
	public void mute(){
		mediaPlayer.setMute(true);
	}
	
	public boolean ismute(){
		return mediaPlayer.isMute();
	}
	
	public void unmute(){
		mediaPlayer.setMute(false);
	}
	
	public String getSongName(){
		if (mp3SongFile.getTag().getFirst("TT2").isEmpty()){
			return mp3SongFile.getTag().getFirst("TIT2");
		} else {
			return mp3SongFile.getTag().getFirst("TT2");
		}	
	}
	
	public String getArtist(){
		if (mp3SongFile.getTag().getFirst("TP1").isEmpty()){
			return mp3SongFile.getTag().getFirst("TPE1");
		} else {
			return mp3SongFile.getTag().getFirst("TP1");
		}
	}
	
	public String getAlbum(){
		if (mp3SongFile.getTag().getFirst("TAL").isEmpty()){
			return mp3SongFile.getTag().getFirst("TALB");
		} else {
			return mp3SongFile.getTag().getFirst("TAL");
		}
	}
		
	public String getDate(){
		if (mp3SongFile.getTag().getFirst("TYE").isEmpty()){
			return mp3SongFile.getTag().getFirst("TYER");
		} else {
		return mp3SongFile.getTag().getFirst("TYE");
		}
	}
	
	public BufferedImage getAlbumArt() throws IOException{
	    BufferedImage artBI = null;
		List<Artwork> albumArtList = mp3SongFile.getTag().getArtworkList();
		for (Artwork artwork : albumArtList){
			byte[] rawArtImageData = artwork.getBinaryData();
			if (rawArtImageData != null){
				int i = 1;
				System.out.println("Testing " + i);
				artBI = ImageIO.read(new ByteArrayInputStream(rawArtImageData));
				i++;
			}
		}
		return artBI;
	}
	
	public String getLength(){		
		
		double finalDuration = mp3SongFile.getAudioHeader().getTrackLength();
		double decMin = finalDuration / 60;
		double hour = decMin/60;
		double min = (hour - ((long)hour)) * 60;
		double sec = (min - ((long)min)) * 60;
		
		long finalHour = (long)hour;
		long finalMin = (long)min;
		long finalSec = (long)sec;
		String hourString = (finalHour < 10 ? "0" : "") + finalHour;
		String minString = (finalMin < 10 ? "0" : "") + finalMin;
		String secString = (finalSec < 10 ? "0" : "") + finalSec;
		
		StringBuffer sbuff = new StringBuffer(hourString+":"+minString+":"+secString);
		String songLength = sbuff.toString();
		
		return songLength;
	}
	public String getFormat(){
		return mp3SongFile.getAudioHeader().getFormat();
	}
	public String getEncoding(){
		return mp3SongFile.getAudioHeader().getEncodingType();
	}
	public MP3File getFile(){
		return mp3SongFile;
	}

	}
	

