// LanguageTranslationTest
//Select a domain, then identify or select the language of text, and then translate the text from one supported language to another.
//Example: Translate 'hello my friend' from English to Spanish using the Language Translation service.
import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.Language;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;

//After deployment go to the relative URI to test the functionality.
//You would see a form to provide the input values.
@WebServlet("/")
public class Snippet extends SuperGluev2 {
	
	public String parameters = "{\"username\":\"\",\"password\":\"\",\"text\":\"hello my friend\",\"fromLanguage\":\"ENGLISH\",\"toLanguage\":\"SPANISH\"}";
	
	@Override
	protected JsonObject process(String jsonString) {
		JsonParser parser = new JsonParser(); 
		JsonObject myBean = parser.parse(jsonString).getAsJsonObject();  
		
//		LanguageTranslation service = new LanguageTranslation();
		LanguageTranslator service = new LanguageTranslator();
		
		service.setUsernameAndPassword(myBean.get("username").getAsString(), myBean.get("password").getAsString());
		
		//fromlanguage and tolanguage arg need to be upper case
		TranslationResult translationResult = service.translate(myBean.get("text").getAsString(),
				Language.valueOf(myBean.get("fromLanguage").getAsString().toUpperCase()), Language.valueOf(myBean.get("toLanguage").getAsString().toUpperCase())).execute();
		
        JsonObject json = parser.parse(translationResult.toString()).getAsJsonObject();
		
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
