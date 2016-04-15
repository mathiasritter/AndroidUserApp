package at.mritter.dezsys11;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends UserActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the form.
        super.setmEmailView((AutoCompleteTextView) findViewById(R.id.email));

        EditText mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    submit();
                    return true;
                }
                return false;
            }
        });

        super.setmPasswordView(mPasswordView);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });

        super.setmLoginFormView(findViewById(R.id.login_form));
        super.setmProgressView(findViewById(R.id.login_progress));
    }

    /**
     * Method that performs RESTful webservice invocations
     *
     * @param user
     */
    public Response callRestAPI(User user){

        Response response = new Response();
        UserEndpointCaller caller = new UserEndpointCaller(getApplicationContext(), new ResponseHandler(response));
        caller.login(user);
        return response;
    }

}

