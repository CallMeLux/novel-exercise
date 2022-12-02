package com.novelpractive.novel.Novels;

import com.novelpractive.novel.Novels.dto.requests.EditNovelRequest;
import com.novelpractive.novel.Novels.dto.requests.NewNovelRequest;
import com.novelpractive.novel.Novels.dto.response.NovelResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/novels")
public class NovelController {
    private final NovelService novelService;

    @Autowired
    public NovelController(NovelService novelService){
        this.novelService = novelService;
    }

    @GetMapping("/all")
    public List<NovelResponse> getAllNovels(){return novelService.findAll(); }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public NovelResponse addNovel(@RequestBody @Valid NewNovelRequest newNovelRequest){
        return novelService.NewNovel(newNovelRequest);
    }

    @PutMapping
    public String update(@RequestBody EditNovelRequest editNovelRequest){
     Novel updatedNovel = novelService.findNovelById(editNovelRequest.getNovel_title());
     if(updatedNovel != null){
         novelService.update(editNovelRequest);
         return "Novel was updated.";
     }
     else{
         return "Novel not found";
     }

    }

    @DeleteMapping("delete/{novel_title}")
    public String delete(@PathVariable String novel_title){
        novelService.remove(novel_title);
        return "Novel has been removed";
    }


    @GetMapping
    public List<NovelResponse> findAll(){
        return novelService.findAll();
    }

    @GetMapping("/{novel_title}")
    public NovelResponse findById(@PathVariable String novel_title){
        return novelService.findById(novel_title);
    }

    @GetMapping("/find/{char_name}")
    public List<NovelResponse> findNovelByCharacter(@PathVariable String char_name){
        return novelService.findNovelByCharacter(char_name);
    }

}//end of class
