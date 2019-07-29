package pl.myproject.repository;

import pl.myproject.model.RealEstateAgency;

import java.util.ArrayList;
import java.util.List;

public class AgencyRepository {
    private List<RealEstateAgency> agenciesRepository = new ArrayList();

    public AgencyRepository() {
    }

    public List<RealEstateAgency> getAgenciesRepository() {
        return agenciesRepository;
    }

    public void setAgenciesRepository(List<RealEstateAgency> agenciesRepository) {
        this.agenciesRepository = agenciesRepository;
    }

    public void addAgency(RealEstateAgency realEstateAgency){
        this.agenciesRepository.add(realEstateAgency);
    }
}
