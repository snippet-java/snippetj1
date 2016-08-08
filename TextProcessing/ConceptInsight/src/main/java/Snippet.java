import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.concept_insights.v2.ConceptInsights;
import com.ibm.watson.developer_cloud.concept_insights.v2.model.Annotations;
import com.ibm.watson.developer_cloud.concept_insights.v2.model.Graph;

@WebServlet("/")
//After deployment go to the relative URI to test the functionality.
//You would see a form to provide the input values.
public class Snippet extends SuperGlue {
	
	public class Parameters {
		public String userName = "";
		public String password = "";
		public String text = "IBM Watson won the Jeopardy television show hosted by Alex Trebek";
	}
	
	@Override
	protected Object process(Object myBean) {
		ConceptInsights service = new ConceptInsights();
		
		if(!((Parameters) myBean).userName.isEmpty() && !((Parameters) myBean).password.isEmpty())
			service.setUsernameAndPassword(((Parameters) myBean).userName, ((Parameters) myBean).password);
		
		Annotations annotations = service.annotateText(Graph.WIKIPEDIA, ((Parameters) myBean).text).execute();

		return annotations.toString();
	}
	
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

	@Override
	protected Object getParameters() {
		return new Parameters();
	}
	
	private static final long serialVersionUID = 1L;
}
