package com.novelpractive.novel.Characters;

import com.novelpractive.novel.Novels.Novel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CharacterRepository extends CrudRepository<Character,String> {

    @Query(value = "from Character where char_name = :char_name")
    Optional<Character> findByName(String char_name);

    @Query(value = "from Character where novel_title = :title")
    Optional<String> checkNovel(String novel_title);

    @Query(value = "from Character where novel_title = :title")
    Iterable<Character> findByNovel(String novel_title);

    @Override
    boolean existsById(String s);

    @Override
    void deleteById(String s);


}
