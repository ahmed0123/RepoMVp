package com.world.mvp.login;

public interface ILoginInteractor {
    void validateCredentialsAsync(OnLoginFinishedListener listener, String username, String password);
}
