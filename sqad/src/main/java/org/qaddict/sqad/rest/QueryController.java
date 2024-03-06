package org.qaddict.sqad.rest;

import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import foundation.jpa.querydsl.EntityConverter;
import foundation.jpa.querydsl.QueryVariables;
import foundation.jpa.querydsl.QuerydslParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static org.qaddict.sqad.data.records.QBuild.build;
import static org.qaddict.sqad.data.records.QBuildRevision.buildRevision;
import static org.qaddict.sqad.data.records.QIssue.issue;
import static org.qaddict.sqad.data.records.QIssueRevision.issueRevision;
import static org.qaddict.sqad.data.records.QPlan.plan;
import static org.qaddict.sqad.data.records.QPlanRevision.planRevision;
import static org.qaddict.sqad.data.records.QProject.project;
import static org.qaddict.sqad.data.records.QProjectRevision.projectRevision;
import static org.qaddict.sqad.data.records.QRequirement.requirement;
import static org.qaddict.sqad.data.records.QRequirementRevision.requirementRevision;
import static org.qaddict.sqad.data.records.QRun.run;
import static org.qaddict.sqad.data.records.QRunRevision.runRevision;
import static org.qaddict.sqad.data.records.QSuite.suite;
import static org.qaddict.sqad.data.records.QSuiteRevision.suiteRevision;
import static org.qaddict.sqad.data.records.QTask.task;
import static org.qaddict.sqad.data.records.QTaskRevision.taskRevision;
import static org.qaddict.sqad.data.records.QTest.test;
import static org.qaddict.sqad.data.records.QTestRevision.testRevision;

@RestController
@RequestMapping(Api.V1)
public record QueryController(JPAQueryFactory factory, EntityConverter entityConverter) {

    private static final Map<String, EntityPath<?>> entityByName = Stream.of(
            build, buildRevision,
            issue, issueRevision,
            plan, planRevision,
            project, projectRevision,
            requirement, requirementRevision,
            run, runRevision,
            suite, suiteRevision,
            task, taskRevision,
            test, testRevision
    ).collect(toMap(Object::toString, identity()));

    @GetMapping("/query/{entityName}")
    public Page<?> query(
            @PathVariable String entityName,
            @RequestParam(defaultValue = "") String where,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "") String select,
            @RequestParam(defaultValue = "") String groupBy,
            @RequestParam(defaultValue = "false") Boolean unpaged,
            Pageable pageable
    ) throws IOException {
        EntityPath<?> entity = entityByName.get(entityName);
        QuerydslParser parser = new QuerydslParser(entity, entityConverter, QueryVariables.none());
        JPAQuery<?> query = factory.from(entity);
        if(!where.isEmpty())
            query.where(parser.parsePredicate(where));
        if(!orderBy.isEmpty())
            query.orderBy(parser.parseOrderSpecifier(orderBy));
        if(select.isEmpty())
            query.select(entity);
        else
            query.select(parser.parseSelect(select));
        if(!groupBy.isEmpty())
            query.groupBy(parser.parseSelect(groupBy));
        if(!unpaged) {
            query.offset(pageable.getOffset()).limit(pageable.getPageSize());
        }
        return new PageImpl<>(query.fetch(), unpaged ? Pageable.unpaged() : pageable, query.fetchCount());
    }

}
