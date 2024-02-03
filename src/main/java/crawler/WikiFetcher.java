package crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Description: Singleton pattern Web crawler
 * @Author: whj
 * @Date: 2024-02-03 19:44
 */
public class WikiFetcher {
    private static final String IdOfWiki = "mw-content-text";
    private static final String Paragraph = "p";
    private static final long MININTERVAL = 1000;
    private static long lastRequestTime = -1;


    private WikiFetcher() {

    }

    private static class WikiFetcherHolder {
        private static final WikiFetcher INSTANCE = new WikiFetcher();
    }

    public WikiFetcher getInstance() {
        return WikiFetcherHolder.INSTANCE;
    }


    /**
     * desc:Fetches and parses a URL string,
     *     returning a list of paragraph elements.
    * date 2024-02-03
    * @author whj
    * @param url
    * @return Elements
    **/
    public Elements fetchWikipedia(String url) throws IOException {
        sleepIfNeeded();
        Connection connect = Jsoup.connect(url);
        Document document = connect.get();
        Element content = document.getElementById(IdOfWiki);
        Elements paragraphs = content.select(Paragraph);
        return paragraphs;
    }

    /**
     * desc:When you write a Web crawler, it is easy to download too many pages too fast,
     * which might violate the terms of service for the server you are downloading from.
     * It measures the time between requests and, if we don’t leave enough time between requests,
     * it sleeps until a reasonable interval has elapsed. By default, the interval is one second.
     * date 2024-02-03
     *
     * @author whj
     **/
    private void sleepIfNeeded() {
        if (lastRequestTime != -1) {
            long currentTime = System.currentTimeMillis();
            long nextRequestTime = lastRequestTime + MININTERVAL;
            if (currentTime < nextRequestTime) {
                try {
                    Thread.sleep(nextRequestTime - currentTime);
                } catch (InterruptedException e) {
                    System.err.println("Warning: sleep interrupted in fetchWikipedia.");
                }
            }
        }
        lastRequestTime = System.currentTimeMillis();
    }
}
