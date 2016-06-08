// LanguageTranslationTest
//Select a domain, then identify or select the language of text, and then translate the text from one supported language to another.
//Example: Translate 'hello' from English to Spanish using the Language Translation service.
import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.language_translation.v2.LanguageTranslation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.TranslationResult;

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
		public String userName = "0b687f94-4b72-44c4-9767-d750725a4a62";
		public String password = "DM1gWzfXhVPV";
		public String text = "hello";
		public String fromLanguage = "en";
		public String toLanguage = "es";
	}

	@Override
	Object getParameters() {
		return new Parameters();
	}
	@Override
	protected Object process(Object myBean) {
		LanguageTranslation service = new LanguageTranslation();
		service.setUsernameAndPassword(((Parameters) myBean).userName, ((Parameters) myBean).password);
		// test with translate language
		TranslationResult translationResult = service.translate(((Parameters) myBean).text,
				((Parameters) myBean).fromLanguage, ((Parameters) myBean).toLanguage);
		return translationResult.toString();
	}
}
