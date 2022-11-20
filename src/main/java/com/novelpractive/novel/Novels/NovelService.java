package com.novelpractive.novel.Novels;


import com.novelpractive.novel.Novels.dto.requests.EditNovelRequest;
import com.novelpractive.novel.Novels.dto.requests.NewNovelRequest;
import com.novelpractive.novel.Novels.dto.response.NovelResponse;
import com.novelpractive.novel.Novels.util.InvalidUserInputException;
import com.novelpractive.novel.Novels.util.ResourceNotPersistedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NovelService {
    private final NovelRepository novelRepository;

    @Autowired
    public NovelService(NovelRepository novelRepository){this.novelRepository = novelRepository;}

    @Transactional
    public NovelResponse NewNovel(NewNovelRequest newNovelRequest){

        Novel newNovel = new Novel(newNovelRequest);
        newNovel.setNovel_title(newNovel.getNovel_title());
        newNovel.setGenre(newNovel.getGenre());
        newNovel.setAuthor(newNovel.getAuthor());
        newNovel.setChar_name(newNovel.getChar_name());
        newNovel.setAmountOfCharacters(newNovel.getAmountOfCharacters());


        //validations

        isTitleAvailable(newNovelRequest.getNovel_title());

        //need something to check the amount of characters in the novel, and that there isn't more than three.
        //as well as a way to add the character to the novel. I want to have at least one character
        //in order to actually create the novel.

        //Actually thinking about it, I only need to create a novel with one character for now, and then
        //add or update more characters in another function that can limit it to three.



        

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

        if (editNovelRequest.getNovel_title() != null){
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

        if(editNovelRequest.getChar_name() != null){
            novel.setChar_name((editNovelRequest.getChar_name()));
        }

        if(editNovelRequest.getAmountOfCharacters() != 0 && editNovelRequest.getAmountOfCharacters() < 4){
            novel.setAmountOfCharacters(editNovelRequest.getAmountOfCharacters());
        }


    }//end update



}//end of class
