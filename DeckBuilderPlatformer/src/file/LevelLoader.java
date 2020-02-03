package file;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import game.Cycler;

public class LevelLoader {
	
	File level;
	Cycler cycler;
	
	public LevelLoader(Cycler cycler) {
		this.cycler=cycler;
	}
	public void loadLevel(String level) {
		try (Scanner s=new Scanner(new File("levels.txt"))){
			String line=s.nextLine();
			while(!line.equals(level)) {
				line=s.nextLine();
			}
			System.out.println(line);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
