package com.vazgen.investment.controller.v1;

import com.vazgen.investment.dto.ProjectCreateDTO;
import com.vazgen.investment.dto.ProjectDTO;
import com.vazgen.investment.dto.ProjectFindDTO;
import com.vazgen.investment.dto.ProjectUpdateDTO;
import com.vazgen.investment.facade.ProjectFacade;
import com.vazgen.investment.model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectFacade projectFacade;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Project create(@Valid @RequestBody final ProjectCreateDTO request)   {
        return projectFacade.create(request);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Project update(@RequestBody final ProjectUpdateDTO request, @PathVariable final long id)    {
        return projectFacade.update(request, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable final long id) {
        projectFacade.delete(id);
    }

    @GetMapping("/{id}")
    public Project findById(@PathVariable final long id)    {
        return projectFacade.findById(id);
    }

    @PostMapping("/title")
    public List<ProjectDTO> findByTitle(@RequestBody final ProjectFindDTO request)    {
        return projectFacade.findByTitle(request);
    }

    @GetMapping
    public List<ProjectDTO> findAll() {
        return projectFacade.findAll();
    }
}
