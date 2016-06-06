// DocumentConversionTest
//Service provides an Application Programming Interface (API) that enables developers to transform a document into a new format. The input is a single PDF, Word, or HTML document and the output is an HTML document, a Text document, or Answer units that can be used with other Watson services.
import java.io.File;
import java.net.URISyntaxException;

import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.document_conversion.v1.DocumentConversion;
import com.ibm.watson.developer_cloud.document_conversion.v1.model.Answers;
@WebServlet("/")
//After deployment go to the relative URI to test the functionality.
//You would see a form to provide the input values.
public class Snippet extends SuperGlue {
	
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) throws URISyntaxException {
		// @param versionDate - version of the API that being called
		// latest version 2015-12-15. for more info, can refer to
		// https://www.ibm.com/smarterplanet/us/en/ibmwatson/developercloud/doc/document-conversion/updates.shtml
		Snippet myclass = new Snippet();
		Parameters parameters = myclass.new Parameters();
		parameters.setVersionDate("2015-12-15");
		parameters.setUserName("e19a0428-c14c-4f79-b355-b94c3f28a27c");
		parameters.setPassword("1X2ylaRCsetU");
		//****** Process method contains the key logic ******
		Object processResult = myclass.process(((Parameters) parameters));
		
		JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(processResult.toString()).getAsJsonObject();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(json));
	}
	
	public class Parameters {
		String versionDate;
		String userName;
		String password;
		public String getVersionDate() { return versionDate; }
		public void setVersionDate(String versionDate) { this.versionDate = versionDate; }
		public String getUserName() { return userName; }
		public void setUserName(String userName) { this.userName = userName; }
		public String getPassword() { return password; }
		public void setPassword(String password) { this.password = password; }
	}
	
	@Override
	protected Object process(Object myBean) {		
		DocumentConversion service = new DocumentConversion(((Parameters) myBean).getVersionDate());
		service.setUsernameAndPassword(((Parameters) myBean).getUserName(), ((Parameters) myBean).getPassword());
		//for Bluemix
//		File doc = new File("public/snippets/java/TextProcessing/DocumentConversion/src/main/resources/BluemixTutorial.htm");
		//for local
		File doc = new File("TextProcessing/DocumentConversion/src/main/resources/BluemixTutorial.htm");
		Answers htmlToAnswers = service.convertDocumentToAnswer(doc);
		
		return htmlToAnswers.toString();
	}
	
	@Override
	protected Object getParameters() {
		return new Parameters();
	}
}
