package com.novelpractive.novel.Novels.dto.requests;


import com.novelpractive.novel.Characters.Characters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EditNovelRequest {

    private String novel_title;
    private String author;
    private String genre;
    private Characters characters;
    private int amountOfCharacters = 0;

}
