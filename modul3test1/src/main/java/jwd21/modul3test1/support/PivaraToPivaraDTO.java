package jwd21.modul3test1.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd21.modul3test1.model.Pivara;
import jwd21.modul3test1.web.dto.PivaraDTO;


@Component
public class PivaraToPivaraDTO implements Converter<Pivara, PivaraDTO>{

	@Override
	public PivaraDTO convert(Pivara pivara) {
		PivaraDTO pivaraDTO = new PivaraDTO();
		pivaraDTO.setId(pivara.getId());
		pivaraDTO.setNaziv(pivara.getNaziv());
		pivaraDTO.setPib(pivara.getPib());
		pivaraDTO.setDrzava(pivara.getDrzava());
		
		return pivaraDTO;
	}
	
	public List<PivaraDTO> convert(List<Pivara> pivare) {
		List<PivaraDTO> ret = new ArrayList<PivaraDTO>();
		
		for(Pivara p : pivare){
			ret.add(convert(p));
		}
		
		return ret;
	}

}
