package me.pietrek777.cryptoprice.sample;

import me.pietrek777.cryptoprice.exception.DataReceivingException;
import me.pietrek777.cryptoprice.exception.NoSuchCoinException;
import me.pietrek777.cryptoprice.model.CryptoPrice;
import me.pietrek777.cryptoprice.model.FiatCurrency;
import me.pietrek777.cryptoprice.model.FiatPrice;
import org.junit.Test;

public class ConversionsTest {
    @Test
    public void conversionTest() throws Exception{
        CryptoPrice price = new CryptoPrice(100, "bitcoin");
        System.out.println("100 bitcoin is:");
        System.out.println(price.convert("ethereum"));
        System.out.println(price.convert("litecoin"));
        System.out.println(price.convert("bitcoin-cash"));
        System.out.println(price.convert("lisk"));
    }
    @Test
    public void toFiatConversionTest() throws DataReceivingException, NoSuchCoinException{
        CryptoPrice price = new CryptoPrice(1600, "verge");
        System.out.println("1600 verge is:");
        System.out.println(price.convertToFiat(FiatCurrency.EUR));
        System.out.println(price.convertToFiat(FiatCurrency.USD));
        System.out.println(price.convertToFiat(FiatCurrency.PLN));
    }
    @Test
    public void fiatToCryptoConversionTest() throws Exception{
        FiatPrice price = new FiatPrice(2137, FiatCurrency.PLN);
        System.out.println("2137 PLN is:");
        System.out.println(price.convert("bitcoin"));
        System.out.println(price.convert("ethereum"));
    }
}
