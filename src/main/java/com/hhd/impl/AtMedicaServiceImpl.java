package com.hhd.impl;

import com.hhd.entities.AtencionMedica;
import com.hhd.respository.AtMedicaRepository;
import com.hhd.service.AtMedicaService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtMedicaServiceImpl implements AtMedicaService {

    @Autowired
    public AtMedicaRepository repository;

    @Override
    public AtencionMedica AddAtencion(AtencionMedica atMedica) {
        return repository.save(atMedica);
    }

	@Override
	public AtencionMedica findAtencionMedicaByIdFicha(int idFicha) {
		return repository.findAtencionMedicaByIdFicha(idFicha);
	}

	@Override
	public Map<String, Object> getDatosPacientePdf(int idficha) {
		return repository.getDatosPacientePdf(idficha);
	}

	@Override
	public Map<String, Object> getAntededentesPdf(int idficha) {
		return repository.getAntededentesPdf(idficha);
	}

	@Override
	public Map<String, Object> getMedicoPdf(int idficha) {
		return repository.getMedicoPdf(idficha);
	}

}
