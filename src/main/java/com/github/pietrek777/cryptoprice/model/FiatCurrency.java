package com.github.pietrek777.cryptoprice.model;

import java.util.Arrays;
import java.util.Optional;

/**
 * This enum contains every fiat currency handled by CoinMarketCap. What did you expect?
 *
 * @author pietrek777
 */
public enum FiatCurrency {
    AUD("AUD"),
    BRL("BRL"),
    CAD("CAD"),
    CHF("CHF"),
    CLP("CLP"),
    CNY("CNY"),
    CZK("CZK"),
    DKK("DKK"),
    EUR("EUR"),
    GBP("GBP"),
    HKD("HKD"),
    HUF("HUF"),
    IDR("IDR"),
    ILS("ILS"),
    INR("INR"),
    JPY("JPY"),
    KRW("KRW"),
    MXN("MXN"),
    MYR("MYR"),
    NOK("NOK"),
    NZD("NZD"),
    PHP("PHP"),
    PKR("PKR"),
    PLN("PLN"),
    RUB("RUB"),
    SEK("SEK"),
    SGD("SGD"),
    THB("THB"),
    TRY("TRY"),
    TWD("TWD"),
    USD("USD"),
    ZAR("ZAR");

    private String symbol;

    /**
     * This field contains default fiat currency used for conversions. It was, is and will be USD, so you won't need to change it.
     */
    public static final FiatCurrency DEFAULT = FiatCurrency.USD;

    FiatCurrency(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns FiatCurrency with selected symbol. Ignores case.
     * @param symbol 3-letter unique code of fiat currency (<a href="https://en.wikipedia.org/wiki/ISO_4217">in ISO 4217 standard</a>)
     * @return FiatCurrency with given symbol or null, if FiatCurrency with this symbol doesn't exist
     */
    public static FiatCurrency getBySymbol(String symbol) {
        Optional<FiatCurrency> optional = Arrays.stream(FiatCurrency.values()).filter(x -> x.getSymbol().equalsIgnoreCase(symbol)).findFirst();
        return optional.orElse(null);
    }
}

