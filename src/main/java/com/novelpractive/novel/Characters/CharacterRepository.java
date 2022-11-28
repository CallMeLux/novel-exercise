package com.novelpractive.novel.Characters;

import com.novelpractive.novel.Characters.dto.response.CharacterResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CharacterRepository extends CrudRepository<Characters,String> {

    @Query(value = "from Characters where char_name = :char_name")
    Optional<Characters> findByName(String char_name);

    @Query(value = "from Characters where title = :novel_title")
    Optional<String> checkNovel(String novel_title);

    @Query(value = "from Characters where title = :novel_title")
    List<CharacterResponse> findByNovel(String novel_title);

    @Override
    boolean existsById(String s);

    @Override
    void deleteById(String s);


}
