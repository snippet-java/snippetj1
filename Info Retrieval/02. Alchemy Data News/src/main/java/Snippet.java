import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyDataNews;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentsResult;

//After deployment go to the relative URI to test the functionality.
//You would see a form to provide the input values.
@WebServlet("/")
public class Snippet extends SuperGluev2 {

	public String parameters = "{\"apiKey\":\"d740b4c113d1255004bb2b26e5e05d1cccb8d186\"}";
	
	@Override
	protected JsonObject process(String jsonString) {
		JsonParser parser = new JsonParser(); 
		JsonObject myBean = parser.parse(jsonString).getAsJsonObject(); 
		
		AlchemyDataNews service = new AlchemyDataNews();
		service.setApiKey(myBean.get("apiKey").getAsString());
		
		Map<String, Object> params = new HashMap<String, Object>();
		// specify the categories to be included. In this case, title, url,author... are included
		String[] fields = new String[] { "enriched.url.title", "enriched.url.url", "enriched.url.author",
				"enriched.url.publicationDate", "enriched.url.enrichedTitle.entities",
				"enriched.url.enrichedTitle.docSentiment" };
		params.put(AlchemyDataNews.RETURN, StringUtils.join(fields, ","));
		// the time (in UTC seconds) of the beginning of the query duration
		params.put(AlchemyDataNews.START, "now-7d");
		// the time (in UTC seconds) of the end of the query duration
		params.put(AlchemyDataNews.END, "now");
		// return how many articles
		params.put(AlchemyDataNews.COUNT, 7);
		DocumentsResult result = service.getNewsDocuments(params).execute();

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