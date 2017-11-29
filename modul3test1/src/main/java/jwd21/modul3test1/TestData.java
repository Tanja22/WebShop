package jwd21.modul3test1;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd21.modul3test1.model.Pivara;
import jwd21.modul3test1.model.Pivo;
import jwd21.modul3test1.service.PivaraService;
import jwd21.modul3test1.service.PivoService;


@Component
public class TestData {
	
	@Autowired
	private PivaraService pivaraService;
	@Autowired
	private PivoService pivoService;
	
	@PostConstruct
	public void init() {
		Pivara pivara1 = new Pivara();
		pivara1.setNaziv("Kabinet");
		pivara1.setDrzava("Srbija");
		pivara1.setPib("123457");
		pivaraService.save(pivara1);
		
		Pivo pivo1 = new Pivo();
		pivo1.setAlkohol(12.1);
		pivo1.setIbu(4.5);
		pivo1.setNaziv("Stalker");
		pivo1.setVrsta("Double IPA");
		pivo1.setStanje(20);
		pivo1.setPivara(pivara1);
		pivoService.save(pivo1);
		
	}

}
