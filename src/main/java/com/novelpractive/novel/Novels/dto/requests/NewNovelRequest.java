package com.novelpractive.novel.Novels.dto.requests;


import com.novelpractive.novel.Characters.Characters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewNovelRequest {

    private String novel_title;
    private String author;
    private String genre;
    private int amountOfCharacters;

}
