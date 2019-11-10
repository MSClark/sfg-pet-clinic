package clark.springframework.sfgpetclinic.services.map;

import clark.springframework.sfgpetclinic.model.Owner;
import clark.springframework.sfgpetclinic.model.Pet;
import clark.springframework.sfgpetclinic.services.OwnerService;
import clark.springframework.sfgpetclinic.services.PetService;
import clark.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        if(object != null){
            if(object.getPets() != null){
                object.getPets().forEach(pet -> {
                    if(pet.getPetType() == null){
                        pet.setPetType(petTypeService.save(pet.getPetType())); // if the pettype hasnt been set get it from the pet and set it to the pettype
                    } else {
                        throw new RuntimeException("Pet type is required");
                    }
                    if(pet.getId() == null){
                        Pet savedPet = petService.save(pet); //save the pet and get the id
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return (Owner) super.map.entrySet().stream().filter(entry -> lastName.equals(entry.getValue())).map(Map.Entry::getKey);
    }
}
