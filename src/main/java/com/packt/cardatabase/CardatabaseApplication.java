package com.packt.cardatabase;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;

import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CardatabaseApplication {
    @Autowired
    private CarRepository repository;
    @Autowired
    private OwnerRepository ownerRepository;

    public static void main(String[] args) {
        SpringApplication.run(CardatabaseApplication.class, args);
    }


    @Bean
    CommandLineRunner runner() {
//		return args -> {
//			//デモデータ作成コマンドをここに書くよ
//			repository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000));
//			repository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2014, 29000));
//			repository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2018, 39000));
//		}
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Owner owner1 = new Owner("John","Johnson");
                ownerRepository.save(owner1);
                Owner owner2 = new Owner("Mary" , "Robinson");
                ownerRepository.save(owner2);
                Car car1 = new Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000,owner1);
                repository.save(car1);
                Car car2 = new Car("Nissan", "Leaf", "White", "SSJ-3002", 2014, 29000,owner2);
                repository.save(car2);
                Car car3 = new Car("Toyota", "Prius", "Silver", "KKO-0212", 2018, 39000,owner2);
                repository.save(car3);
            }
        };
    }

}
