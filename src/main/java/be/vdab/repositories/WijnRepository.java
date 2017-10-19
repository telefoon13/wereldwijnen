package be.vdab.repositories;

import be.vdab.entities.WijnenEntity;

import java.util.Optional;

public class WijnRepository extends AbstractRepository {


	public Optional<WijnenEntity> read(long id){
		return Optional.ofNullable(getEntityManager().find(WijnenEntity.class, id));
	}

	public WijnenEntity findById(long wijnid) {
		return getEntityManager().createNamedQuery("WijnenEntity.findById",WijnenEntity.class).setParameter("wijnid",wijnid).getSingleResult();
	}
}
