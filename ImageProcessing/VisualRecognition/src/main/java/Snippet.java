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
public class Snippet extends SuperGluev2 {

	public String parameters = "{\"apiKey\":\"\",\"url\":\"https://www.whitehouse.gov/sites/whitehouse.gov/files/images/first-family/44_barack_obama%5B1%5D.jpg\"}";
	
	@Override
	protected JsonObject process(String jsonString) {
		JsonParser parser = new JsonParser(); 
		JsonObject myBean = parser.parse(jsonString).getAsJsonObject();  
		
		VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_19);
	    service.setApiKey(myBean.get("apiKey").getAsString());    
	    
	    File obama;    
	    URL url = null;
		try {
			url = new URL(myBean.get("url").getAsString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	    obama = new File("test.jpg");     
	    try {
			FileUtils.copyURLToFile(url, obama);
		} catch (IOException e) {
			e.printStackTrace();
		}     
	    VisualRecognitionOptions voptoins = new VisualRecognitionOptions.Builder().images(obama).build();
		service.detectFaces(voptoins);
		DetectedFaces result = service.detectFaces(voptoins).execute();
System.out.println(result.toString());
        JsonObject json = parser.parse(result.toString()).getAsJsonObject();
		
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