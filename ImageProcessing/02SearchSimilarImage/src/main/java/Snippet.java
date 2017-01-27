import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.annotation.WebServlet;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/")
public class Snippet extends SuperGluev2 {

	public String parameters = 
			"{\"apiKey\":\"cf8ff9af4fd5323e190b6df6b730ab4919464c73\","
			+ "\"imageurl\":\"http://www.binhindioutlet.me/wp-content/uploads/2015/04/MS1001.jpg\","
			+ "\"collectionId\":\"cheok_collection_8da233\"}";
	
	@Override
	protected JsonObject process(String jsonString) {
		JsonParser parser = new JsonParser(); 
		JsonObject myBean = parser.parse(jsonString).getAsJsonObject();  
		
	    System.out.println("Downloading picture.");
	    File fileA = new File("test.jpg"); 
	    URL url = null;
		try {
			url = new URL(myBean.get("imageurl").getAsString());
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-Agent", "NING/1.0");
		    FileUtils.copyInputStreamToFile(conn.getInputStream(), fileA);
		} catch (IOException e) {
			e.printStackTrace();
		}    
	    System.out.println("picture downloaded.");
		System.out.println("Processing picture...");
	    String apiURL = 
	    		"https://gateway-a.watsonplatform.net/visual-recognition/api/v3/collections/" 
					+ myBean.get("collectionId").getAsString() 
					+ "/find_similar?"
					+ "api_key=" + myBean.get("apiKey").getAsString() 
					+ "&version=2016-05-20";
	    String result = makeHTTPPostCall(apiURL, fileA);
		
        JsonObject json = parser.parse(result).getAsJsonObject();
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
	 
	 protected static String makeHTTPPostCall(String httpURL, File img) {
	    	
	    	HttpClient client = HttpClientBuilder.create().build();
			
	    	HttpPost post = new HttpPost(httpURL);

			post.setEntity(
					MultipartEntityBuilder.create()
					.addBinaryBody("image_file", img)
					.build());

			HttpResponse resp;
			String result = "";
			try {
				resp = client.execute(post);
				BufferedReader rd = new BufferedReader(
				        new InputStreamReader(resp.getEntity().getContent()));
	
				String line = "";
				while ((line = rd.readLine()) != null) {
					result += line;
				}
			} catch (ClientProtocolException e) {
				System.err.println(e.getMessage());
			} catch (IOException e) {
				System.err.println(e.getMessage());
			} 
			return result;
	  }
	
	private static final long serialVersionUID = 1L;
}