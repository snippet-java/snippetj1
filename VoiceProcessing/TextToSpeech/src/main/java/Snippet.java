
import java.util.List;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

public class Snippet{
	
	public static void main(String[] args) {
		 TextToSpeech service = new TextToSpeech();
		 service.setUsernameAndPassword("",""); // SET YOUR USERNAME AND PASSWORD
		 List<Voice> voices = service.getVoices().execute();
		 System.out.println(voices);
	}
}
