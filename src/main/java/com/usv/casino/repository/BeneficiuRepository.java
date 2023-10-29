package com.usv.casino.repository;

import com.usv.casino.entity.Beneficiu;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BeneficiuRepository extends CrudRepository<Beneficiu, UUID> {

}
