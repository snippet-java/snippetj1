import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;
//After deployment go to the relative URI to test the functionality.
//You would see a form to provide the input values.
@WebServlet("/")
public class Snippet extends SuperGlue {
	
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		Snippet myclass = new Snippet();
		Parameters params = myclass.new Parameters();
		//****** Process method contains the key logic ******
		Object processResult = myclass.process(((Parameters) params));
		
		JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(processResult.toString()).getAsJsonObject();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(json));
	}
	
	public class Parameters {
		public String apiKey = "913f155354acfc4810935b58249e5edefa63f9ba";
		public String textToBeAnalysed = "IBM Watson won the Jeopardy television show hosted by Alex Trebek";
	}
	
	@Override
	protected Object process(Object myBean) {
		AlchemyLanguage service = new AlchemyLanguage();
		service.setApiKey(((Parameters) myBean).apiKey);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(AlchemyLanguage.TEXT, ((Parameters) myBean).textToBeAnalysed);
		DocumentSentiment sentiment = service.getSentiment(params);
		
		return sentiment.toString();
	}
	
	@Override
	protected Object getParameters() {
		return new Parameters();
	}
	
}
