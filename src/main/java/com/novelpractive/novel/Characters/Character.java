package com.novelpractive.novel.Characters;

import com.novelpractive.novel.Characters.dto.request.NewCharacterRequest;
import com.novelpractive.novel.Novels.Novel;
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
public class Character {

    @Id
    @Column(name = "char_name")
    private String char_name;

    @NotNull
    @Column(name = "char_age")
    private int char_age;

    @NotNull
    @Column(name = "occupation")
    private String occupation;

    @NotNull
    @Column(name = "char_likes")
    private String char_likes;

    @NotNull
    @Column(name = "char_dislikes")
    private String char_dislikes;

    @ManyToOne
    @JoinColumn(name = "title")
    private Novel novel;


    public Character(NewCharacterRequest newCharacterRequest){

        this.char_name = newCharacterRequest.getChar_name();
        this.char_age = newCharacterRequest.getChar_age();
        this.char_dislikes = newCharacterRequest.getChar_dislikes();
        this.char_likes = newCharacterRequest.getChar_likes();
        this.occupation = newCharacterRequest.getOccupation();
        this.novel = newCharacterRequest.getNovel();

    }


}//end of class
