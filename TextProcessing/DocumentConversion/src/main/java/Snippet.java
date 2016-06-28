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
public class Snippet extends SuperGlue {
	
	public class Parameters {
		public String versionDate = "2015-12-15";
		public String userName = "e19a0428-c14c-4f79-b355-b94c3f28a27c";
		public String password = "1X2ylaRCsetU";
	}
	
	@Override
	public Object process(Object myBean) {		
		DocumentConversion service = new DocumentConversion(((Parameters) myBean).versionDate);
		service.setUsernameAndPassword(((Parameters) myBean).userName, ((Parameters) myBean).password);
		File doc = findFile();
		Answers htmlToAnswers = service.convertDocumentToAnswer(doc).execute();
		
		return htmlToAnswers.toString();
	}
	
	@Override
	protected Object getParameters() {
		return new Parameters();
	}
	
	public static void main(String[] args) throws URISyntaxException {
		
		Snippet myclass = new Snippet();
		Parameters parameters = myclass.new Parameters();
		//****** Process method contains the key logic ******
		Object processResult = myclass.process(((Parameters) parameters));
		
		JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(processResult.toString()).getAsJsonObject();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(json));
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
