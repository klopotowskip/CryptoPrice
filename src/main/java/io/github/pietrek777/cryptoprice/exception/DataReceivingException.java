package com.github.pietrek777.cryptoprice.exception;

/**
 * Thrown, while receiving data from server was impossible for some reason (e.g. no internet connection, API data was non-parsable).
 *
 * <b>When cryptocurrency name is invalid, NoSuchCoin will be throw instead</b>
 *
 * @author pietrek777
 * @see NoSuchCoinException
 * */
public class DataReceivingException extends Exception {
}
