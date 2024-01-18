package com.usv.casino.repository;

import com.usv.casino.entity.Bonus;
import com.usv.casino.enums.EnumTipBonus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface BonusRepository extends CrudRepository<Bonus, UUID> {
    Optional<Bonus> findByNumeBonus(EnumTipBonus numeBonus);
}