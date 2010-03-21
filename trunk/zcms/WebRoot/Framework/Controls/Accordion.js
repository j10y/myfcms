Accordion = function(element, opts)
{
	this.element = $(element);
	this.defaultPanel = 0;
	this.defaultMenuitem = 0;
	this.openClass = "AccordionPanelOpen";
	this.menuClass = "MenuList";
	this.currentPanel = null;
	this.currentMenuitem = null;

	this.useFixedPanelHeights = true;
	this.fixedPanelHeight = 0;

	Accordion.setOptions(this, opts, true);

	this.attachBehaviors();
};

Accordion.setOptions = function(obj, optionsObj, ignoreUndefinedProps)
{
	if (!optionsObj)
		return;
	for (var optionName in optionsObj)
	{
		if (ignoreUndefinedProps && optionsObj[optionName] == undefined)
			continue;
		obj[optionName] = optionsObj[optionName];
	}
};

Accordion.prototype.openPanel = function(elementOrIndex)
{
	var panelA = this.currentPanel;
	var panelB;

	if (typeof elementOrIndex == "number")
		panelB = this.getPanels()[elementOrIndex];
	else
		panelB = $(elementOrIndex);
	
	if (!panelB || panelA == panelB)	
		return null;

	var contentA = panelA ? this.getPanelContent(panelA) : null;
	var contentB = this.getPanelContent(panelB);

	if (!contentB)
		return null;

	if (this.useFixedPanelHeights && !this.fixedPanelHeight)
		this.fixedPanelHeight = (contentA.offsetHeight) ? contentA.offsetHeight : contentA.scrollHeight;

		if(contentA)
		{
			contentA.style.display = "none";
			contentA.style.height = "0px";
		}
		contentB.style.display = "block";
		contentB.style.height = this.useFixedPanelHeights ? this.fixedPanelHeight + "px" : "auto";

	if(panelA)
	{
		$E.removeClassName(this.openClass, panelA);
	}

	$E.addClassName(this.openClass, panelB);

	this.currentPanel = panelB;

	return panelB;
};

Accordion.prototype.closePanel = function()
{
	// 本折叠控件同时只有一个子面板展开，调用本方法将关闭当前展开的子面板
	// 在固定高度模式（useFixedPanelHeights==true），本方法不起作用

	if (!this.useFixedPanelHeights && this.currentPanel)
	{
		var panel = this.currentPanel;
		var content = this.getPanelContent(panel);
		if (content)
		{
				content.style.display = "none";
				content.style.height = "0px";
		}		
		$E.removeClassName(this.openClass, panel);
		this.currentPanel = null;
	}
};

Accordion.prototype.onMenuitemClick = function(e,menuitem)
{
	if(menuitem!=this.currentMenuitem){
		$E.removeClassName("selected", this.currentMenuitem);
		$E.addClassName("selected", menuitem);
		this.currentMenuitem=menuitem;
	}
	if(e)
		stopEvent(e);
	return false;
}

Accordion.prototype.onPanelTabClick = function(e, panel)
{
	if (panel != this.currentPanel)
		this.openPanel(panel);
	else
		this.closePanel();

	stopEvent(e);

	return false;
};

Accordion.prototype.attachPanelHandlers = function(panel)
{
	if (!panel)
		return;

	var tab = this.getPanelTab(panel);

	if (tab)
	{
		var self = this;
		tab.attachEvent("onclick", function(e) { return self.onPanelTabClick(e, panel); }, false);
	}
};

Accordion.prototype.initMenu = function(menuitem, isDefault)
{
	var self = this;
	menuitem.attachEvent("onclick", function(e) { return self.onMenuitemClick(e,menuitem);}, false);
	if(isDefault){
		this.onMenuitemClick(null,menuitem);
	}
}
Accordion.prototype.initPanel = function(panel, isDefault)
{
	var content = this.getPanelContent(panel);
	if (isDefault)
	{
		this.currentPanel = panel;
		$E.addClassName(this.openClass, panel);

		if (content)
		{
			if (this.useFixedPanelHeights)
			{
				if (this.fixedPanelHeight)
					content.style.height = this.fixedPanelHeight + "px";
			}
			else
			{
				content.style.height = "auto";
			}
		}
	}
	else
	{
		$E.removeClassName(this.openClass, panel);

		if (content)
		{
			content.style.height = "0px";
			content.style.display = "none";
		}
	}
	
	this.attachPanelHandlers(panel);
};
Accordion.prototype.attachBehaviors = function()
{
	var panels = this.getPanels();
	for (var i = 0; i < panels.length; i++)
		this.initPanel(panels[i], i == this.defaultPanel);
	var menu = this.getMenu();
	for (var i = 0; i < menu.length; i++)
		this.initMenu(menu[i], i == this.defaultMenuitem);
};

Accordion.prototype.getPanels = function()
{
	return this.element.children;
};

Accordion.prototype.getCurrentPanel = function()
{
	return this.currentPanel;
};

Accordion.prototype.getPanelIndex = function(panel)
{
	var panels = this.getPanels();
	for( var i = 0 ; i < panels.length; i++ )
	{
		if( panel == panels[i] )
			return i;
	}
	return -1;
};

Accordion.prototype.getCurrentPanelIndex = function()
{
	return this.getPanelIndex(this.currentPanel);
};

Accordion.prototype.getPanelTab = function(panel)
{
	if (!panel)
		return null;
	return panel.children[0];
};

Accordion.prototype.getPanelContent = function(panel)
{
	if (!panel)
		return null;
	return panel.children[1];
};

Accordion.prototype.getMenu = function()
{
	var menuArr=$CN(this.menuClass,"UL",this.element);
	var linksArr=[];
	menuArr.each(function(e){linksArr=linksArr.concat($T("A",e));})
	return linksArr;
};
