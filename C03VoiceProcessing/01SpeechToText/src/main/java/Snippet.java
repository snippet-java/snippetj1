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

@WebServlet("/")
public class Snippet extends SuperGluev2 {
	
	public class Parameters {
		public String userName = "";
		public String password = "";
	}
	
	public String parameters = "{\"username\":\"\",\"password\":\"\"}";
	
	@Override
	protected JsonObject process(String jsonString) {
		JsonParser parser = new JsonParser(); 
		JsonObject myBean = parser.parse(jsonString).getAsJsonObject();  
		
		SpeechToText service = new SpeechToText();
		service.setUsernameAndPassword(myBean.get("username").getAsString(), myBean.get("password").getAsString());
		
		File audio = findFile();
		SpeechResults transcript = service.recognize(audio).execute();

        JsonObject json = parser.parse(transcript.toString()).getAsJsonObject();
		
		return json;
	}
	
	public static void main(String[] args) {
		Snippet myclass = new Snippet();
		
		//****** Process method contains the key logic ******
		JsonObject processResult = myclass.process(myclass.parameters);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(processResult));
	}

	@Override
	JsonObject getParameters() {
		return new JsonParser().parse(parameters).getAsJsonObject();
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
