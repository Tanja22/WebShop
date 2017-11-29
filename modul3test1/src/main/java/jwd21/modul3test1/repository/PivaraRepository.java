package jwd21.modul3test1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd21.modul3test1.model.Pivara;


@Repository
public interface PivaraRepository extends JpaRepository<Pivara, Long>{

}
