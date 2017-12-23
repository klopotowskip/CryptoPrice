package me.pietrek777.cryptoprice.dao;

import me.pietrek777.cryptoprice.exception.NoSuchCoinException;
import me.pietrek777.cryptoprice.model.DetailedCoinData;
import me.pietrek777.cryptoprice.model.FiatCurrency;

import me.pietrek777.cryptoprice.util.CmcApiDataProvider;

import me.pietrek777.cryptoprice.exception.DataReceivingException;

/**
 * PriceDAO implementation for CoinMarketCap API
 *
 * @author pietrek777
 *
 * @see me.pietrek777.cryptoprice.dao.PriceDAO
 *
 */
public class CmcPriceDAO extends CmcDAO implements PriceDAO{
    @Override
    public DetailedCoinData getPrice(String coinId) throws DataReceivingException, NoSuchCoinException{
        return getPrice(coinId, FiatCurrency.DEFAULT);
    }
    @Override
    public DetailedCoinData getPrice(String coinId, FiatCurrency convert) throws DataReceivingException, NoSuchCoinException{
        return getDetailedCoinData(CmcApiDataProvider.getInstance().getCoin(coinId, convert), convert);
    }
}
