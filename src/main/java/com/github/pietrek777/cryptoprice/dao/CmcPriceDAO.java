package com.github.pietrek777.cryptoprice.dao;

import com.github.pietrek777.cryptoprice.exception.DataReceivingException;
import com.github.pietrek777.cryptoprice.model.DetailedCoinData;
import com.github.pietrek777.cryptoprice.model.FiatCurrency;
import com.github.pietrek777.cryptoprice.util.CmcApiDataProvider;
import com.github.pietrek777.cryptoprice.exception.NoSuchCoinException;

/**
 * PriceDAO implementation for CoinMarketCap API
 *
 * @author pietrek777
 *
 * @see PriceDAO
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
