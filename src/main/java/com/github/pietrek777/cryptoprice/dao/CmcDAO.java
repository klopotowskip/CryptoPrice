package com.github.pietrek777.cryptoprice.dao;

import com.github.pietrek777.cryptoprice.model.*;
import me.pietrek777.cryptoprice.model.*;
import org.json.simple.JSONObject;

/**
 *
 * Generic class for CoinMarketCap (CMC) DAO.
 *
 * @author pietrek777
 *
 */
public abstract class CmcDAO {
    private JSONObject jsonObject;
    private FiatCurrency convert;

    /**
     * Converts JSON object to Coin POJO
     *
     * @see Coin
     *
     * @param object JSON object received from CMC api
     * @param convert Fiat currency, used for conversions
     * @return Coin object
     */
    Coin getCoin(JSONObject object, FiatCurrency convert){
        this.jsonObject = object;
        this.convert = convert;
        return new Coin()
                .setId(get("id"))
                .setName(get("name"))
                .setSymbol(get("symbol"))
                .setRank(Integer.parseInt((String) jsonObject.get("rank")))
                .setFiatPrice(new FiatPrice(getDouble("price_usd"), FiatCurrency.USD))
                .setBtcPrice(getDouble("price_btc"))
                .setPercentChange24h(getDouble("percent_change_24h"))
                .setConvertedFiatPrice(fiatOf("price"));
    }

    /**
     * Converts JSON object to DetailedCoinData POJO
     *
     * @see DetailedCoinData
     *
     * @param object JSON object received from CMC api
     * @param convert Fiat currency, used for conversions
     * @return DetailedCoinData object
     */
    DetailedCoinData getDetailedCoinData(JSONObject object, FiatCurrency convert){
        this.jsonObject = object;
        this.convert = convert;
        return new DetailedCoinData()
                .setCoin(getCoin(object, convert))
                .setVolume24hUsd(getDouble("24h_volume_usd"))
                .setMarketCapUsd(getDouble("market_cap_usd"))
                .setAvailableSupply(getDouble("available_supply"))
                .setTotalSupply(getDouble("total_supply"))
                .setMaxSupply(getInfinitableDouble("max_supply"))
                .setPercentChange1h(getDouble("percent_change_1h"))
                .setPercentChange7d(getDouble("percent_change_7d"))
                .setLastUpdated(Long.parseLong((String) jsonObject.get("last_updated")))
                .setVolume24hConverted(fiatOf("24h_volume"))
                .setMarketCapConverted(fiatOf("market_cap"));
    }

    GlobalData getGlobalData(JSONObject object, FiatCurrency convert){
        this.jsonObject = object;
        this.convert = convert;
        return new GlobalData()
                .setMarketCap((Double) jsonObject.get("total_market_cap_usd"))
                .setVolume24h((Double) jsonObject.get("total_24h_volume_usd"))
                .setBtcPercentage((Double) jsonObject.get("bitcoin_percentage_of_market_cap"))
                .setActiveCurrencies((Long) jsonObject.get("active_currencies"))
                .setActiveAssets((Long) jsonObject.get("active_assets"))
                .setActiveMarkets((Long) jsonObject.get("active_markets"))
                .setMarketCapConverted(simpleFiatOf("total_market_cap"))
                .setVolume24hConverted(simpleFiatOf("total_24h_volume"));

    }
    private String get(String key){
        return (String) jsonObject.get(key);
    }
    private double getInfinitableDouble(String key){
        if(jsonObject.get(key)==null) return Double.POSITIVE_INFINITY;
        return Double.parseDouble((String) jsonObject.get(key));
    }
    private double getDouble(String key){
        if(jsonObject.get(key)==null) throw new NullPointerException();
        return Double.parseDouble((String) jsonObject.get(key));
    }
    private int getInt(String key){
        return Integer.parseInt((String) jsonObject.get(key));
    }

    private FiatPrice fiatOf(String key){
        double d = getDouble(key+"_"+convert.getSymbol().toLowerCase());
        return new FiatPrice(d, convert);
    }
    private FiatPrice simpleFiatOf(String key){
        double d = (Double) jsonObject.get(key+"_"+convert.getSymbol().toLowerCase());
        return new FiatPrice(d, convert);
    }
}


