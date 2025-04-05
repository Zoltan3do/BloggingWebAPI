package com.zoltan.bloggingwebapi.services;

import com.zoltan.bloggingwebapi.entities.Feed;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RSSFeedService {
    public List<Feed> fetchWikipediaRssFeeds() {
        System.out.println("Inizio recupero feed da Wikipedia");
        List<Feed> feeds = new ArrayList<>();
        String url = "https://en.wikipedia.org/wiki/Special:RecentChanges?limit=1000";

        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(30000)
                    .get();

            Elements items = doc.select("div.mw-changeslist li");
            System.out.println("Trovati " + items.size() + " elementi");

            for (Element item : items) {
                try {
                    String title = "";
                    String description = "";
                    String link = "";
                    String author = "";
                    String changeType = "";
                    String changeSize = "";

                    Element titleElement = item.selectFirst("a.mw-changeslist-title");
                    if (titleElement != null) {
                        title = titleElement.text().trim();
                        link = "https://en.wikipedia.org" + titleElement.attr("href");
                    }

                    Element userElement = item.selectFirst("a.mw-userlink");
                    if (userElement != null) {
                        author = userElement.text().trim();
                    }

                    Element flagElement = item.selectFirst("abbr.minoredit, span.mw-changeslist-new");
                    if (flagElement != null) {
                        changeType = flagElement.text().trim();
                    }

                    Element sizeElement = item.selectFirst("span.mw-changeslist-separator ~ .mw-changeslist-diff");
                    if (sizeElement != null) {
                        changeSize = sizeElement.text().trim();
                    }

                    description = String.format("Pagina '%s' %s da %s (%s)",
                            title,
                            changeType.isEmpty() ? "modificata" : changeType,
                            author.isEmpty() ? "utente anonimo" : author,
                            changeSize.isEmpty() ? "dimensione modifica sconosciuta" : changeSize);

                    if (!title.isEmpty() && !link.isEmpty()) {
                        Feed feed = new Feed(
                                title,
                                description,
                                link,
                                LocalDate.now(),
                                "Wikipedia"
                        );
                        feeds.add(feed);
                    }
                } catch (Exception e) {
                    System.out.println("Errore nell'elaborazione di un elemento: " + e.getMessage());
                }
            }
            System.out.println("Recuperati " + feeds.size() + " feed dopo pulizia");
        } catch (Exception e) {
            System.out.println("Errore nel recupero dei feed: " + e);
            e.printStackTrace();
        }

        return feeds;
    }
}