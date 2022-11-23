package com.novelpractive.novel.Characters.dto.response;

import com.novelpractive.novel.Characters.Character;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterResponse {

    private String char_name;
    private int char_age;
    private String occupation;
    private String char_likes;
    private String char_dislikes;

    public CharacterResponse(Character character){
        this.char_name = character.getChar_name();
        this.char_age = character.getChar_age();
        this.occupation = character.getOccupation();
        this.char_likes = character.getChar_likes();
        this.char_dislikes = character.getChar_dislikes();
    }


}
