package com.novelpractive.novel.Characters;

import com.novelpractive.novel.Characters.dto.request.NewCharacterRequest;
import com.novelpractive.novel.Novels.Novel;
import com.novelpractive.novel.Novels.NovelService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fictional_character")
public class Characters {

    @Id
    @Column(name = "char_name")
    private String char_name;

    @NotNull
    @Column(name = "char_age")
    private int char_age;


    @Column(name = "occupation")
    private String occupation;


    @Column(name = "char_likes")
    private String char_likes;


    @Column(name = "char_dislikes")
    private String char_dislikes;

    @ManyToOne
    @JoinColumn(name = "title")
    private Novel novel;


    public Characters(NewCharacterRequest newCharacterRequest, Novel novel){

        this.char_name = newCharacterRequest.getChar_name();
        this.char_age = newCharacterRequest.getChar_age();
        this.char_dislikes = newCharacterRequest.getChar_dislikes();
        this.char_likes = newCharacterRequest.getChar_likes();
        this.occupation = newCharacterRequest.getOccupation();
        this.novel = novel;
    }


}//end of class
