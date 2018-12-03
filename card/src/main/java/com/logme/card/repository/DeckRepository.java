package com.logme.card.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.logme.card.entity.Deck;

@Service
public interface DeckRepository extends CrudRepository<Deck, Long> {

}
