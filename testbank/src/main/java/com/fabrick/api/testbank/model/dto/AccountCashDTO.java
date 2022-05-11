package com.fabrick.api.testbank.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountCashDTO implements Serializable {

    @JsonProperty
    private String accountId;
    @JsonProperty
    private String iban;
    @JsonProperty
    private String abiCode;
    @JsonProperty
    private String cabCode;
    @JsonProperty
    private String countryCode;
    @JsonProperty
    private String internationalCin;
    @JsonProperty
    private String nationalCin;
    @JsonProperty
    private String account;
    @JsonProperty
    private String alias;
    @JsonProperty
    private String productName;
    @JsonProperty
    private String holderName;
    @JsonProperty
    private Date activatedDate;
    @JsonProperty
    private String currency;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getAbiCode() {
        return abiCode;
    }

    public void setAbiCode(String abiCode) {
        this.abiCode = abiCode;
    }

    public String getCabCode() {
        return cabCode;
    }

    public void setCabCode(String cabCode) {
        this.cabCode = cabCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getInternationalCin() {
        return internationalCin;
    }

    public void setInternationalCin(String internationalCin) {
        this.internationalCin = internationalCin;
    }

    public String getNationalCin() {
        return nationalCin;
    }

    public void setNationalCin(String nationalCin) {
        this.nationalCin = nationalCin;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public Date getActivatedDate() {
        return activatedDate;
    }

    public void setActivatedDate(Date activatedDate) {
        this.activatedDate = activatedDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountCashDTO that = (AccountCashDTO) o;
        return Objects.equals(getAccountId(), that.getAccountId()) && Objects.equals(getIban(), that.getIban()) && Objects.equals(getAbiCode(), that.getAbiCode()) && Objects.equals(getCabCode(), that.getCabCode()) && Objects.equals(getCountryCode(), that.getCountryCode()) && Objects.equals(getInternationalCin(), that.getInternationalCin()) && Objects.equals(getNationalCin(), that.getNationalCin()) && Objects.equals(getAccount(), that.getAccount()) && Objects.equals(getAlias(), that.getAlias()) && Objects.equals(getProductName(), that.getProductName()) && Objects.equals(getHolderName(), that.getHolderName()) && Objects.equals(getActivatedDate(), that.getActivatedDate()) && Objects.equals(getCurrency(), that.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountId(), getIban(), getAbiCode(), getCabCode(), getCountryCode(), getInternationalCin(), getNationalCin(), getAccount(), getAlias(), getProductName(), getHolderName(), getActivatedDate(), getCurrency());
    }
}
