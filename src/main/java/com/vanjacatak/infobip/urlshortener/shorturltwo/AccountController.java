package com.vanjacatak.infobip.urlshortener.shorturltwo;

import com.vanjacatak.infobip.urlshortener.exception.MissingParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    private AccountDAOService accountService;

    @Autowired
    private ShortUrlDAOService shortUrlService;

    @GetMapping("/account")
    public List<Account> getAllAccounts() {

        return accountService.findAll();
    }

    @PostMapping("/account")
    public HashMap<String, Object> createAccount(HttpServletResponse httpServletResponse,
                                                 @RequestBody Map<String, Object> request) {

        HashMap<String, Object> response = new HashMap<>();

        if (request.get("AccountId") != null) {

            String accountId = (String) request.get("AccountId");

            if (!accountService.checkIfAccountExists(accountId)) {

                String accountPassword = accountService.createNewAccount(accountId).getPassword();

                response.put("success", true);
                response.put("description", AccountDAOService.accountSuccessMessage());
                response.put("password", accountPassword);

                httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);



            } else {

                response.put("success", false);
                response.put("description", AccountDAOService.accountExistsMessage());
            }

            return response;
        }

        throw new MissingParameterException("Missing 'AccountId' parameter");

    }

    @GetMapping("/statistic/{accountId}")
    public HashMap<String, Integer> getStatisticForAccount(HttpServletResponse httpServletResponse,
                                                           @RequestHeader(required = false, value = "Authorization") String reg,
                                                           @PathVariable("accountId") String accountId) {

        HashMap<String, Integer> response = new HashMap<>();

        if ( reg != null) {

            if (accountService.authorizeAccount(reg) && accountId.equals(accountService.getAccountIdFromAuthHeader(reg))) {

                response = shortUrlService.getStatsForAccount(accountId);
                return response;

            }

        }

        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return response;

    }
}
