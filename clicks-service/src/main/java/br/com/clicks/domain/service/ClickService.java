package br.com.clicks.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.clicks.domain.Click;
import br.com.clicks.domain.repository.ClickRepository;

@Service
public class ClickService {

	@Autowired
	private ClickRepository clickRepository;
	
	@Transactional
	public void save(Click click) {
        clickRepository.save(click);
	}

	@Transactional
    public void remove(Click click) {
	    clickRepository.delete(click);
    }

	@Transactional
    public void remove(Long id) {
	    Click click = get(id);
	    clickRepository.delete(click);
    }
	
	@Transactional
	public Click get(Long id) {
	    return clickRepository.findOne(id);
	}

	@Transactional
	public List<Click> findAll() {
		return clickRepository.findAll();
	}
}