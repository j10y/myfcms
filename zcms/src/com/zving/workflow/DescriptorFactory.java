 package com.zving.workflow;
 
 import org.w3c.dom.Element;
 
 public class DescriptorFactory
 {
   private static DescriptorFactory factory = new DescriptorFactory();
 
   public static void setFactory(DescriptorFactory factory)
   {
     factory = factory;
   }
 
   public static DescriptorFactory getFactory() {
     return factory;
   }
 
   public ActionDescriptor createActionDescriptor() {
     return new ActionDescriptor();
   }
 
   public ActionDescriptor createActionDescriptor(Element action) {
     return new ActionDescriptor(action);
   }
 
   public ConditionDescriptor createConditionDescriptor() {
     return new ConditionDescriptor();
   }
 
   public ConditionDescriptor createConditionDescriptor(Element function) {
     return new ConditionDescriptor(function);
   }
 
   public ConditionalResultDescriptor createConditionalResultDescriptor() {
     return new ConditionalResultDescriptor();
   }
 
   public ConditionalResultDescriptor createConditionalResultDescriptor(Element element) {
     return new ConditionalResultDescriptor(element);
   }
 
   public ConditionsDescriptor createConditionsDescriptor() {
     return new ConditionsDescriptor();
   }
 
   public ConditionsDescriptor createConditionsDescriptor(Element element) {
     return new ConditionsDescriptor(element);
   }
 
   public FunctionDescriptor createFunctionDescriptor() {
     return new FunctionDescriptor();
   }
 
   public FunctionDescriptor createFunctionDescriptor(Element function) {
     return new FunctionDescriptor(function);
   }
 
   public JoinDescriptor createJoinDescriptor(Element join) {
     return new JoinDescriptor(join);
   }
 
   public JoinDescriptor createJoinDescriptor() {
     return new JoinDescriptor();
   }
 
   public PermissionDescriptor createPermissionDescriptor() {
     return new PermissionDescriptor();
   }
 
   public PermissionDescriptor createPermissionDescriptor(Element permission) {
     return new PermissionDescriptor(permission);
   }
 
   public ResultDescriptor createResultDescriptor() {
     return new ResultDescriptor();
   }
 
   public ResultDescriptor createResultDescriptor(Element element) {
     return new ResultDescriptor(element);
   }
 
   public SplitDescriptor createSplitDescriptor() {
     return new SplitDescriptor();
   }
 
   public SplitDescriptor createSplitDescriptor(Element split) {
     return new SplitDescriptor(split);
   }
 
   public StepDescriptor createStepDescriptor() {
     return new StepDescriptor();
   }
 
   public StepDescriptor createStepDescriptor(Element step) {
     return new StepDescriptor(step);
   }
 
   public StepDescriptor createStepDescriptor(Element step, AbstractDescriptor parent) {
     return new StepDescriptor(step, parent);
   }
 
   public WorkflowDescriptor createWorkflowDescriptor(Element root) {
     return new WorkflowDescriptor(root);
   }
 
   public WorkflowDescriptor createWorkflowDescriptor() {
     return new WorkflowDescriptor();
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.DescriptorFactory
 * JD-Core Version:    0.5.3
 */