import javax.servlet.annotation.WebServlet;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest.Builder;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;


@WebServlet("/")
//After deployment go to the relative URI to test the functionality.
//You would see a form to provide the input values.
public class Snippet extends SuperGlue {
	
	public class Parameters {
		public String userName = "ac00ef1a-5ad1-416c-b778-27953b982e13";
		public String password = "nHMkzS2ShYYY";
		public String workspace_id = "6a95d1df-8f65-4249-a58b-b5c83e56f4e4";
		
		public String question1 = "Hi Watson";
		public String question2 = "Turn on the wiper";
	}
	
	@Override
	protected Object process(Object myBean) {
		ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2016_07_11);
		service.setUsernameAndPassword(((Parameters) myBean).userName, ((Parameters) myBean).password);
		  
		String output = "";
		
		Builder builder = new MessageRequest.Builder();
		  
	    MessageRequest messageReq = builder.inputText("").build();
	    MessageResponse response = service.message(((Parameters) myBean).workspace_id, messageReq).execute();
	    output += "      Watson: " + response.getText();
	  
	    output += "\n" + ((Parameters) myBean).question1;
	    messageReq = builder.context(response.getContext()).inputText(((Parameters) myBean).question1).build();
	    response = service.message(((Parameters) myBean).workspace_id, messageReq).execute();
	    output += "\n" + "      Watson: " + response.getText();
	    
	    output += "\n" + ((Parameters) myBean).question2;
	    messageReq = builder.context(response.getContext()).inputText(((Parameters) myBean).question2).build();
	    response = service.message(((Parameters) myBean).workspace_id, messageReq).execute();
	    output += "\n" + "      Watson: " + response.getText();
	    
	    return output;
	}
	
	public static void main(String[] args) {
		Snippet myclass = new Snippet();
		Parameters params = myclass.new Parameters();
		//****** Process method contains the key logic ******
		Object processResult = myclass.process(((Parameters) params));
		
		System.out.println(processResult.toString());
	}

	@Override
	protected Object getParameters() {
		return new Parameters();
	}
	
	private static final long serialVersionUID = 1L;
}
