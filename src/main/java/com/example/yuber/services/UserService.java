package com.example.yuber.services;

import com.example.yuber.exceptions.UserAlreadyExistsException;
import com.example.yuber.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

public class UserService {

    private static List<User> users;
    private static final Path USERs_PATH = FileSystemService.getPathToFile("config", "user.json");
    //private static final Path USERs_PATH = Paths.get("/Users/giuliamandres/Desktop/Year2/SEF/yuber/src/main/resources.user.json");

    public static void loadUsersFromFile() throws IOException{
        if(!Files.exists(USERs_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("user.json")), USERs_PATH.toFile());
        }
        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERs_PATH.toFile(), new TypeReference<>() {
        });
    }

    public static void addUser(String username, String password, String role, String name, String surname, String address, String phone_number) throws UserAlreadyExistsException{
        checkUserDoesNotExist(username);
        users.add(new User(username, encodePassword(username, password), role, address, name, surname, phone_number));
        persistUser();

    }

    private static void checkUserDoesNotExist(String username) throws UserAlreadyExistsException{
        for (User user : users) {
            if(Objects.equals(username, user.getUsername()))
                throw new UserAlreadyExistsException(username);
        }
    }

    private static void persistUser() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERs_PATH.toFile(), users);
        }catch (IOException e){
            throw new RuntimeException();
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest messageDigest = getMessageDigest();
        messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));

        return new String(hashedPassword, StandardCharsets.UTF_8).replace("\"", "");
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
        }catch (NoSuchAlgorithmException e){
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return messageDigest;
    }
}
