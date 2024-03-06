package org.qaddict.sqad.rest;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.qaddict.sqad.data.records.Project;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Api.V1)
public class ProjectWorkflowController {

    private final EntityManager entityManager;

    public ProjectWorkflowController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping("/project/save")
    @Transactional
    public Project save(@RequestBody Project project) {
        return entityManager.merge(project);
    }

}
