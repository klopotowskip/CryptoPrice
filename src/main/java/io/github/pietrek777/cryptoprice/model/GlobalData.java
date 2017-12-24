package io.github.pietrek777.cryptoprice.model;

import java.math.BigDecimal;

public class GlobalData {
    private BigDecimal marketCap;
    private BigDecimal volume24h;
    private double btcPercentage;
    private long activeCurrencies;
    private long activeAssets;
    private long activeMarkets;

    private FiatPrice marketCapConverted;
    private FiatPrice volume24hConverted;

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public GlobalData setMarketCap(double marketCap) {
        this.marketCap = BigDecimal.valueOf(marketCap);
        return this;
    }
    public GlobalData setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
        return this;
    }

    public BigDecimal getVolume24h() {
        return volume24h;
    }

    public GlobalData setVolume24h(double volume24h) {
        this.volume24h = BigDecimal.valueOf(volume24h);
        return this;
    }

    public GlobalData setVolume24h(BigDecimal volume24h) {
        this.volume24h = volume24h;
        return this;
    }


    public double getBtcPercentage() {
        return btcPercentage;
    }

    public GlobalData setBtcPercentage(double btcPercentage) {
        this.btcPercentage = btcPercentage;
        return this;
    }


    public long getActiveCurrencies() {
        return activeCurrencies;
    }

    public GlobalData setActiveCurrencies(long activeCurrencies) {
        this.activeCurrencies = activeCurrencies;
        return this;
    }

    public long getActiveAssets() {
        return activeAssets;
    }

    public GlobalData setActiveAssets(long activeAssets) {
        this.activeAssets = activeAssets;
        return this;
    }

    public long getActiveMarkets() {
        return activeMarkets;
    }

    public GlobalData setActiveMarkets(long activeMarkets) {
        this.activeMarkets = activeMarkets;
        return this;
    }

    public FiatPrice getMarketCapConverted() {
        return marketCapConverted;
    }

    public GlobalData setMarketCapConverted(FiatPrice marketCapConverted) {
        this.marketCapConverted = marketCapConverted;
        return this;
    }

    public FiatPrice getVolume24hConverted() {
        return volume24hConverted;
    }

    public GlobalData setVolume24hConverted(FiatPrice volume24hConverted) {
        this.volume24hConverted = volume24hConverted;
        return this;
    }

    public GlobalData(){}

    public GlobalData(BigDecimal marketCap, BigDecimal volume24h, double btcPercentage, int activeCurrencies, int activeMarkets) {
        this.marketCap = marketCap;
        this.volume24h = volume24h;
        this.btcPercentage = btcPercentage;
        this.activeCurrencies = activeCurrencies;
        this.activeMarkets = activeMarkets;
    }
}
