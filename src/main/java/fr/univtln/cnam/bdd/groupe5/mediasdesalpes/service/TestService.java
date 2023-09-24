package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository.ITestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestService {
    private final ITestRepository testRepository;

    @Autowired
    public TestService(ITestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public String getVersion() {
        return testRepository.getVersion();
    }


    public Integer getTest() {
        return testRepository.getTest();
    }
}
