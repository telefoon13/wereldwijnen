package be.vdab.repositories;

import be.vdab.entities.BestelbonnenEntity;

import java.util.Optional;

public class BestelBonRepository extends AbstractRepository {

	public Optional<BestelbonnenEntity> read(long id){
		return Optional.ofNullable(getEntityManager().find(BestelbonnenEntity.class, id));
	}

	public void create(BestelbonnenEntity bestelbon){
		getEntityManager().persist(bestelbon);
	}
}
