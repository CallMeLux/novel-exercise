package com.novelpractive.novel.Characters;

import com.novelpractive.novel.Characters.dto.request.EditCharacterRequest;
import com.novelpractive.novel.Characters.dto.request.NewCharacterRequest;
import com.novelpractive.novel.Characters.dto.response.CharacterResponse;
import com.novelpractive.novel.Novels.Novel;
import com.novelpractive.novel.Novels.dto.response.NovelResponse;
import com.novelpractive.novel.util.InvalidUserInputException;
import com.novelpractive.novel.util.ResourceNotFoundException;
import com.novelpractive.novel.util.ResourceNotPersistedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository){this.characterRepository = characterRepository ;}

    @Transactional
    public CharacterResponse newCharacter(NewCharacterRequest newCharacterRequest){

        //submit to repository

        Character newCharacter = new Character(newCharacterRequest);

        newCharacter.setChar_name(newCharacter.getChar_name());
        newCharacter.setChar_age(newCharacter.getChar_age());
        newCharacter.setOccupation(newCharacter.getOccupation());
        newCharacter.setChar_likes(newCharacter.getChar_likes());
        newCharacter.setChar_dislikes(newCharacter.getChar_dislikes());

        newCharacter = characterRepository.save(newCharacter);

        if(newCharacter == null){
            throw new ResourceNotPersistedException("Character was not created.");
        }

        //validations

        isCharacterAvailable(newCharacterRequest.getChar_name());

        return new CharacterResponse(newCharacter);

    }//end of response

    @Transactional(readOnly = true)
    private boolean isCharacterAvailable(String char_name) {
        if(characterRepository.findByName(char_name).isPresent()){
            throw new ResourceNotPersistedException("This character already exists in the database.");
        }

        return true;
    }

    @Transactional
    public void remove(String char_name){
        Character character= characterRepository.findByName(char_name).orElseThrow(() -> new InvalidUserInputException("Character not found."));
        characterRepository.save(character);
    }//end remove


    @Transactional
    public void update(EditCharacterRequest editCharacterRequest){

        Character character = characterRepository.findByName(editCharacterRequest.getChar_name()).orElseThrow(() -> new InvalidUserInputException("Character not found."));

        if(editCharacterRequest.getChar_name() != null){
            if(isCharacterAvailable(editCharacterRequest.getChar_name())){
                character.setChar_name(editCharacterRequest.getChar_name());
            }
        }

        if(editCharacterRequest.getChar_age() != 0){
            character.setChar_age(editCharacterRequest.getChar_age());
        }

        if(editCharacterRequest.getOccupation() != null){
            character.setOccupation(editCharacterRequest.getOccupation());
        }

        if(editCharacterRequest.getChar_likes() != null){
            character.setChar_likes(editCharacterRequest.getChar_likes());
        }

        if(editCharacterRequest.getChar_dislikes() != null){
            character.setChar_dislikes(editCharacterRequest.getChar_dislikes());
        }

    }//end update

    @Transactional(readOnly = true)
    public List<CharacterResponse> findAll(){
        return ((Collection<Character>) characterRepository.findAll())
                .stream()
                .map(CharacterResponse::new)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public CharacterResponse findById(String char_name) {
        Character foundCharacter = characterRepository.findById(char_name).orElseThrow(() -> new ResourceNotFoundException("No Character found with this name."));
        CharacterResponse characterResponse = new CharacterResponse(foundCharacter);
        return characterResponse;
    }


}//end of class
