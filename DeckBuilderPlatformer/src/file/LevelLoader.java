package file;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import game.Cycler;
import game.ID;

public class LevelLoader {
	
	File level;
	Cycler cycler;
	
	public LevelLoader(Cycler cycler) {
		this.cycler=cycler;
	}
	public void loadLevelFromLoadScrn() {
		try (Scanner s=new Scanner(new File("savefile.txt"))){
			String level=s.nextLine();
			s.close();
			loadLevel(level);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadLevel(String level) {
		try (Scanner s=new Scanner(new File("test.txt"))){
			String line=s.nextLine();
			while(!line.equals(level)) {
				line=s.nextLine();
			}
			for(int i=0;i<10;i++) {
				line=s.nextLine();
				String[] bits=line.split(",");
				for(int j=0;j<bits.length;j++) {
					switch(Integer.parseInt(bits[j])) {
					case 0:
						break;
					case 1:
						cycler.addObject(ID.BLOCK, j*32, i*32);
						break;
					case 2:
						cycler.addObject(ID.PLAYER, j*32, i*32);
						break;
					case 3:
						cycler.addObject(ID.PORTAL, j*32, i*32);
						break;
					case 4:
						cycler.addObject(ID.SPIKES, j*32, i*32);
						break;
						
					}
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
