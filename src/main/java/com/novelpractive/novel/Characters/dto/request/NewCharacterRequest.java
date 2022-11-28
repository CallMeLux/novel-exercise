package com.novelpractive.novel.Characters.dto.request;

import com.novelpractive.novel.Novels.Novel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewCharacterRequest {

    private String char_name;
    private int char_age;
    private String occupation;
    private String char_likes;
    private String char_dislikes;
    private Novel novel;

}
