package com.utility;

import java.io.Serializable;

public class ClientMessage implements Serializable {
    public String login;
    public String password;
    public String message;
    public String argument;
    public Object object;
    public int id;
    public String[] messages;

    public ClientMessage(String[] messages, String login, String password){
        this.messages = messages;
        this.login = login;
        this.password = password;
    }

    public ClientMessage(String message, Object object, String argument, String login, String password) {
        this.message = message;
        this.object = object;
        this.argument = argument;
        this.login = login;
        this.password = password;
    }

    public ClientMessage(String message, Object object, String argument, int id, String login, String password) {
        this.message = message;
        this.object = object;
        this.argument = argument;
        this.id = id;
        this.login = login;
        this.password = password;
    }

}

