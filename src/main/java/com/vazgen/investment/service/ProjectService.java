package com.vazgen.investment.service;

import com.vazgen.investment.dao.ProjectRepository;
import com.vazgen.investment.dto.ProjectCreateDTO;
import com.vazgen.investment.dto.ProjectDTO;
import com.vazgen.investment.dto.ProjectFindDTO;
import com.vazgen.investment.dto.ProjectUpdateDTO;
import com.vazgen.investment.exception.ResourceNotFoundException;
import com.vazgen.investment.model.Project;
import com.vazgen.investment.model.entity.ProjectEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project create(final ProjectCreateDTO request) {
        ProjectEntity entity = new ProjectEntity(request);
        projectRepository.save(entity);
        return new ProjectDTO(entity);
    }

    public Project update(final ProjectUpdateDTO request, final long id) {
        ProjectEntity entity = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project", id));
        entity = entity.withType(request.getType().orElse(entity.getType()))
                .withStatus(request.getStatus().orElse(entity.getStatus()))
                .withRequiredMoney(request.getRequiredMoney().orElse(entity.getRequiredMoney()))
                .withCollectedMoney(request.getCollectedMoney().orElse(entity.getCollectedMoney()))
                .withTitle(request.getTitle().orElse(entity.getTitle()))
                .withArticle(request.getArticle().orElse(entity.getArticle()))
                .withPreview(request.getPreview().orElse(entity.getPreview()))
                .withTagList(request.getTagList().orElse(entity.getTagList()))
                .withContributionList(request.getContributionList().orElse(entity.getContributionList()))
                .withUpdatedAt(LocalDateTime.now());
        // TODO: 06.06.2022 КОСТЫЛЬ НА updated_at
        projectRepository.save(entity);
        return new ProjectDTO(entity);
    }

    public void delete(final long id) {
        ProjectEntity entity = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project", id));
        projectRepository.delete(entity);
    }

    
    public Project findById(final long id) {
        return new ProjectDTO(projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project", id)));
    }

    public List<ProjectDTO> findAll() {
        List<ProjectEntity> projectEntities = new ArrayList<>();
        projectRepository.findAll().forEach(projectEntities::add);
        return projectEntities.stream().map(ProjectDTO::new).collect(Collectors.toList());
    }

    public List<ProjectDTO> findByTitle(final ProjectFindDTO request) {
        List<ProjectEntity> projectEntities = new ArrayList<>();
        projectRepository.findByTitleContainsIgnoreCase(request.getTitle()).forEach(projectEntities::add);
        return projectEntities.stream().map(ProjectDTO::new).collect(Collectors.toList());
    }
}
