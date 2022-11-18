package com.novelpractive.novel.Novels.dto.response;

import com.novelpractive.novel.Novels.Novel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NovelResponse {
    private String novel_title;
    private String author;
    private String genre;
    private String char_name;

    public NovelResponse(Novel novel){
        this.novel_title = novel.getNovel_title();
        this.author = novel.getAuthor();
        this.genre = novel.getGenre();
        this.char_name = novel.getChar_name();
    }

}
