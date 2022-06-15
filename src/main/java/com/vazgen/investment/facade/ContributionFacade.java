package com.vazgen.investment.facade;

import com.vazgen.investment.dto.ContributionCreateDTO;
import com.vazgen.investment.dto.ContributionUpdateDTO;
import com.vazgen.investment.model.Contribution;
import com.vazgen.investment.service.ContributionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContributionFacade {

    private final ContributionService contributionService;

    public Contribution create(final ContributionCreateDTO request) {
        return contributionService.create(request);
    }

    public Contribution update(final ContributionUpdateDTO request, final long id)  {
        return contributionService.update(request, id);
    }

    public void delete(final long id)   {
        contributionService.delete(id);
    }

    public Contribution findById(final long id) {
        return contributionService.findById(id);
    }

    public List<Contribution> findAll() {
        return contributionService.findAll();
    }

}
