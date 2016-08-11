import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;


@WebServlet("/")
public class Snippet extends SuperGlue {
	  
	public class Parameters {
		public String userName = "";
		public String password = "";
		public String text = "I know the times are difficult! Our sales have been "
		          + "disappointing for the past three quarters for our data analytics "
		          + "product suite. We have a competitive data analytics product "
		          + "suite in the industry. But we need to do our job selling it! "
		          + "We need to acknowledge and fix our sales challenges. "
		          + "We can’t blame the economy for our lack of execution! "
		          + "We are missing critical sales opportunities. "
		          + "Our product is in no way inferior to the competitor products. "
		          + "Our clients are hungry for analytical tools to improve their "
		          + "business outcomes. Economy has nothing to do with it.";
	}
	
	@Override
	public Object process(Object myBean) {		
		ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
	    service.setUsernameAndPassword(((Parameters) myBean).userName, ((Parameters) myBean).password); // SET YOUR USERNAME AND PASSWORD
	   
	    // Call the service and get the tone
	    ToneAnalysis tone = service.getTone(((Parameters) myBean).text, null).execute();
		
		return tone.toString();
	}
	
	@Override
	protected Object getParameters() {
		return new Parameters();
	}

	public static void main(String[] args) {
		Snippet myclass = new Snippet();
		Parameters parameters = myclass.new Parameters();
		//****** Process method contains the key logic ******
		Object processResult = myclass.process(((Parameters) parameters));
		
		JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(processResult.toString()).getAsJsonObject();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(json));
	}
}