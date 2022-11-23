package com.novelpractive.novel.Novels;


import com.novelpractive.novel.Novels.dto.requests.NewNovelRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "novel")
public class Novel {
    @Id
    @Column(name = "title")
    private String novel_title;

    @Column(name = "author")
    private String author;

    @Column(name = "genre")
    private String genre;

    //change this to many to one and the type as Character
    @Column(name = "char_name")
    private String char_name;

    @Column(name = "amountOfCharacters")
    private int amountOfCharacters = 0;


    public Novel(NewNovelRequest newNovelRequest){
        this.novel_title = newNovelRequest.getNovel_title();
        this.author = newNovelRequest.getAuthor();
        this.genre = newNovelRequest.getGenre();
        this.char_name = newNovelRequest.getChar_name();
        this.amountOfCharacters = newNovelRequest.getAmountOfCharacters();
    }

}
