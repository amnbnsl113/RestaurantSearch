package com.place.eat.resturantsearch.model.viewmodel;

import java.io.Serializable;

/**
 * Created by aman on 12/9/17.
 */

public class BaseViewModel implements Serializable {
    private boolean isSuccess;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
