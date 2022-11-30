package com.novelpractive.novel.Characters.dto.response;

import com.novelpractive.novel.Characters.Characters;
import com.novelpractive.novel.Novels.Novel;
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
    private String novel;

    public CharacterResponse(Characters characters){
        this.char_name = characters.getChar_name();
        this.char_age = characters.getChar_age();
        this.occupation = characters.getOccupation();
        this.char_likes = characters.getChar_likes();
        this.char_dislikes = characters.getChar_dislikes();
        this.novel = characters.getNovel().getNovel_title();
    }


}
