package com.bidding.dell.BIDJUNCTION.Network;

import org.json.JSONArray;

/**
 * Created by amit on 24/3/17.
 */

public interface FetchDataListenerArray {
    void onFetchComplete(JSONArray data);

    void onFetchFailure(String msg);

    void onFetchStart();
}