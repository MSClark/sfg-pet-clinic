package clark.springframework.sfgpetclinic.services;

import clark.springframework.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{ // owner and long map/correlate to the types - generic type and ID of the parent interface

    Owner findByLastName(String lastName);

}
