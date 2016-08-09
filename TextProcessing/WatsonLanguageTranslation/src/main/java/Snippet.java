// LanguageTranslationTest
//Select a domain, then identify or select the language of text, and then translate the text from one supported language to another.
//Example: Translate 'hello' from English to Spanish using the Language Translation service.
import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.language_translation.v2.LanguageTranslation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.Language;
import com.ibm.watson.developer_cloud.language_translation.v2.model.TranslationResult;

//After deployment go to the relative URI to test the functionality.
//You would see a form to provide the input values.
@WebServlet("/")
public class Snippet extends SuperGlue {
	
	public class Parameters {
		public String userName = "";
		public String password = "";
		public String text = "hello my friend";
		public Language fromLanguage = Language.ENGLISH;
		public Language toLanguage = Language.SPANISH;
	}
	
	@Override
	protected Object process(Object myBean) {
		LanguageTranslation service = new LanguageTranslation();
		
		service.setUsernameAndPassword(((Parameters) myBean).userName, ((Parameters) myBean).password);
		
		TranslationResult translationResult = service.translate(((Parameters) myBean).text,
				((Parameters) myBean).fromLanguage, ((Parameters) myBean).toLanguage).execute();
		
		return translationResult.toString();
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
	Object getParameters() {
		return new Parameters();
	}
	
	private static final long serialVersionUID = 1L;
}
