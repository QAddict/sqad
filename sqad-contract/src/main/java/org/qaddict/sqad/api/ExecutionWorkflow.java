package org.qaddict.sqad.api;

import org.qaddict.sqad.data.records.Plan;
import org.qaddict.sqad.data.records.Run;

public interface ExecutionWorkflow {

    Plan createPlan(String suiteUri, String buildUri);

    Plan getPlan(String planUri);

    Run start(String taskUri);

    Run finish(Run run);

}
