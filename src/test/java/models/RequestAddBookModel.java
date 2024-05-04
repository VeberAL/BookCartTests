package models;

import lombok.Data;

import java.util.List;

@Data
public class RequestAddBookModel {

    private String userId;
    private List<CollectionOfIsbns> collectionOfIsbns;

    @Data
    public static class CollectionOfIsbns {
        private String isbn;
    }

}