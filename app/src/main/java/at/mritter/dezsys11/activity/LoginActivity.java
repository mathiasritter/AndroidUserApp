package at.mritter.dezsys11.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import at.mritter.dezsys11.R;
import at.mritter.dezsys11.model.Response;
import at.mritter.dezsys11.rest.ResponseHandler;
import at.mritter.dezsys11.model.User;
import at.mritter.dezsys11.rest.UserRequestor;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends UserActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        super.setupForm();

        Button mRegisterButton = (Button) findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Method that performs RESTful webservice invocations
     *
     * @param user
     */
    @Override
    public Response callRestAPI(User user){

        Response response = new Response();
        UserRequestor caller = new UserRequestor(getApplicationContext(), new ResponseHandler(response));
        caller.login(user);
        return response;
    }

    @Override
    public void success() {
        Intent intent = new Intent(this, SuccessActivity.class);
        intent.putExtra("email", super.getEmail());

        super.clear();
        showProgress(false);
        startActivity(intent);
    }

}

