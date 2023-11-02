package com.usv.casino.repository;

import com.usv.casino.entity.Bonus_Utilizator;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface Bonus_UtilizatorRepository extends CrudRepository<Bonus_Utilizator, UUID> {

}
