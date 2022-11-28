package com.novelpractive.novel.Novels;


import com.novelpractive.novel.Characters.Characters;
import com.novelpractive.novel.Novels.dto.requests.EditNovelRequest;
import com.novelpractive.novel.Novels.dto.requests.NewNovelRequest;
import com.novelpractive.novel.Novels.dto.response.NovelResponse;
import com.novelpractive.novel.util.InvalidUserInputException;
import com.novelpractive.novel.util.ResourceNotFoundException;
import com.novelpractive.novel.util.ResourceNotPersistedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NovelService {
    private final NovelRepository novelRepository;

    @Autowired
    public NovelService(NovelRepository novelRepository){this.novelRepository = novelRepository;}

    @Transactional
    public NovelResponse NewNovel(NewNovelRequest newNovelRequest){


        //submit to repository

        Novel newNovel = new Novel(newNovelRequest);

        newNovel.setNovel_title(newNovel.getNovel_title());
        newNovel.setGenre(newNovel.getGenre());
        newNovel.setAuthor(newNovel.getAuthor());
        //newNovel.setCharacters(newNovel.getCharacters());
        newNovel.setAmountOfCharacters(newNovel.getAmountOfCharacters());


        if(newNovel == null){
            throw new ResourceNotPersistedException("Registration of the Novel Failed.");
        }

        if(newNovel.getAmountOfCharacters() > 3){
            throw new ResourceNotPersistedException("Too many characters!");
        }

        //validations

        isTitleAvailable(newNovelRequest.getNovel_title());

        //need something to check the amount of characters in the novel, and that there isn't more than three.
        //as well as a way to add the character to the novel. I want to have at least one character
        //in order to actually create the novel.

        //Actually thinking about it, I only need to create a novel with one character for now, and then
        //add or update more characters in another function that can limit it to three.



        return new NovelResponse(novelRepository.save(newNovel));


    }//end of novel response

    @Transactional(readOnly = true)
    private boolean isTitleAvailable(String novel_title) {
        if(novelRepository.checkTitle(novel_title).isPresent()){
            throw new ResourceNotPersistedException("This Novel is already in the database.");
        }

        return true;
    }//check if a novel of that name already exists

    @Transactional
    public void remove(String novel_title){
        Novel novel = novelRepository.checkTitle(novel_title).orElseThrow(() -> new InvalidUserInputException("Novel not found."));
        novelRepository.save(novel);
    }//end remove



    @Transactional
    public void update(EditNovelRequest editNovelRequest){

        Novel novel = novelRepository.checkTitle(editNovelRequest.getNovel_title()).orElseThrow(() -> new InvalidUserInputException("Novel not found."));

        if(editNovelRequest.getNovel_title() != null){
            if(isTitleAvailable(editNovelRequest.getNovel_title())){
                novel.setNovel_title(editNovelRequest.getNovel_title());
            }
        }

        if(editNovelRequest.getAuthor() != null){
            novel.setAuthor(editNovelRequest.getAuthor());
        }


        if(editNovelRequest.getGenre() != null){
            novel.setGenre(editNovelRequest.getGenre());
        }

//        if(editNovelRequest.getCharacters() != null){
//            novel.setCharacters((Set<Characters>) editNovelRequest.getCharacters());
//        }

        if(editNovelRequest.getAmountOfCharacters() != 0 && editNovelRequest.getAmountOfCharacters() < 4){
            novel.setAmountOfCharacters(editNovelRequest.getAmountOfCharacters());
        }


    }//end update


    @Transactional(readOnly = true)
    public List<NovelResponse> findAll(){
        return ((Collection<Novel>) novelRepository.findAll())
                .stream()
                .map(NovelResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public NovelResponse findById(String novel_title) {
        Novel foundNovel = novelRepository.findById(novel_title).orElseThrow(() -> new ResourceNotFoundException("No Novel found with this title."));
        NovelResponse novelResponse = new NovelResponse(foundNovel);
        return novelResponse;
    }


    @Transactional(readOnly = true)
    public List<NovelResponse> findNovelByCharacter(String char_name){
        return ((Collection<Novel>) novelRepository.findByCharacter(char_name)).stream().map(NovelResponse::new).collect(Collectors.toList());
    }




}//end of class
