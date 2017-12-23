package me.pietrek777.cryptoprice.model;

import me.pietrek777.cryptoprice.dao.CmcPriceDAO;
import me.pietrek777.cryptoprice.dao.PriceDAO;
import me.pietrek777.cryptoprice.exception.DataReceivingException;
import me.pietrek777.cryptoprice.exception.NoSuchCoinException;

import java.math.BigDecimal;

/**
 * Represents the defined amount of some fiat currency
 * This class also allows FiatPrice objects to be converted to CryptoPrice objects using {@link me.pietrek777.cryptoprice.model.FiatPrice#convert(String)} method
 *
 * @author pietrek777
 *
 * @see me.pietrek777.cryptoprice.model.CryptoPrice
 *
 */
public class FiatPrice {
    private BigDecimal price;
    private FiatCurrency fiatCurrency;

    public BigDecimal getPrice() {
        return price;
    }

    public FiatPrice setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public FiatCurrency getFiatCurrency() {
        return fiatCurrency;
    }

    public FiatPrice setFiatCurrency(FiatCurrency fiatCurrency) {
        this.fiatCurrency = fiatCurrency;
        return this;
    }

    public FiatPrice(BigDecimal price, FiatCurrency fiatCurrency) {
        this.price = price;
        this.fiatCurrency = fiatCurrency;
    }

    public FiatPrice(double price, FiatCurrency fiatCurrency) {
        this.price = BigDecimal.valueOf(price);
        this.fiatCurrency = fiatCurrency;
    }
    public FiatPrice(){}

    /**
     * Converts FiatPrice object to CryptoPrice object of selected cryptocurrency. Uses prices from CoinMarketCap API for conversions (however using another APIs will be allowed in the future)
     *
     * @param convertToName name of cryptocurrency, which you want to convertToFiat to
     *
     * @return equivalent CryptoPrice object
     *
     * @throws DataReceivingException if any problem occurred during receiving data from API
     * @throws NoSuchCoinException if cryptocurrency with specified name doesn't exist
     */
    public CryptoPrice convert(String convertToName) throws DataReceivingException, NoSuchCoinException{
        PriceDAO priceDAO = new CmcPriceDAO();
        BigDecimal fiatPrice = priceDAO.getPrice(convertToName, fiatCurrency).getCoin().getConvertedFiatPrice().getPrice();
        BigDecimal result = price.divide(fiatPrice, 7, BigDecimal.ROUND_HALF_UP);
        return new CryptoPrice(result, convertToName);
    }

    /**
     * Returns info about the object in friendly form
     *
     * @return string with info
     */
    @Override
    public String toString() {
        return getPrice().toPlainString() + " " + getFiatCurrency().getSymbol();
    }
}
