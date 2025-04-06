package com.javarush.restonspring.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelRequestTo {
    @NotBlank(message = "Название метки обязательно")
    @Size(min = 2, max = 32, message = "Название метки должно содержать от 2 до 32 символов")
    @JsonProperty("name")
    private String name;
}
