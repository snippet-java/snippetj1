import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

@WebServlet("/")
public class Snippet extends SuperGluev2 {
	
	public String parameters = "{\"username\":\"\",\"password\":\"\"}";
	
	@Override
	protected JsonObject process(String jsonString) {
		JsonParser parser = new JsonParser(); 
		JsonObject myBean = parser.parse(jsonString).getAsJsonObject();  
		
		TextToSpeech service = new TextToSpeech();
		service.setUsernameAndPassword(myBean.get("username").getAsString(), myBean.get("password").getAsString());
		 
		List<Voice> voices = service.getVoices().execute();
		
		JsonObject json = new JsonObject();
		json.add("voice", parser.parse(voices.toString()).getAsJsonArray());
		
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
	
	private static final long serialVersionUID = 1L;
}