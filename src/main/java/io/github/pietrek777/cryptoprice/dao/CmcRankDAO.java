package io.github.pietrek777.cryptoprice.dao;

import io.github.pietrek777.cryptoprice.exception.DataReceivingException;
import io.github.pietrek777.cryptoprice.model.FiatCurrency;
import io.github.pietrek777.cryptoprice.model.Coin;
import io.github.pietrek777.cryptoprice.util.CmcApiDataProvider;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CmcRankDAO extends CmcDAO implements RankDAO {
    private List<Coin> buffer;
    private FiatCurrency convert;

    public List<Coin> getCoinRank(int start, int limit) throws DataReceivingException {
        return getCoinRank(start, limit, FiatCurrency.DEFAULT);
    }

    public List<Coin> getCoinRank(int start, int limit, FiatCurrency convert) throws DataReceivingException {
        this.buffer = new ArrayList<>();
        this.convert = convert;
        JSONArray jsonArray = CmcApiDataProvider.getInstance().getRank(start, limit);
        jsonArray.stream().forEach(this::handleRecord);
        return buffer;
    }

    private <T> void handleRecord(T t) {
        JSONObject jsonObject = (JSONObject) t;
        buffer.add(getCoin(jsonObject, convert));
    }
}
