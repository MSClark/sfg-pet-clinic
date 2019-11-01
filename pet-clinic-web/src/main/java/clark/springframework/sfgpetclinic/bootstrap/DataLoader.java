package clark.springframework.sfgpetclinic.bootstrap;

import clark.springframework.sfgpetclinic.model.*;
import clark.springframework.sfgpetclinic.services.OwnerService;
import clark.springframework.sfgpetclinic.services.PetTypeService;
import clark.springframework.sfgpetclinic.services.SpecialtyService;
import clark.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {

        if(petTypeService.findAll().size() == 0){

            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType saveCatType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("radiology");
        Specialty savedRadiology = specialtyService.save(radiology);
        //makes sure you get the persisted version rather than whats instantiated here - persists to map with ID

        Specialty surgery = new Specialty();
        surgery.setDescription("surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Matthew");
        owner1.setLastName("Clark");
        owner1.setAddress("3819 s mill ave");
        owner1.setCity("tempe");
        owner1.setPhoneNumber("5202804699");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Brendan");
        owner2.setLastName("Clary");
        owner2.setCity("elk grove");
        owner2.setAddress("152 essex way");
        owner2.setPhoneNumber("911");
        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Mike");
        vet1.setLastName("Jones");
        vet1.getSpecialties().add(savedSurgery);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Holly");
        vet2.setLastName("Adams");
        vet2.getSpecialties().add(savedDentistry);
        vetService.save(vet2);

        System.out.println("Loaded Vets...");

        Pet mattsPet = new Pet();
        mattsPet.setPetType(dog);
        mattsPet.setOwner(owner1);
        mattsPet.setBirthDate(LocalDate.now());
        mattsPet.setName("jake");
        owner1.getPets().add(mattsPet);

        Pet brendansPet = new Pet();
        brendansPet.setPetType(cat);
        brendansPet.setOwner(owner2);
        brendansPet.setBirthDate(LocalDate.now());
        brendansPet.setName("abby");
        owner2.getPets().add(brendansPet);
    }
}
