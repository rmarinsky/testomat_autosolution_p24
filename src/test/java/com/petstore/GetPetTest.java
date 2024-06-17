package com.petstore;

import com.petstore.api.dto.Pet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GetPetTest {

    @Test
    @DisplayName("Olool test for get pet ")
    void oloolTestForGetPet() {
       new PetController().createPet(new Pet().name("dog").status(Pet.StatusEnum.AVAILABLE));
    }

}
