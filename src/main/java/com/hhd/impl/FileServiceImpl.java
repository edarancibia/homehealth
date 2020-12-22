package com.hhd.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhd.entities.File;
import com.hhd.respository.FilesRepository;
import com.hhd.service.FilesService;

@Service
public class FileServiceImpl implements FilesService {

	@Autowired
	public FilesRepository repository;
	
	@Override
	public File addFile(File file) {
		return repository.save(file);
	}

	@Override
	public List<File> getFilesByIdFicha(int idficha) {
		return repository.findByIdFicha(idficha);
	}

	@Override
	public void deleteByIdFile(int idfile) {
		File file = repository.findByIdFile(idfile);
		
		if (file != null) {
			repository.delete(file);
		}
		
	}

	@Override
	public File getByIdfile(int idfile) {
		return repository.findByIdFile(idfile);
	}

	@Override
	public List<Map<String, Object>> fingFilesByRutPac(int rutpac) {
		return repository.fingFilesByRutPac(rutpac);
	}

}
