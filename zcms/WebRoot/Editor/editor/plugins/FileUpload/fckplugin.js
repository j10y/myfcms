var FileUploadCommond = function() {this.Name = 'FileUpload';}
FileUploadCommond.prototype.Execute = function () {FileUpload();}
FileUploadCommond.prototype.GetState = function() {return FCK_TRISTATE_OFF;}

// Register the related command.
FCKCommands.RegisterCommand( 'FileUpload',new FileUploadCommond()) ;

// Create the "Plaholder" toolbar button.
var FileUploadItem = new FCKToolbarButton( 'FileUpload', "插入附件" ) ;
FileUploadItem.IconPath = FCKPlugins.Items['FileUpload'].Path + 'FileUpload.gif' ;
FCKToolbarItems.RegisterItem( 'FileUpload', FileUploadItem ) ;

function FileUpload(){
	FCKUndo.SaveUndoStep() ;
	//FCK.SetHTML(formattext(FCK.GetXHTML(false))); 
	var documentWin = window.parent.parent;
	documentWin.uploadFile();
	FCKUndo.SaveUndoStep() ;
}
