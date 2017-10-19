package be.vdab.services;

import be.vdab.entities.SoortenEntity;
import be.vdab.repositories.SoortRepository;

import java.util.Optional;

public class SoortService extends AbstractService {

	private final SoortRepository soortRepository = new SoortRepository();

	public Optional<SoortenEntity> read(long id){
		return soortRepository.read(id);
	}
}
