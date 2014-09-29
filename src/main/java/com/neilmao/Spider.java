package com.neilmao;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.*;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: neil
 * Date: 29/09/14
 * Time: 11:55 AM
 */
public class Spider implements Runnable{

    private static final Log LOG = LogFactory.getLog(Spider.class);

    private static final int TIMEOUT = 1 * 500;

    private static final String AVAILABILITY_URL = "https://reserve.cdn-apple.com/AU/en_AU/reserve/iPhone/availability.json";

    private static final String STORE_URL = "https://reserve.cdn-apple.com/AU/en_AU/reserve/iPhone/stores.json";


    @Override
    public void run() {


    }

    public void getAvailability() {

        HttpClient httpClient = getHttpClient();

        CookieStore cookieStore = new BasicCookieStore();

        HttpContext httpContext = new BasicHttpContext();
        httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);

        Date date = new Date();

        LOG.info("Trying to get availability at " + date.toString());

        try {
            JSONParser parser = new JSONParser();

            HttpResponse response = getRequest(httpClient, STORE_URL, "", httpContext);

            parser.parseStores(response.getEntity().getContent());

            response = getRequest(httpClient, AVAILABILITY_URL, "", httpContext);

            Map<String, Model> modelMap = parser.parseAvailability(response.getEntity().getContent());



        } catch (IOException e) {
            LOG.error("Getting availability failed." + e.toString());
        }
    }

    private HttpClient getHttpClient() {
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(TIMEOUT).
                setConnectionRequestTimeout(TIMEOUT).
                setSocketTimeout(TIMEOUT).
                build();
        return HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
    }

    private HttpResponse getRequest(HttpClient httpClient, String link, String paramStr, HttpContext context) throws IOException {
        HttpGet get = new HttpGet(link + "?" + paramStr);
        return httpClient.execute(get, context);
    }

    private String inputStreamToString(InputStream inputStream) throws IOException {
        Reader reader = new InputStreamReader(inputStream, "gb2312");
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String buffer;
        while ((buffer = bufferedReader.readLine()) != null) {
            sb.append(buffer).append("\n");
        }
        return sb.toString();
    }
}
