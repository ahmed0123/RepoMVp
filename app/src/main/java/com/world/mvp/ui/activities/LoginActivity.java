package com.world.mvp.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.world.mvp.BuildConfig;
import com.world.mvp.R;
import com.world.mvp.login.ILoginView;
import com.world.mvp.login.LoginPresenter;


public class LoginActivity extends ActionBarActivity implements ILoginView {

    /******************************************************************************************
     - LoginActivity ONLY knows how to display views and sending events and data to the presenter
     - LoginActivity doesn't know anything about the model (SynchronousLoginInteractor)
     - The only changes to the LoginActivity to allow for asynchronous behavior was to add a ProgressDialog
    ********************************************************************************************
    */

    @Bind(R.id.login_github_username)
    EditText githubUsernameEditText;

    @Bind(R.id.login_fake_password)
    EditText fakePasswordEditText;

    LoginPresenter presenter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (BuildConfig.DEBUG) {
            githubUsernameEditText.setText("ahmed0123");
            fakePasswordEditText.setText("ahmed1234");
        }

        presenter = new LoginPresenter(this);
    }

    @OnClick(R.id.login_submit_button)
    public void loginTapped(View view){
        progressDialog = ProgressDialog.show(this, "Authenticating...", null);
        String email =  githubUsernameEditText.getText().toString();
        String password = fakePasswordEditText.getText().toString();
        // Pass user event straight to presenter
        presenter.attemptLogin(email, password);
    }

    @Override
    public void navigateToListActivity() {
        progressDialog.dismiss();
        Toast.makeText(this, "Login Success!",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, RepoListFragmentActivity.class);
        i.putExtra("username", githubUsernameEditText.getText().toString());
        startActivity(i);
    }

    @Override
    public void loginFailed() {
        progressDialog.dismiss();
        Toast.makeText(this, "Login Invalid: Must be 3 letters or longer", Toast.LENGTH_SHORT).show();
    }
}



