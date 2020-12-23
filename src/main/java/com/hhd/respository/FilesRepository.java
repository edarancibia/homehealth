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
			+ "from files f ,usuario u, ficha f2  \n"
			+ "where  f.rut_pac = f2.rut_pac and f.rut_usu = u.rutnum  and f.idficha = :idficha",nativeQuery = true)
	List<Map<String, Object>> fingFilesByRutPac(int idficha);

}
