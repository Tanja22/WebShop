package jwd21.modul3test1.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd21.modul3test1.model.Pivo;
import jwd21.modul3test1.web.dto.PivoDTO;

@Component
public class PivoToPivoDTO implements Converter<Pivo, PivoDTO>{
	

	@Override
	public PivoDTO convert(Pivo pivo) {
		PivoDTO dto = new PivoDTO();
		dto.setId(pivo.getId());
		dto.setNaziv(pivo.getNaziv());
		dto.setVrsta(pivo.getVrsta());
		dto.setAlkohol(pivo.getAlkohol());
		dto.setIbu(pivo.getIbu());
		dto.setStanje(pivo.getStanje());
		dto.setPivaraId(pivo.getPivara().getId());
		dto.setPivaraNaziv(pivo.getPivara().getNaziv());
		
		return dto;
	}
	
	public List<PivoDTO> convert(List<Pivo> piva){
		List<PivoDTO> ret = new ArrayList<PivoDTO>();
		
		for(Pivo p : piva){
			ret.add(convert(p));
		}
		
		return ret;
	}
	


}
