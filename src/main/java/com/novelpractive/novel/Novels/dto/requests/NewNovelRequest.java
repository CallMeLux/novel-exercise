package com.novelpractive.novel.Novels.dto.requests;


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
    private String char_name;

}
