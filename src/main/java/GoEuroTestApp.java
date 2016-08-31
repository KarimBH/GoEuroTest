
import CsvExport.CsvWriter;
import CsvExport.CsvWritingException;
import RemoteApiConsumer.GoeuroUrlBuilder;
import RemoteApiConsumer.RemoteApiCallException;
import RemoteApiConsumer.UrlBuildingException;
import RemoteApiConsumer.WebServiceConsumer;
import model.PartialLocation;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by k.benhmida on 28/08/2016.
 */
public class GoEuroTestApp {

    private static Logger logger = LogManager.getLogger(GoEuroTestApp.class);

    public static void main(String[] args) throws IOException {
        logger.info("Entering application");
        // Check if CITY_NAME argument was provided
        if (args.length == 0 || "".equals(args[0].trim())) {
            System.err.println("Proper Usage is: java -jar GoEuroTest.jar \"CITY_NAME\"");
            System.exit(0);
        }
        new GoEuroTestApp().getResults(args[0]);
        logger.info("Exiting application");
    }


    private void getResults(String cityName) {
        HttpClient client = HttpClientBuilder.create().build();
        try {
            String url = GoeuroUrlBuilder.getUrl(cityName);
            InputStream responseInputStream = new WebServiceConsumer(client).getInputStream(url);
            if (responseInputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseInputStream));
                PartialLocation[] partialLocations = PartialLocation.fromJson(bufferedReader);

                String[] FIELD_MAPPING = new String[]{
                        "id",
                        "name",
                        "type",
                        "geoPosition.latitude",
                        "geoPosition.longitude",
                };
                CsvWriter csvWriter = new CsvWriter(PartialLocation.class, FIELD_MAPPING);
                csvWriter.writeToCSV(partialLocations, "./out.csv");
            }
        } catch (UrlBuildingException e) {
            e.printStackTrace();
        } catch(RemoteApiCallException e) {
            e.printStackTrace();
        } catch (CsvWritingException e) {
            e.printStackTrace();
        }
    }
}
