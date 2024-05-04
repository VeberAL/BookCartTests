package models;

import lombok.Data;

import java.util.List;

@Data
public class AddBookModel {

    private String userId;
    private List<IsbnBookModel> collectionOfIsbns;

    public void addIsbnToList(IsbnBookModel isbnModel) {
        collectionOfIsbns.add(isbnModel);
    }
}