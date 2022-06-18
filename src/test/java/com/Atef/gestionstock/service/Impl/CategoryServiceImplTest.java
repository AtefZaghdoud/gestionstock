package com.Atef.gestionstock.service.Impl;

import com.Atef.gestionstock.dto.CategoryDto;
import com.Atef.gestionstock.exception.EntityNotFoundException;
import com.Atef.gestionstock.exception.ErrorCodes;
import com.Atef.gestionstock.exception.InvalidEntityException;
import com.Atef.gestionstock.service.CategoryService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService service;

    @Test
    public void shouldSaveCategoryWithSuccess(){
        CategoryDto expectedCategory = CategoryDto.builder()
                .code("Cat test")
                .designation("Designation test")
                .idEntreprise(1)
                .build();
        CategoryDto savedCatgory = service.save(expectedCategory);

        Assertions.assertNotNull(savedCatgory);
        Assertions.assertNotNull(savedCatgory.getId());
        assertEquals(expectedCategory.getCode(),savedCatgory.getCode());
        assertEquals(expectedCategory.getDesignation(),savedCatgory.getDesignation());
        assertEquals(expectedCategory.getIdEntreprise(),savedCatgory.getIdEntreprise());

    }
    @Test
    public void shouldUpdateCategoryWithSuccess(){
        CategoryDto expectedCategory = CategoryDto.builder()
                .code("Cat test")
                .designation("Designation test")
                .idEntreprise(1)
                .build();
        CategoryDto savedCatgory = service.save(expectedCategory);

        CategoryDto categoryToUpdate = savedCatgory;
        categoryToUpdate.setCode("Cat update");

        savedCatgory = service.save(categoryToUpdate);

        Assertions.assertNotNull(categoryToUpdate);
        Assertions.assertNotNull(categoryToUpdate.getId());
        assertEquals(categoryToUpdate.getCode(),savedCatgory.getCode());
        assertEquals(categoryToUpdate.getDesignation(),savedCatgory.getDesignation());
        assertEquals(categoryToUpdate.getIdEntreprise(),savedCatgory.getIdEntreprise());
    }


    @Test
    public void shouldThrowInvalidEntityException(){
        CategoryDto expectedCategory = CategoryDto.builder().build();

        InvalidEntityException expectedException = assertThrows(InvalidEntityException.class, () -> service.save(expectedCategory));

        assertEquals(ErrorCodes.CATEGORY_NOT_VALID, expectedException.getErrorCodes() );
        assertEquals(1,expectedException.getErrors().size());
        assertEquals("Veuillez renseigner le code de la categorie ", expectedException.getErrors().get(0));


    }

    @Test
    public void shouldThrowEntityNotFoundException(){
        CategoryDto expectedCategory = CategoryDto.builder().build();

        EntityNotFoundException expectedException = assertThrows(EntityNotFoundException.class, () -> service.findById(0));

        assertEquals(ErrorCodes.CATEGORY_NOT_FOUND, expectedException.getErrorCodes() );
        assertEquals("Aucun category avec l'ID 0n'ete trouve dans la BDD ",expectedException.getMessage());


    }
    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundException2(){
       service.findById(0);
    }

}