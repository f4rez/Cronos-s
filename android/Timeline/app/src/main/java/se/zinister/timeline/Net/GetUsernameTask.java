package se.zinister.timeline.Net;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;

import java.io.IOException;

import se.zinister.timeline.LoginActivity;
import se.zinister.timeline.fragments.ChallengeFriendFragment;
import se.zinister.timeline.fragments.FindUsersFragment;
import se.zinister.timeline.fragments.MatchFragment;
import se.zinister.timeline.fragments.MatchStatistics;
import se.zinister.timeline.fragments.StartPageFragment;

/**
 * Created by josef on 2015-05-07.
 */
public class GetUsernameTask extends AsyncTask<Void, Void, String> {
    LoginActivity mActivity;
    String mScope;
    String mEmail;

    public GetUsernameTask(LoginActivity activity, String name, String scope) {
        this.mActivity = activity;
        this.mScope = scope;
        this.mEmail = name;
    }

    /**
     * Executes the asynchronous job. This runs when you call execute()
     * on the AsyncTask instance.
     */
    @Override
    protected String doInBackground(Void... params) {
        try {
            Log.d("LoginTask", "Trying to fetch token");
            String token = fetchToken();
            if (token != null) {
                Log.d("LoginTask", "token =" + token);

            } else  {
                Log.d("LoginTask", "Couldn't fetch token");
            }
        } catch (IOException e) {
            // The fetchToken() method handles Google-specific exceptions,
            // so this indicates something went wrong at a higher level.
            // TIP: Check for network connectivity before starting the AsyncTask.

        }
        return null;
    }

    /**
     * Gets an authentication token from Google and handles any
     * GoogleAuthException that may occur.
     */
    protected String fetchToken() throws IOException {
        try {
            return GoogleAuthUtil.getToken(mActivity, mEmail, mScope);
        } catch (GoogleAuthException fatalException) {
            // Some other type of unrecoverable exception has occurred.
            // Report and log the error as appropriate for your app.
           // mActivity.handleException(fatalException);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String returned) {
    Log.d("AAAAAAA","aaaa");

    }
}
