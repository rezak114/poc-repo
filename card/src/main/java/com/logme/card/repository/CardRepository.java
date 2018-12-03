package com.logme.card.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logme.card.entity.Card;
import com.logme.card.enums.FaceEnum;
import com.logme.card.enums.SuitEnum;

@Service
@Transactional
public interface CardRepository extends CrudRepository<Card, Long> {

	Optional<Card> findBySuitAndFace(SuitEnum suitEnum,FaceEnum faceEnum);
}
