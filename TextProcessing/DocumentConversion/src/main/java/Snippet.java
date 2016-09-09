// DocumentConversionTest
//Service provides an Application Programming Interface (API) that enables developers to transform a document into a new format. The input is a single PDF, Word, or HTML document and the output is an HTML document, a Text document, or Answer units that can be used with other Watson services.
import java.io.File;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.annotation.WebServlet;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.document_conversion.v1.DocumentConversion;
import com.ibm.watson.developer_cloud.document_conversion.v1.model.Answers;
@WebServlet("/")
//After deployment go to the relative URI to test the functionality.
//You would see a form to provide the input values.
public class Snippet extends SuperGluev2 {
	
	public String parameters = "{\"username\":\"\",\"password\":\"\",\"versionDate\":\"2015-12-15\"}";
	
	@Override
	public JsonObject process(String jsonString) {	
		JsonParser parser = new JsonParser(); 
		JsonObject myBean = parser.parse(jsonString).getAsJsonObject();  
		
		DocumentConversion service = new DocumentConversion(myBean.get("versionDate").getAsString());
		
		service.setUsernameAndPassword(myBean.get("username").getAsString(), myBean.get("password").getAsString());
		
		File doc = findFile();
		Answers htmlToAnswers = service.convertDocumentToAnswer(doc).execute();
		
		JsonObject json = parser.parse(htmlToAnswers.toString()).getAsJsonObject();
		
		return json;
	}
	
	public static void main(String[] args) throws URISyntaxException {
		
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
	
	private File findFile() {
		File root = new File(System.getProperty("user.dir"));
        String fileName = "BluemixTutorial.htm";
        File sourceFile = null;
        try {
            boolean recursive = true;

            Collection files = FileUtils.listFiles(root, new String[] {"htm"}, recursive);

            for (Iterator iterator = files.iterator(); iterator.hasNext();) {
                File file = (File) iterator.next();
                if (file.getName().equals(fileName)) {
                	sourceFile = new File(file.getAbsolutePath());
                	break;
                }
            }
            	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sourceFile;
	}
	
	private static final long serialVersionUID = 1L;
}
