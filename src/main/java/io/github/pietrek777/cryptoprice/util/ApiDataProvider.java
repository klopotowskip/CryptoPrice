package io.github.pietrek777.cryptoprice.util;

import io.github.pietrek777.cryptoprice.exception.DataReceivingException;
import io.github.pietrek777.cryptoprice.exception.NoSuchCoinException;
import io.github.pietrek777.cryptoprice.model.FiatCurrency;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Provides data about cryptocurrencies from API
 *
 * @author Piotr
 */
public interface ApiDataProvider {

    /**
     * Returns JSON object with data of a single cryptocurrency
     *
     * @param name name of cryptocurrency
     * @param fiatCurrency fiat currency for conversions
     *
     * @return JSONObject with data
     *
     * @throws NoSuchCoinException if specified coin doesn't exists
     * @throws DataReceivingException if when something's wrong with receiving data from server
     */
    JSONObject getCoin(String name, FiatCurrency fiatCurrency) throws NoSuchCoinException, DataReceivingException;

    /**
     * Returns JSONArray with part of coins ranking
     *
     * @param start starting index (where 0 is first index)
     * @param limit maximum size of array
     *
     * @return JSONArray with part of coins ranking
     *
     * @throws NoSuchCoinException if specified coin doesn't exists
     * @throws DataReceivingException if when something's wrong with receiving data from server
     */
    JSONArray getRank(int start, int limit) throws NoSuchCoinException, DataReceivingException;

    /**
     * Returns JSONArray with part of coins ranking
     *
     * @param start starting index (where 0 is first index)
     * @param limit maximum size of array
     * @param convertToName name of cryptocurrency, which you want to convertToFiat to
     *
     * @return JSONArray with part of coins ranking
     *
     * @throws NoSuchCoinException if specified coin doesn't exists
     * @throws DataReceivingException if when something's wrong with receiving data from server
     */
    JSONArray getRank(int start, int limit, FiatCurrency convertToName) throws NoSuchCoinException, DataReceivingException;


    /**
     * Returns JSONObject with global data
     *
     * @param convertToName fiat currency for conversions
     * @return JSONObject with global data
     * @throws DataReceivingException when something's wrong with receiving data from server
     */
    JSONObject getGlobalData(FiatCurrency convertToName) throws DataReceivingException;

    /**
     * Establishes connection with specified URL, then returns it
     *
     * @param url String with URL
     *
     * @return JSONArray with part of coins ranking
     *
     * @throws Exception if any exception occurs (really?)
     */
    default HttpURLConnection getConnection(String url) throws Exception{
        URL urlObj = new URL(url);
        HttpURLConnection connection =  (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        return connection;
    }

    default String readFromStream(InputStream stream) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    /**
     * Returns response of a server as a string
     * @param url String with URL
     *
     * @return String with response
     *
     * @throws DataReceivingException when something's wrong with receiving data from server (also if HTTP response code is not 200)
     * @throws NoSuchCoinException if specified coin doesn't exists
     */
    default String getResponse(String url) throws DataReceivingException, NoSuchCoinException {
        HttpURLConnection connection;
        boolean found = true;
        try {
            connection = getConnection(url);
            if (connection.getResponseCode() == 404) {
                found = false;
                throw new Exception();
            }
            if (connection.getResponseCode() != 200) throw new DataReceivingException();
            String result =  readFromStream(connection.getInputStream());
            connection.disconnect();
            return result;

        } catch (Exception e) {
            if(!found) throw new NoSuchCoinException();
            else throw new DataReceivingException();
        }


    }
}
