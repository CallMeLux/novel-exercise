package com.novelpractive.novel.Novels;


import com.novelpractive.novel.Novels.dto.requests.NewNovelRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    //change this to one to many and the type as Character
    @OneToMany
    @JoinColumn(name = "char_name")
    private Character character;

    @Column(name = "amountOfCharacters")
    private int amountOfCharacters = 0;

    //change the request and responses to match the new data types and information from character model.
    public Novel(NewNovelRequest newNovelRequest){
        this.novel_title = newNovelRequest.getNovel_title();
        this.author = newNovelRequest.getAuthor();
        this.genre = newNovelRequest.getGenre();
        this.character = newNovelRequest.getCharacter();
        this.amountOfCharacters = newNovelRequest.getAmountOfCharacters();
    }

}
