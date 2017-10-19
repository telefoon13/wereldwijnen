package be.vdab.services;

import be.vdab.entities.WijnenEntity;
import be.vdab.exceptions.RecordAangepastException;
import be.vdab.repositories.WijnRepository;

import javax.persistence.OptimisticLockException;
import javax.persistence.RollbackException;
import java.math.BigDecimal;
import java.util.Optional;

public class WijnService extends AbstractService {

	private final WijnRepository wijnRepository = new WijnRepository();

	public Optional<WijnenEntity> read(long id){
		return wijnRepository.read(id);
	}

	public WijnenEntity findById(long wijnid) {
		return wijnRepository.findById(wijnid);
	}

	public void toevoegenInBestelling(long id, int aantal){
		beginTransaction();
		try{
			wijnRepository.read(id).ifPresent(wijnenEntity -> wijnenEntity.toevoegenInBestelling(aantal));
			commit();
		} catch (RollbackException ex){
			if (ex.getCause() instanceof OptimisticLockException){
				throw new RecordAangepastException();
			}
		} catch (RuntimeException ex){
			rollback();
			throw ex;
		}
	}
}
