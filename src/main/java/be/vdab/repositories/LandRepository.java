package be.vdab.repositories;

import be.vdab.entities.LandenEntity;

import java.util.List;
import java.util.Optional;

public class LandRepository extends AbstractRepository {

	public List<LandenEntity> findAll(){
		return getEntityManager().createNamedQuery("LandenEntity.findAll", LandenEntity.class).getResultList();
	}

	public Optional<LandenEntity> read(long id){
		return Optional.ofNullable(getEntityManager().find(LandenEntity.class, id));
	}
}
