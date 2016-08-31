package RemoteApiConsumer;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Created by k.benhmida on 28/08/2016.
 */
public class GoeuroUrlBuilder {

    //externalize to a properties file
    private static final String GOEURO_URL_TEMPLATE = "http://api.goeuro.com/api/v2/position/suggest/en/%s";

    /**
     * build the request url according to the provided city name.
     *
     * @param cityName the name of the city
     * @return the url to call
     */
    public static String getUrl(String cityName) throws UrlBuildingException {
        try {
            String urlEncodedCityName = URLEncoder.encode(cityName, StandardCharsets.UTF_8.toString()).replace("+", "%20");
            return String.format(GOEURO_URL_TEMPLATE, urlEncodedCityName);
        } catch (UnsupportedEncodingException e) {
           throw new UrlBuildingException("An exception occured while building remote api url", e);
        }
    }

}
