package io.github.pietrek777.cryptoprice.sample;

import io.github.pietrek777.cryptoprice.dao.CmcPriceDAO;
import io.github.pietrek777.cryptoprice.dao.PriceDAO;
import io.github.pietrek777.cryptoprice.model.DetailedCoinData;
import io.github.pietrek777.cryptoprice.model.FiatCurrency;
import io.github.pietrek777.cryptoprice.model.Coin;
import org.junit.Test;

public class SimpleGetPriceTest {
    @Test
    public void getPriceTest() throws Exception{
        PriceDAO priceDAO = new CmcPriceDAO();
        DetailedCoinData data = priceDAO.getPrice("bitcoin");
        Coin coin = data.getCoin();
        System.out.println("Id: " + coin.getId());
        System.out.println("Name: " + coin.getName());
        System.out.println("Symbol: " + coin.getSymbol());
        System.out.println("Rank: " + coin.getRank());
        System.out.println("Fiat price: " + coin.getFiatPrice().getPrice() + coin.getFiatPrice().getFiatCurrency().getSymbol());
        System.out.println("Btc price: " + coin.getBtcPrice().toPlainString()+"BTC");
        System.out.println("Percent change 24h: " + coin.getPercentChange24h() + "%");

        System.out.println("Volume 24h: " + data.getVolume24hUsd().toPlainString());
        System.out.println("Market cap in USD: " + data.getMarketCapUsd().toPlainString());
        System.out.println("Available supply: " + data.getAvailableSupply().toPlainString());
        System.out.println("Total supply: " + data.getTotalSupply().toPlainString());
        System.out.println("Max supply " + data.getMaxSupply().toPlainString());
        System.out.println("Change 1h: " + data.getPercentChange1h() + "%");
        System.out.println("Change 7d: " + data.getPercentChange1h() + "%");
        System.out.println("Last updated: " + data.getLastUpdated().toString());
    }

    @Test
    public void convertedGetPriceTest() throws Exception{
        PriceDAO priceDAO = new CmcPriceDAO();
        DetailedCoinData data = priceDAO.getPrice("ethereum", FiatCurrency.PLN);
        Coin coin = data.getCoin();
        System.out.println("Id: " + coin.getId());
        System.out.println("Name: " + coin.getName());
        System.out.println("Symbol: " + coin.getSymbol());
        System.out.println("Rank: " + coin.getRank());
        System.out.println("USD price: " + coin.getFiatPrice().getPrice() + coin.getFiatPrice().getFiatCurrency().getSymbol());
        System.out.println("Btc price: " + coin.getBtcPrice().toPlainString()+"BTC");
        System.out.println("Percent change 24h: " + coin.getPercentChange24h() + "%");
        System.out.println("Price converted: " + coin.getConvertedFiatPrice().getPrice() + coin.getConvertedFiatPrice().getFiatCurrency().getSymbol());

        System.out.println("Volume 24h: " + data.getVolume24hUsd().toPlainString());
        System.out.println("Market cap in USD: " + data.getMarketCapUsd().toPlainString());
        System.out.println("Available supply: " + data.getAvailableSupply().toPlainString());
        System.out.println("Total supply: " + data.getTotalSupply().toPlainString());
        if(data.getMaxSupply()!=null) System.out.println("Max supply " + data.getMaxSupply().toPlainString());
        else System.out.println("This coin doesn't have it's maximum supply");
        System.out.println("Change 1h: " + data.getPercentChange1h() + "%");
        System.out.println("Change 7d: " + data.getPercentChange1h() + "%");
        System.out.println("Last updated: " + data.getLastUpdated().toString());
        System.out.println("24h volume converted: " + data.getVolume24hConverted().getPrice().toPlainString() + " " +  data.getVolume24hConverted().getFiatCurrency().getSymbol());
        System.out.println("Market cap converted: " + data.getMarketCapConverted().getPrice().toPlainString() + " " +data.getMarketCapConverted().getFiatCurrency().getSymbol());
    }
}
