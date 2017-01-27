import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest.Builder;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;


@WebServlet("/")
public class Snippet extends SuperGluev2 {
	
	
	public String parameters = "{\"username\":\"ac00ef1a-5ad1-416c-b778-27953b982e13\",\"password\":\"nHMkzS2ShYYY\","
			+ "\"workspace_id\":\"6a95d1df-8f65-4249-a58b-b5c83e56f4e4\","
			+ "\"question1\":\"Hi Watson\",\"question2\":\"Turn on the wiper\"}";
	
	@Override
	protected JsonObject process(String jsonString) {
		JsonParser parser = new JsonParser(); 
		JsonObject myBean = parser.parse(jsonString).getAsJsonObject();
		
		ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2016_07_11);
		service.setUsernameAndPassword(myBean.get("username").getAsString(), myBean.get("password").getAsString());
		  
		JsonArray messageArray = new JsonArray();
		
		Builder builder = new MessageRequest.Builder();
		  
	    MessageRequest messageReq = builder.inputText("").build();
	    MessageResponse response = service.message(myBean.get("workspace_id").getAsString(), messageReq).execute();
	    messageArray.add(response.getText().toString());
	  
	    messageReq = builder.context(response.getContext()).inputText(myBean.get("question1").getAsString()).build();
	    response = service.message(myBean.get("workspace_id").getAsString(), messageReq).execute();
	    messageArray.add(response.getInputText());
	    messageArray.add(response.getText().toString());
	    
	    messageReq = builder.context(response.getContext()).inputText(myBean.get("question2").getAsString()).build();
	    response = service.message(myBean.get("workspace_id").getAsString(), messageReq).execute();
	    messageArray.add(response.getInputText());
	    messageArray.add(response.getText().toString());

	    JsonObject messageJson = new JsonObject();
	    messageJson.add("Responses", messageArray);
	    
        JsonObject json = parser.parse(messageJson.toString()).getAsJsonObject();
		
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

