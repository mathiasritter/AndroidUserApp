package at.mritter.dezsys11.activity;

import android.os.Bundle;
import android.widget.Toast;

import at.mritter.dezsys11.R;
import at.mritter.dezsys11.model.Response;
import at.mritter.dezsys11.rest.ResponseHandler;
import at.mritter.dezsys11.model.User;
import at.mritter.dezsys11.rest.UserRequestor;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends UserActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        super.setupForm();
    }


    @Override
    public Response callRestAPI(User user) {

        Response response = new Response();
        UserRequestor caller = new UserRequestor(getApplicationContext(), new ResponseHandler(response));
        caller.register(user);
        return response;
    }

    @Override
    public void success() {
        showProgress(false);
        Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_LONG).show();
        finish();
    }
}

