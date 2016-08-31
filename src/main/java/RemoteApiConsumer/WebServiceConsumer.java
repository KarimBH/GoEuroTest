package RemoteApiConsumer;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by k.benhmida on 28/08/2016.
 */
public class WebServiceConsumer {

    private static Logger logger = LogManager.getLogger(WebServiceConsumer.class);

    private HttpClient httpClient;

    public WebServiceConsumer(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public InputStream getInputStream(String url) throws RemoteApiCallException {

        logger.info("Getting data from : {}", url);

        HttpGet getRequest = new HttpGet(url);
        getRequest.addHeader("accept", "application/json");
        HttpResponse response = null;
        try {
            response = httpClient.execute(getRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RemoteApiCallException(
                        String.format("Failed to get %s . HTTP error code : %d", url, response.getStatusLine().getStatusCode())
                );
            }
            return response.getEntity().getContent();
        } catch (IOException | UnsupportedOperationException e) {
            throw new RemoteApiCallException(String.format("An exception occured while getting %s", url), e);
        }
    }


}
