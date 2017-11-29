package jwd21.modul3test1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd21.modul3test1.model.Pivo;


@Repository
public interface PivoRepository extends JpaRepository<Pivo, Long>{
	
	Page<Pivo> findByPivaraId(Long pivaraId, Pageable pageRequest);
	
	@Query("SELECT p FROM Pivo p WHERE "
			+ "(:naziv IS NULL or p.naziv like :naziv ) AND "
			+ "(:minIbu IS NULL OR p.ibu >= :minIbu  ) AND "
			+ "(:maxIbu IS NULL OR p.ibu <= :maxIbu) AND "
			+ "(:nazivPivara IS NULL or p.pivara.naziv like :nazivPivara )"
			)
	Page<Pivo> pretraga(
			@Param("naziv") String naziv, 
			@Param("minIbu") Integer minIbu,  
			@Param("maxIbu") Integer maxIbu,
			@Param("nazivPivara") String nazivPivara,
			Pageable pageRequest);

}
