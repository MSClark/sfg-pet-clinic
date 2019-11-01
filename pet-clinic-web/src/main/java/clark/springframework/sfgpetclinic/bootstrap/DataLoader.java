package clark.springframework.sfgpetclinic.bootstrap;

import clark.springframework.sfgpetclinic.model.Owner;
import clark.springframework.sfgpetclinic.model.Pet;
import clark.springframework.sfgpetclinic.model.PetType;
import clark.springframework.sfgpetclinic.model.Vet;
import clark.springframework.sfgpetclinic.services.OwnerService;
import clark.springframework.sfgpetclinic.services.PetTypeService;
import clark.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType saveCatType = petTypeService.save(cat);

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
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Holly");
        vet2.setLastName("Adams");
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
