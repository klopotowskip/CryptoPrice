package com.github.pietrek777.cryptoprice.sample;

import com.github.pietrek777.cryptoprice.dao.CmcRankDAO;
import com.github.pietrek777.cryptoprice.dao.RankDAO;
import com.github.pietrek777.cryptoprice.model.Coin;
import org.junit.Test;

import java.util.List;

public class RankTest {
    @Test
    public void rankTest() throws Exception{
        RankDAO dao = new CmcRankDAO();
        List<Coin> rank = dao.getCoinRank(10, 10);
        rank.forEach(System.out::println);
    }
}
