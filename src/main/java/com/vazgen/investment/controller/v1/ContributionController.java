package com.vazgen.investment.controller.v1;

import com.vazgen.investment.dto.ContributionCreateDTO;
import com.vazgen.investment.dto.ContributionUpdateDTO;
import com.vazgen.investment.facade.ContributionFacade;
import com.vazgen.investment.model.Contribution;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1/contribution")
@RequiredArgsConstructor
public class ContributionController {

    private final ContributionFacade contributionFacade;

    @PostMapping
    public Contribution create(@Valid @RequestBody final ContributionCreateDTO request)   {
        return contributionFacade.create(request);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Contribution update(@RequestBody final ContributionUpdateDTO request, @PathVariable final long id)  {
        return contributionFacade.update(request, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable final long id) {
        contributionFacade.delete(id);
    }

    @GetMapping("/{id}")
    public Contribution findById(@PathVariable final long id)   {
        return contributionFacade.findById(id);
    }

    @GetMapping
    public List<Contribution> findAll() {
        return contributionFacade.findAll();
    }

}
