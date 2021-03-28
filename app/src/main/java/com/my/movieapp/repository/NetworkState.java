package com.my.movieapp.repository;

public class NetworkState {


    private Status status;
    
    private String message;
    
    private NetworkState(Status status, String message) {
        this.status = status;
        this.message = message;
    }
    
    private NetworkState(Status status) {
        this.status = status;
    }
    
    public static final NetworkState LOADED = new NetworkState(Status.SUCCESS);
    
    public static final NetworkState LOADING = new NetworkState(Status.RUNNING);
    
    public static final NetworkState ERROR = new NetworkState(Status.FAILED, "Something went wrong");
    
    public static final NetworkState ENDOFLIST = new NetworkState(Status.FAILED, "You have reached the end");

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
