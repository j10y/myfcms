var NewFlashCommond = function() {this.Name = 'NewFlash';}
NewFlashCommond.prototype.Execute = function () {NewFlash();}
NewFlashCommond.prototype.GetState = function() {return FCK_TRISTATE_OFF;}

// Register the related command.
FCKCommands.RegisterCommand( 'NewFlash',new NewFlashCommond()) ;

// Create the "Plaholder" toolbar button.
var NewFlashItem = new FCKToolbarButton( 'NewFlash', "插入flash" ) ;
NewFlashItem.IconPath = FCKPlugins.Items['NewFlash'].Path + 'NewFlash.gif' ;
FCKToolbarItems.RegisterItem( 'NewFlash', NewFlashItem ) ;

function NewFlash(){
	FCKUndo.SaveUndoStep() ;
	//FCK.SetHTML(formattext(FCK.GetXHTML(false))); 
	var documentWin = window.parent.parent;
	documentWin.uploadFlash();
	FCKUndo.SaveUndoStep() ;
}
