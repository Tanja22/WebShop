package jwd21.modul3test1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd21.modul3test1.model.Pivo;

public interface PivoService {
	Page<Pivo> findAll(int pageNum);
	Pivo findOne(Long id);
	void save(Pivo pivo);
	void remove(Long id);
	Page<Pivo> findByPivaraId(int pageNum, Long pivaraId);
	Page<Pivo> pretraga(
			@Param("naziv") String naziv, 
			@Param("minIbu") Integer minIbu, 
			@Param("maxibu") Integer maxIbu,
			@Param("nazivPivara") String nazivPivara,
			int page);

}
