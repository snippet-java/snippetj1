// SpeechToTextTest
//to recognize the text from a .wav file.
import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.annotation.WebServlet;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
//After deployment go to the relative URI to test the functionality.
//You would see a form to provide the input values.
@WebServlet("/")
public class Snippet extends SuperGlue {
	
	public class Parameters {
		public String userName = "90260bbe-07af-47ee-abe3-bb6199e4ed1d";
		public String password = "2aIQdfh6KFCx";
	}
	
	
	@Override
	protected Object process(Object myBean) {
		SpeechToText service = new SpeechToText();
		service.setUsernameAndPassword(((Parameters) myBean).userName, ((Parameters) myBean).password);
		File audio = findFile();
		SpeechResults transcript = service.recognize(audio);
		
		return transcript.toString();
	}
	
	public static void main(String[] args) {
		Snippet myclass = new Snippet();
		Parameters parameter = myclass.new Parameters();
		//****** Process method contains the key logic ******
		Object processResult = myclass.process(((Parameters) parameter));
		
		JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(processResult.toString()).getAsJsonObject();	
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(json));
	}
	
	@Override
	protected Object getParameters() {
		return new Parameters();
	}
	
	private File findFile() {
		File root = new File(System.getProperty("user.dir"));
        String fileName = "STTInput.wav";
        File sourceFile = null;
        try {
            boolean recursive = true;

            Collection files = FileUtils.listFiles(root, new String[] {"wav"}, recursive);

            for (Iterator iterator = files.iterator(); iterator.hasNext();) {
                File file = (File) iterator.next();
                if (file.getName().equals(fileName)) {
                	sourceFile = new File(file.getAbsolutePath());
                	break;
                }
            }
            	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sourceFile;
	}
	
	private static final long serialVersionUID = 1L;
}
