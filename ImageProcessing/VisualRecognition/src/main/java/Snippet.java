import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.annotation.WebServlet;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualRecognitionOptions;

@WebServlet("/")
public class Snippet extends SuperGlue {

	public class Parameters {
		public String apiKey = "";
		public String url = "https://www.whitehouse.gov/sites/whitehouse.gov/files/images/first-family/44_barack_obama%5B1%5D.jpg";
	}
	
	@Override
	public Object process(Object myBean) {	
		 VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_19);
	    service.setApiKey(((Parameters) myBean).apiKey);    
	    File obama;    
	    URL url = null;
		try {
			url = new URL(((Parameters) myBean).url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    obama = new File("test.jpg");     
	    try {
			FileUtils.copyURLToFile(url, obama);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
	    VisualRecognitionOptions voptoins = new VisualRecognitionOptions.Builder().images(obama).build();
		service.detectFaces(voptoins);
		DetectedFaces result = service.detectFaces(voptoins).execute();
		
		return result;
	}
	
	@Override
	protected Object getParameters() {
		return new Parameters();
	}
	
	public static void main(String[] args) {
		Snippet myclass = new Snippet();
		Parameters parameters = myclass.new Parameters();
		//****** Process method contains the key logic ******
		Object processResult = myclass.process(((Parameters) parameters));
		
		JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(processResult.toString()).getAsJsonObject();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(json));
    }

}