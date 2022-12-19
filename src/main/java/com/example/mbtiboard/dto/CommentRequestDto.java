package com.example.mbtiboard.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotNull(message = "내용을 입력해주세요.")
    private String content;

}
