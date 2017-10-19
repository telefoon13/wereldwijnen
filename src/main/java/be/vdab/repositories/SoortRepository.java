package be.vdab.repositories;

import be.vdab.entities.SoortenEntity;

import java.util.List;
import java.util.Optional;

public class SoortRepository extends AbstractRepository {

	public Optional<SoortenEntity> read(long id){
		return Optional.ofNullable(getEntityManager().find(SoortenEntity.class, id));
	}
}
