package com.hhd.service;

import java.util.List;
import java.util.Map;

import com.hhd.entities.AtencionMedica;

public interface AtMedicaService {

    public abstract AtencionMedica AddAtencion(AtencionMedica atMedica);
    public abstract AtencionMedica findAtencionMedicaByIdFicha(int idFicha);
    Map<String, Object> getDatosPacientePdf(int idficha);
    Map<String, Object> getAntededentesPdf(int idficha);
    Map<String, Object> getMedicoPdf(int idficha);
}
