package com.novelpractive.novel.Characters;


import com.novelpractive.novel.Characters.dto.request.EditCharacterRequest;
import com.novelpractive.novel.Characters.dto.request.NewCharacterRequest;
import com.novelpractive.novel.Characters.dto.response.CharacterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CharacterResponse addCharacter(@RequestBody @Valid NewCharacterRequest newCharacterRequest){
        return characterService.newCharacter(newCharacterRequest);
    }

    @PutMapping
    public String update(@RequestBody EditCharacterRequest editCharacterRequest){
        characterService.update(editCharacterRequest);
        return "Character was updated.";
    }

    @DeleteMapping("delete/{char_name}")
    public String delete(@PathVariable String char_name){
        characterService.remove(char_name);
        return "Character has been removed.";
    }

    @GetMapping
    public List<CharacterResponse> findAll(){return characterService.findAll();}

    @GetMapping("/{char_name}")
    public CharacterResponse findById(@PathVariable String char_name){return characterService.findById(char_name); }


    @GetMapping("/character/{novel_title}")
    public List<CharacterResponse> findCharacterByNovel(@PathVariable String novel_title){
        return characterService.findCharacterByNovel(novel_title);
    }



}//end of controller class
