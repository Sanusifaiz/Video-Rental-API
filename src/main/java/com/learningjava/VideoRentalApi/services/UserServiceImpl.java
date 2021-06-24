package com.learningjava.expensetrackerapi.services;

import com.learningjava.expensetrackerapi.entity.User;
import com.learningjava.expensetrackerapi.exceptions.EtAuthException;
import com.learningjava.expensetrackerapi.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Pattern;


@Service
@Transactional       //rolls back any method when eroor occurs
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        try {
            if(email != null && password !=null){
                email = email.toLowerCase();

                var user = repository.findOneByEmail(email);
                if(user != null) {
                  if(!BCrypt.checkpw(password, user.getPassword())){
                      throw new EtAuthException("Incorrect Password");
                  }
                  return user;
                }
                throw new EtAuthException("Invalid email/password");
            }
            throw new EtAuthException("Email/Password cannot be null");
        }catch (Exception e){
            throw new EtAuthException("Invalid email/password");
        }
    }

    @Override
    public Optional<User> registerUser(User user) throws EtAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(user.getEmail() != null ) {
           String email = user.getEmail().toLowerCase();
            //email = email.toLowerCase();
        }
        if(!pattern.matcher(user.getEmail()).matches()){
            throw new EtAuthException("Invalid email format");
        }
        User saveUser = repository.findOneByEmail(user.getEmail());
        if(saveUser != null ) {
            throw new EtAuthException("email already in use");
        }
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashedPassword);
        repository.save(user);
        return repository.findById(user.getUserId());
    }
}
