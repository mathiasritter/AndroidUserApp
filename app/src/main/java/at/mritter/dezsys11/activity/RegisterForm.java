package at.mritter.dezsys11.activity;

import android.os.Bundle;
import android.widget.Toast;

import at.mritter.dezsys11.layout.EditTextWithBackButton;
import at.mritter.dezsys11.R;
import at.mritter.dezsys11.model.Response;
import at.mritter.dezsys11.rest.ResponseHandler;
import at.mritter.dezsys11.model.User;
import at.mritter.dezsys11.rest.UserRequester;

/**
 * A register screen that offers registration via email/password.
 *
 * @author Mathias Ritter
 * @version 1.0
 */
public class RegisterForm extends UserForm {

    /**
     * @see android.support.v7.app.AppCompatActivity#onCreate(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        super.setupForm();

        ((EditTextWithBackButton) findViewById(R.id.email)).setOnBackButtonListener(new EditTextWithBackButton.IOnBackButtonListener() {
            @Override
            public boolean OnEditTextBackButton() {
                finish();
                return true;
            }
        });

        ((EditTextWithBackButton) findViewById(R.id.password)).setOnBackButtonListener(new EditTextWithBackButton.IOnBackButtonListener() {
            @Override
            public boolean OnEditTextBackButton() {
                finish();
                return true;
            }
        });

    }

    /**
     * @see UserForm#callRestAPI(User)
     */
    @Override
    public Response callRestAPI(User user) {

        Response response = new Response();
        UserRequester caller = new UserRequester(getApplicationContext(), new ResponseHandler(response));
        caller.register(user);
        return response;
    }

    /**
     * @see UserForm#success()
     */
    @Override
    public void success() {
        showProgress(false);
        Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_LONG).show();
        finish();
    }
}

