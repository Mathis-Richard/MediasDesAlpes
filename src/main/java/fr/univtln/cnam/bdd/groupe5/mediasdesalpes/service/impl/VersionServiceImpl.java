package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository.IVersionRepository;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.IVersionService;
import org.springframework.stereotype.Service;

@Service
public class VersionServiceImpl implements IVersionService {

    private final IVersionRepository versionRepository;

    public VersionServiceImpl(IVersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    @Override
    public String getVersion() {
        return versionRepository.getVersion();
    }

}
