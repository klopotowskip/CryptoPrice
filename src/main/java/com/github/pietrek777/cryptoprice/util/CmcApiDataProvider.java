package com.github.pietrek777.cryptoprice.util;

import com.github.pietrek777.cryptoprice.exception.DataReceivingException;
import com.github.pietrek777.cryptoprice.model.FiatCurrency;
import com.github.pietrek777.cryptoprice.exception.NoSuchCoinException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Implementation of {@link ApiDataProvider} for CoinMarketCap API.
 * Use it, if you want pure JSON objects from API. Unless use classes from {@link me.pietrek777.cryptoprice.dao} package
 *
 * @author pietrek777
 *
 */
public class CmcApiDataProvider implements ApiDataProvider{
    private static CmcApiDataProvider instance;
    public static CmcApiDataProvider getInstance() {
        if (instance == null) {
            instance = new CmcApiDataProvider();
        }
        return instance;
    }
    private static final String BASE_URL = "https://api.coinmarketcap.com/v1/";

    private static final String TICKER_URL = BASE_URL + "ticker/";
    private static final String GLOBAL_DATA_URL = BASE_URL + "global/";

    public JSONObject getCoin(String name, FiatCurrency fiatCurrency) throws NoSuchCoinException, DataReceivingException {
        String response = getResponse(TICKER_URL +name+"/?convert=" + fiatCurrency.getSymbol());
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray;
        try{
            jsonArray = (JSONArray) jsonParser.parse(response);
        } catch(Exception e){
            throw new DataReceivingException();
        }
        return (JSONObject) jsonArray.get(0);
    }
    public JSONObject getGlobalData(FiatCurrency convertTo) throws DataReceivingException{
        String response;
        //If NoSuchCoinException is throw, it means, that we got 404 HTTP Response Code, but it's not user's fault
        try{
            response = getResponse(GLOBAL_DATA_URL + "/?convert=" + convertTo.getSymbol());
        } catch (NoSuchCoinException e){
            throw new DataReceivingException();
        }
        JSONParser jsonParser = new JSONParser();
        try{
            return (JSONObject) jsonParser.parse(response);
        } catch (Exception e){
            throw new DataReceivingException();
        }
    }
    public JSONArray getRank(int start, int limit) throws DataReceivingException{
        return getRank(start, limit, FiatCurrency.DEFAULT);

    }
    public JSONArray getRank(int start, int limit, FiatCurrency convertToName) throws DataReceivingException{
        String response;

        //If NoSuchCoinException is throw, it means, that we got 404 HTTP Response Code, but it's not user's fault
        try{
            response = getResponse(TICKER_URL +"?convert=" + convertToName.getSymbol() + "start="+start+"&limit="+limit);
        } catch(NoSuchCoinException e){
            throw new DataReceivingException();
        }

        JSONParser jsonParser = new JSONParser();
        JSONArray array;
        try{
            array = (JSONArray) jsonParser.parse(response);
        } catch(ParseException e){
            throw new DataReceivingException();
        }
        return array;

    }
}
