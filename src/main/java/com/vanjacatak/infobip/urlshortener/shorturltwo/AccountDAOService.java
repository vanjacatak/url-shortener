package com.vanjacatak.infobip.urlshortener.shorturltwo;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class AccountDAOService {

    public static List<Account> accounts = new ArrayList<>();

    public static String accountSuccessMessage() {
        return "Account successfully registered";
    }

    public static String accountExistsMessage() {
        return "Account with that ID already exists";
    }

    public Account createNewAccount(String accountId) {

        Account account = new Account(accountId, RandomStringGenerator.generatePassword());
        accounts.add(account);
        return account;

    }

    public boolean checkIfAccountExists(String accountId) {

        for(Account account:accounts)
            if (account.getAccountId().equals(accountId))
                return true;

        return false;
    }

    private Account findByAccountId(String accountId) {

        for (Account account:accounts)
            if (account.getAccountId().equals(accountId))
                return account;

        return null;
    }

    private String[] base64Decode (String data) {

        String base64Credentials = data.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);

        final String[] values = credentials.split(":", 2);

        return values;
    }

    public List<Account> findAll() {
        return accounts;
    }

    public boolean authorizeAccount(String accountData) {

        String userData[] = base64Decode(accountData);

        Account account = findByAccountId(userData[0]);
        if (null == account)
            return false;
        if (account.getPassword().equals(userData[1]))
            return true;

        return false;
    }

    public String getAccountIdFromAuthHeader(String accountData) {

        String userData[] = base64Decode(accountData);

        return userData[0];
    }

}
