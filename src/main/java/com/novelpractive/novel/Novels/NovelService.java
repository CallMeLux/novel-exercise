package com.novelpractive.novel.Novels;


import com.novelpractive.novel.Novels.dto.requests.NewNovelRequest;
import com.novelpractive.novel.Novels.dto.response.NovelResponse;
import com.novelpractive.novel.Novels.util.InvalidUserInputException;
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

        //validations

        if (!validateAmountofCharacters(newNovelRequest.getAmountOfCharacters())){
            throw new InvalidUserInputException("Too many Characters!");
        }





    }//end of novel response


    private boolean validateAmountofCharacters(int amountOfCharacters){
        if(amountOfCharacters > 3){
            return false;
        }
        return true;

    }

}
