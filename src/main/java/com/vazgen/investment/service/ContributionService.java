package com.vazgen.investment.service;

import com.vazgen.investment.dao.ContributionRepository;
import com.vazgen.investment.dto.ContributionCreateDTO;
import com.vazgen.investment.dto.ContributionDTO;
import com.vazgen.investment.dto.ContributionUpdateDTO;
import com.vazgen.investment.dto.ProjectUpdateDTO;
import com.vazgen.investment.exception.ResourceNotFoundException;
import com.vazgen.investment.model.Contribution;
import com.vazgen.investment.model.entity.ContributionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContributionService {

    private final ContributionRepository contributionRepository;
    private final ProjectService projectService;

    public Contribution create(final ContributionCreateDTO request) {
        //Своеобразная проверка на существование проекта
        // TODO: 15.06.2022  Почему-то выписывает без уточнения о том, что не найден именно проект
        projectService.findById(request.getProjectId());
        ContributionEntity entity = new ContributionEntity(request);
        contributionRepository.save(entity);
        return new ContributionDTO(entity);
    }

    public Contribution update(final ContributionUpdateDTO request, final long id) {
        ContributionEntity entity = contributionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contribution", id));

        // TODO: 15.06.2022 Подумать о механике присвоения amount
        if(!entity.isApproved() && request.isApproved().orElse(false))  {
            projectService.update(new ProjectUpdateDTO(entity.getAmount() + projectService.findById(entity.getProjectId()).getCollectedMoney()),
                    entity.getProjectId());
        }
        //Вытаскивание isApproved из Optional без проверки на null неопасно из-за предварительной проверки на isPresent() и &&
        if(entity.isApproved() && request.isApproved().isPresent() && !request.isApproved().get())  {
            projectService.update(new ProjectUpdateDTO(projectService.findById(entity.getProjectId()).getCollectedMoney() - entity.getAmount()),
                    entity.getProjectId());
        }

        entity = entity.withAccount(request.getAccount().orElse(entity.getAccount()))
                .withAmount(request.getAmount().orElse(entity.getAmount()))
                .withApproved(request.isApproved().orElse(entity.isApproved()));
        contributionRepository.save(entity);
        return new ContributionDTO(entity);
    }

    public void delete(final long id)   {
        ContributionEntity entity = contributionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contribution", id));

        if(entity.isApproved())  {
            projectService.update(new ProjectUpdateDTO(projectService.findById(entity.getProjectId()).getCollectedMoney() - entity.getAmount()),
                    entity.getProjectId());
        }
        contributionRepository.delete(entity);
    }

    public Contribution findById(final long id) {
        return new ContributionDTO(contributionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contribution", id)));
    }

    public List<Contribution> findAll() {
        List<ContributionEntity> contributionEntities = new ArrayList<>();
        contributionRepository.findAll().forEach(contributionEntities::add);
        return contributionEntities.stream().map(ContributionDTO::new).collect(Collectors.toList());
    }

}
