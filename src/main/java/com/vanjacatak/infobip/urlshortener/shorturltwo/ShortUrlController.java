package com.vanjacatak.infobip.urlshortener.shorturltwo;

import com.vanjacatak.infobip.urlshortener.exception.MissingParameterException;
import com.vanjacatak.infobip.urlshortener.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ShortUrlController {

    @Autowired
    private AccountDAOService accountService;

    @Autowired
    private ShortUrlDAOService shortUrlService;

    @PostMapping("/register")
    public HashMap<String, Object> registerURL(HttpServletRequest httpServletRequest,
                                               HttpServletResponse httpServletResponse,
                                               @RequestHeader(required = false, value = "Authorization") String reg,
                                               @RequestBody Map<String, Object> request) {


        HashMap<String, Object> response = new HashMap<>();

        if ( reg != null) {

            if (accountService.authorizeAccount(reg)) {

                if (request.get("URL") != null) {

                    String accountId = accountService.getAccountIdFromAuthHeader(reg);
                    String url = (String) request.get("URL");

                    int redirectType = 302;
                    if (request.get("redirectType") != null && (int) request.get("redirectType") == 301)
                        redirectType = 301;

                    ShortUrl shortUrl = shortUrlService.createNewShortUrl(url, redirectType, accountId);
                    shortUrl.generateShortUrl();

                    String path = httpServletRequest
                            .getRequestURL()
                            .toString()
                            .replaceFirst(httpServletRequest.getRequestURI(), "");

                    response.put("shortUrl", path + "/" + shortUrl.getShortUrl());

                    return response;

                }

                throw new MissingParameterException("Missing 'URL' parameter");

            }

        }

        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return response;

    }

    @GetMapping("/short/{shortUrlKey}")
    public void redirectUser(HttpServletResponse response, @PathVariable("shortUrlKey") String shortUrlKey) {

        ShortUrl shortUrl = shortUrlService.findByShortUrl(shortUrlKey);

        if (shortUrl != null) {

            shortUrl.setNumberOfHits(shortUrl.getNumberOfHits() + 1);
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", shortUrl.getLongUrl());

        } else
            throw new NotFoundException("URL with key: " + shortUrlKey + " does not exist.");

    }

}
