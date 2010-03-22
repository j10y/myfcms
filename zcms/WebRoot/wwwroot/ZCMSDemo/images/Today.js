//单个数字配零
	function getDouble(number){
		var numbers=["0","1","2","3","4","5","6","7","8","9"];
		for(var i=0;i<numbers.length;i++){
			if(numbers[i]==number){
				return "0"+numbers[i];
			}else if(i==9){
				return number;
			}
			
		}
	}
//得到当天时间
	function getTodayTime(){
		var days=["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
		var today=new Date();
		var str= (today.getYear()<1900?1900+today.getYear():today.getYear())+"年" + getDouble([today.getMonth()+1])+"月" +getDouble(today.getDate()) +"日 &nbsp;"+ days[today.getDay()]+"<br>"+getDouble(today.getHours())+":"+getDouble(today.getMinutes())+":"+getDouble(today.getSeconds());
		document.getElementById('datetime').innerHTML=str;
		
	}
//每隔一秒刷新一次
	setInterval("getTodayTime()",1000);
