package com.htsoft.oa.service.flow;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.action.flow.FlowRunInfo;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.flow.ProcessForm;
import com.htsoft.oa.model.flow.ProcessRun;
import java.util.List;

public abstract interface ProcessRunService extends BaseService<ProcessRun>
{
  public abstract ProcessRun initNewProcessRun(ProDefinition paramProDefinition);

  public abstract void saveProcessRun(ProcessRun paramProcessRun, ProcessForm paramProcessForm, FlowRunInfo paramFlowRunInfo);

  public abstract ProcessRun getByExeId(String paramString);

  public abstract ProcessRun getByTaskId(String paramString);

  public abstract ProcessRun getByPiId(String paramString);

  public abstract void saveAndNextStep(FlowRunInfo paramFlowRunInfo);

  public abstract void removeByDefId(Long paramLong);

  public abstract List<ProcessRun> getByUserIdSubject(Long paramLong, String paramString, PagingBean paramPagingBean);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.ProcessRunService
 * JD-Core Version:    0.5.4
 */