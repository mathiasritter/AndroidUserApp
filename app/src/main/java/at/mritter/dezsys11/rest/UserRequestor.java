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

public class UserRequestor {

    private Context context;
    private ResponseHandlerInterface handler;
    private AsyncHttpClient client;
    private final String APP_HOST = "https://dezsys11.herokuapp.com";

    public UserRequestor(Context context, ResponseHandlerInterface handler) {
        this.client = new SyncHttpClient();
        this.client.setTimeout(50000);
        this.handler = handler;
        this.context = context;
    }

    public void register(User user) {
        this.post(APP_HOST + "/register", user);
    }

    public void login(User user) {
        this.post(APP_HOST + "/login", user);
    }

    private void post(String host, User user) {
        StringEntity entity = this.createEntity(user);
        client.post(this.context, host, entity, "application/json", this.handler);
    }

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
