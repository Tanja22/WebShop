package jwd21.modul3test1.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd21.modul3test1.model.Pivara;
import jwd21.modul3test1.model.Pivo;
import jwd21.modul3test1.service.PivaraService;
import jwd21.modul3test1.service.PivoService;
import jwd21.modul3test1.support.PivaraToPivaraDTO;
import jwd21.modul3test1.support.PivoToPivoDTO;
import jwd21.modul3test1.web.dto.PivaraDTO;
import jwd21.modul3test1.web.dto.PivoDTO;

@RestController
@RequestMapping(value="/api/pivare")
public class ApiPivaraController {
	
	@Autowired
	private PivaraService pivaraService;
	@Autowired
	private PivoService pivoService;
	@Autowired
	private PivaraToPivaraDTO toDTO;
	@Autowired
	private PivoToPivoDTO toPivoDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PivaraDTO>> get(){
		return new ResponseEntity<List<PivaraDTO>>(toDTO.convert(pivaraService.findAll()),
									HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PivaraDTO> get(@PathVariable Long id){
		
		Pivara pivara = pivaraService.findOne(id);
		
		if(pivara == null){
			return new ResponseEntity<PivaraDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PivaraDTO>(toDTO.convert(pivara),
									HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/{pivaraId}/piva")
	public ResponseEntity<List<PivoDTO>> pivaPivare(
												@PathVariable Long pivaraId,
												@RequestParam(defaultValue="0") int pageNum){
		Page<Pivo> piva = pivoService.findByPivaraId(pageNum, pivaraId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(piva.getTotalPages()) );
		return  new ResponseEntity<List<PivoDTO>>(
				toPivoDTO.convert(piva.getContent()),
				headers,
				HttpStatus.OK);
		
	}

}
