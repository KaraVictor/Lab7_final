package com.client;

import com.utility.ClientMessage;
import com.utility.Serializer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class Authorizer {

    public static String[] authorization(DatagramChannel datagramChannel, InetSocketAddress serverAddress) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ClientSender clientSender = new ClientSender(datagramChannel, serverAddress);
        ClientReceiver clientReceiver = new ClientReceiver(datagramChannel);

        while (true) {
            System.out.println("введите \"sign up\", если хотите зарегистрироваться, введите \"sign in\", если хотите войти");
            String command = scanner.nextLine().toLowerCase().trim();
            String login;
            String password;
            if (command.equals("sign up")) {
                System.out.println("введите логин:");
                login = scanner.nextLine();
                System.out.println("введите пароль:");
                password = scanner.nextLine();
                ClientMessage clientMessage = new ClientMessage("register".split(" "), login, password);
                clientSender.send(Serializer.Serialization(clientMessage));
                clientReceiver.receive();
            }
            else if (command.equals("sign in")) {
                System.out.println("введите логин:");
                login = scanner.nextLine();
                System.out.println("введите пароль:");
                password = scanner.nextLine();
                ClientMessage clientMessage = new ClientMessage("check".split(" "), login, password);
                clientSender.send(Serializer.Serialization(clientMessage));
                String messageFromServer = clientReceiver.receiveString();
                System.out.println(messageFromServer);
                if (messageFromServer != null && messageFromServer.equals("вход в аккаунт выполнен успешно")) {
                    String[] user = new String[2];
                    user[0] = login;
                    user[1] = password;
                    return user;
                }
            }
            else {
                System.out.println("неверная команда");
            }
        }

    }
}
