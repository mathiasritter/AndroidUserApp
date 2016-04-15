package at.mritter.dezsys11.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import at.mritter.dezsys11.R;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String email = extras.getString("email");
            TextView mPasswordView = (TextView) findViewById(R.id.message);
            if (email != null && mPasswordView != null) {
                mPasswordView.append(" as " + email);
            }
        }
    }
}
