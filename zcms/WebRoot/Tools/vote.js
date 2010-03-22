function clickInput(id){
	document.getElementById(id+'_Button').checked=true;
}

function checkVote(id){
	var f = document.getElementById('voteForm_'+id);
	var inputs = f.getElementsByTagName('input');
	var arrs = [];
	for(var i=0;i<inputs.length;i++){
		if(inputs[i].getAttribute("type")=="radio"||inputs[i].getAttribute("type")=="checkbox"){
			var sid = inputs[i].getAttribute("name").substring("8");
			var b = false;
			for(var j=0;j<arrs.length;j++){
				if(arrs[j]==sid){
					b = true;
					break;
				}
			}
			if(!b){
				arrs.push(sid);
			}
		}
	}
	
	var str='';
	var position = '';
	var err = false;
	for(var i=0;i<arrs.length;i++){
		var sid = arrs[i];
		var eles = document.getElementsByName('Subject_'+sid);
		var flag = false;
		for(var j=0;j<eles.length;j++){
			if(eles[j].checked){
				flag = true;
				break;
			}
		}
		if(!flag){
			err = true;
			if(document.getElementById(arrs[i])){
				if(document.getElementById(arrs[i]).innerText){
					str+='\n'+document.getElementById(arrs[i]).innerText;
				}else{
					str+='\n'+document.getElementById(arrs[i]).textContent;
				}
				if(!position){
					position = sid;
				}
			}
		}
	}
	if(err){
		var url = window.location+'';
		alert('您还有以下调查没有填写：'+str);
		window.location = url.substring(0,url.lastIndexOf('#'))+'#'+position;
		return false;
	}
	return true;
}