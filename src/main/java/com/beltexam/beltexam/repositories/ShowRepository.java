package com.beltexam.beltexam.repositories;

import com.beltexam.beltexam.models.Show;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShowRepository extends CrudRepository<Show, Long> {
    List<Show> findAll();
}
