import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualRecognitionOptions;
public class VisualRecognitionExample {
  public static void main(String[] args) throws IOException {
    VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_19);
    service.setApiKey("");    
    File obama;    
    URL url = new URL("https://www.whitehouse.gov/sites/whitehouse.gov/files/images/first-family/44_barack_obama%5B1%5D.jpg");
    obama = new File("test.jpg");     
    FileUtils.copyURLToFile(url, obama);     
    VisualRecognitionOptions voptoins = new VisualRecognitionOptions.Builder().images(obama).build();
	service.detectFaces(voptoins);
	DetectedFaces result = service.detectFaces(voptoins).execute();
    System.out.println(result.toString());
    }
}