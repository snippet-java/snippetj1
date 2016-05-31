import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;
//After deployment go to the relative URI to test the functionality.
//You would see a form to provide the input values.
@WebServlet("/")
public class Snippet extends SuperGlue {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		Snippet myclass = new Snippet();
		Parameters params = myclass.new Parameters();
		params.setApiKey("913f155354acfc4810935b58249e5edefa63f9ba");
		params.setTextToBeAnalysed("IBM Watson won the Jeopardy television show hosted by Alex Trebek");
		//****** Process method contains the key logic ******
		Object processResult = myclass.process(((Parameters) params));
		System.out.println(new Gson().toJson(processResult));
	}
	public class Parameters {
		String apiKey;
		String textToBeAnalysed;
		public String getTextToBeAnalysed() { return textToBeAnalysed; }
		public void setTextToBeAnalysed(String textToBeAnalysed) { this.textToBeAnalysed = textToBeAnalysed; }
		public String getApiKey() { return apiKey; }
		public void setApiKey(String apiKey) { this.apiKey = apiKey; }
	}
	@Override
	protected Object process(Object myBean) {
		AlchemyLanguage service = new AlchemyLanguage();
		service.setApiKey(((Parameters) myBean).getApiKey());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(AlchemyLanguage.TEXT, ((Parameters) myBean).getTextToBeAnalysed());
		DocumentSentiment sentiment = service.getSentiment(params);
		
		return sentiment.toString();
	}
	@Override
	protected Object getParameters() {
		return new Parameters();
	}
	
}
