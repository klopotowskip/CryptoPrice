package com.github.pietrek777.cryptoprice.dao;

import com.github.pietrek777.cryptoprice.exception.DataReceivingException;
import com.github.pietrek777.cryptoprice.model.FiatCurrency;
import com.github.pietrek777.cryptoprice.util.CmcApiDataProvider;
import com.github.pietrek777.cryptoprice.model.GlobalData;

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
