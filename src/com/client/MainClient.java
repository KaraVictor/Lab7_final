package com.client;

import com.client.ClientReceiver;
import com.client.ClientSender;
import com.lab5_data.*;
import com.utility.ClientMessage;
import com.utility.Serializer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.time.LocalDateTime;
import java.util.Scanner;

public class MainClient {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        DatagramChannel clientChannel = DatagramChannel.open();
        InetSocketAddress serverAddress = new InetSocketAddress("localhost", 2004);
        clientChannel.connect(serverAddress);
        ClientReceiver clientReceiver = new ClientReceiver(clientChannel);
        ClientSender clientSender = new ClientSender(clientChannel, serverAddress);
        String messages;
        String[] arrayMessages;

        String[] user = Authorizer.authorization(clientChannel, serverAddress);

        while (true) {
            System.out.println("введите \"help\" если хотите увидеть список команд");
            messages = scanner.nextLine().toLowerCase().trim();
            arrayMessages = messages.split(" ");
            ByteBuffer buffer;
            if (arrayMessages[0].equals("exit")) {
                System.out.println("вы завершили работу клиентского приложения");
                break;
            }
            else if (arrayMessages[0].equals("insert") & arrayMessages.length == 2) {
                String name = Receiver.getName();
                Coordinates coordinates = Receiver.getCoordinates();
                Integer oscarCount = Receiver.getOscarCount();
                Long goldenPalmCount = Receiver.getGoldenPalm();
                Double totalBoxOffice = Receiver.getTotalBoxOffice();
                MpaaRating mpaaRating = Receiver.getMpaaRating();
                Person operator = Receiver.getOperator();
                Movie movie = new Movie(name, coordinates, oscarCount, goldenPalmCount, totalBoxOffice, mpaaRating, operator);
                ClientMessage clientMessage = new ClientMessage("insert", movie, arrayMessages[1], user[0], user[1]);
                buffer = Serializer.Serialization(clientMessage);
            }
            else if (arrayMessages[0].equals("update") & arrayMessages.length == 2) {
                String fieldName;
                Object object = null;
                System.out.println("введите имя поля, которое вы хотите изменить");
                System.out.println("доступные поля для редактирования: name, coordinates, oscar count, golden palm, total box" +
                        "office, mpaa rating, operator");
                while (true) {
                    fieldName = scanner.nextLine().trim().toLowerCase();
                    String[] string = fieldName.split(" ");
                    boolean f = false;
                    switch (string[0]) {
                        case "name": {
                            object = Receiver.getName();
                            break;
                        }
                        case "coordinates": {
                            object = Receiver.getCoordinates();
                            break;
                        }
                        case "oscar": {
                            object = Receiver.getOscarCount();
                            break;
                        }
                        case "golden": {
                            object = Receiver.getGoldenPalm();
                            break;
                        }
                        case "total": {
                            object = Receiver.getTotalBoxOffice();
                            break;
                        }
                        case "mpaa": {
                            object = Receiver.getMpaaRating();
                            break;
                        }
                        case "operator": {
                            object = Receiver.getOperator();
                            break;
                        }
                        default:
                            f = true;
                    }
                    if (f) {
                        System.out.println("такого поля нет, введите ещё раз:");
                    } else {
                        break;
                    }
                }
                ClientMessage clientMessage = new ClientMessage("update", object, fieldName, Integer.parseInt(arrayMessages[1]), user[0], user[1]);
                buffer = Serializer.Serialization(clientMessage);
            }
            else {
                ClientMessage clientMessage = new ClientMessage(arrayMessages, user[0], user[1]);
                buffer = Serializer.Serialization(clientMessage);
            }
            // отправляем сообщение на сервер
            clientSender.send(buffer);
            // получаем ответ от сервера
            clientReceiver.receive();
        }
        clientChannel.close();
    }
}