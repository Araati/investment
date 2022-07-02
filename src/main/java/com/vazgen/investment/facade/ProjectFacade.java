package com.vazgen.investment.facade;

import com.vazgen.investment.dto.ProjectCreateDTO;
import com.vazgen.investment.dto.ProjectDTO;
import com.vazgen.investment.dto.ProjectFindDTO;
import com.vazgen.investment.dto.ProjectUpdateDTO;
import com.vazgen.investment.model.Project;
import com.vazgen.investment.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectFacade {

    private final ProjectService projectService;

    public Project create(final ProjectCreateDTO request) {
        return projectService.create(request);
    }

    public Project update(final ProjectUpdateDTO request, final long id) {
        return projectService.update(request, id);
    }

    public void delete(final long id) {
        projectService.delete(id);
    }

    public Project findById(final long id) {
        return projectService.findById(id);
    }

    public List<ProjectDTO> findAll() {
        return projectService.findAll();
    }

    public List<ProjectDTO> findByTitle(final ProjectFindDTO request) {
        return projectService.findByTitle(request);
    }
}
