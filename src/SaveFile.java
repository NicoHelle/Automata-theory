import java.io.FileWriter;
import java.io.IOException;
//CLASS: Provides a basic method to save the GUI TextArea as .txt file.
public class SaveFile {
	
	//if the path is valid, the textArea field will be saved as a txt file at the specified path. 
	// Otherwise an error message will be printed.
	public SaveFile(String textArea, String path) {
	try {
	      FileWriter filewrite = new FileWriter(path);
	      filewrite.write(textArea);
	      filewrite.close();
	      System.out.println("file got saved successfully");
	    } catch (IOException e) {
	      System.out.println("ERROR");
	      e.printStackTrace();
	    }
   }
}
