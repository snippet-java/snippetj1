import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.NaturalLanguageClassifier;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classification;

@WebServlet("/")
public class Snippet extends SuperGluev2 {

	public String parameters = 
			"{\"username\":\"1e2a85d3-f9f3-4d77-9b44-d0d56c93a028\","
			+ "\"password\":\"EjhIkxAUWWVQ\","
			+ "\"text\":\"I like basketball?\"}";
	
	@Override
	protected JsonObject process(String jsonString) {
		JsonParser parser = new JsonParser(); 
		JsonObject myBean = parser.parse(jsonString).getAsJsonObject();  
		
		NaturalLanguageClassifier service = new NaturalLanguageClassifier();
		service.setUsernameAndPassword(myBean.get("username").getAsString(), myBean.get("password").getAsString());

		Classification result = service.classify("ff18c7x157-nlc-2810", myBean.get("text").getAsString()).execute();
		System.out.println(result);
		
        JsonObject json = parser.parse(result.toString()).getAsJsonObject();
		
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