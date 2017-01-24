import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;

@WebServlet("/")
public class Snippet extends SuperGluev2 {
	
	public String parameters = 
			"{\"username\":\"\","
			+ "\"password\":\"\","
			+ "\"text\":\"I know the times are difficult! Our sales have been "
		          + "disappointing for the past three quarters for our data analytics "
		          + "product suite. We have a competitive data analytics product "
		          + "suite in the industry. But we need to do our job selling it! "
		          + "We need to acknowledge and fix our sales challenges. "
		          + "We can’t blame the economy for our lack of execution! "
		          + "We are missing critical sales opportunities. "
		          + "Our product is in no way inferior to the competitor products. "
		          + "Our clients are hungry for analytical tools to improve their "
		          + "business outcomes. Economy has nothing to do with it.\"}";
	
	@Override
	protected JsonObject process(String jsonString) {
		JsonParser parser = new JsonParser(); 
		JsonObject myBean = parser.parse(jsonString).getAsJsonObject();
		
		ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
		service.setUsernameAndPassword(myBean.get("username").getAsString(), myBean.get("password").getAsString());
	   
	    // Call the service and get the tone
	    ToneAnalysis tone = service.getTone(myBean.get("text").getAsString(), null).execute();

        JsonObject json = parser.parse(tone.toString()).getAsJsonObject();
		
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