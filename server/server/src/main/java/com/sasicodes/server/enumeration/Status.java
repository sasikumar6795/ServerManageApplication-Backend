package com.sasicodes.server.enumeration;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum Status {
    SERVER_UP("SERVER_UP"),
    SERVER_DOWN("SERVER_DOWN");

    private final String status;

    public String getStatus()
    {
        return this.status;
    }





}
