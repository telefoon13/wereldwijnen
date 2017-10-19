package be.vdab.services;

import be.vdab.entities.LandenEntity;
import be.vdab.repositories.LandRepository;

import java.util.List;
import java.util.Optional;

public class LandService extends AbstractService {

	private final LandRepository landRepository = new LandRepository();

	public List<LandenEntity> findAll(){
		return landRepository.findAll();
	}

	public Optional<LandenEntity> read(long id){
		return landRepository.read(id);
	}
}
