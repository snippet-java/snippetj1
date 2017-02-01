import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyDataNews;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentsResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


//After deployment go to the relative URI to test the functionality.
//You would see a form to provide the input values.
@WebServlet("/")
public class Snippet extends SuperGluev2 {

	public String parameters = "{\"apiKey\":\"8bea72d282329f66b671f37e49e164a6ab51c2b3\"}";
	
	@Override
	protected JsonObject process(String jsonString) {
		JsonParser parser = new JsonParser(); 
		JsonObject myBean = parser.parse(jsonString).getAsJsonObject(); 
		
		AlchemyDataNews service = new AlchemyDataNews();
		service.setApiKey(myBean.get("apiKey").getAsString());
		
		Map<String, Object> params = new HashMap<String, Object>();
		// specify the categories to be included. In this case, title, url,author... are included
		String[] fields = new String[] { "enriched.url.title", "enriched.url.url", "enriched.url.author",
				"enriched.url.publicationDate", "enriched.url.enrichedTitle.entities",
				"enriched.url.enrichedTitle.docSentiment" };
		params.put(AlchemyDataNews.RETURN, StringUtils.join(fields, ","));
		// the time (in UTC seconds) of the beginning of the query duration
		params.put(AlchemyDataNews.START, "1440720000");
		// the time (in UTC seconds) of the end of the query duration
		params.put(AlchemyDataNews.END, "1441407600");
		// return how many articles
		params.put(AlchemyDataNews.COUNT, 7);
		DocumentsResult result = service.getNewsDocuments(params).execute();

        JsonObject json = parser.parse(result.toString()).getAsJsonObject();
		
		return json;
		
	}
	
	public static void main(String[] args) {
		Snippet myclass = new Snippet();
		
		//****** Process method contains the key logic ******
		JsonObject processResult = myclass.process(myclass.parameters);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(processResult));
       generateHTML();
	}

	@Override
	JsonObject getParameters() {
		return new JsonParser().parse(parameters).getAsJsonObject();
	}
  
    public static void generateHTML() {
      	Snippet myclass = new Snippet();
		JsonObject json = myclass.process(myclass.parameters);
      
    	//JsonObject json = parser.parse(fileString).getAsJsonObject();
        
        JsonArray docsArray = json.get("result").getAsJsonObject().get("docs").getAsJsonArray();
        //System.out.println(docsArray);
        
        String title;
        String url;
        String author; //source-> enriched -> url -> author
        String publicationDate;
        String docSentimentType;
        
        String html = "",htmlMainInterpolate="";
        String htmlHeader = 
        		"<!DOCTYPE html>\r\n" + 
        		"<html lang=\"en\">\r\n" + 
        		"<head>\r\n" + 
        		"    <meta charset=\"UTF-8\">\r\n" + 
        		"    <title>Alchemy Data News</title>\r\n" + 
        		"</head>\r\n" + 
        		"<body>";
        String htmlMain = 
        		"<a href= %s > %s </a>\r\n" + 
        		"\r\n" + 
        		"    <table style=\"width:40%%\">\r\n" + 
        		"        <tr>\r\n" + 
        		"            <td>Author:</td>\r\n" + 
        		"            <td>%s</td>\r\n" + 
        		"        </tr>\r\n" + 
        		"        <tr>\r\n" + 
        		"            <td>Publication Date:</td>\r\n" + 
        		"            <td>%s</td>\r\n" + 
        		"        </tr>\r\n" + 
        		"        <tr>\r\n" + 
        		"            <td>Doc Sentiment type:</td>\r\n" + 
        		"            <td>%s</td>\r\n" + 
        		"        </tr>\r\n" + 
        		"    </table>\r\n" + 
        		"\r\n" + 
        		"<br><br>";
        String htmlFooter = 
        		"</body>\r\n" + 
        		"</html>";
        
        html += htmlHeader;
        
        for(JsonElement je:docsArray ){
        	
        	author = je.getAsJsonObject().get("source").getAsJsonObject().get("enriched").
        			getAsJsonObject().get("url").getAsJsonObject().get("author").getAsString();        	
        	
        	title = je.getAsJsonObject().get("source").getAsJsonObject().get("enriched").
                	getAsJsonObject().get("url").getAsJsonObject().get("title").getAsString();
        	
        	url = je.getAsJsonObject().get("source").getAsJsonObject().get("enriched").
                	getAsJsonObject().get("url").getAsJsonObject().get("url").getAsString();
        	
        	publicationDate = je.getAsJsonObject().get("source").getAsJsonObject().get("enriched").
                	         getAsJsonObject().get("url").getAsJsonObject().get("publicationDate").
                	         getAsJsonObject().get("date").getAsString();
        	
        	docSentimentType = je.getAsJsonObject().get("source").getAsJsonObject().get("enriched").
       	         getAsJsonObject().get("url").getAsJsonObject().get("enrichedTitle").
       	         getAsJsonObject().get("docSentiment").getAsJsonObject().get("type").getAsString();
        	
        	htmlMainInterpolate = String.format(htmlMain,url,title,author,publicationDate,docSentimentType);        	
        	html+=htmlMainInterpolate;
        }        
        html += htmlFooter;        
        System.out.println(html);           
        try {
			File file = new File("output.html");
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(html);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
       
    }	
	private static final long serialVersionUID = 1L;
}
