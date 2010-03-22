package com.zving.workflow;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class WorkflowDescriptor extends AbstractDescriptor {
	public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	public static final String DOCTYPE_DECL = "<!DOCTYPE workflow PUBLIC \"-//OpenSymphony Group//DTD OSWorkflow 2.8//EN\" \"http://www.opensymphony.com/osworkflow/workflow_2_8.dtd\">";
	protected List initialActions = new ArrayList();
	protected List joins = new ArrayList();
	protected List splits = new ArrayList();
	protected List steps = new ArrayList();
	protected String workflowName = null;

	public WorkflowDescriptor() {
	}

	public WorkflowDescriptor(Element root) {
		init(root);
	}

	public ActionDescriptor getAction(int id) {
		for (Iterator iterator = this.steps.iterator(); iterator.hasNext();) {
			StepDescriptor stepDescriptor = (StepDescriptor) iterator.next();
			ActionDescriptor actionDescriptor = stepDescriptor.getAction(id);

			if (actionDescriptor != null) {
				return actionDescriptor;
			}

		}

		for (Iterator iterator = this.initialActions.iterator(); iterator.hasNext();) {
			ActionDescriptor actionDescriptor = (ActionDescriptor) iterator.next();

			if (actionDescriptor.getId() == id) {
				return actionDescriptor;
			}
		}

		return null;
	}

	public ActionDescriptor getInitialAction(int id) {
		for (Iterator iterator = this.initialActions.iterator(); iterator.hasNext();) {
			ActionDescriptor actionDescriptor = (ActionDescriptor) iterator.next();

			if (actionDescriptor.getId() == id) {
				return actionDescriptor;
			}
		}

		return null;
	}

	public List getInitialActions() {
		return this.initialActions;
	}

	public JoinDescriptor getJoin(int id) {
		for (Iterator iterator = this.joins.iterator(); iterator.hasNext();) {
			JoinDescriptor joinDescriptor = (JoinDescriptor) iterator.next();

			if (joinDescriptor.getId() == id) {
				return joinDescriptor;
			}
		}

		return null;
	}

	public List getJoins() {
		return this.joins;
	}

	public void setName(String name) {
		this.workflowName = name;
	}

	public String getName() {
		return this.workflowName;
	}

	public SplitDescriptor getSplit(int id) {
		for (Iterator iterator = this.splits.iterator(); iterator.hasNext();) {
			SplitDescriptor splitDescriptor = (SplitDescriptor) iterator.next();

			if (splitDescriptor.getId() == id) {
				return splitDescriptor;
			}
		}

		return null;
	}

	public List getSplits() {
		return this.splits;
	}

	public StepDescriptor getStep(long id) {
		for (Iterator iterator = this.steps.iterator(); iterator.hasNext();) {
			StepDescriptor step = (StepDescriptor) iterator.next();

			if (step.getId() == id) {
				return step;
			}
		}

		return null;
	}

	public List getSteps() {
		return this.steps;
	}

	public void addInitialAction(ActionDescriptor descriptor) {
		addAction(this.initialActions, descriptor);
	}

	public void addJoin(JoinDescriptor descriptor) {
		if (getJoin(descriptor.getId()) != null) {
			throw new IllegalArgumentException("Join with id " + descriptor.getId()
					+ " already exists");
		}

		this.joins.add(descriptor);
	}

	public void addSplit(SplitDescriptor descriptor) {
		if (getSplit(descriptor.getId()) != null) {
			throw new IllegalArgumentException("Split with id " + descriptor.getId()
					+ " already exists");
		}

		this.splits.add(descriptor);
	}

	public void addStep(StepDescriptor descriptor) {
		if (getStep(descriptor.getId()) != null) {
			throw new IllegalArgumentException("Step with id " + descriptor.getId()
					+ " already exists");
		}

		this.steps.add(descriptor);
	}

	public boolean removeAction(ActionDescriptor actionToRemove) {
		for (Iterator iterator = getSteps().iterator(); iterator.hasNext();) {
			StepDescriptor stepDescriptor = (StepDescriptor) iterator.next();
			ActionDescriptor actionDescriptor = stepDescriptor.getAction(actionToRemove.getId());

			if (actionDescriptor != null) {
				stepDescriptor.getActions().remove(actionDescriptor);
				return true;
			}
		}

		return false;
	}

	public void writeXML(PrintWriter out, int indent) {
		XMLUtil.printIndent(out, indent++);
		out.println("<workflow>");

		XMLUtil.printIndent(out, indent++);
		out.println("<initial-actions>");

		for (int i = 0; i < this.initialActions.size(); ++i) {
			ActionDescriptor action = (ActionDescriptor) this.initialActions.get(i);
			action.writeXML(out, indent);
		}

		XMLUtil.printIndent(out, --indent);
		out.println("</initial-actions>");

		XMLUtil.printIndent(out, indent++);
		out.println("<steps>");

		for (int i = 0; i < this.steps.size(); ++i) {
			StepDescriptor step = (StepDescriptor) this.steps.get(i);
			step.writeXML(out, indent);
		}

		XMLUtil.printIndent(out, --indent);
		out.println("</steps>");

		if (this.splits.size() > 0) {
			XMLUtil.printIndent(out, indent++);
			out.println("<splits>");

			for (int i = 0; i < this.splits.size(); ++i) {
				SplitDescriptor split = (SplitDescriptor) this.splits.get(i);
				split.writeXML(out, indent);
			}

			XMLUtil.printIndent(out, --indent);
			out.println("</splits>");
		}

		if (this.joins.size() > 0) {
			XMLUtil.printIndent(out, indent++);
			out.println("<joins>");

			for (int i = 0; i < this.joins.size(); ++i) {
				JoinDescriptor join = (JoinDescriptor) this.joins.get(i);
				join.writeXML(out, indent);
			}

			XMLUtil.printIndent(out, --indent);
			out.println("</joins>");
		}

		XMLUtil.printIndent(out, --indent);
		out.println("</workflow>");
	}

	protected void init(Element root) {
		NodeList children = root.getChildNodes();

		Element intialActionsElement = XMLUtil.getChildElement(root, "initial-actions");
		List initialActions = XMLUtil.getChildElements(intialActionsElement, "action");

		for (int i = 0; i < initialActions.size(); ++i) {
			Element initialAction = (Element) initialActions.get(i);
			ActionDescriptor actionDescriptor = DescriptorFactory.getFactory()
					.createActionDescriptor(initialAction);
			actionDescriptor.setParent(this);
			this.initialActions.add(actionDescriptor);
		}

		Element stepsElement = XMLUtil.getChildElement(root, "steps");
		List steps = XMLUtil.getChildElements(stepsElement, "step");

		for (int i = 0; i < steps.size(); ++i) {
			Element step = (Element) steps.get(i);
			StepDescriptor stepDescriptor = DescriptorFactory.getFactory().createStepDescriptor(
					step, this);
			this.steps.add(stepDescriptor);
		}

		Element splitsElement = XMLUtil.getChildElement(root, "splits");

		if (splitsElement != null) {
			List split = XMLUtil.getChildElements(splitsElement, "split");

			for (int i = 0; i < split.size(); ++i) {
				Element s = (Element) split.get(i);
				SplitDescriptor splitDescriptor = DescriptorFactory.getFactory()
						.createSplitDescriptor(s);
				splitDescriptor.setParent(this);
				this.splits.add(splitDescriptor);
			}

		}

		Element joinsElement = XMLUtil.getChildElement(root, "joins");

		if (joinsElement != null) {
			List join = XMLUtil.getChildElements(joinsElement, "join");

			for (int i = 0; i < join.size(); ++i) {
				Element s = (Element) join.get(i);
				JoinDescriptor joinDescriptor = DescriptorFactory.getFactory()
						.createJoinDescriptor(s);
				joinDescriptor.setParent(this);
				this.joins.add(joinDescriptor);
			}
		}
	}

	private void addAction(Object actionsCollectionOrMap, ActionDescriptor descriptor) {
		if (getAction(descriptor.getId()) != null) {
			throw new IllegalArgumentException("action with id " + descriptor.getId()
					+ " already exists for this step.");
		}

		if (actionsCollectionOrMap instanceof Map)
			((Map) actionsCollectionOrMap).put(new Integer(descriptor.getId()), descriptor);
		else
			((Collection) actionsCollectionOrMap).add(descriptor);
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.workflow.WorkflowDescriptor JD-Core Version: 0.5.3
 */