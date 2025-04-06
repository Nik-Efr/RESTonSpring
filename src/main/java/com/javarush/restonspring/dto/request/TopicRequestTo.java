package com.javarush.restonspring.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicRequestTo {
    @NotNull(message = "ID автора обязателен")
    @JsonProperty("writerId")
    private Long writerId;

    @NotBlank(message = "Заголовок обязателен")
    @Size(min = 2, max = 64, message = "Заголовок должен содержать от 2 до 64 символов")
    @JsonProperty("title")
    private String title;

    @NotBlank(message = "Содержание обязательно")
    @Size(min = 4, max = 2048, message = "Содержание должно содержать от 4 до 2048 символов")
    @JsonProperty("content")
    private String content;
}
