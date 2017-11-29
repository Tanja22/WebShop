package jwd21.modul3test1.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd21.modul3test1.model.Pivo;
import jwd21.modul3test1.service.PivaraService;
import jwd21.modul3test1.service.PivoService;
import jwd21.modul3test1.web.dto.PivoDTO;

@Component
public class PivoDTOToPivo implements Converter<PivoDTO, Pivo>{
	
	@Autowired
	private PivoService pivoService;
	@Autowired
	private PivaraService pivaraService;

	@Override
	public Pivo convert(PivoDTO dto) {
		Pivo pivo;
		if(dto.getId()==null){
			pivo = new Pivo();
			pivo.setPivara(
					pivaraService.findOne(
							dto.getPivaraId()));
		}else{
			pivo = pivoService.findOne(dto.getId());
		}
		pivo.setNaziv(dto.getNaziv());
		pivo.setVrsta(dto.getVrsta());
		pivo.setAlkohol(dto.getAlkohol());
		pivo.setIbu(dto.getIbu());
		pivo.setStanje(dto.getStanje());
		
		return pivo;
	}

}
