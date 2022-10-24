package com.server;

import com.lab5_command.Operations;
import com.lab5_command.Save;
import com.lab5_data.*;
import com.utility.Database;
import com.utility.Serializer;
import com.utility.ClientMessage;
import com.utility.ServerMessage;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.security.MessageDigest;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Stream;

public class MainServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, SQLException {
        TreeMap<String, String> users = new TreeMap<>();

        DatagramChannel serverChannel = DatagramChannel.open();
        serverChannel.configureBlocking(false);
        InetSocketAddress serverAddress = new InetSocketAddress("localhost", 2004);
        serverChannel.bind(serverAddress);
        System.out.println("сервер начинает работу #" + serverAddress);
        ServerReceiver serverReceiver = new ServerReceiver(serverChannel);
        ServerSender serverSender = new ServerSender(serverChannel);

        String nameServerFile = System.getenv("FILE");
        File serverFile = new File(nameServerFile);
        Collection collection = new Collection();
        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_READ);

        //        String url = "jdbc:postgresql://localhost:5432/studs";
//        String username = "s336616";
//        String password = "uj3qJgrQ2RxoFWu6";
        String url = "jdbc:postgresql://localhost:5432/lab7";
        String username = "postgres";
        String password = "Victor675673";
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();

        try {
            Database.createTable(connection);
            FileReader fileReader = new FileReader();
            fileReader.fileReader(connection, collection, serverFile);
        } catch (Exception e) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM usersInfo");
            while(resultSet.next()){
                users.put(resultSet.getString("login"), resultSet.getString("password"));

            }
            resultSet = statement.executeQuery("SELECT * FROM lab7");
            while(resultSet.next()){
                String keyData = resultSet.getString("key");
                collection.collection.put(keyData, new Movie(resultSet.getString("name"),
                        new Coordinates(resultSet.getDouble("coordinate_x"), resultSet.getLong("coordinate_y")),
                        LocalDateTime.parse(resultSet.getString("creationDate")), resultSet.getInt("oscarCount"), resultSet.getLong("goldenPalmCount"),
                        resultSet.getDouble("totalBoxOffice"), MpaaRating.valueOf(resultSet.getString("mpaaRating")),
                        new Person(resultSet.getString("namePerson"), resultSet.getLong("heightPerson"), Country.valueOf(resultSet.getString("nationalityPerson")))));
                collection.collection.get(keyData).setId(resultSet.getInt("id"));
                collection.collection.get(keyData).setCreator(resultSet.getString("creator"));
            }
        }

        new Thread(() -> {
            while (true) {
                try {
                    selector.select();
                } catch (IOException error) {
                    error.printStackTrace();
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if(key.isReadable()) {
                        try {
                            ServerMessage serverAnswer = new ServerMessage("команда выполнена успешно" + '\n');
                            ByteBuffer byteBuffer = serverReceiver.receive(serverSender);
                            ClientMessage clientMessage = (ClientMessage) Serializer.Deserialization(byteBuffer);
                            if (clientMessage.message != null) {
                                if (clientMessage.message.equals("insert")) {
                                    Movie movie = (Movie) clientMessage.object;
                                    movie.setCreationDate();
                                    boolean checkAdd= true;
                                    for (String i : collection.collection.keySet()) {
                                        if (i.equals(clientMessage.argument)) {
                                            serverAnswer.setMessage("элемент с таким ключом уже существует");
                                            checkAdd = false;
                                            break;
                                        }
                                    }
                                    if (checkAdd) {
                                        try {
                                            Database.insertBD(connection, movie, clientMessage.argument, clientMessage.login);
                                            ResultSet resultSet = statement.executeQuery("SELECT id FROM lab7" + " WHERE key = " + "'" + clientMessage.argument + "'" + ";");
                                            if (resultSet.next()) {
                                                movie.setId(resultSet.getInt("id"));
                                            }
                                            movie.setCreator(clientMessage.login);
                                            collection.collection.put(clientMessage.argument, movie);
                                            serverAnswer.setMessage("новый элемент успешно добавлен");
                                        }
                                        catch (Exception e) {
                                            System.out.println("элемент не был успешно добавлен в коллекцию");
                                        }
                                    }
                                }
                                else {
                                    String newKey = "";
                                    for (String key_check : collection.collection.keySet()) {
                                        if (collection.collection.get(key_check).getId() == clientMessage.id) {
                                            newKey = key_check;
                                            break;
                                        }
                                    }
                                    if (newKey.equals("")) {
                                        serverAnswer.setMessage("нет элемента с таким id");
                                    }
                                    else {
                                        switch (clientMessage.argument) {
                                            case "name": {
                                                if (Database.updateStringDB(connection, clientMessage.object, "name", clientMessage.id)) {
                                                    collection.collection.get(newKey).setName((String) clientMessage.object);
                                                }
                                                break;
                                            }
                                            case "coordinates": {
                                                Coordinates coordinates = (Coordinates) clientMessage.object;
                                                if (Database.updateIntDB(connection, coordinates.getX(), "coordinates_of_x", clientMessage.id) &
                                                        Database.updateIntDB(connection, coordinates.getY(), "coordinates_of_y", clientMessage.id)) {
                                                    collection.collection.get(newKey).setCoordinates(coordinates);
                                                }
                                                break;
                                            }
                                            case "oscar": {
                                                if (Database.updateIntDB(connection, clientMessage.object, "oscarCount", clientMessage.id)) {
                                                    collection.collection.get(newKey).setOscarsCount((Integer) clientMessage.object);
                                                }
                                                break;
                                            }
                                            case "golden": {
                                                if (Database.updateIntDB(connection, clientMessage.object, "goldenPalmCount", clientMessage.id)) {
                                                    collection.collection.get(newKey).setGoldenPalmCount((Long) clientMessage.object);
                                                }
                                                break;
                                            }
                                            case "total": {
                                                if (Database.updateIntDB(connection, clientMessage.object, "totalBoxOffice", clientMessage.id)) {
                                                    collection.collection.get(newKey).setTotalBoxOffice((Double) clientMessage.object);
                                                }
                                                break;
                                            }
                                            case "mpaa": {
                                                if (Database.updateStringDB(connection, clientMessage.object, "mpaaRating", clientMessage.id)) {
                                                    collection.collection.get(newKey).setMpaaRating((MpaaRating) clientMessage.object);
                                                }
                                                break;
                                            }
                                            case "operator": {
                                                Person person = (Person) clientMessage.object;
                                                if (Database.updateStringDB(connection, person.getName(), "namePerson", clientMessage.id) &
                                                        Database.updateIntDB(connection, person.getHeight(), "heightPerson", clientMessage.id) &
                                                        Database.updateStringDB(connection, person.getNationality(), "nationalityPerson", clientMessage.id)) {
                                                    collection.collection.get(newKey).setOperator((Person) clientMessage.object);
                                                }
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            else if (clientMessage.messages[0].equals("register")) {
                                if (users.containsKey(clientMessage.login)) {
                                    serverAnswer.setMessage("этот логин уже занят");
                                }
                                else {
                                    serverAnswer.setMessage("вы успешно зарегистрировались");
                                    String query = "INSERT INTO usersInfo(login, password) VALUES (?, ?)";
                                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                                    preparedStatement.setString(1, clientMessage.login);
                                    preparedStatement.setString(2,  Arrays.toString(MessageDigest.getInstance("SHA-224").digest(clientMessage.password.getBytes())));
                                    preparedStatement.execute();
                                    users.put(clientMessage.login, Arrays.toString(MessageDigest.getInstance("SHA-224").digest(clientMessage.password.getBytes())));
                                }
                            }
                            else if (clientMessage.messages[0].equals("check")) {
                                serverAnswer.setMessage("неверный логин или пароль");
                                if (users.containsKey(clientMessage.login)) {
                                    users.get(clientMessage.login);
                                    MessageDigest.getInstance("SHA-224").digest(clientMessage.password.getBytes());
                                    {
                                        serverAnswer.setMessage("вход в аккаунт выполнен успешно");
                                    }
                                }
                            }
                            else {
                                Operations operations = new Operations();
                                serverAnswer.setMessage("");;
                                operations.operations(connection, clientMessage.messages, operations, collection, serverAnswer, serverFile, clientMessage.login);
                            }
                            //Сортировка в обратном лексикографическом порядке с помощью Stream API и лямбда-выражений
                            Stream<String> stream = collection.collection.keySet().stream().sorted((key1, key2) -> -key1.compareTo(key2));
                            stream.forEach((s) -> collection.collection.put(s, collection.collection.remove(s)));

                            int i = 0;
                            for (; i + 9000 < serverAnswer.message.length(); i += 9000) {
                                serverSender.send(Serializer.Serialization(new ServerMessage(serverAnswer.message.substring(i, i + 9000))));
                                Thread.sleep(50);
                            }

                            if (i < serverAnswer.message.length()) {
                                serverSender.send(Serializer.Serialization(new ServerMessage(serverAnswer.message.substring(i))));
                                Thread.sleep(50);
                            }
                            serverSender.send(Serializer.Serialization(new ServerMessage("end")));
                        }
                        catch ( Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            Save save = new Save();
            while (true) {
                if(scanner.hasNext()) {
                    String command = scanner.nextLine().toLowerCase().trim();
                    if (command.equals("exit")) {
                        save.save(collection, serverFile);
                        System.out.println("cервер завершает свою работу");
                        System.exit(0);
                    } else if (command.equals("save")) {
                        save.save(collection, serverFile);
                    } else {
                        System.out.println("неизвестная команда");
                    }
                }
            }
        }).start();

    }
}

