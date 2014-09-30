package com.neilmao.iphone6;

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
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: neil
 * Date: 29/09/14
 * Time: 11:55 AM
 */
public class Spider {

    private static final Log LOG = LogFactory.getLog(Spider.class);

    private static final int TIMEOUT = 1 * 1000;

    private static final String AVAILABILITY_URL = "https://reserve.cdn-apple.com/AU/en_AU/reserve/iPhone/availability.json";

    private static final String STORE_URL = "https://reserve.cdn-apple.com/AU/en_AU/reserve/iPhone/stores.json";

    private String logFile;

    public Spider(String logFile) {
        this.logFile = logFile;
    }

    public void getAvailability(boolean importantOnly, boolean sydneyOnly) throws InterruptedException, IOException {

        PrintWriter writer = new PrintWriter(this.logFile, "UTF-8");

        do {
            HttpClient httpClient = getHttpClient();

            CookieStore cookieStore = new BasicCookieStore();

            HttpContext httpContext = new BasicHttpContext();
            httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);

            try {
                JSONParser parser = new JSONParser();

                InputStream inputStream;

                inputStream = getRequest(httpClient, STORE_URL, "", httpContext).getEntity().getContent();

                //inputStream = getClass().getResourceAsStream("/meta/store.json");

                parser.parseStores(inputStream);

                inputStream = getRequest(httpClient, AVAILABILITY_URL, "", httpContext).getEntity().getContent();

                //inputStream = getClass().getResourceAsStream("/meta/availability.json");

                Map<String, Store> storeMap = parser.parseAvailability(inputStream);

                Set<String> sydneyStoreKeySet = new HashSet<String>();
                sydneyStoreKeySet.add("R238");
                sydneyStoreKeySet.add("R523");
                sydneyStoreKeySet.add("R254");
                sydneyStoreKeySet.add("R253");
                sydneyStoreKeySet.add("R458");
                sydneyStoreKeySet.add("R440");

                StringBuilder sb = new StringBuilder();

                Date date = new Date();

                sb.append("========================\n");
                sb.append("TimeStamp: " + date + " Important Only:" + importantOnly + " Sydney Only:" + sydneyOnly + "\n");

                for (Store store : storeMap.values()) {
                    if (!sydneyOnly || sydneyStoreKeySet.contains(store.getCode())) {
                        if (store.isEnabled()) {
                            store.checkStock();
                            if (store.isHasStock()) {
                                if (!importantOnly || store.isHasImportantStock()) {
                                    sb.append("Store: " + store.getName() + "\n");
                                    List<Model> models = store.getModelList();
                                    for (Model model : models) {
                                        if (model.isAvailable()) {
                                            if (model.isImportant()) {
                                                sb.append("[*] ");
                                            }
                                            if (!importantOnly || model.isImportant())
                                                sb.append(model.getName() + "\n");
                                        }
                                    }
                                    sb.append("------------------------\n");
                                }
                            }
                        }
                    }
                }

                sb.append("========================\n");

                LOG.info(sb.toString());

                writer.println(sb.toString());
                writer.flush();

            } catch (IOException e) {
                LOG.error("Getting availability failed." + e.toString());
            }
            Thread.sleep(100L);
        } while (true);
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
}
