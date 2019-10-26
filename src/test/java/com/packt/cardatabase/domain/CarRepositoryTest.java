package com.packt.cardatabase.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository repository;

    private OwnerRepository ownerRepository;

    @Test
    public void saveCarTest(){
        Owner owner = new Owner("John" , "Johnson");
        entityManager.persistAndFlush(owner);
        Car car = new Car("Tesla", "Model X", "White", "ABC-1234", 2017, 86000,owner);
        entityManager.persistAndFlush(car);
        assertThat(car.getId()).isNotNull();
    }

    @Test
    public void deleteCars() {
        Owner owner1 = new Owner("John" , "Johnson");
        entityManager.persistAndFlush(owner1);
        Owner owner2 = new Owner("Mary" , "Robinson");
        entityManager.persistAndFlush(owner2);

        entityManager.persistAndFlush(new Car("Tesla", "Model X", "White", "ABC-1234", 2017, 86000,owner1));
        entityManager.persistAndFlush(new Car("Mini", "Cooper", "Yellow", "BWS-3007", 2015, 24500,owner2));
        repository.deleteAll();
        assertThat(repository.findAll()).isEmpty();
    }
}