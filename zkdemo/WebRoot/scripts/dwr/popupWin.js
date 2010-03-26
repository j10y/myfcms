  var IMGDIR = '../images/';
  var zoomobj = Array();
  var oldObj;
  var thisObj;
  var mapTable = '';
  var infoTable = '';
  var topTitle = '分类管理';
  var areaWidth = window.screen.width * 0.6;
  var areaHeight = window.screen.height * 0.5;
  var userAgent = navigator.userAgent.toLowerCase();
  var is_opera = userAgent.indexOf('opera') != -1 && opera.version();
  var is_ie = (userAgent.indexOf('msie') != -1 && !is_opera) && userAgent.substr(userAgent.indexOf('msie') + 5, 3);
  
 function zoom(obj,infoTxt) {
     if(!document.getElementById('zoomimglayer_bg')) {
     div = document.createElement('div');
     div.id = 'zoomimglayer_bg';
     div.style.position = 'absolute';
     document.getElementById('append_parent').appendChild(div);
     
     div = document.createElement('div');
     div.id = 'zoomimglayer';
     div.style.position = 'absolute';
     div.style.backgroundColor = '#fff';
     div.className = 'popupmenu_popup';
     div.style.padding = 0;
     document.getElementById('append_parent').appendChild(div);
     }
     
     document.getElementById('zoomimglayer_bg').style.display = '';
     document.getElementById('zoomimglayer_bg').style.width = '100%';
     if(is_ie){
     	document.getElementById('zoomimglayer_bg').style.height = (document.body.scrollHeight > document.body.clientHeight)?document.body.scrollHeight:document.body.clientHeight   + 'px';
   }else{
    	document.getElementById('zoomimglayer_bg').style.height = document.body.scrollHeight  + 'px';
   }
     
     document.getElementById('zoomimglayer_bg').style.backgroundColor = '#000';
     document.getElementById('zoomimglayer_bg').style.filter = 'progid:DXImageTransform.Microsoft.Alpha(opacity=50,finishOpacity=100,style=0)';
     document.getElementById('zoomimglayer_bg').style.opacity = 0.5;
     
     zoomobj['srcinfo'] = fetchOffset(obj);
     zoomobj['srcobj'] = obj;
     document.getElementById('zoomimglayer').style.display = '';
     document.getElementById('zoomimglayer').style.left = zoomobj['srcinfo']['left'] + 'px';
     document.getElementById('zoomimglayer').style.top = zoomobj['srcinfo']['top'] + 'px';
     document.getElementById('zoomimglayer').style.zIndex = 999;
     
     var controllTxt = '<div class="controllTable"><table background="' + IMGDIR + '/controllBar.png" width="' + areaWidth + '" height="27" cellspacing="0" cellpadding="0"><tr><td align="left" width="95%">&nbsp;<img  src="' + IMGDIR +'/sj.gif" border="0"/>&nbsp;&nbsp;<strong>' + topTitle + '</strong></td><td align="center"><a href="javascript:zoomclose();" ><img  src="' + IMGDIR +'/close.gif" border="0" title="关闭" /></a></td></tr></table></div>';
     
     document.getElementById('zoomimglayer').innerHTML = controllTxt + infoTxt;
	 
    }
    
    function mapInfo(obj){
    	DepartmentListDWR.getSuperDepartment(function (jsonText){
			
			if (jsonText != null){
				mapTable = mapTable + '<table width="' + areaWidth + '" cellspacing="0" cellpadding="0"><tr><td>';
	    		infoTable = '<table width="95%" align="center" cellpadding="8">';
				var superDepts = JSON.parse(jsonText);
				
				var j = 0;
				for(i=0; i<superDepts.superDepartList.size(); i++){
					var departId = superDepts.superDepartList[i].departId;
					var departName = superDepts.superDepartList[i].departName;
					
					if (j == 0) infoTable = infoTable + '<tr>';
					j++;
					
					if (departId == 'B198')
						infoTable = infoTable + '<td id="' + departId + '" width="20%"><a class="info1">' + departName + '</a>&nbsp;<img src="' + IMGDIR + '/arrow_down1.png" title="下级单位" onclick="javascript:changeStyle(\'' + departId + '\');"/></td>';
					else 
						infoTable = infoTable + '<td id="' + departId + '" width="20%"><a class="info1" href="index.do?deptId=' + departId + '&flag=navigate">' + departName + '</a>&nbsp;<img src="' + IMGDIR + '/arrow_down1.png" title="下级单位" onclick="javascript:changeStyle(\'' + departId + '\');"/></td>';
					
					if (j%5 == 0){ 
						infoTable = infoTable + '</tr>';
						j = 0;
					}
				}
				infoTable = infoTable + '</tr></table>';
		    	mapTable = mapTable + infoTable;
		    	mapTable = mapTable + '</td></tr><tr><td height="15" background="' + IMGDIR + '/h.png"></td></tr></table><div id="nextInfoTxt"></div>';
				zoom(obj,mapTable);
			}
		});
    }
    
    function nextMapInfo(obj){
    	
    	DepartmentListDWR.getChildDepartment(obj,function (jsonText){
		
			if (jsonText != null){
				var nextMapTable = '<table width="' + areaWidth + '" cellspacing="0" cellpadding="0"><tr><td>';
		    	var nextInfoTable = '<table width="95%" align="center" style="line-height:24px">';
				var childDepts = JSON.parse(jsonText);
				var j = 0;
				
				for(i=0; i<childDepts.childDepartList.size(); i++){
					var departId = childDepts.childDepartList[i].departId;
					var departName = childDepts.childDepartList[i].departName;
					
					if (j == 0) nextInfoTable = nextInfoTable + '<tr>';
					j++;
					nextInfoTable = nextInfoTable + '<td><a class="info2" href="index.do?deptId=' + departId + '&flag=navigate" target="_blank">' + departName + '</a></td>';
					if (j%5 == 0){ 
						nextInfoTable = nextInfoTable + '</tr>';
						j = 0;
					}
				}
				nextInfoTable = nextInfoTable + '</tr></table>';
		    	nextMapTable = nextMapTable + nextInfoTable;
		    	nextMapTable = nextMapTable + '</td></tr></tabel>';
				
				$('nextInfoTxt').innerHTML = nextMapTable
			}
		});
    }
    
    function changeStyle(obj){
    	var d = $(obj);
	    d.style.color = '#000000';
	    d.style.backgroundColor = '#dbdbdb';
    	if (oldObj != null){
	    	oldObj.style.color = '#cc0000';
	    	oldObj.style.backgroundColor = '#FFFFFF';
    	}
		
		oldObj = d;
		
    	nextMapInfo(obj);
    }
    
   /** 
    function mapInfo(obj){
    	DepartmentListDWR.getAllDepartment(function (jsonText){
    		if (jsonText != null){
    			mapTable = mapTable + '<table width="' + areaWidth + '" height="' + areaHeight + '" cellspacing="0" cellpadding="0"><tr><td>';
    			infoTable = '';
    			var departList = JSON.parse(jsonText);
    			
    			var j = -1;
    			var k = -1;
    			var m = 'div';
				for(i=0; i<departList.allDepartList.size(); i++){
					var departId = departList.allDepartList[i].departId;
					var departName = departList.allDepartList[i].departName;
					var departType = departList.allDepartList[i].departType;
					
					if (departType == '0')
					{
						if (j > -1)
							infoTable = infoTable + '</tr></table>';
						m = m + i;
						infoTable = infoTable + '<br/><table><tr><td colspan="8" onclick="javascript:display(' + m + ');"><a href="index.do?deptId=' + departId + '&flag=navigate" target="_blank">' + departName + '</a></td></tr>';
						j = 0;
					}
					else if (departType == '1')
					{
						if (j == 0) infoTable = infoTable + '<tr class="' + m + '" style="display:none"><td width="5%">&nbsp;</td>';
						j = j + 1;
						infoTable = infoTable + '<td><a href="index.do?deptId=' + departId + '&flag=navigate">' + departName + '</a></td>';
						if (j%7 == 0){ 
							infoTable = infoTable + '</tr>';
							j = 0;
						}
					}
					else if (departType == '2'){
						if (k == -1){
							infoTable = infoTable + '</tr></table>';
							infoTable = infoTable + '<br/><table width="98%"><tr><td width="10%" align="left">省直管市</td><td colspan="7" align="center" width="80%" style="color:blue;cursor:hand" onclick="javascript:display(\'szgs\');">>>>>></td></tr>';
							k = k + 1;
						}
						if (k == 0) infoTable = infoTable + '<tr id="szgs" style="display:none"><td width="5%">&nbsp;</td>';
						k = k + 1;
						infoTable = infoTable + '<td><a href="index.do?deptId=' + departId + '&flag=navigate">' + departName + '</a></td>';
						if (k%7 == 0){ 
							infoTable = infoTable + '</tr>';
							k = 0;
						}
					}
				}
				infoTable = infoTable + '</table></td></tr></table>';
				
				
		    	mapTable = mapTable + infoTable;
		    	mapTable = mapTable + '</td></tr></tabel>';
				zoom(obj,mapTable);
    		}
    	});
    }
    */
    
    
    function zoomclose() {
     document.getElementById('zoomimglayer').innerHTML = '';
     document.getElementById('zoomimglayer').style.display = 'none';
     document.getElementById('zoomimglayer_bg').style.display = 'none';
     mapTable = '';
     infoTable = '';
     setDictListSelect('dictId','selected');
    }
    
    function fetchOffset(obj) {
	 var left_offset = (document.body.clientWidth - areaWidth)  / 2;
	 var top_offset = (document.body.clientWidth + document.body.scrollTop) / 2 - 250;
	 //while((obj = obj.offsetParent) != null) {
	 //left_offset += obj.offsetLeft;
	 //top_offset += obj.offsetTop;
	//}
	 return { 'left' : left_offset, 'top' : top_offset };
    }
    
 function popupWin(obj){
 	thisObj = obj;
 	mapTable = '';
    infoTable = '';
 	MovieList(obj);
}

function BMList(obj){
    	DictListDWR.getDictListByTypeName('xzgl',function (jsonText){
			
			if (jsonText != null){
				mapTable = mapTable + '<table width="' + areaWidth + '" cellspacing="0" cellpadding="0"><tr><td>';
	    		infoTable = '<table width="95%" align="center" cellpadding="0"><tr><td width="12%"></td><td width="22%"></td><td width="16%"></td><td width="18%"></td><td width="16%"></td><td width="16%"></td></tr>';
				var dict = JSON.parse(jsonText);
				
				var j = 0;
				for(i=0; i<dict.DictList.size(); i++){
					var departId = dict.DictList[i].id;
					var departName = dict.DictList[i].dictName;
					
					if (j == 0) infoTable = infoTable + '<tr>';
					j++;
				    
				    if (departId != 117 && departId != 118 )
				    	infoTable = infoTable + '<td height="25" background="' + IMGDIR + '/zwgk_yw_bg2.gif" valign="middle"><input type="radio" name="deptRadio" value="' + departName + '"/>&nbsp;<a href="./index.do?form=XZSYSF&f=1&f1=1&f2=-1&dept=' + departName + '" valign="middle">' + departName + '</a></td>';
					else
						infoTable = infoTable + '<td height="25" valign="middle"><input type="radio" name="deptRadio" value="' + departName + '"/>&nbsp;<a href="./index.do?form=XZSYSF&f=1&f1=1&f2=-1&dept=' + departName + '" valign="middle">' + departName + '</a></td>';
					
					
					if (j%6 == 0){ 
						infoTable = infoTable + '</tr>';
						j = 0;
					}
				}
				infoTable = infoTable + '</tr></table>';
		    	mapTable = mapTable + infoTable;
		    	mapTable = mapTable + '</td></tr><tr><td height="15" background="' + IMGDIR + '/h.png"></td></tr>';
		    	mapTable = mapTable + '<tr><td bgcolor="#FFDE6C"><table width="95%" align="center" cellpadding="4"><tr><td align="right" width="40%" class="xjj">输入关键字（收费项目）</td><td align="left" width="15%"><input name="project1" id="project1"  type="text" size="16"/></td><td align="left"><input name="search" type="image" src="images/outlook/01/sect1_c.gif" onclick="javascript:doSearch();"/></td></tr></table></td></tr></table>';
				zoom(obj,mapTable);
			}
		});
    }
    
    function selectChange(id,name){
	var chk = document.getElementById(id);
	var chks = document.getElementsByName(name);
	
	if (chk.checked){
		for (i = 0; i < chks.length; i++)
			chks[i].checked = true;
	}else if (!chk.checked){
		for (i = 0; i < chks.length; i++)
			chks[i].checked = false;
	}
}

function doDelete(chkName){

	var chks = document.getElementsByName(chkName);
	var infoIdArray = '';
	
	for (i = 0; i < chks.length; i++){
		if (chks[i].checked){
			infoIdArray = infoIdArray + chks[i].value;
			if (i < chks.length - 1)
				infoIdArray = infoIdArray + ',';
		}
	}
	
	if (infoIdArray == ''){
		alert('请选择至少一个要删除的项！');
		return ;
	}

	if (!confirm('确认要删除吗？'))
		return ;
		
	MovieDictManagementDWR.deleteMovieDict(infoIdArray);
	dealing();
}

function doAdd(){
	if (infoCheck('dictItem','dictOrder')){
		var dictItem = $('dictItem').value;
		var dictOrder = $('dictOrder').value;
		var dictInfo = dictItem + ',' + dictOrder;
		
		MovieDictManagementDWR.addMovieDict(dictInfo);
		dealing();
	}
}

function doModify(){

	if (infoCheck('dictItem','dictOrder')){
		var dictItem = $('dictItem').value;
		var dictOrder = $('dictOrder').value;
		var id = $('id').value;
		var dictInfo = id + ',' + dictItem + ',' + dictOrder;
		
		MovieDictManagementDWR.modifyMovieDict(dictInfo);
		dealing();
	}
}

function doCancel(){
	var addBt = $('addButton');
	var modifyBt = $('modifyButton');
	var cancelBt = $('cancelButton');
	
	addBt.setAttribute('style',"display:''");
	modifyBt.setAttribute('style', "display:none");
	cancelBt.setAttribute('style', "display:none");
	
	addBt.style.display = '';
	modifyBt.style.display = 'none';
	cancelBt.style.display = 'none';
}

function toModify(id,dictItem,dictOrder){
	var addBt = $('addButton');
	var modifyBt = $('modifyButton');
	var cancelBt = $('cancelButton');
	
	
	addBt.setAttribute('style',"display:none");
	modifyBt.setAttribute('style', "display:''");
	cancelBt.setAttribute('style', "display:''");
	
	addBt.style.display = 'none';
	modifyBt.style.display = '';
	cancelBt.style.display = '';
	
	$('id').value = id;
	$('dictItem').value = dictItem;
	$('dictOrder').value = dictOrder;
}

function dealingShow(obj, show)
{
  var layer = document.getElementById(obj);
  if (show)
  {
    layer.style.left = window.screen.width - (window.screen.width - areaWidth)  / 2 - 200;
    layer.style.top = 10 - (window.screen.height - areaHeight) / 4;
    layer.style.display = "";
  }
  else{
    layer.style.display = "none";
	popupWin(thisObj);
  }
}

function dealing(){
	dealingShow('loading', true);
	setTimeout('dealingShow(\'loading\', false)',1500);
}

function infoCheck(item,order){
	var dictItem = $(item).value;
	var dictOrder = $(order).value;
	
	if(dictItem == '' || dictItem == null){
		return false;	
	}
	else if(dictOrder == '' || dictOrder == null){
		return false;	
	}
	else 
		return true;
}
    
function MovieList(obj){
   	MovieDictManagementDWR.getMovieDictList(function (jsonText){
		
		if (jsonText != null){
			mapTable = mapTable + '<table width="' + areaWidth + '" cellspacing="0" cellpadding="0"><tr><td>';
    		infoTable = '<table width="98%" align="center" cellpadding="4">';
			var dict = JSON.parse(jsonText);
			
			var j = 0;
			for(i=0; i<dict.DictList.length; i++){
				var id = dict.DictList[i].id;
				var dictItem = dict.DictList[i].dictName;
				var dictOrder = dict.DictList[i].dictOrder;
				
				if (j == 0) infoTable = infoTable + '<tr>';
				j++;
			    
			    infoTable = infoTable + '<td height="25" valign="middle"><input type="checkbox" name="dictChk" value="' + id + '"/>&nbsp;<a href="#" title="点击进行修改" onclick="toModify(\'' + id + '\',\'' + dictItem + '\',\'' + dictOrder + '\');">' + dictItem + '</a></td>';
				
				if (j%5 == 0){ 
					infoTable = infoTable + '</tr>';
					j = 0;
				}
			}
			infoTable = infoTable + '</tr></table>';
	    	mapTable = mapTable + infoTable;
	    	mapTable = mapTable + '</td></tr><tr><td><br/><table width="95%" cellpadding="4"><tr><td align="right" width="40%" class="xjj"><input type="checkbox" id="selectAll" onclick="javascript:selectChange(\'selectAll\',\'dictChk\');"/>全选&nbsp;&nbsp;<input type="button" name="delete" value="删除所选类别" onclick="javascript:doDelete(\'dictChk\');"/></td></tr></table></td></tr>';
	    	mapTable = mapTable + '<tr><td height="15" background="' + IMGDIR + '/h.png"></td></tr>';
	    	mapTable = mapTable + '<tr><td align="center"><table width="100%" align="center" cellpadding="2"><tr><input type="hidden" name="id" id="id" value=""/><td width="25%" align="left"><font color="#CA0000">（注：皆为必填）</font></td><td align="right" width="14%" class="xjj">类别名称</td><td align="left" width="15%"><input name="dictItem" id="dictItem"  type="text" size="12"/></td><td align="right" width="8%" class="xjj">排序</td><td align="left" width="15%"><input name="dictOrder" id="dictOrder"  type="text" size="10"/></td><td align="left"><input name="add" type="button" id="addButton" value="增加" style="display:" onclick="javascript:doAdd();"/><input name="add" type="button" id="modifyButton" value="修改" style="display:none" onclick="javascript:doModify();"/>&nbsp;&nbsp;<input name="add" type="button" id="cancelButton" value="取消" style="display:none" onclick="javascript:doCancel();"/></td></tr></table></td></tr></table>';
	    	mapTable = mapTable + '<div id="loading" style="position: absolute; width: 150px; height: 24px; z-index: 1; display:none; valign:middle; align:center; background-color:#666666}"><table border="0" bgcolor="#002448" cellpadding="1" cellspacing="0"><tr><td><table bgcolor="#FEF5DA" width="150" height="28" ><tr><td valign="middle" align="center" width="20" height="26"><img src="' + IMGDIR + '/loading2.gif"/></td><td>数据处理中.....</td></tr></table></td></tr></table></div>';
			zoom(obj,mapTable);
		}
	});
}