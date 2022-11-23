package com.novelpractive.novel.Characters;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CharacterRepository extends CrudRepository<Character,String> {

    @Query(value = "from Character where char_name = :char_name")
    Optional<Character> findByName(String char_name);


}
