package clark.springframework.sfgpetclinic.bootstrap;

import clark.springframework.sfgpetclinic.model.*;
import clark.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;
    private final PetService petService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialtyService specialtyService, VisitService visitService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
        this.petService = petService;
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
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty surgery = new Specialty();
        surgery.setDescription("surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Specialty radiology = new Specialty();
        radiology.setDescription("radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Owner owner1 = new Owner();
        owner1.setFirstName("Matthew");
        owner1.setLastName("Clark");
        owner1.setAddress("3918 s mill ave");
        owner1.setCity("tempe");
        owner1.setPhoneNumber("5202804699");
        ownerService.save(owner1);

        Pet mattsPet = new Pet();
        mattsPet.setPetType(savedDogPetType);
        mattsPet.setOwner(owner1);
        mattsPet.setBirthDate(LocalDate.now());
        mattsPet.setName("Jake");
        owner1.getPets().add(mattsPet);
        petService.save(mattsPet);

        Owner owner2 = new Owner();
        owner2.setFirstName("Brendan");
        owner2.setLastName("Clary");
        owner2.setAddress("153 essex ct");
        owner2.setCity("egv");
        owner2.setPhoneNumber("5202804690");
        ownerService.save(owner2);

        Pet brendansPet = new Pet();
        brendansPet.setPetType(cat);
        brendansPet.setOwner(owner2);
        brendansPet.setBirthDate(LocalDate.now());
        brendansPet.setName("Emmy");
        owner2.getPets().add(brendansPet);
        petService.save(brendansPet);

        System.out.println("Loaded Owners...");

        Visit catVisit = new Visit();
        catVisit.setPet(mattsPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("UTI");
        visitService.save(catVisit);

        Vet vet1 = new Vet();
        vet1.setFirstName("Mike");
        vet1.setLastName("Jones");
        vet1.getSpecialties().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Michelle");
        vet2.setLastName("Jonas");
        vet2.getSpecialties().add(savedDentistry);
        vetService.save(vet2);

    }
}
