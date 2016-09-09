// LanguageTranslationTest
//Select a domain, then identify or select the language of text, and then translate the text from one supported language to another.
//Example: Translate 'hello' from English to Spanish using the Language Translation service.
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.TradeoffAnalytics;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.Dilemma;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.Option;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.Problem;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.column.Column;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.column.Column.Goal;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.column.NumericColumn;

//After deployment go to the relative URI to test the functionality.
//You would see a form to provide the input values.
@WebServlet("/")
public class Snippet extends SuperGluev2 {
	
	public String parameters = "{\"username\":\"\",\"password\":\"\",\"text\":\"hello my friend\",\"fromLanguage\":\"ENGLISH\",\"toLanguage\":\"SPANISH\"}";
	
	@Override
	protected JsonObject process(String jsonString) {
		JsonParser parser = new JsonParser(); 
		
		TradeoffAnalytics service = new TradeoffAnalytics();
	    service.setEndPoint("https://watson-api-explorer.mybluemix.net/tradeoff-analytics/api");
	    service.setUsernameAndPassword("", ""); 
	    Problem problem = new Problem("phone");
	    String price = "price";
	    String ram = "ram";
	    String screen = "screen";

	    // Define the objectives
	    List<Column> columns = new ArrayList<Column>();
	    problem.setColumns(columns);

	    columns.add(new NumericColumn().range(0, 100).key(price).goal(Goal.MIN)
	        .objective(true));
	    columns.add(new NumericColumn().key(screen).goal(Goal.MAX).objective(true));
	    columns.add(new NumericColumn().key(ram).goal(Goal.MAX));

	    // Define the options to choose
	    List<Option> options = new ArrayList<Option>();
	    problem.setOptions(options);

	    HashMap<String, Object> galaxySpecs = new HashMap<String, Object>();
	    galaxySpecs.put(price, 50);
	    galaxySpecs.put(ram, 45);
	    galaxySpecs.put(screen, 5);
	    options.add(new Option("1", "Galaxy S4").values(galaxySpecs));

	    HashMap<String, Object> iphoneSpecs = new HashMap<String, Object>();
	    iphoneSpecs.put(price, 99);
	    iphoneSpecs.put(ram, 40);
	    iphoneSpecs.put(screen, 4);
	    options.add(new Option("2", "iPhone 5").values(iphoneSpecs));

	    HashMap<String, Object> optimusSpecs = new HashMap<String, Object>();
	    optimusSpecs.put(price, 10);
	    optimusSpecs.put(ram, 300);
	    optimusSpecs.put(screen, 5);
	    options.add(new Option("3", "LG Optimus G").values(optimusSpecs));

	    // Call the service and get the resolution
	    Dilemma dilemma = service.dilemmas(problem).execute();

        JsonObject json = parser.parse(dilemma.toString()).getAsJsonObject();
		
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
