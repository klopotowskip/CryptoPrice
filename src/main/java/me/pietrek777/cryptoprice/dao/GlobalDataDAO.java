package me.pietrek777.cryptoprice.dao;

import me.pietrek777.cryptoprice.exception.DataReceivingException;
import me.pietrek777.cryptoprice.model.FiatCurrency;
import me.pietrek777.cryptoprice.model.GlobalData;

public interface GlobalDataDAO {
    /**
     * Gets GlobalData object from the endpoint. Uses default fiat currency for conversions.
     *
     * @return GlobalData object
     * @throws DataReceivingException object when any network exception occur
     */
    GlobalData getGlobalData() throws DataReceivingException;

    /**
     * Gets GlobalData object from the endpoint. Uses specified fiat currency for conversions.
     *
     * @param convertToName fiat currency used for conversions
     * @return GlobalData objects (with conversions)
     * @throws DataReceivingException object when any network exception occur
     */
    GlobalData getGlobalData(FiatCurrency convertToName) throws DataReceivingException;
}
