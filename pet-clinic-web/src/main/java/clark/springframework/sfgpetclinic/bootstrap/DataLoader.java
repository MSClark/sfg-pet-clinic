package clark.springframework.sfgpetclinic.bootstrap;

import clark.springframework.sfgpetclinic.model.Owner;
import clark.springframework.sfgpetclinic.model.Vet;
import clark.springframework.sfgpetclinic.services.OwnerService;
import clark.springframework.sfgpetclinic.services.VetService;
import clark.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import clark.springframework.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Matthew");
        owner1.setLastName("Clark");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Brendan");
        owner2.setLastName("Clary");
        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Mike");
        vet1.setLastName("Jones");
        vetService.save(vet1);


        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Holly");
        vet2.setLastName("Adams");
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
