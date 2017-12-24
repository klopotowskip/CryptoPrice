package io.github.pietrek777.cryptoprice.dao;

import io.github.pietrek777.cryptoprice.exception.DataReceivingException;
import io.github.pietrek777.cryptoprice.model.Coin;
import io.github.pietrek777.cryptoprice.model.FiatCurrency;
import io.github.pietrek777.cryptoprice.util.ApiDataProvider;

import java.util.List;

/**
 * Represents generic DAO capable of receiving data about the coin/token ranking. This ranking is represented as java.util.List of Coin objects
 *
 * <b>Classes implementing this interface should only create only a List of Coin objects from JSONObject. ApiDataProvider is responsible for receiving JSON data from endpoint.</b>
 *
 * @see Coin
 * @see ApiDataProvider
 *
 *
 * @author pietrek777
 *
 */
public interface RankDAO {
    /**
     * Returns ranking of coins as a <b>java.util.List</b>
     * @param start starting index (0 is first index)
     * @param limit limiter
     * @return ranking of coins as a list
     * @throws DataReceivingException when something's wrong with receiving data from server
     */
    List<Coin> getCoinRank(int start, int limit) throws DataReceivingException;
    /**
     * Returns ranking of converted coins as a <b>java.util.List</b>
     * @param start starting index (0 is first index)
     * @param limit limiter
     * @param convert fiat currency for conversions
     *
     * @return ranking of coins as a list
     * @throws DataReceivingException when something's wrong with receiving data from server
     */
    List<Coin> getCoinRank(int start, int limit, FiatCurrency convert) throws DataReceivingException;
}
