package me.pietrek777.cryptoprice.sample;

import me.pietrek777.cryptoprice.dao.CmcGlobalDataDAO;
import me.pietrek777.cryptoprice.dao.GlobalDataDAO;
import me.pietrek777.cryptoprice.exception.DataReceivingException;
import me.pietrek777.cryptoprice.exception.NoSuchCoinException;
import me.pietrek777.cryptoprice.model.FiatCurrency;
import me.pietrek777.cryptoprice.model.GlobalData;
import org.junit.Test;

public class GlobalDataTest {
    @Test
    public void globalDataTest() throws DataReceivingException{
        GlobalDataDAO dao = new CmcGlobalDataDAO();
        GlobalData data = dao.getGlobalData();

        System.out.println("Market cap (in USD): " + data.getMarketCap().toPlainString());
        System.out.println("24h volume (in USD): " + data.getVolume24h().toPlainString());
        System.out.println("Bitcoin dominance: " + data.getBtcPercentage() + "%");
        System.out.println("Active currencies: " + data.getActiveCurrencies());
        System.out.println("Active assets: " + data.getActiveAssets());
        System.out.println("Active markets: " + data.getActiveMarkets());
    }
    @Test
    public void convertedGlobalDataTest() throws DataReceivingException, NoSuchCoinException{
        GlobalDataDAO dao = new CmcGlobalDataDAO();
        GlobalData data = dao.getGlobalData(FiatCurrency.EUR);

        System.out.println("Market cap (in USD): " + data.getMarketCap());
        System.out.println("24h volume (in USD): " + data.getVolume24h());
        System.out.println("Bitcoin dominance: " + data.getBtcPercentage() + "%");
        System.out.println("Active currencies: " + data.getActiveCurrencies());
        System.out.println("Active assets: " + data.getActiveAssets());
        System.out.println("Active markets: " + data.getActiveMarkets());

        System.out.println("Market cap converted: " + data.getMarketCapConverted());
        System.out.println("24h volume converted: " + data.getVolume24hConverted());
    }
}
