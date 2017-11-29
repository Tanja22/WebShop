package jwd21.modul3test1.service;

import java.util.List;

import jwd21.modul3test1.model.Pivara;


public interface PivaraService {
	List<Pivara> findAll();
	Pivara findOne(Long id);
	void save(Pivara pivara);

}
