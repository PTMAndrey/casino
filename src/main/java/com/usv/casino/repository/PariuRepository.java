package com.usv.casino.repository;

import com.usv.casino.entity.Pariu;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PariuRepository extends CrudRepository<Pariu, UUID> {

}
