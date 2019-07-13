package com.vanjacatak.infobip.urlshortener.shorturltwo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class ShortUrlDAOService {

    public static List<ShortUrl> urlList = new ArrayList<>();

    public ShortUrl createNewShortUrl(String longUrl, int redirectType, String creator) {

        ShortUrl shortUrl = new ShortUrl(longUrl, redirectType, creator);
        urlList.add(shortUrl);
        return shortUrl;

    }

    public ShortUrl findByShortUrl(String shortUrlKey) {

        for (ShortUrl shortUrl:urlList)
            if (shortUrl.getShortUrl().equals(shortUrlKey))
                return shortUrl;

        return null;
    }

    public HashMap<String, Integer> getStatsForAccount(String accountId) {

        HashMap<String, Integer> stats = new HashMap<>();

        for (ShortUrl shortUrl:urlList)
            if (shortUrl.getCreator().equals(accountId))
                stats.put(shortUrl.getLongUrl(), shortUrl.getNumberOfHits());


        return stats;
    }


}
