var NewImageCommond = function() {this.Name = 'NewImage';}
NewImageCommond.prototype.Execute = function () {NewImage();}
NewImageCommond.prototype.GetState = function() {return FCK_TRISTATE_OFF;}

// Register the related command.
FCKCommands.RegisterCommand( 'NewImage',new NewImageCommond()) ;

// Create the "Plaholder" toolbar button.
var NewImageItem = new FCKToolbarButton( 'NewImage', "图片上传" ) ;
NewImageItem.IconPath = FCKPlugins.Items['NewImage'].Path + 'NewImage.gif' ;
FCKToolbarItems.RegisterItem( 'NewImage', NewImageItem ) ;

function NewImage(){
	FCKUndo.SaveUndoStep() ;
	//FCK.SetHTML(formattext(FCK.GetXHTML(false))); 
	var documentWin = window.parent.parent;
	documentWin.upload();
	FCKUndo.SaveUndoStep() ;
}
