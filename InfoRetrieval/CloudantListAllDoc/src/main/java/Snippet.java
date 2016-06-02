// AlchemyDataNewsTest
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.annotation.WebServlet;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.http.Http;
import com.cloudant.http.HttpConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


//After deployment go to the relative URI to test the functionality.
//You would see a form to provide the input values.
@WebServlet("/")
public class Snippet extends SuperGlue {
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException {
		Snippet myclass = new Snippet();
		Parameters params = myclass.new Parameters();
		params.setUsername("313d6562-626e-4edc-855f-528c7bba73e3-bluemix");
		params.setPassword("38fdea42f285bd146e7a7d2baa15f91dc486dababd609a1c61e3b2a2452aa55a");
		params.setDbName("pet");
		//****** Process method contains the key logic ******
		Object processResult = myclass.process(((Parameters) params));
		
		JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(processResult.toString()).getAsJsonObject();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(json));
	}
	
	public class Parameters {
		
		String username;
		String password;
		String dbName;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getDbName() {
			return dbName;
		}
		public void setDbName(String dbName) {
			this.dbName = dbName;
		}
		
	}
	
	@Override
	public Object process(Object myBean) {
		Object result = "No result";
		try {
			CloudantClient client = ClientBuilder.url(new URL("https://" + ((Parameters)myBean).getUsername() + ".cloudant.com"))
			        .username(((Parameters)myBean).getUsername())
			        .password(((Parameters)myBean).getPassword())
			        .build();
			

			//@param pet - name of database
			//@param false - if database don't exist, dont create it
			Database db = client.database(((Parameters)myBean).getDbName(), false);
			
			//To get all docs from the db "pet"
			HttpConnection response = client.executeRequest(Http.GET(new URL("https://" + ((Parameters)myBean).getUsername() + ".cloudant.com/" + db.info().getDbName() + "/_all_docs?include_docs=true")));
			
		    result = response.responseAsString();
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	}

	@Override
	Object getParameters() {
		return new Parameters();
	}



}