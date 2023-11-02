package com.usv.casino.repository;

import com.usv.casino.entity.Joc;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JocRepository extends CrudRepository<Joc, UUID> {

}
