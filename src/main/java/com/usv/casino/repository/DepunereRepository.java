package com.usv.casino.repository;

import com.usv.casino.entity.Depunere;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DepunereRepository extends CrudRepository<Depunere, UUID> {

}
