package br.com.clicks.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.clicks.domain.Click;

@Repository
public interface ClickRepository extends CrudRepository<Click, Long>, ClickRepositoryCustom {
	
	List<Click> findAll();
}