package com.vanjacatak.infobip.urlshortener.shorturltwo;

public class ShortUrl {

    protected ShortUrl() {

    }

    public ShortUrl(String longUrl, int redirectType, String creator) {
        this.longUrl = longUrl;
        this.shortUrl = null;
        this.redirectType = redirectType;
        this.creator = creator;
        this.numberOfHits = 0;
    }

    public void generateShortUrl() {
        if (this.shortUrl == null)
            this.shortUrl = RandomStringGenerator.generateShortUrl();
    }

    private String longUrl;

    private String shortUrl;

    private int redirectType;

    private String creator;

    private int numberOfHits;

    public String getLongUrl() {
        return longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public int getRedirectType() {
        return redirectType;
    }

    public String getCreator() {
        return creator;
    }

    public int getNumberOfHits() {
        return numberOfHits;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public void setRedirectType(int redirectType) {
        this.redirectType = redirectType;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setNumberOfHits(int numberOfHits) {
        this.numberOfHits = numberOfHits;
    }
}
