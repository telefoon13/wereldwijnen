package be.vdab.services;

import be.vdab.entities.BestelbonnenEntity;
import be.vdab.repositories.BestelBonRepository;
import be.vdab.valueobjects.BestelbonLijnen;

import java.util.Optional;

public class BestelBonService extends AbstractService {

	private final BestelBonRepository bestelBonRepository = new BestelBonRepository();

	public Optional<BestelbonnenEntity> read(long id){
		return bestelBonRepository.read(id);
	}

	public void create(BestelbonnenEntity bestelbon){
		beginTransaction();
		try{
			bestelBonRepository.create(bestelbon);
			commit();
		} catch (RuntimeException ex){
			rollback();
			throw ex;
		}
	}

	public void bestelbonLijnToevoegen(long id, BestelbonLijnen bestelbonLijn){
		beginTransaction();
		try {
			bestelBonRepository.read(id).ifPresent(bestelbonnenEntity -> bestelbonnenEntity.addBestelbonLijnen(bestelbonLijn));
			commit();
		} catch (RuntimeException ex){
			rollback();
			throw ex;
		}
	}

}
