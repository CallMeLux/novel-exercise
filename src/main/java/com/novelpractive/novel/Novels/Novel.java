package com.novelpractive.novel.Novels;


import com.novelpractive.novel.Characters.Characters;
import com.novelpractive.novel.Novels.dto.requests.NewNovelRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

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

    //change this to one to many and the type as Characters
    @OneToMany
    @JoinColumn(name = "char_name")
    private Set<Characters> characters;
    @NotNull
    @Column(name = "amount_of_characters")
    private int amountOfCharacters;

    //change the request and responses to match the new data types and information from characters model.
    public Novel(NewNovelRequest newNovelRequest){
        this.novel_title = newNovelRequest.getNovel_title();
        this.author = newNovelRequest.getAuthor();
        this.genre = newNovelRequest.getGenre();
        this.characters = (Set<Characters>) newNovelRequest.getCharacters();
        this.amountOfCharacters = newNovelRequest.getAmountOfCharacters();
    }



}
