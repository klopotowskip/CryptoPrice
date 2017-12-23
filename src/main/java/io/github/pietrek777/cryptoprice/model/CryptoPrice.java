package com.github.pietrek777.cryptoprice.model;

import com.github.pietrek777.cryptoprice.dao.CmcPriceDAO;
import com.github.pietrek777.cryptoprice.dao.PriceDAO;
import com.github.pietrek777.cryptoprice.exception.DataReceivingException;
import com.github.pietrek777.cryptoprice.exception.NoSuchCoinException;

import java.math.BigDecimal;

/**
 * Represents the defined amount of some crypto currency
 * This class also allows CryptoPrice objects to be converted to FiatPrice objects using {@link CryptoPrice#convert(String)} method, but converting to CryptoPrice objects of another cryptocurrencies is also available with {@link CryptoPrice#convertToFiat(FiatCurrency)} method.
 *
 * @author pietrek777
 *
 * @see CryptoPrice
 *
 */
public class CryptoPrice {
    private BigDecimal amount;
    private String name;

    public BigDecimal getAmount() {
        return amount;
    }

    public CryptoPrice setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public CryptoPrice setPrice(double amount) {
        this.amount = BigDecimal.valueOf(amount);
        return this;
    }

    public String getName() {
        return name;
    }

    public CryptoPrice setName(String name) {
        this.name = name.toUpperCase();
        return this;
    }

    public CryptoPrice(BigDecimal amount, String symbol) {
        this.amount = amount;
        this.name = symbol;
    }

    public CryptoPrice(double amount, String symbol) {
        this.amount = BigDecimal.valueOf(amount);
        this.name = symbol;
    }

    @Override
    public String toString() {
        return getAmount().toPlainString() + " " + getName();
    }

    public CryptoPrice convert(String convertToSymbol) throws NoSuchCoinException, DataReceivingException {
        PriceDAO priceDAO = new CmcPriceDAO();
        BigDecimal firstUsdPrice = getData().getCoin().getFiatPrice().getPrice().multiply(amount);
        BigDecimal secondUsdPrice = priceDAO.getPrice(convertToSymbol).getCoin().getFiatPrice().getPrice();
        BigDecimal result = firstUsdPrice.divide(secondUsdPrice, 7, BigDecimal.ROUND_HALF_UP);
        return new CryptoPrice(result, convertToSymbol);
    }
    public FiatPrice convertToFiat(FiatCurrency fiatCurrency) throws DataReceivingException, NoSuchCoinException{
        PriceDAO priceDAO = new CmcPriceDAO();
        Coin data = priceDAO.getPrice(getName(), fiatCurrency).getCoin();
        BigDecimal priceInFiat = data.getConvertedFiatPrice().getPrice();
        return new FiatPrice(priceInFiat.multiply(getAmount()), fiatCurrency);
    }

    private DetailedCoinData getData() throws DataReceivingException, NoSuchCoinException{
        PriceDAO priceDAO = new CmcPriceDAO();
        return priceDAO.getPrice(getName());
    }

}
