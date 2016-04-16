package at.mritter.dezsys11.rest;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import at.mritter.dezsys11.model.User;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Executes rest api calls for login/registration of an user
 *
 * @author Mathias Ritter
 * @version 1.0
 */
public class UserRequester {

    private Context context;
    private ResponseHandlerInterface handler;
    private AsyncHttpClient client;
    private final String APP_HOST = "https://dezsys11.herokuapp.com";

    public UserRequester(Context context, ResponseHandlerInterface handler) {
        this.client = new SyncHttpClient();
        this.client.setTimeout(50000);
        this.handler = handler;
        this.context = context;
    }

    /**
     * Registers a given user through a rest api call
     *
     * @param user the user that will be registered
     */
    public void register(User user) {
        this.post(APP_HOST + "/register", user);
    }

    /**
     * Login a given user through a rest api call
     *
     * @param user the user that will be logged in
     */
    public void login(User user) {
        this.post(APP_HOST + "/login", user);
    }

    /**
     * Executes a post request
     *
     * @param host Host destination address
     * @param user User object that will be used in the request body
     */
    private void post(String host, User user) {
        StringEntity entity = this.createEntity(user);
        client.post(this.context, host, entity, "application/json", this.handler);
    }

    /**
     * creates an StringEntity out of an user object
     *
     * @param user user object that will be converted
     * @return user object as string entity
     */
    private StringEntity createEntity(User user) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", user.getEmail());
            jsonObject.put("password", user.getPassword());
            return new StringEntity(jsonObject.toString());
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
