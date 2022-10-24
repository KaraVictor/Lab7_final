package com.client;

import com.utility.Serializer;
import com.utility.ServerMessage;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientReceiver {
    private final DatagramChannel channel;

    public ClientReceiver(DatagramChannel channel) {
        this.channel = channel;
    }

    public void receive() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(65536);
        try {
            String serverAnswer = "";
            while (true) {
                if (channel.isConnected()) {
                    channel.receive(byteBuffer);
                    ServerMessage serverMessage = (ServerMessage) Serializer.Deserialization(byteBuffer);
                    if (serverMessage.message.equals("end")) {
                        break;
                    }
                    serverAnswer += serverMessage.message;
                    byteBuffer = ByteBuffer.allocate(65536);
                }
            }
            System.out.println(serverAnswer);
        }
        catch (Exception ignored) {
            System.out.println("сервер не отвечает, повторите попытку позже");
        }
    }

    public String receiveString() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(65536);
        try {
            String serverAnswer = "";
            while (true) {
                if (channel.isConnected()) {
                    channel.receive(byteBuffer);
                    ServerMessage serverMessage = (ServerMessage) Serializer.Deserialization(byteBuffer);
                    if (serverMessage.message.equals("end")) {
                        break;
                    }
                    serverAnswer += serverMessage.message;
                    byteBuffer = ByteBuffer.allocate(65536);
                }
            }
            return serverAnswer;
        }
        catch (Exception ignored) {
            System.out.println("сервер не отвечает, повторите попытку позже");
        }
        return null;
    }
}
