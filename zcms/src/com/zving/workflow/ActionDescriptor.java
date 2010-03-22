package com.zving.workflow;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;

public class ActionDescriptor extends AbstractDescriptor {
	protected List conditionalResults = new ArrayList();
	protected List postFunctions = new ArrayList();
	protected List preFunctions = new ArrayList();
	protected RestrictionDescriptor restriction;
	protected ResultDescriptor unconditionalResult;
	protected String name;
	protected String view;
	protected boolean autoExecute = false;
	protected boolean common;
	protected boolean finish = false;
	protected boolean quickLink = true;

	ActionDescriptor() {
	}

	ActionDescriptor(Element action) {
		init(action);
	}

	public void setAutoExecute(boolean autoExecute) {
		this.autoExecute = autoExecute;
	}

	public boolean getAutoExecute() {
		return this.autoExecute;
	}

	public boolean isCommon() {
		return this.common;
	}

	public List getConditionalResults() {
		return this.conditionalResults;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	public boolean isFinish() {
		return this.finish;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public List getPostFunctions() {
		return this.postFunctions;
	}

	public List getPreFunctions() {
		return this.preFunctions;
	}

	public void setRestriction(RestrictionDescriptor restriction) {
		this.restriction = restriction;
	}

	public RestrictionDescriptor getRestriction() {
		return this.restriction;
	}

	public void setUnconditionalResult(ResultDescriptor unconditionalResult) {
		this.unconditionalResult = unconditionalResult;
	}

	public ResultDescriptor getUnconditionalResult() {
		return this.unconditionalResult;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getView() {
		return this.view;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		if (this.name != null) {
			sb.append(this.name);
		}

		if ((this.view != null) && (this.view.length() > 0)) {
			sb.append(" (").append(this.view).append(")");
		}

		return sb.toString();
	}

	public void writeXML(PrintWriter out, int indent) {
		XMLUtil.printIndent(out, indent++);

		StringBuffer buf = new StringBuffer("<action id=\"");
		buf.append(getId());
		buf.append("\"");

		if ((this.name != null) && (this.name.length() > 0)) {
			buf.append(" name=\"");
			buf.append(XMLUtil.encode(this.name));
			buf.append("\"");
		}

		if ((this.view != null) && (this.view.length() > 0)) {
			buf.append(" view=\"");
			buf.append(XMLUtil.encode(this.view));
			buf.append("\"");
		}

		if (this.finish) {
			buf.append(" finish=\"true\"");
		}

		if (this.autoExecute) {
			buf.append(" auto=\"true\"");
		}

		if (this.quickLink) {
			buf.append(" quick=\"true\"");
		}

		buf.append(">");
		out.println(buf.toString());

		if (this.restriction != null)
			this.restriction.writeXML(out, indent);
		FunctionDescriptor function;
		if (this.preFunctions.size() > 0) {
			XMLUtil.printIndent(out, indent++);
			out.println("<pre-functions>");

			for (int i = 0; i < this.preFunctions.size(); ++i) {
				function = (FunctionDescriptor) this.preFunctions.get(i);
				function.writeXML(out, indent);
			}

			XMLUtil.printIndent(out, --indent);
			out.println("</pre-functions>");
		}

		XMLUtil.printIndent(out, indent++);
		out.println("<results>");

		for (int i = 0; i < this.conditionalResults.size(); ++i) {
			ConditionalResultDescriptor result = (ConditionalResultDescriptor) this.conditionalResults
					.get(i);
			result.writeXML(out, indent);
		}

		if (this.unconditionalResult != null) {
			this.unconditionalResult.writeXML(out, indent);
		}

		XMLUtil.printIndent(out, --indent);
		out.println("</results>");

		if (this.postFunctions.size() > 0) {
			XMLUtil.printIndent(out, indent++);
			out.println("<post-functions>");

			for (int i = 0; i < this.postFunctions.size(); ++i) {
				function = (FunctionDescriptor) this.postFunctions.get(i);
				function.writeXML(out, indent);
			}

			XMLUtil.printIndent(out, --indent);
			out.println("</post-functions>");
		}

		XMLUtil.printIndent(out, --indent);
		out.println("</action>");
	}

	protected void init(Element action) {
		try {
			setId(Integer.parseInt(action.getAttribute("id")));
		} catch (Exception ex) {
			throw new IllegalArgumentException("Invalid action id value '"
					+ action.getAttribute("id") + "'");
		}

		this.name = action.getAttribute("name");
		this.view = action.getAttribute("view");
		this.autoExecute = "true".equalsIgnoreCase(action.getAttribute("auto"));
		this.finish = "true".equalsIgnoreCase(action.getAttribute("finish"));
		if ("false".equalsIgnoreCase(action.getAttribute("quick"))) {
			this.quickLink = false;
		}

		Element pre = XMLUtil.getChildElement(action, "pre-functions");

		if (pre != null) {
			List preFunctions = XMLUtil.getChildElements(pre, "function");

			for (int k = 0; k < preFunctions.size(); ++k) {
				Element preFunction = (Element) preFunctions.get(k);
				FunctionDescriptor functionDescriptor = DescriptorFactory.getFactory()
						.createFunctionDescriptor(preFunction);
				functionDescriptor.setParent(this);
				this.preFunctions.add(functionDescriptor);
			}

		}

		Element resultsElememt = XMLUtil.getChildElement(action, "results");
		List results = XMLUtil.getChildElements(resultsElememt, "result");

		for (int k = 0; k < results.size(); ++k) {
			Element result = (Element) results.get(k);
			ConditionalResultDescriptor conditionalResultDescriptor = new ConditionalResultDescriptor(
					result);
			conditionalResultDescriptor.setParent(this);
			this.conditionalResults.add(conditionalResultDescriptor);
		}

		Element unconditionalResult = XMLUtil.getChildElement(resultsElememt,
				"unconditional-result");

		if (unconditionalResult != null) {
			this.unconditionalResult = DescriptorFactory.getFactory().createResultDescriptor(
					unconditionalResult);
			this.unconditionalResult.setParent(this);
		}

		Element post = XMLUtil.getChildElement(action, "post-functions");

		if (post != null) {
			List postFunctions = XMLUtil.getChildElements(post, "function");

			for (int k = 0; k < postFunctions.size(); ++k) {
				Element postFunction = (Element) postFunctions.get(k);
				FunctionDescriptor functionDescriptor = DescriptorFactory.getFactory()
						.createFunctionDescriptor(postFunction);
				functionDescriptor.setParent(this);
				this.postFunctions.add(functionDescriptor);
			}

		}

		Element restrictElement = XMLUtil.getChildElement(action, "restrict-to");

		if (restrictElement != null) {
			this.restriction = new RestrictionDescriptor(restrictElement);

			if (this.restriction.getConditionsDescriptor() == null)
				this.restriction = null;
			else
				this.restriction.setParent(this);
		}
	}

	void setCommon(boolean common) {
		this.common = common;
	}

	public boolean isQuickLink() {
		return this.quickLink;
	}

	public void setQuickLink(boolean quickLink) {
		this.quickLink = quickLink;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.workflow.ActionDescriptor JD-Core Version: 0.5.3
 */