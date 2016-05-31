// AlchemyDataNewsTest
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyDataNews;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentsResult;


//After deployment go to the relative URI to test the functionality.
//You would see a form to provide the input values.
@WebServlet("/")
public class Snippet extends SuperGlue {
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException {
		Snippet myclass = new Snippet();
		Parameters params = myclass.new Parameters();
		params.setApikey("913f155354acfc4810935b58249e5edefa63f9ba");
		//****** Process method contains the key logic ******
		Object processResult = myclass.process(((Parameters) params));
		System.out.println(new Gson().toJson(processResult));
	}
	
	public class Parameters {
		String apikey;
		public String getApikey() {	return apikey; }
		public void setApikey(String apikey) { this.apikey = apikey; }
	}
	
	@Override
	public Object process(Object myBean) {
		AlchemyDataNews service = new AlchemyDataNews();
		service.setApiKey(((Parameters)myBean).getApikey());
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
		DocumentsResult result = service.getNewsDocuments(params);
		
		return result;
		
	}

	@Override
	Object getParameters() {
		return new Parameters();
	}



}