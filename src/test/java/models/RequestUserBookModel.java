package models;

import lombok.Data;

@Data
public class RequestUserBookModel {
    private String userName, password;
}