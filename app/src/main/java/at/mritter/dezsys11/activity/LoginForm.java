package at.mritter.dezsys11.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import at.mritter.dezsys11.R;
import at.mritter.dezsys11.model.Response;
import at.mritter.dezsys11.rest.ResponseHandler;
import at.mritter.dezsys11.model.User;
import at.mritter.dezsys11.rest.UserRequester;

/**
 * A login screen that offers login via email/password.
 *
 * @author Mathias Ritter
 * @version 1.0
 */
public class LoginForm extends UserForm {

    /**
     * @see android.support.v7.app.AppCompatActivity#onCreate(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        super.setupForm();

        Button mRegisterButton = (Button) findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginForm.this, RegisterForm.class);
                startActivity(intent);
            }
        });
    }

    /**
     * @see UserForm#callRestAPI(User)
     */
    @Override
    public Response callRestAPI(User user){

        Response response = new Response();
        UserRequester caller = new UserRequester(getApplicationContext(), new ResponseHandler(response));
        caller.login(user);
        return response;
    }

    /**
     * @see UserForm#success()
     */
    @Override
    public void success() {
        Intent intent = new Intent(this, SuccessActivity.class);
        intent.putExtra("email", super.getEmail());

        super.clearForm();
        showProgress(false);
        startActivity(intent);
    }

}

