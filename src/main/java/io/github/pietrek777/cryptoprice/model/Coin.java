package io.github.pietrek777.cryptoprice.model;

import java.math.BigDecimal;

/**
 * Represents a simple cryptocurrency. Contains basic info about it, such as name, symbol, place in the raking or price. Contains fields for price in default fiat, and for converted fiat price (both are equal to each other if there is no conversion specified).
 *
 * Every setter of this class returns current object, so it can be easily chained (see <a>https://en.wikipedia.org/wiki/Method_chaining#Java</a>)
 */
public class Coin {
    private String id;
    private String name;
    private String symbol;
    private int rank;
    private FiatPrice fiatPrice;
    private BigDecimal btcPrice;
    private double percentChange24h;

    private FiatPrice convertedFiatPrice;

    public String getId() {
        return id;
    }

    public Coin setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Coin setName(String name) {
        this.name = name;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public Coin setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public int getRank() {
        return rank;
    }

    public Coin setRank(int rank) {
        this.rank = rank;
        return this;
    }

    /**
     * @return price in a default fiat currency (USD)
     */
    public FiatPrice getFiatPrice() {
        return fiatPrice;
    }

    public Coin setFiatPrice(FiatPrice fiatPrice) {
        this.fiatPrice = fiatPrice;
        return this;
    }

    /**
     * @return price in Bitcoin
     */
    public BigDecimal getBtcPrice() {
        return btcPrice;
    }

    public Coin setBtcPrice(double btcPrice) {
        this.btcPrice = BigDecimal.valueOf(btcPrice);
        return this;
    }
    public Coin setBtcPrice(BigDecimal btcPrice) {
        this.btcPrice = btcPrice;
        return this;
    }

    public double getPercentChange24h() {
        return percentChange24h;
    }

    public Coin setPercentChange24h(double percentChange24h) {
        this.percentChange24h = percentChange24h;
        return this;
    }

    /**
     * @return price in a fiat currency used for conversion (in this object)
     */
    public FiatPrice getConvertedFiatPrice() {
        return convertedFiatPrice;
    }

    public Coin setConvertedFiatPrice(FiatPrice convertedFiatPrice) {
        this.convertedFiatPrice = convertedFiatPrice;
        return this;
    }

    /**
     * Returns readable info about the object
     * @return string with info
     */
    @Override
    public String toString() {
        return getRank() + ". " + getName()
                + " – price: " + getConvertedFiatPrice()
                + ", BTC price: " + getBtcPrice()
                + ", 24h change: " + getPercentChange24h();
    }
}
