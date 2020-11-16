package com.pifrans.modules.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.modules.users.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
