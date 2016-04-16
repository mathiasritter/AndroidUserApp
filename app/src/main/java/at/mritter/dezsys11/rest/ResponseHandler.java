package at.mritter.dezsys11.rest;


import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import at.mritter.dezsys11.model.Response;
import cz.msebera.android.httpclient.Header;

/**
 * Handles responses from rest api calls.
 *
 * @author Mathias Ritter
 * @version 1.0
 */
public class ResponseHandler extends TextHttpResponseHandler {

    private Response response;

    public ResponseHandler(Response response) {
        this.response = response;
    }

    /**
     * @see TextHttpResponseHandler#onSuccess(int, Header[], String)
     */
    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString) {
        try {
            JSONObject jsonResponse = new JSONObject(responseString);
            if (jsonResponse.has("status") && jsonResponse.has("message")) {
                response.setStatus(jsonResponse.getInt("status"));
                response.setMessage(jsonResponse.getString("message"));
            } else {
                throw new IllegalArgumentException("Response does not contain a status or a message");
            }
        } catch (JSONException | IllegalArgumentException e) {
            response.setStatus(statusCode);
            response.setMessage("Unexpected exception occurred: " + e.getMessage());
        }
    }

    /**
     * @see TextHttpResponseHandler#onFailure(int, Header[], String, Throwable)
     */
    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable error) {
        try {
            JSONObject jsonResponse = new JSONObject(responseString);
            if (jsonResponse.has("status") && jsonResponse.has("message")) {
                response.setStatus(jsonResponse.getInt("status"));
                response.setMessage(jsonResponse.getString("message"));
            } else {
                throw new IllegalArgumentException("Response does not contain a status or a message");
            }
        } catch (JSONException | IllegalArgumentException e) {
            response.setStatus(statusCode);
            response.setMessage("Unexpected exception occurred: " + e.getMessage());
        }
    }

}
