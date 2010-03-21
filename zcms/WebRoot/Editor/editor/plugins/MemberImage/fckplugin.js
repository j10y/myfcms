FCKCommands.RegisterCommand('MemberImage', new FCKDialogCommand('MemberImage',"图片上传", FCKPlugins.Items['MemberImage'].Path + 'MemberImage.html',480,160 )) ;

var MemberImage = new FCKToolbarButton( 'MemberImage', "图片上传", "图片上传", null, null, false, true) ;
MemberImage.IconPath = FCKPlugins.Items['MemberImage'].Path + 'MemberImage.gif' ;

FCKToolbarItems.RegisterItem( 'MemberImage', MemberImage ) ;