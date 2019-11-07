package clark.springframework.sfgpetclinic.repositories;

import clark.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
