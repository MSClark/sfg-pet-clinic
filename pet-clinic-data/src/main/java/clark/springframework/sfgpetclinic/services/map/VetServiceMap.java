package clark.springframework.sfgpetclinic.services.map;

import clark.springframework.sfgpetclinic.model.Specialty;
import clark.springframework.sfgpetclinic.model.Vet;
import clark.springframework.sfgpetclinic.services.SpecialtyService;
import clark.springframework.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {

        if(object.getSpecialties().size() > 0){
            object.getSpecialties().forEach(specialty -> {
                if(specialty.getId() == null){
                    Specialty savedSpecialty = specialtyService.save(specialty);
                    /*
                        makes sure if there's a specialty that hasn't been persisted to do so and set the id in doing so
                        i.e. if there's any specialties with a null id save it and make sure the specialty in the list has that new ID value
                     */
                    specialty.setId(savedSpecialty.getId());
                } else {

                }
            });
        }
        return super.save(object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
