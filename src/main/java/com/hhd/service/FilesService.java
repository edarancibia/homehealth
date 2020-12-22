package com.hhd.service;

import java.util.List;
import java.util.Map;

import com.hhd.entities.File;

public interface FilesService {

	public abstract File addFile(File file);
	List<File> getFilesByIdFicha(int idficha);
	public void deleteByIdFile(int idfile);
	public abstract File getByIdfile(int idfile);
	List<Map<String, Object>> fingFilesByRutPac(int rutpac);
}
