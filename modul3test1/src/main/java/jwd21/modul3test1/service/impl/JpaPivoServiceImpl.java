package jwd21.modul3test1.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd21.modul3test1.model.Pivo;
import jwd21.modul3test1.repository.PivoRepository;
import jwd21.modul3test1.service.PivoService;


@Service
@Transactional
public class JpaPivoServiceImpl implements PivoService{
	
	@Autowired
	private PivoRepository pivoRepository;

	

	@Override
	public Page<Pivo> findAll(int pageNum) {
		return pivoRepository.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Pivo findOne(Long id) {
		return  pivoRepository.findOne(id);
	}

	@Override
	public void save(Pivo pivo) {
		pivoRepository.save(pivo);
		
	}

	@Override
	public void remove(Long id) {
		pivoRepository.delete(id);
		
	}

	@Override
	public Page<Pivo> findByPivaraId(int pageNum, Long pivaraId) {
		return pivoRepository.findByPivaraId(pivaraId, new PageRequest(pageNum, 5));
	}

	@Override
	public Page<Pivo> pretraga(String naziv, Integer minIbu, Integer maxIbu, String nazivPivara, int page) {
		if(naziv != null ){
			naziv = "%" + naziv + "%";
		}
		if(nazivPivara != null ){
			nazivPivara = "%" + nazivPivara + "%";
		}
		return pivoRepository.pretraga(naziv, minIbu, maxIbu, nazivPivara, new PageRequest(page, 5));
	}

}
