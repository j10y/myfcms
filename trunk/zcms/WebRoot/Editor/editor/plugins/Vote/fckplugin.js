var VoteCommond = function() {this.Name = 'Vote';}
VoteCommond.prototype.Execute = function () {Vote();}
VoteCommond.prototype.GetState = function() {return FCK_TRISTATE_OFF;}

// Register the related command.
FCKCommands.RegisterCommand( 'Vote',new VoteCommond()) ;

// Create the "Plaholder" toolbar button.
var VoteItem = new FCKToolbarButton( 'Vote', "插入调查" ) ;
VoteItem.IconPath = FCKPlugins.Items['Vote'].Path + 'vote.gif' ;
FCKToolbarItems.RegisterItem( 'Vote', VoteItem ) ;

function Vote(){
	FCKUndo.SaveUndoStep() ;
	//FCK.SetHTML(formattext(FCK.GetXHTML(false))); 
	var documentWin = window.parent.parent;
	documentWin.insertVote();
	FCKUndo.SaveUndoStep() ;
}