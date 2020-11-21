package com.hhd.service;

import com.hhd.entities.EpicrisisMedica;

public interface EpiMedicaService {

	public abstract EpicrisisMedica addEpicrisisMedica(EpicrisisMedica epicrisis);
	public abstract EpicrisisMedica findEpicrisisMedicaByIdFicha(int idficha);
}
