package com.cataloging.event;

public interface DomainEvent<T> {

    public void setStatus(String status);

    public String getStatus();

    public void setMessage(String message);

    public String getMessage();

}
