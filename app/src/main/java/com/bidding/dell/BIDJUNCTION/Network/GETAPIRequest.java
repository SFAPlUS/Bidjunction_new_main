package com.bidding.dell.BIDJUNCTION.Network;

import android.content.Context;
import android.util.Log;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class GETAPIRequest {
    public void request(final Context context, final com.bidding.dell.BIDJUNCTION.Network.FetchDataListener listener, final String ApiURL) throws JSONException {
        if (listener != null) {
            //call onFetchComplete of the listener
            //to show progress dialog
            //-indicates calling started
            listener.onFetchStart();
        }
        //base server URL
//            String baseUrl="http://studypeek.com/test/";
        //add extension api url received from caller
        //and make full api
        String url = ApiURL;
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (listener != null) {

                                    //received response
                                    //call onFetchComplete of the listener
                                    listener.onFetchComplete(response);

                            }
                            else
                            {
                                listener.onFetchComplete(null);
                            }
                        } catch ( Exception e) {
                            listener.onFetchComplete(null);
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NoConnectionError) {
                    listener.onFetchFailure("Network Connectivity Problem");
                } else if (error.networkResponse != null && error.networkResponse.data != null) {
                    VolleyError volley_error = new VolleyError(new String(error.networkResponse.data));
                    String errorMessage = "";
                    try {
                        JSONObject errorJson = new JSONObject(volley_error.getMessage().toString());
                        if (errorJson.has("error")) errorMessage = errorJson.getString("error");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (errorMessage.isEmpty()) {
                        errorMessage = volley_error.getMessage();
                    }

                    if (listener != null) listener.onFetchFailure(errorMessage);
                } else {
                    listener.onFetchFailure("Something went wrong. Please try again later");
                }

            }
        });

        RequestQueueService.getInstance(context).addToRequestQueue(postRequest.setShouldCache(false));
    }

    public void arrayrequest(final Context context, final com.bidding.dell.BIDJUNCTION.Network.FetchDataListener listener, final String ApiURL) throws JSONException {
        if (listener != null) {
            //call onFetchComplete of the listener
            //to show progress dialog
            //-indicates calling started
            listener.onFetchStart();
        }
        //base server URL
//            String baseUrl="http://studypeek.com/test/";
        //add extension api url received from caller
        //and make full api
        String url = ApiURL;

        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            if (listener != null) {
                                listener.onFetchComplete(response.getJSONObject(0));
                                //listener.onFetchComplete(response.toJSONObject("test"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.d("return", "" + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof NoConnectionError) {
                            listener.onFetchFailure("Network Connectivity Problem");
                        } else if (error.networkResponse != null && error.networkResponse.data != null) {
                            VolleyError volley_error = new VolleyError(new String(error.networkResponse.data));
                            String errorMessage = "";
                            try {
                                JSONObject errorJson = new JSONObject(volley_error.getMessage().toString());
                                if (errorJson.has("error"))
                                    errorMessage = errorJson.getString("error");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            if (errorMessage.isEmpty()) {
                                errorMessage = volley_error.getMessage();
                            }

                            if (listener != null) listener.onFetchFailure(errorMessage);
                        } else {
                            listener.onFetchFailure("Something went wrong. Please try again later");
                        }

                    }
                });
        RequestQueueService.getInstance(context).addToRequestQueue(jsonArrayRequest.setShouldCache(false));
    }
}
