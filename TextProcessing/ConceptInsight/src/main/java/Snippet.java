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
	
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		Snippet myclass = new Snippet();
		Parameters params = myclass.new Parameters();
		params.setUserName("9ae4556d-5fdd-4a57-9f42-5f32b66c6916");
		params.setPassword("ivNCfTqJJCz4");
		params.setText("IBM Watson won the Jeopardy television show hosted by Alex Trebek");
		//****** Process method contains the key logic ******
		Object processResult = myclass.process(((Parameters) params));
		
		JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(processResult.toString()).getAsJsonObject();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(json));
	}
	
	public class Parameters {
		String userName;
		String password;
		String text;
		public String getUserName() { return userName; }
		public void setUserName(String userName) { this.userName = userName; }
		public String getPassword() { return password; }
		public void setPassword(String password) { this.password = password; }
		public String getText() { return text; }
		public void setText(String text) { this.text = text; }
	}
	
	@Override
	protected Object process(Object myBean) {
		ConceptInsights service = new ConceptInsights();
		service.setUsernameAndPassword(((Parameters) myBean).getUserName(), ((Parameters) myBean).getPassword());
		Annotations annotations = service.annotateText(Graph.WIKIPEDIA, ((Parameters) myBean).getText());

		return annotations.toString();
	}

	@Override
	protected Object getParameters() {
		return new Parameters();
	}
}
