package org.qaddict.sqad.rest;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.qaddict.sqad.api.ExecutionWorkflow;
import org.qaddict.sqad.data.records.Build;
import org.qaddict.sqad.data.records.Plan;
import org.qaddict.sqad.data.records.Run;
import org.qaddict.sqad.data.records.Suite;
import org.qaddict.sqad.data.records.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.qaddict.sqad.data.records.QBuild.build;
import static org.qaddict.sqad.data.records.QPlan.plan;
import static org.qaddict.sqad.data.records.QSuite.suite;
import static org.qaddict.sqad.data.records.QTask.task;

@RestController
@RequestMapping(Api.V1)
public class ExecutionWorkflowController implements ExecutionWorkflow {

    private final JPAQueryFactory factory;
    private final EntityManager entityManager;

    public ExecutionWorkflowController(JPAQueryFactory factory, EntityManager entityManager) {
        this.factory = factory;
        this.entityManager = entityManager;
    }

    @Override
    public Plan createPlan(String suiteUri, String buildUri) {
        Suite aSuite = factory.selectFrom(suite).where(suite.uri.eq(suiteUri)).fetchOne();
        Build aBuild = factory.selectFrom(build).where(build.uri.eq(buildUri)).fetchOne();
        Plan plan = entityManager.merge(new Plan().setSuite(aSuite).setBuild(aBuild));
        aSuite.getTests().forEach(test -> entityManager.persist(new Task().setPlan(plan).setTest(test)));
        return plan;
    }

    @Override
    @GetMapping("/execution/plan")
    public Plan getPlan(@RequestParam String planUri) {
        return factory.selectFrom(plan).where(plan.uri.eq(planUri)).fetchOne();
    }

    @Override
    @Transactional
    @GetMapping("/execution/start")
    public Run start(@RequestBody String taskUri) {
        Task aTask = factory.selectFrom(task).where(task.uri.eq(taskUri)).fetchOne();
        return entityManager.merge(new Run().setTask(aTask));
    }

    @Override
    @PostMapping("/execution/finish")
    public Run finish(@RequestBody Run run) {
        return entityManager.merge(run);
    }

}
