/***
insert a new blankPage or split pages
**/
var InsertPageCommond = function() {this.Name = 'InsertPage';}
InsertPageCommond.prototype.Execute = function () {InsertPage();}
InsertPageCommond.prototype.GetState = function() {return FCK_TRISTATE_OFF;}

// Register the related command.
FCKCommands.RegisterCommand( 'InsertPage',new InsertPageCommond()) ;

// Create the "InsertPage" toolbar button.
var InsertPageItem = new FCKToolbarButton( 'InsertPage', "插入分页" ) ;
InsertPageItem.IconPath = FCKPlugins.Items['InsertPage'].Path + 'InsertPage.gif' ;
FCKToolbarItems.RegisterItem( 'InsertPage', InsertPageItem ) ;

function InsertPage(){
	FCKUndo.SaveUndoStep() ;
	
	var PAGE_BRREAK = "<!--_ZVING_PAGE_BREAK_-->";
	var oSel;
  if(FCKBrowserInfo.IsIE){
  	oSel = document.selection;
  }else{
  	oSel = FCK.EditorWindow.getSelection() ;
  }
  if(oSel == null){
  	return;
  }
  
	if(FCKSelection.GetType() == 'Control'){
		return;
	}
	
	//alert(FCKSelection.GetParentElement().nodeName);
	
	var parentNode = FCKSelection.GetParentElement().nodeName;
	if(parentNode=="TR" || parentNode=="TD"){
		return;
	}
	
  //move the focus to the start
	FCKSelection.Collapse(true);
	FCK.InsertHtml(PAGE_BRREAK);
	var html = FCK.GetXHTML(false);

	var splitIndex = html.indexOf(PAGE_BRREAK);
	if(splitIndex>=0){
		var html1 = html.substr(0,splitIndex);
		var html2 = html.substr(splitIndex+PAGE_BRREAK.length);
		
		//alert(html2);
		
		var documentWin = window.parent.parent;
		documentWin.editorMode = 1;
		var currentPage = documentWin.currentPage;
		documentWin.addPage();
		documentWin.setPageContent(currentPage,html1);
		FCK.SetHTML(html2);
	}
	FCKUndo.SaveUndoStep() ;
}

