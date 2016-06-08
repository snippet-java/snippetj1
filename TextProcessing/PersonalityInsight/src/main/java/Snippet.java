// PersonInsightTest
///Use linguistic analytics to infer personality and social characteristics, including Big Five, Needs, and Values, from text.
//Example: Analyze text and get a personality profile
import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.personality_insights.v2.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v2.model.Profile;

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
		public String userName = "e99c66c3-7ffd-4001-8bff-d57d218d7461";
		public String password = "dcb7G7Z3MrWE";
		public String text = "Call me Ishmael. Some years ago-never mind how long precisely-having "
				+ "little or no money in my purse, and nothing particular to interest me on shore, "
				+ "I thought I would sail about a little and see the watery part of the world. "
				+ "It is a way I have of driving off the spleen and regulating the circulation. "
				+ "Whenever I find myself growing grim about the mouth; whenever it is a damp, "
				+ "drizzly November in my soul; whenever I find myself involuntarily pausing before "
				+ "coffin warehouses, and bringing up the rear of every funeral I meet; and especially "
				+ "whenever my hypos get such an upper hand of me, that it requires a strong moral "
				+ "principle to prevent me from deliberately stepping into the street, and methodically "
				+ "knocking people's hats off-then, I account it high time to get to sea as soon as I can. "
				+ "This is my substitute for pistol and ball. With a philosophical flourish Cato throws himself "
				+ "upon his sword; I quietly take to the ship. There is nothing surprising in this. "
				+ "If they but knew it, almost all men in their degree, some time or other, cherish "
				+ "very nearly the same feelings towards the ocean with me. There now is your insular "
				+ "city of the Manhattoes, belted round by wharves as Indian isles by coral reefs-commerce surrounds "
				+ "it with her surf. Right and left, the streets take you waterward.";
	}
	
	@Override
	protected Object process(Object myBean) {
		PersonalityInsights service = new PersonalityInsights();
		service.setUsernameAndPassword(((Parameters) myBean).userName, ((Parameters) myBean).password);
		// Demo content from Moby Dick by Hermann Melville (Chapter 1)
		String text = ((Parameters) myBean).text;
		Profile profile = service.getProfile(text);
		
		return profile.toString();
	}
	
	@Override
	protected Object getParameters() {
		return new Parameters();
	}
}
