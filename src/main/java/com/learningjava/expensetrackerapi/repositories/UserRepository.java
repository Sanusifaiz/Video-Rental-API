package com.learningjava.expensetrackerapi.repositories;

import com.learningjava.expensetrackerapi.entity.User;
import com.learningjava.expensetrackerapi.exceptions.EtAuthException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    User findByEmailAndPassword(String email, String password) throws
            EtAuthException;

    User findOneByEmail(String email );
}
