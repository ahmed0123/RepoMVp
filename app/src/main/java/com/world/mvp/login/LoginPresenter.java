package com.world.mvp.login;


public class LoginPresenter implements ILoginPresenter, OnLoginFinishedListener {

    /******************************************************************************************
     - LoginPresenter has a reference to both the View and the Interactor
     - LoginPresenter retrieves data from the model, and notifies the view to display it.
     - OnLoginFinishedListener adds the methods that are necessary for asynchronous callbacks which leaves the rest of the interface intact
    *******************************************************************************************
    */

    // Referencing any class that implements the ILoginView interface provides greater flexibility
    private ILoginView view;
    private LoginInteractor interactor;

    public LoginPresenter(ILoginView loginView) {
        this.view = loginView;
        this.interactor = new LoginInteractor();
    }

    public void attemptLogin(String username, String password){
        interactor.validateCredentialsAsync(this, username, password);
    }

    @Override
    public void onError() {
        view.loginFailed();
    }

    @Override
    public void onSuccess() {
        view.navigateToListActivity();
    }
}
