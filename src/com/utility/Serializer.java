package com.utility;

import java.io.*;
import java.nio.ByteBuffer;

public class Serializer {
    public static ByteBuffer Serialization(Object message) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(65536);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(message);
        objectOutputStream.close();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return  ByteBuffer.wrap(bytes);
    }

    public synchronized static Object Deserialization(ByteBuffer byteBuffer) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();
    }
}
