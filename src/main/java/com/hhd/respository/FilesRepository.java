package com.hhd.respository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hhd.entities.File;

@Repository
public interface FilesRepository extends JpaRepository<File, Long> {
	
	List<File> findByIdFicha(int idficha);
	public abstract File findByIdFile(int idfile);
	
	@Query(value = "SELECT DATE_FORMAT(f.fecha,'%d-%m-%Y') as fecha, f.idficha ,f.descripcion ,f.url,\n"
			+ "UPPER(CONCAT(u.ape_pat,' ',u.nombre)) as usuario \n"
			+ "from files f ,usuario u \n"
			+ "where  f.rut_pac = :rutpac and f.rut_usu = u.rutnum ",nativeQuery = true)
	List<Map<String, Object>> fingFilesByRutPac(int rutpac);

}
