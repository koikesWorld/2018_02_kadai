package kadai1;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

public class Data {
	public static void main(String args[]) throws IOException, GeneralSecurityException {
		String spreadsheetId = "11BCnspCt2Mut3nhc4WMY6CYTd0zF9C3eCzsk1AEpKLM";
		String range = "A1:E6";

		Sheets sheetsService = createSheetesService();
	    Sheets.Spreadsheets.Values.Get request =
	            sheetsService.spreadsheets().values().get(spreadsheetId, range);
	    request.setMajorDimension("ROWS");
	    //ここにAPI KEYをセットする
	    request.setKey("**********");

	    ValueRange response = request.execute();

	    List<List<Object>> lists  = response.getValues();
	    for( List<Object> list : lists) {
	    	for(Object element : list) {
	    		System.out.print("'" + element + "'" + ",");
	    	}
	    	System.out.print("\n");
	    }
	}

		public static Sheets createSheetesService() throws GeneralSecurityException, IOException {
			HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		    GoogleCredential credential = null;

			return new Sheets.Builder(httpTransport, jsonFactory, credential)
					.setApplicationName("SpreadSheetSearch")
					.build();
		}
}
