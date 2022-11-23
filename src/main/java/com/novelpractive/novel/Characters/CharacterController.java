package com.novelpractive.novel.Characters;


import com.novelpractive.novel.Characters.dto.response.CharacterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService){this.characterService = characterService;}

    @GetMapping("/find/{char_name}")
    public CharacterResponse getCharacter(@PathVariable String char_name){return characterService.findById(char_name);}

    @GetMapping("/all")
    public List<CharacterResponse> getAllCharacters(){return characterService.findAll();}

}//end of controller class
