package com.example.poetryapp.Response;

import com.example.poetryapp.models.poetryrecylermodel;
import java.util.List;

public class Apiresponse {

    private String status;
    private String message;
    private List<poetryrecylermodel> data;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<poetryrecylermodel> getData() {
        return data;
    }
}
