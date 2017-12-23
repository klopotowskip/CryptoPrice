package me.pietrek777.cryptoprice.dao;

import me.pietrek777.cryptoprice.exception.NoSuchCoinException;
import me.pietrek777.cryptoprice.model.DetailedCoinData;
import me.pietrek777.cryptoprice.model.FiatCurrency;

import me.pietrek777.cryptoprice.exception.DataReceivingException;

/**
 * Represents generic DAO capable of receiving data about price of a single coin/token as a DetailedCoinData object.
 *
 * <b>Classes implementing this interface should only create only a DetailedCoinData object from JSONObject. ApiDataProvider is responsible for receiving JSON data from endpoint.</b>
 *
 * @see me.pietrek777.cryptoprice.model.DetailedCoinData
 * @see me.pietrek777.cryptoprice.util.ApiDataProvider
 *
 * @author pietrek777
 *
 */
public interface PriceDAO{
    /**
     * Gets DetailedCoinData object from the endpoint. Uses default fiat currency for conversions.
     *
     * @param coinId the name of a specific coin/token
     *
     * @return DetailedCoinData object
     *
     * @throws DataReceivingException when any network exception occur.
     * @throws NoSuchCoinException when endpoint returns HTTP 404 response code
     */
    DetailedCoinData getPrice(String coinId) throws DataReceivingException, NoSuchCoinException;

    /**
     * Gets DetailedCoinData object from the endpoint. Uses specified fiat currency for conversions.
     *
     * @param coinId the name of a specific coin/token
     * @param convert fiat currency used for conversions
     *
     * @return DetailedCoinData object
     *
     * @throws DataReceivingException when any network exception occur
     * @throws NoSuchCoinException when endpoint returns HTTP 404 response code
     */
    DetailedCoinData getPrice(String coinId, FiatCurrency convert) throws DataReceivingException, NoSuchCoinException;
}
