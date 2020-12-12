package com.hhd.respository;

import com.hhd.entities.AtencionMedica;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AtMedicaRepository extends JpaRepository<AtencionMedica, Long> {

    public abstract AtencionMedica findAtencionMedicaByIdFicha(int idFicha);
    
    //DATOS DEL PACIENTE PARA PDF
    @Query(value = "SELECT \n"
    		+ "UPPER(CONCAT(p.a_pat,' ',p.a_mat,' ',p.nombre)) as nombre,CONCAT(p.rut_num,'-',p.rut_dig) as rut ,\n"
    		+ "CONCAT(i.edad, ' ','Años') as edad ,\n"
    		+ "CASE \n"
    		+ "	when p.id_prevision = 1 then 'FONASA' \n"
    		+ "	when p.id_prevision = 2 then 'PARTICULAR' \n"
    		+ "	when p.id_prevision = 3 then 'ISAPRE' \n"
    		+ "END as prevision \n"
    		+ "from ingreso i ,ficha f ,paciente p \n"
    		+ "where f.idficha = i.id_ficha and f.rut_pac = p.rut_num \n"
    		+ "and f.idficha = :idficha",nativeQuery = true)
    Map<String, Object> getDatosPacientePdf(int idficha);
    
    //ANTECEDENTES MORBIDOS PARA PDF
    @Query(value = "SELECT \n"
    		+ "CASE \n"
    		+ "	WHEN am.hta = 1 then 'SI'\n"
    		+ "	ELSE 'NO'\n"
    		+ "END as hta,\n"
    		+ "CASE \n"
    		+ "	WHEN am.dld = 1 THEN 'SI'\n"
    		+ "	ELSE 'NO'\n"
    		+ "end as dld,\n"
    		+ "CASE \n"
    		+ "	WHEN am.tbc = 1 THEN 'SI'\n"
    		+ "	ELSE 'NO'\n"
    		+ "end as tbc,\n"
    		+ "CASE \n"
    		+ "	WHEN am.epoc = 1 THEN 'SI'\n"
    		+ "	ELSE 'NO'\n"
    		+ "end as epoc,\n"
    		+ "CASE \n"
    		+ "	WHEN am.LCFA = 1 THEN 'SI'\n"
    		+ "	ELSE 'NO'\n"
    		+ "end as lcfa,\n"
    		+ "case\n"
    		+ "	WHEN am.acxfa = 1 THEN 'SI'\n"
    		+ "	ELSE 'NO'\n"
    		+ "end as acxfa,\n"
    		+ "CASE \n"
    		+ "	when am.acv = 1 then 'SI'\n"
    		+ "	ELSE 'NO'\n"
    		+ "END as acv,\n"
    		+ "CASE \n"
    		+ "	when am.depre = 1 then 'SI'\n"
    		+ "	ELSE 'NO'\n"
    		+ "END as depre,\n"
    		+ "CASE \n"
    		+ "	when am.ob = 1 then 'SI'\n"
    		+ "	ELSE 'NO'\n"
    		+ "END as ob,\n"
    		+ "CASE \n"
    		+ "	when am.dm = 1 then 'SI'\n"
    		+ "	ELSE 'NO'\n"
    		+ "END as dm,\n"
    		+ "CASE \n"
    		+ "	when am.ca = 1 then 'SI'\n"
    		+ "	ELSE 'NO'\n"
    		+ "END as ca,\n"
    		+ "CASE \n"
    		+ "	when am.cardio = 1 then 'SI'\n"
    		+ "	ELSE 'NO'\n"
    		+ "END as cardio\n"
    		+ "FROM atencion_medica am \n"
    		+ "where am.id_ficha = :idficha", nativeQuery = true)
    Map<String, Object> getAntededentesPdf(int idficha);
    
    @Query(value = "SELECT \n"
    		+ "UPPER(CONCAT(u.nombre,' ',u.ape_pat,' ',u.ape_mat)) as medico, u.rutnum \n"
    		+ "from atencion_medica am ,usuario u \n"
    		+ "where am.rut_usu = u.rutnum and am .id_ficha = :idficha",nativeQuery = true)
    Map<String, Object> getMedicoPdf(int idficha);
 
}
