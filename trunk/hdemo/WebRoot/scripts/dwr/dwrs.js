var userAgent = navigator.userAgent.toLowerCase();
var is_opera = userAgent.indexOf('opera') != -1 && opera.version();
var is_moz = (navigator.product == 'Gecko') && userAgent.substr(userAgent.indexOf('firefox') + 8, 3);
var is_ie = (userAgent.indexOf('msie') != -1 && !is_opera) && userAgent.substr(userAgent.indexOf('msie') + 5, 3);
var idNum = 0;
function initPage(){
	setYear('year');
}

function setYear(obj){
	
	InfoStatDWR.getMinYear(function(minYear){
	
		var opts = '';
		var maxYear = (new Date()).getUTCFullYear();
		
		if(is_ie){
			
			for(i = maxYear; i >= minYear; i--){
				opts = document.createElement('option');
				opts.value = i;
				if(i == $('yearValue').value) opts.selected = true;
				opts.innerText = i;
				$(obj).appendChild(opts);
			}
		}else{
			
			for(i = maxYear; i >= minYear; i--){
				if(i == $('yearValue').value)
					opts = opts + '<option selected value=' + i + '>' + i + '</option>';
				else 
					opts = opts + '<option value=' + i + '>' + i + '</option>';
			}
			$(obj).innerHTML = $(obj).innerHTML + opts;
		}
		
	});
}

function getDictByTypeName(obj,selected,typeName){
	
	DictListDWR.getDictListByTypeName(typeName,function (jsonText){
		
		if (jsonText != null){
			var opts = '';
			var dicts = JSON.parse(jsonText);
			
			if(is_ie){
			
				for(i=0; i<dicts.DictList.size(); i++){
					var id = dicts.DictList[i].id;
					var dictName = dicts.DictList[i].dictName;
					
					opts = document.createElement('option');
					opts.value = dictName;
					if(dictName == $(selected).value) opts.selected = true;
					opts.innerText = dictName;
					$(obj).appendChild(opts);
				}
			}else{
			
				for(i=0; i<dicts.DictList.size(); i++){
					var id = dicts.DictList[i].id;
					var dictName = dicts.DictList[i].dictName;
					
					if(dictName == $(selected).value)
						opts = opts + '<option selected value=' + dictName + '>' + dictName + '</option>';
					else 
						opts = opts + '<option value=' + dictName + '>' + dictName + '</option>';
				}
				$(obj).innerHTML = $(obj).innerHTML + opts;
			}
		}
	});
}

function getDictAreaByTypeName(obj,selected,typeName){
	
	DictListDWR.getDictAreaListByTypeName(typeName,function (jsonText){
		
		if (jsonText != null){
			var opts = '';
			var dicts = JSON.parse(jsonText);
			
			if(is_ie){
			
				for(i=0; i<dicts.DictList.size(); i++){
					var id = dicts.DictList[i].id;
					var dictName = dicts.DictList[i].dictName;
					
					opts = document.createElement('option');
					opts.value = dictName;
					if(dictName == $(selected).value) opts.selected = true;
					opts.innerText = dictName;
					$(obj).appendChild(opts);
				}
			}else{
			
				for(i=0; i<dicts.DictList.size(); i++){
					var id = dicts.DictList[i].id;
					var dictName = dicts.DictList[i].dictName;
					
					if(dictName == $(selected).value)
						opts = opts + '<option selected value=' + dictName + '>' + dictName + '</option>';
					else 
						opts = opts + '<option value=' + dictName + '>' + dictName + '</option>';
				}
				$(obj).innerHTML = $(obj).innerHTML + opts;
			}
		}
	});
}

function getWaterAreaList(obj,selected){
	
	DictListDWR.getWaterAreaList(function (jsonText){
		
		if (jsonText != null){
			var opts = '';
			var citys = JSON.parse(jsonText);
			
			if(is_ie){
			
				for(i=0; i<citys.WaterAreaList.size(); i++){
					var areaName = citys.WaterAreaList[i].areaName;
					
					opts = document.createElement('option');
					opts.value = areaName;
					if(areaName == $(selected).value) opts.selected = true;
					opts.innerText = areaName;
					$(obj).appendChild(opts);
				}
			}else{
			
				for(i=0; i<citys.WaterAreaList.size(); i++){
					var areaName = citys.WaterAreaList[i].areaName;
					
					if(areaName == $(selected).value)
						opts = opts + '<option selected value=' + areaName + '>' + areaName + '</option>';
					else 
						opts = opts + '<option value=' + areaName + '>' + areaName + '</option>';
				}
				$(obj).innerHTML = $(obj).innerHTML + opts;
			}
		}
	});
}

function getFuelAreaList(obj,selected){
	
	DictListDWR.getFuelAreaList(function (jsonText){
		
		if (jsonText != null){
			var opts = '';
			var fuels = JSON.parse(jsonText);
			
			if(is_ie){
			
				for(i=0; i<fuels.FuelAreaList.size(); i++){
					var areaName = fuels.FuelAreaList[i].areaName;
					
					opts = document.createElement('option');
					opts.value = areaName;
					if(areaName == $(selected).value) opts.selected = true;
					opts.innerText = areaName;
					$(obj).appendChild(opts);
				}
			}else{
			
				for(i=0; i<fuels.FuelAreaList.size(); i++){
					var areaName = fuels.FuelAreaList[i].areaName;
					
					if(areaName == $(selected).value)
						opts = opts + '<option selected value=' + areaName + '>' + areaName + '</option>';
					else 
						opts = opts + '<option value=' + areaName + '>' + areaName + '</option>';
				}
				$(obj).innerHTML = $(obj).innerHTML + opts;
			}
		}
	});
}

function getRoadListByLinesName(obj,selected,linesName){
	linesName = $(linesName).value;
	DictListDWR.getRoadListByLinesName(linesName,function (jsonText){
		
		if (jsonText != null){
			var opts = '';
			var roads = JSON.parse(jsonText);
			
			if(is_ie){
			
				for(i=0; i<roads.RoadList.size(); i++){
					var id = roads.RoadList[i].id;
					var site = roads.RoadList[i].site;
					
					opts = document.createElement('option');
					opts.value = site;
					if(site == $(selected).value) opts.selected = true;
					opts.innerText = site;
					$(obj).appendChild(opts);
				}
			}else{
			
				for(i=0; i<roads.RoadList.size(); i++){
					var id = roads.RoadList[i].id;
					var site = roads.RoadList[i].site;
					
					if(site == $(selected).value)
						opts = opts + '<option selected value=' + site + '>' + site + '</option>';
					else 
						opts = opts + '<option value=' + site + '>' + site + '</option>';
				}
				$(obj).innerHTML = $(obj).innerHTML + opts;
			}
		}
	});
}

function getDocTypeMan(typeId,obj){
	var optName = 'subOpt';
	clearChild(obj,optName);
	
	DocumentDWR.getDocTypeMan(typeId,function (jsonText){
		
		if (jsonText != null){
			var opts = '';
			var docTypeMans = JSON.parse(jsonText);
			
			if(is_ie){
				idNum = docTypeMans.docTypeManList.size()
				for(i=0; i<docTypeMans.docTypeManList.size(); i++){
					var id = docTypeMans.docTypeManList[i].id;
					var columnName = docTypeMans.docTypeManList[i].columnName;
					
					opts = document.createElement('option');
					opts.id = 'id' + (i+1);
					opts.value = id;
					if(id == $('subTypeValue').value) opts.selected = true;
					opts.innerText = columnName;
					$(obj).appendChild(opts);
				}
			}else{
			
				for(i=0; i<docTypeMans.docTypeManList.size(); i++){
					var id = docTypeMans.docTypeManList[i].id;
					var columnName = docTypeMans.docTypeManList[i].columnName;
					
					if(id == $('subTypeValue').value)
						opts = opts + '<option name="' + optName + '" selected value=' + id + '>' + columnName + '</option>';
					else 
						opts = opts + '<option name="' + optName + '" value=' + id + '>' + columnName + '</option>';
				}
				$(obj).innerHTML = $(obj).innerHTML + opts;
			}
		}
	});
}

function columnChange(obj){
	var typeId = $(obj).value;
	if (typeId != null && typeId != '')
		getDocTypeMan(typeId,'subType');
	else clearChild('subType','subOpt');
}

function initOnLoad(){
	getColumn('bigType');
	var typeId = $('bigTypeValue').value;
	getDocTypeMan(typeId,'subType');
}

function clearChild(obj,optName){
	
		for (i = idNum; i > 0; i--){	
			$('id' + i).remove();
		}
		idNum = 0;
	
}

function previewTemplates(obj1,obj2){
	var templateName = $F(obj2);
	$(obj1).src = 'images/' + templateName + '.jpg';
	$(obj1).width = 300;
	$(obj1).height = 250;
}

function getSuperDepartment(obj){
	DepartmentListDWR.getSuperDepartment(function (jsonText){
		
		if (jsonText != null){
			var opts = '';
			var superDepts = JSON.parse(jsonText);
			
			if(is_ie){
			
				for(i=0; i<superDepts.superDepartList.size(); i++){
					var departId = superDepts.superDepartList[i].departId;
					var departName = superDepts.superDepartList[i].departName;
					
					opts = document.createElement('option');
					opts.value = departId;
					opts.innerText = departName;
					$(obj).appendChild(opts);
				}
			}else{
			
				for(i=0; i<superDepts.superDepartList.size(); i++){
					var departId = superDepts.superDepartList[i].departId;
					var departName = superDepts.superDepartList[i].departName;
					opts = opts + '<option value=' + departId + '>' + departName + '</option>';
				}
				$(obj).innerHTML = $(obj).innerHTML + opts;
			}
		}
	});
}

function getChildDepartment(supDepart,obj){
	var optName = 'subOpt';
	clearChild(obj,optName);
	
	DepartmentListDWR.getChildDepartment(supDepart,function (jsonText){
		
		if (jsonText != null){
			var opts = '';
			var childDepts = JSON.parse(jsonText);
			
			if(is_ie){
				idNum = childDepts.childDepartList.size()
				for(i=0; i<childDepts.childDepartList.size(); i++){
					var departId = childDepts.childDepartList[i].departId;
					var departName = childDepts.childDepartList[i].departName;
					
					opts = document.createElement('option');
					opts.id = 'id' + (i+1);
					opts.value = 'index.do?deptId=' + departId + '&flag=navigate';
					opts.innerText = departName;
					$(obj).appendChild(opts);
				}
			}else{
			
				for(i=0; i<childDepts.childDepartList.size(); i++){
					var departId = childDepts.childDepartList[i].departId;
					var departName = childDepts.childDepartList[i].departName;
					opts = opts + '<option name="' + optName + '" value="index.do?deptId=' + departId + '&flag=navigate">' + departName + '</option>';
				}
				$(obj).innerHTML = $(obj).innerHTML + opts;
			}
		}
	});
}

function superDepartChange(obj){
	var typeId = $(obj).value;
	if (typeId != null && typeId != '')
		getChildDepartment(typeId,'childDepart');
	else clearChild('childDepart','subOpt');
}

function getPicNews(){

	PicNewsDWR.getPicNews(68,function(jsonText){
		if (jsonText != null){
			
			var picsList = JSON.parse(jsonText);
			var pics = '',links = '',texts = '';
			
			for(i=0; i<picsList.PicsList.size(); i++){
			
				pics = pics + picsList.PicsList[i].pics;
				if (i < picsList.PicsList.size()-1)
					pics = pics + '|';
					
				links = links + escape("./index.do?form=VIEW01&id=" + picsList.PicsList[i].links);
				if (i < picsList.PicsList.size()-1)
					links = links + '|';
					
				texts = texts + picsList.PicsList[i].texts;
				if (i < picsList.PicsList.size()-1)
					texts = texts + '|';
			}
			
			var focus_width = 250;
			var focus_height = 180;
			var text_height = 20;
			var str = createPixViewer(focus_width,focus_height,text_height,pics,links,texts);
			$('picdiv').innerHTML = str;
		}
	});
}

function setDictListSelect(obj,selected){
	var optName = 'movieDictOpt';
	clearChild(obj,optName);
	
	MovieDictManagementDWR.getMovieDictList(function (jsonText){
	
		if (jsonText != null){
			var opts = '';
			var dicts = JSON.parse(jsonText);
			idNum = dicts.DictList.length;
			if(is_ie){
			
				for(i=0; i<dicts.DictList.length; i++){
					var id = dicts.DictList[i].id;
					var dictItem = dicts.DictList[i].dictName;
					
					opts = document.createElement('option');
					opts.value = id;
					opts.id = 'id' + (i+1);
					if(id == $(selected).value) opts.selected = true;
					opts.innerText = dictItem;
					document.getElementById(obj).appendChild(opts);
				}
			}else{
				
				for(i=0; i<dicts.DictList.length; i++){
					var id = dicts.DictList[i].id;
					var dictItem = dicts.DictList[i].dictName;
					
					if(id == $(selected).value)
						opts = opts + '<option id="id' + (i+1) + '" selected value=' + id + '>' + dictItem + '</option>';
					else 
						opts = opts + '<option id="id' + (i+1) + '" value=' + id + '>' + dictItem + '</option>';
				}
				$(obj).innerHTML = $(obj).innerHTML + opts;
			}
		}
	});
}

function setPhotoDictListSelect(obj,personId,dictId){
	var optName = 'photoDictOpt';
	clearChild(obj,optName);
	
	PhotoDictManagementDWR.getPhotoDictList(personId,function (jsonText){
	
		if (jsonText != null){
			var opts = '';
			var dicts = JSON.parse(jsonText);
			idNum = dicts.DictList.length;
			
			if(is_ie){
			
				for(i=0; i<dicts.DictList.length; i++){
					var id = dicts.DictList[i].id;
					var dictItem = dicts.DictList[i].dictName;
					
					opts = document.createElement('option');
					opts.value = id;
					opts.id = 'id' + (i+1);
					if(id == dictId) opts.selected = true;
					opts.innerText = dictItem;
					document.getElementById(obj).appendChild(opts);
				}
			}else{
				
				for(i=0; i<dicts.DictList.length; i++){
					var id = dicts.DictList[i].id;
					var dictItem = dicts.DictList[i].dictName;
					
					if(id == dictId)
						opts = opts + '<option id="id' + (i+1) + '" selected value=' + id + '>' + dictItem + '</option>';
					else  
						opts = opts + '<option id="id' + (i+1) + '" value=' + id + '>' + dictItem + '</option>';
				}
				$(obj).innerHTML = $(obj).innerHTML + opts;
			}
		}
	});
}
