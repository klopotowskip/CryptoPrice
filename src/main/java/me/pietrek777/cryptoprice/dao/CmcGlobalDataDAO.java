package me.pietrek777.cryptoprice.dao;

import me.pietrek777.cryptoprice.exception.DataReceivingException;
import me.pietrek777.cryptoprice.model.FiatCurrency;
import me.pietrek777.cryptoprice.model.GlobalData;
import me.pietrek777.cryptoprice.util.CmcApiDataProvider;

public class CmcGlobalDataDAO extends CmcDAO implements GlobalDataDAO {
    @Override
    public GlobalData getGlobalData() throws DataReceivingException {
        return getGlobalData(FiatCurrency.DEFAULT);
    }

    @Override
    public GlobalData getGlobalData(FiatCurrency convertToName) throws DataReceivingException {
        return getGlobalData(CmcApiDataProvider.getInstance().getGlobalData(convertToName), convertToName);
    }
}
