package com.usv.casino.repository;

import com.usv.casino.entity.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CardRepository extends CrudRepository<Card, UUID> {

}
