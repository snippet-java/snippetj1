import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;

@WebServlet("/")
public class Snippet extends SuperGluev2 {
	
	public String parameters = 
			"{\"apiKey\":\"8bea72d282329f66b671f37e49e164a6ab51c2b3\", "
			+ "\"text\":\"Yes I win the first place in the competition. I am so happy!\"}";
	
	@Override
	protected JsonObject process(String jsonString) {
		JsonParser parser = new JsonParser(); 
		JsonObject myBean = parser.parse(jsonString).getAsJsonObject();
		
		AlchemyLanguage service = new AlchemyLanguage();
		service.setApiKey(myBean.get("apiKey").getAsString());
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(AlchemyLanguage.TEXT, (myBean.get("text").getAsString()));
		DocumentSentiment sentiment = service.getSentiment(params).execute();

        JsonObject json = parser.parse(sentiment.toString()).getAsJsonObject();
		
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
