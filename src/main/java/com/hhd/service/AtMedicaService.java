package com.hhd.service;

import java.util.List;
import java.util.Map;

import com.hhd.entities.AtencionMedica;

public interface AtMedicaService {

    public abstract AtencionMedica AddAtencion(AtencionMedica atMedica);
    public abstract AtencionMedica findAtencionMedicaByIdFicha(int idFicha);
}
