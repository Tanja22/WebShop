package jwd21.modul3test1.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd21.modul3test1.model.Pivo;
import jwd21.modul3test1.service.PivoService;
import jwd21.modul3test1.support.PivoDTOToPivo;
import jwd21.modul3test1.support.PivoToPivoDTO;
import jwd21.modul3test1.web.dto.PivoDTO;

@RestController
@RequestMapping("/api/piva")
public class ApiPivoController {
	
	@Autowired
	private PivoService pivoService;
	@Autowired
	private PivoToPivoDTO toDTO;
	@Autowired
	private PivoDTOToPivo toPivo;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PivoDTO>> get(
			@RequestParam(required=false) String naziv,
			@RequestParam(required=false) Integer minIbu,
			@RequestParam(required=false) Integer maxIbu,
			@RequestParam(required=false) String nazivPivara,
			@RequestParam(defaultValue="0") int pageNum){
		
		Page<Pivo> piva;
		if(naziv != null || minIbu != null || maxIbu != null || nazivPivara != null) {
			piva = pivoService.pretraga(naziv, minIbu, maxIbu, nazivPivara, pageNum);
		}else{
			piva = pivoService.findAll(pageNum);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(piva.getTotalPages()) );
		return  new ResponseEntity<List<PivoDTO>>(
				toDTO.convert(piva.getContent()),
				headers,
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}") 
	public ResponseEntity<PivoDTO> get(@PathVariable Long id){
		Pivo pivo = pivoService.findOne(id);
		if(pivo == null){
			return  new ResponseEntity<PivoDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PivoDTO>(toDTO.convert(pivo), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<PivoDTO> add(@RequestBody PivoDTO novoPivo){
		Pivo pivo = toPivo.convert(novoPivo); 
		pivoService.save(pivo);
		
		return new ResponseEntity<PivoDTO>(toDTO.convert(pivo),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public ResponseEntity<PivoDTO> edit(@PathVariable Long id,
											@RequestBody PivoDTO izmenjeno){
		if(!id.equals(izmenjeno.getId())){
			return new ResponseEntity<PivoDTO>(HttpStatus.BAD_REQUEST);
		}
		
		Pivo pivo = toPivo.convert(izmenjeno); 
		pivoService.save(pivo);
		
		return new ResponseEntity<PivoDTO>(toDTO.convert(pivo),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<PivoDTO> delete(@PathVariable Long id){
		pivoService.remove(id);
		return new ResponseEntity<PivoDTO>(HttpStatus.NO_CONTENT);
	}

}
