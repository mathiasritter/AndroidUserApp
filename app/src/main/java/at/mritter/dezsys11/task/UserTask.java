package at.mritter.dezsys11.task;

import android.os.AsyncTask;
import android.widget.Toast;

import at.mritter.dezsys11.activity.UserForm;
import at.mritter.dezsys11.model.Response;
import at.mritter.dezsys11.model.User;


/**
 * Represents an asynchronous login/registration task used to authenticate
 * the user.
 *
 * @author Mathias Ritter
 * @version 1.0
 */
public class UserTask extends AsyncTask<Void, Void, Boolean> {

    private UserForm userForm;
    private User user;
    private Response response;

    public UserTask(UserForm userForm) {
        this.user = new User(userForm.getEmail(), userForm.getPassword());
        this.userForm = userForm;
    }

    /**
     * @see AsyncTask#doInBackground(Object[])
     */
    @Override
    protected Boolean doInBackground(Void... params) {
        response = userForm.callRestAPI(user);
        // rest api call was successful if status is 200 or 201
        return response.getStatus() == 200 || response.getStatus() == 201;
    }

    /**
     * @see AsyncTask#onPostExecute(Object)
     */
    @Override
    protected void onPostExecute(final Boolean success) {

        if (success) {
            userForm.success();
        } else {
            userForm.showProgress(false);
            Toast.makeText(userForm.getApplicationContext(), response.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    /**
     * @see AsyncTask#onCancelled()
     */
    @Override
    protected void onCancelled() {
        userForm.showProgress(false);
    }
}

