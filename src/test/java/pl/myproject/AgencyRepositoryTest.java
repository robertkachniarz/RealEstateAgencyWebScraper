package pl.myproject;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import pl.myproject.model.RealEstateAgency;
import pl.myproject.repository.AgencyRepository;

public class AgencyRepositoryTest {

    @Test
    public void shouldAddAgencyToRepository(){
        //given
        RealEstateAgency realEstateAgency = new RealEstateAgency("Agencja 1", "ul. Spokojna 5", "+48601500700");
        AgencyRepository agencyRepository = new AgencyRepository();

        //when
        agencyRepository.addAgency(realEstateAgency);

        //then
        Assertions.assertThat(agencyRepository.getAgenciesRepository()).contains(realEstateAgency);
    }

}
