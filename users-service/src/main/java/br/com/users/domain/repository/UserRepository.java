package br.com.users.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.users.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
	List<User> findAll();

	User findOneByNameAndPassword(String name, String password);
	
	User findOneByName(String name);
}