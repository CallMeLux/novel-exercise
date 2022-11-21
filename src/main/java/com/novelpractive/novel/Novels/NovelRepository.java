package com.novelpractive.novel.Novels;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NovelRepository extends CrudRepository<Novel, String> {

    @Query(value = "from Novel where novel_title = :novel_title")
    Optional<Novel> checkTitle(String novel_title);

    @Query(value = "from Novel where author = :author")
    Optional<String> checkAuthor(String author);

    @Query(value = "from Novel where genre = :genre")
    Optional<String> checkGenre(String genre);


    @Query(value = "from Novel where char_name = :char_name")
    Optional<String> checkCharacter(String char_name);

    @Query(value = "from Novel where char_name = :char_name")
    Iterable<Novel> findByCharacter(String char_name);



    @Override
    boolean existsById(String s);

    @Override
    void deleteById(String s);


}
