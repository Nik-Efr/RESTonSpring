package com.javarush.restonspring.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelResponseTo {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;
}
