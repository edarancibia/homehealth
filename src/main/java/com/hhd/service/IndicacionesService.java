package com.hhd.service;

import com.hhd.entities.IndicacionesAlta;

public interface IndicacionesService {

	public abstract IndicacionesAlta addIndicaciones(IndicacionesAlta indicaciones);
	public abstract IndicacionesAlta findIndicacionesAltaByIdFicha(int idficha);
}
