package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginResponseModel {

    private String userId, password, token, expires;
    @JsonProperty("username")
    private String userName;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private String created_date;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String isActive;
}