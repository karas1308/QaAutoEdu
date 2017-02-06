package resttestpack;


import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class RestTest {

    public static final String ACCESS_KEY = "f208413b43c382be2f83281665d23bc0";
    public static final String BASE_URL = "http://apilayer.net/api/";
    public static final String ENDPOINT = "live";
    public static String currencies = "";
    public static String source = "";

    static CloseableHttpClient httpClient = HttpClients.createDefault();

    @Test
    public void containsAllKeys() throws IOException {
        currencies = "EUR";
        JSONObject json = json();
        String[] elements = new String[]{"success", "terms", "privacy", "timestamp", "source", "quotes"};
        Set set = new HashSet(Arrays.asList(elements));
        assertTrue("Response don't contains all keys ", json.keySet().containsAll(set));
    }

    @Test
    public void currencies() throws IOException {
        currencies = "EUR,UAH,RUB";
        JSONObject json = json();
        String[] elements = new String[]{"USDEUR", "USDUAH", "USDRUB"};
        Set set = new HashSet(Arrays.asList(elements));
        assertTrue("Response don't contains all currencies ", json.getJSONObject("quotes").keySet().containsAll(set));
        assertTrue("Number of currencies dont equals 3", json.getJSONObject("quotes").keySet().size() == 3);
    }

    @Test
    public void currencies170() throws IOException {
        JSONObject json = json();
        assertTrue("Number of currencies dont equals 170, =" + json.getJSONObject("quotes").keySet().size(), json.getJSONObject("quotes").keySet().size() == 170);
    }

    @Test
    public void currenciesEUR() throws IOException {
        currencies = "EUR,UAH";
        JSONObject json = json();
        assertTrue("USDEUR >0,99 or <0.9 " + json.getJSONObject("quotes").get("USDEUR"),
                Double.valueOf(String.valueOf(json.getJSONObject("quotes").get("USDEUR"))) > 0.9 & Double.valueOf(String.valueOf(json.getJSONObject("quotes").get("USDEUR"))) < 0.99);
    }

    @Test
    public void currenciesUAH() throws IOException {
        source = "UAH";
        JSONObject json = json();
        assertTrue(json.getJSONObject("error").get("code").equals(105));
        assertTrue(json.getJSONObject("error").get("info").equals("Access Restricted - Your current Subscription Plan does not support Source Currency Switching."));
    }

    public JSONObject json() throws IOException {
        HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY + "&currencies=" + currencies
                + "&source=" + source);
        CloseableHttpResponse response = httpClient.execute(get);
        HttpEntity entity = response.getEntity();
        JSONObject json = new JSONObject(EntityUtils.toString(entity));
        return json;
    }
}
