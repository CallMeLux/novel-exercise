package com.novelpractive.novel.Characters;

import com.novelpractive.novel.Characters.dto.request.EditCharacterRequest;
import com.novelpractive.novel.Characters.dto.request.NewCharacterRequest;
import com.novelpractive.novel.Characters.dto.response.CharacterResponse;
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

        Characters newCharacters = new Characters(newCharacterRequest);

        newCharacters.setChar_name(newCharacters.getChar_name());
        newCharacters.setChar_age(newCharacters.getChar_age());
        newCharacters.setOccupation(newCharacters.getOccupation());
        newCharacters.setChar_likes(newCharacters.getChar_likes());
        newCharacters.setChar_dislikes(newCharacters.getChar_dislikes());
        newCharacters.setNovel(newCharacters.getNovel());

        if(newCharacters == null){
            throw new ResourceNotPersistedException("Characters was not created.");
        }


        //validations

        isCharacterAvailable(newCharacterRequest.getChar_name());
        isNovelPresent(String.valueOf(newCharacterRequest.getNovel()));


        return new CharacterResponse(characterRepository.save(newCharacters));

    }//end of response

    @Transactional(readOnly = true)
    private boolean isCharacterAvailable(String char_name) {
        if(characterRepository.findByName(char_name).isPresent()){
            throw new ResourceNotPersistedException("This character already exists in the database.");
        }

        return true;
    }

    @Transactional(readOnly = true)
    private boolean isNovelPresent(String novel_title){
        if(characterRepository.checkNovel(novel_title).isPresent()){
            return true;
        }
        else
            throw new InvalidUserInputException("This novel doesn't exist.");
    }

    @Transactional
    public void remove(String char_name){
        Characters characters = characterRepository.findByName(char_name).orElseThrow(() -> new InvalidUserInputException("Characters not found."));
        characterRepository.save(characters);
    }//end remove


    @Transactional
    public void update(EditCharacterRequest editCharacterRequest){

        Characters characters = characterRepository.findByName(editCharacterRequest.getChar_name()).orElseThrow(() -> new InvalidUserInputException("Characters not found."));

        if(editCharacterRequest.getChar_name() != null){
            if(isCharacterAvailable(editCharacterRequest.getChar_name())){
                characters.setChar_name(editCharacterRequest.getChar_name());
            }
        }

        if(editCharacterRequest.getChar_age() != 0){
            characters.setChar_age(editCharacterRequest.getChar_age());
        }

        if(editCharacterRequest.getOccupation() != null){
            characters.setOccupation(editCharacterRequest.getOccupation());
        }

        if(editCharacterRequest.getChar_likes() != null){
            characters.setChar_likes(editCharacterRequest.getChar_likes());
        }

        if(editCharacterRequest.getChar_dislikes() != null){
            characters.setChar_dislikes(editCharacterRequest.getChar_dislikes());
        }

    }//end update

    @Transactional(readOnly = true)
    public List<CharacterResponse> findAll(){
        return ((Collection<Characters>) characterRepository.findAll())
                .stream()
                .map(CharacterResponse::new)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public CharacterResponse findById(String char_name) {
        Characters foundCharacters = characterRepository.findById(char_name).orElseThrow(() -> new ResourceNotFoundException("No Characters found with this name."));
        CharacterResponse characterResponse = new CharacterResponse(foundCharacters);
        return characterResponse;
    }


    @Transactional(readOnly = true)
    public List<CharacterResponse> findCharacterByNovel(String novel_title){
        return characterRepository.findByNovel(novel_title);
    }

}//end of class
