package at.mritter.dezsys11;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;


/**
 * Represents an asynchronous login/registration task used to authenticate
 * the user.
 */
public class UserTask extends AsyncTask<Void, Void, Boolean> {

    private UserActivity userActivity;
    private User user;
    private Response response;

    UserTask(UserActivity userActivity) {
        this.user = new User(userActivity.getEmail(), userActivity.getPassword());
        this.userActivity = userActivity;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        response = userActivity.callRestAPI(user);
        return response.getStatus() == 200;
    }

    @Override
    protected void onPostExecute(final Boolean success) {
        userActivity.showProgress(false);

        if (success) {
            userActivity.success();
        } else {
            Toast.makeText(userActivity.getApplicationContext(), response.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onCancelled() {
        userActivity.showProgress(false);
    }
}
