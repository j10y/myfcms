
FCKCommands.RegisterCommand( 'CleanUp', new FCKDialogCommand( 'CleanUp', "清除HTML格式", FCKPlugins.Items['CleanUp'].Path + 'CleanUp.html',450,380));
var oCleanUpItem = new FCKToolbarButton( 'CleanUp', "清除HTML格式" ) ;
oCleanUpItem.IconPath = FCKPlugins.Items['CleanUp'].Path + 'CleanUp.gif' ;
FCKToolbarItems.RegisterItem( 'CleanUp', oCleanUpItem ) ;
