package file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SaveFiler {

	public String loadLevel() {
		try(Scanner s=new Scanner(new File("savefile.txt"))){
			return s.nextLine();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setLevel(String level) {
		try(PrintWriter pw=new PrintWriter(new File("savefile.txt"))){
			pw.write(level);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
