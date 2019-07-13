package com.vanjacatak.infobip.urlshortener.shorturltwo;

public class Account {

    private String accountId;

    private String password;

    protected Account() {

    }

    public Account(String accountId, String password) {
        this.accountId = accountId;
        this.password = password;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
