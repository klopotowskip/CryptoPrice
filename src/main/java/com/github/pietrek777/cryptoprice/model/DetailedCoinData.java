package com.github.pietrek777.cryptoprice.model;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Wraps {@link Coin} object. Contains more detailed info about a cryptocurrency such as . Contains fields for price in default fiat, and for converted fiat price (both are equal to each other if there is no conversion specified).
 *
 * Every setter of this class returns current object, so it can be easily chained (see <a>https://en.wikipedia.org/wiki/Method_chaining#Java</a>)
 */
public class DetailedCoinData {
    private Coin coin;
    private BigDecimal volume24hUsd;
    private BigDecimal marketCapUsd;
    private BigDecimal availableSupply;
    private BigDecimal totalSupply;
    @Nullable private BigDecimal maxSupply;
    private double percentChange1h;
    private double percentChange7d;
    private Instant lastUpdated;

    private FiatPrice volume24hConverted;
    private FiatPrice marketCapConverted;

    public Coin getCoin() {
        return coin;
    }

    public DetailedCoinData setCoin(Coin coin) {
        this.coin = coin;
        return this;
    }

    public BigDecimal getVolume24hUsd() {
        return volume24hUsd;
    }

    public DetailedCoinData setVolume24hUsd(double volume24hUsd) {
        this.volume24hUsd = BigDecimal.valueOf(volume24hUsd);
        return this;
    }

    public DetailedCoinData setVolume24hUsd(BigDecimal volume24hUsd) {
        this.volume24hUsd = volume24hUsd;
        return this;
    }

    public BigDecimal getMarketCapUsd() {
        return marketCapUsd;
    }

    public DetailedCoinData setMarketCapUsd(double marketCapUsd) {
        this.marketCapUsd = BigDecimal.valueOf(marketCapUsd);
        return this;
    }

    public DetailedCoinData setMarketCapUsd(BigDecimal marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
        return this;
    }

    public BigDecimal getAvailableSupply() {
        return availableSupply;
    }

    public DetailedCoinData setAvailableSupply(double availableSupply) {
        this.availableSupply = BigDecimal.valueOf(availableSupply);
        return this;
    }

    public DetailedCoinData setAvailableSupply(BigDecimal availableSupply) {
        this.availableSupply = availableSupply;
        return this;
    }

    public BigDecimal getTotalSupply() {
        return totalSupply;
    }

    public DetailedCoinData setTotalSupply(double totalSupply) {
        this.totalSupply = BigDecimal.valueOf(totalSupply);
        return this;
    }

    public DetailedCoinData setTotalSupply(BigDecimal totalSupply) {
        this.totalSupply = totalSupply;
        return this;
    }

    /**
     *
     * @return maximum supply of cryptocurrency or null, if there is no maximum supply
     */
    @Nullable
    public BigDecimal getMaxSupply() {
        return maxSupply;
    }

    /**
     * Returns maximum supply of a particular coin. If there is no maximum supply, pass {@link java.lang.Double#POSITIVE_INFINITY} as a parameter.
     *
     * @param maxSupply maximum supply
     * @return this object
     */
    public DetailedCoinData setMaxSupply(double maxSupply) {
        if(maxSupply==Double.POSITIVE_INFINITY) this.maxSupply = null;
        else this.maxSupply = BigDecimal.valueOf(maxSupply);
        return this;
    }

    public double getPercentChange1h() {
        return percentChange1h;
    }

    public DetailedCoinData setPercentChange1h(double percentChange1h) {
        this.percentChange1h = percentChange1h;
        return this;
    }

    public double getPercentChange7d() {
        return percentChange7d;
    }

    public DetailedCoinData setPercentChange7d(double percentChange7d) {
        this.percentChange7d = percentChange7d;
        return this;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public DetailedCoinData setLastUpdated(long lastUpdated) {
        this.lastUpdated = Instant.ofEpochSecond(lastUpdated);
        return this;
    }

    public FiatPrice getVolume24hConverted() {
        return volume24hConverted;
    }

    public DetailedCoinData setVolume24hConverted(FiatPrice volume24hConverted) {
        this.volume24hConverted = volume24hConverted;
        return this;
    }

    /**
     * @return market cap in a fiat currency used for conversion (in this object)
     */
    public FiatPrice getMarketCapConverted() {
        return marketCapConverted;
    }

    public DetailedCoinData setMarketCapConverted(FiatPrice marketCapConverted) {
        this.marketCapConverted = marketCapConverted;
        return this;
    }
}
