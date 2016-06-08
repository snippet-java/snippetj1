import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyVision;
import com.ibm.watson.developer_cloud.alchemy.v1.model.ImageKeywords;

//After deployment go to the relative URI to test the functionality.
//You would see a form to provide the input values.
@WebServlet("/")
public class Snippet extends SuperGlue {
	
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args)
			throws MalformedURLException, URISyntaxException, IllegalArgumentException, IllegalAccessException {
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
	}
	
	@Override
	protected Object process(Object myBean) {
		AlchemyVision service = new AlchemyVision();
		URL imageUrl = null;
		try {
			imageUrl = new URI("http://images.indianexpress.com/2015/02/david_beckham-759.jpg").toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service.setApiKey(((Parameters) myBean).apiKey);
		Boolean forceShowAll = false;
		Boolean knowledgeGraph = false;
		ImageKeywords keywords = service.getImageKeywords(imageUrl, forceShowAll, knowledgeGraph);
		
		return keywords.toString();
	}

	@Override
	protected Object getParameters() {
		return new Parameters();
	}
}
