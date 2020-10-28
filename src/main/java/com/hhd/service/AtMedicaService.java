package com.hhd.service;

import com.hhd.entities.AtencionMedica;

public interface AtMedicaService {

    public abstract AtencionMedica AddAtencion(AtencionMedica atMedica);
    public abstract AtencionMedica findAtMedicaByIdFicha(Long idFicha);
}
