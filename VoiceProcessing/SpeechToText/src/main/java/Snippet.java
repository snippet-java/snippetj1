// SpeechToTextTest
//to recognize the text from a .wav file.
import java.io.File;

import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
//After deployment go to the relative URI to test the functionality.
//You would see a form to provide the input values.
@WebServlet("/SpeechToTextTest")
public class Snippet extends SuperGlue {
	
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		Snippet myclass = new Snippet();
		Parameters parameter = myclass.new Parameters();
		parameter.setUserName("90260bbe-07af-47ee-abe3-bb6199e4ed1d");
		parameter.setPassword("2aIQdfh6KFCx");
		//****** Process method contains the key logic ******
		Object processResult = myclass.process(((Parameters) parameter));
		
		JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(processResult.toString()).getAsJsonObject();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(json));
	}
	
	public class Parameters {
		String userName;
		String password;
		public String getUserName() { return userName; }
		public void setUserName(String userName) { this.userName = userName; }
		public String getPassword() { return password; }
		public void setPassword(String password) { this.password = password; }
	}
	
	
	@Override
	protected Object process(Object myBean) {
		SpeechToText service = new SpeechToText();
		service.setUsernameAndPassword(((Parameters) myBean).getUserName(), ((Parameters) myBean).getPassword());
		//for Bluemix
		File audio = new File("public/snippets/java/VoiceProcessing/SpeechToText/src/main/resources/STTInput.wav");
		//for local
//		File audio = new File("VoiceProcessing/SpeechToText/src/main/resources/STTInput.wav");
		
		SpeechResults transcript = service.recognize(audio);
		
		return transcript.toString();
	}
	
	@Override
	protected Object getParameters() {
		return new Parameters();
	}
}
