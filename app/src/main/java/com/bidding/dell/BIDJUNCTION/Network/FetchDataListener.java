package com.bidding.dell.BIDJUNCTION.Network;

import org.json.JSONObject;

/**
 * Created by amit on 24/3/17.
 */

public interface FetchDataListener {
    void onFetchComplete(JSONObject data);

    void onFetchFailure(String msg);

    void onFetchStart();
}