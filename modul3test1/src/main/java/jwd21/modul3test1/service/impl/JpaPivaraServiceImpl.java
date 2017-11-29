package jwd21.modul3test1.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd21.modul3test1.model.Pivara;
import jwd21.modul3test1.repository.PivaraRepository;
import jwd21.modul3test1.service.PivaraService;

@Service
@Transactional
public class JpaPivaraServiceImpl implements PivaraService{
	@Autowired
	private PivaraRepository pivaraRepository;

	@Override
	public List<Pivara> findAll() {
		return pivaraRepository.findAll();
	}

	@Override
	public Pivara findOne(Long id) {
		return pivaraRepository.findOne(id);
	}

	@Override
	public void save(Pivara pivara) {
		pivaraRepository.save(pivara);
		
	}

}
