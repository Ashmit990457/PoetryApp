package com.example.poetryapp.Response;

public class deleteresponse {
    String status,message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public deleteresponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
