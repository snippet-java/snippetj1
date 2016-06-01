// LanguageTranslationTest
//Select a domain, then identify or select the language of text, and then translate the text from one supported language to another.
//Example: Translate 'hello' from English to Spanish using the Language Translation service.
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyDataNews;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentsResult;
import com.ibm.watson.developer_cloud.language_translation.v2.LanguageTranslation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.TranslationResult;

//After deployment go to the relative URI to test the functionality.
//You would see a form to provide the input values.
@WebServlet("/")
public class Snippet extends SuperGlue {
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	
	public class Parameters {
		String userName;
		String password;
		String text;
		String fromLanguage;
		String toLanguage;
		public String getUserName() { return userName; }
		public void setUserName(String userName) { this.userName = userName; }
		public String getPassword() { return password; }
		public void setPassword(String password) { this.password = password; }
		public String getText() { return text; }
		public void setText(String text) { this.text = text; }
		public String getFromLanguage() { return fromLanguage; }
		public void setFromLanguage(String fromLanguage) { this.fromLanguage = fromLanguage; }
		public String getToLanguage() { return toLanguage; }
		public void setToLanguage(String toLanguage) { this.toLanguage = toLanguage; }
	}
	
	public static void main(String[] args) {
		Snippet myclass = new Snippet();
		Parameters params = myclass.new Parameters();
		params.setUserName("0b687f94-4b72-44c4-9767-d750725a4a62");
		params.setPassword("DM1gWzfXhVPV");
		params.setText("hello");
		params.setToLanguage("es");
		params.setFromLanguage("en");
		//****** Process method contains the key logic ******
		Object processResult = myclass.process(((Parameters) params));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(processResult));
	}

	@Override
	Object getParameters() {
		return new Parameters();
	}
	@Override
	protected Object process(Object myBean) {
		String processResult = "NO RESULT";
		LanguageTranslation service = new LanguageTranslation();
		service.setUsernameAndPassword(((Parameters) myBean).getUserName(), ((Parameters) myBean).getPassword());
		// test with translate language
		TranslationResult translationResult = service.translate(((Parameters) myBean).getText(),
				((Parameters) myBean).getFromLanguage(), ((Parameters) myBean).getToLanguage());
		return translationResult.toString();
	}
}
