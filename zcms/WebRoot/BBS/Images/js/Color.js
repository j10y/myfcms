function trim(s){      
    try{      
        return s.replace(/^\s+|\s+$/g,"");      
    }catch(e){      
        return s;      
    }      
}   
  
function rgb(red,green,blue) {   
var hexarray= new Array(256);   
hexarray[0]="00"; hexarray[1]="01"; hexarray[2]="02";   
hexarray[3]="03"; hexarray[4]="04"; hexarray[5]="05";   
hexarray[6]="06"; hexarray[7]="07"; hexarray[8]="08";   
hexarray[9]="09"; hexarray[10]="0A"; hexarray[11]="0B";    
hexarray[12]="0C"; hexarray[13]="0D"; hexarray[14]="0E";   
hexarray[15]="0F"; hexarray[16]="10"; hexarray[17]="11";   
hexarray[18]="12"; hexarray[19]="13"; hexarray[20]="14";   
hexarray[21]="15"; hexarray[22]="16"; hexarray[23]="17";   
hexarray[24]="18"; hexarray[25]="19"; hexarray[26]="1A";   
hexarray[27]="1B"; hexarray[28]="1C"; hexarray[29]="1D";   
hexarray[30]="1E"; hexarray[31]="1F"; hexarray[32]="20";   
hexarray[33]="21"; hexarray[34]="22"; hexarray[35]="23";   
hexarray[36]="24"; hexarray[37]="25"; hexarray[38]="26";   
hexarray[39]="27"; hexarray[40]="28"; hexarray[41]="29";    
hexarray[42]="2A"; hexarray[43]="2B"; hexarray[44]="2C";   
hexarray[45]="2D"; hexarray[46]="2E"; hexarray[47]="2F";   
hexarray[48]="30"; hexarray[49]="31"; hexarray[50]="32";   
hexarray[51]="33"; hexarray[52]="34"; hexarray[53]="35";   
hexarray[54]="36"; hexarray[55]="37"; hexarray[56]="38";   
hexarray[57]="39"; hexarray[58]="3A"; hexarray[59]="3B";   
hexarray[60]="3C"; hexarray[61]="3D"; hexarray[62]="3E";   
hexarray[63]="3F"; hexarray[64]="40"; hexarray[65]="41";   
hexarray[66]="42"; hexarray[67]="43"; hexarray[68]="44";   
hexarray[69]="45"; hexarray[70]="46"; hexarray[71]="47";   
hexarray[72]="48"; hexarray[73]="49"; hexarray[74]="4A";   
hexarray[75]="4B"; hexarray[76]="4C"; hexarray[77]="4D";   
hexarray[78]="4E"; hexarray[79]="4F"; hexarray[80]="50";   
hexarray[81]="51"; hexarray[82]="52"; hexarray[83]="53";   
hexarray[84]="54"; hexarray[85]="55"; hexarray[86]="56";   
hexarray[87]="57"; hexarray[88]="58"; hexarray[89]="59";   
hexarray[90]="5A"; hexarray[91]="5B"; hexarray[92]="5C";   
hexarray[93]="5D"; hexarray[94]="5E"; hexarray[95]="6F";   
hexarray[96]="60"; hexarray[97]="61"; hexarray[98]="62";   
hexarray[99]="63"; hexarray[100]="64"; hexarray[101]="65";   
hexarray[102]="66"; hexarray[103]="67"; hexarray[104]="68";   
hexarray[105]="69"; hexarray[106]="6A"; hexarray[107]="6B";   
hexarray[108]="6C"; hexarray[109]="6D"; hexarray[110]="6E";   
hexarray[111]="6F"; hexarray[112]="70"; hexarray[113]="71";   
hexarray[114]="72"; hexarray[115]="73"; hexarray[116]="74";   
hexarray[117]="75"; hexarray[118]="76"; hexarray[119]="77";   
hexarray[120]="78"; hexarray[121]="79"; hexarray[122]="7A";   
hexarray[123]="7B"; hexarray[124]="7C"; hexarray[125]="7D";   
hexarray[126]="7E"; hexarray[127]="7F"; hexarray[128]="80";   
hexarray[129]="81"; hexarray[130]="82"; hexarray[131]="83";   
hexarray[132]="84"; hexarray[133]="85"; hexarray[134]="86";   
hexarray[135]="87"; hexarray[136]="88"; hexarray[137]="89";   
hexarray[138]="8A"; hexarray[139]="8B"; hexarray[140]="8C";   
hexarray[141]="8D"; hexarray[142]="8E"; hexarray[143]="8F";   
hexarray[144]="90"; hexarray[145]="91"; hexarray[146]="92";    
hexarray[147]="93"; hexarray[148]="94"; hexarray[149]="95";   
hexarray[150]="96"; hexarray[151]="97"; hexarray[152]="98";   
hexarray[153]="99"; hexarray[154]="9A"; hexarray[155]="9B";   
hexarray[156]="9C"; hexarray[157]="9D"; hexarray[158]="9E";   
hexarray[159]="9F"; hexarray[160]="A0"; hexarray[161]="A1";   
hexarray[162]="A2"; hexarray[163]="A3"; hexarray[164]="A4";   
hexarray[165]="A5"; hexarray[166]="A6"; hexarray[167]="A7";   
hexarray[168]="A8"; hexarray[169]="A9"; hexarray[170]="AA";   
hexarray[171]="AB"; hexarray[172]="AC"; hexarray[173]="AD";   
hexarray[174]="AE"; hexarray[175]="AF"; hexarray[176]="B0";   
hexarray[177]="B1"; hexarray[178]="B2"; hexarray[179]="B3";   
hexarray[180]="B4"; hexarray[181]="B5"; hexarray[182]="B6";   
hexarray[183]="B7"; hexarray[184]="B8"; hexarray[185]="B9";   
hexarray[186]="BA"; hexarray[187]="BB"; hexarray[188]="BC";   
hexarray[189]="BD"; hexarray[190]="BE"; hexarray[191]="BF";   
hexarray[192]="C0"; hexarray[193]="C1"; hexarray[194]="C2";   
hexarray[195]="C3"; hexarray[196]="C4"; hexarray[197]="C5";   
hexarray[198]="C6"; hexarray[199]="C7"; hexarray[200]="C8";   
hexarray[201]="C9"; hexarray[202]="CA"; hexarray[203]="CB";   
hexarray[204]="CC"; hexarray[205]="CD"; hexarray[206]="CE";   
hexarray[207]="CF"; hexarray[208]="D0"; hexarray[209]="D1";   
hexarray[210]="D2"; hexarray[211]="D3"; hexarray[212]="D4";   
hexarray[213]="D5"; hexarray[214]="D6"; hexarray[215]="D7";   
hexarray[216]="D8"; hexarray[217]="D9"; hexarray[218]="DA";   
hexarray[219]="DB"; hexarray[220]="DC"; hexarray[221]="DD";   
hexarray[222]="DE"; hexarray[223]="DF"; hexarray[224]="E0";   
hexarray[225]="E1"; hexarray[226]="E2"; hexarray[227]="E3";   
hexarray[228]="E4"; hexarray[229]="E5"; hexarray[230]="E6";   
hexarray[231]="E7"; hexarray[232]="E8"; hexarray[233]="E9";   
hexarray[234]="EA"; hexarray[235]="EB"; hexarray[236]="EC";   
hexarray[237]="ED"; hexarray[238]="EE"; hexarray[239]="EF";   
hexarray[240]="F0"; hexarray[241]="F1"; hexarray[242]="F2";   
hexarray[243]="F3"; hexarray[244]="F4"; hexarray[245]="F5";   
hexarray[246]="F6"; hexarray[247]="F7"; hexarray[248]="F8";   
hexarray[249]="F9"; hexarray[250]="FA"; hexarray[251]="FB";   
hexarray[252]="FC"; hexarray[253]="FD"; hexarray[254]="FE";    
hexarray[255]="FF";   
hexcode = "#" + hexarray[red] + hexarray[green] + hexarray[blue];   
return hexcode;   
}   
var ColorHex=new Array('00','33','66','99','CC','FF');
var SpColorHex=new Array('FF0000','00FF00','0000FF','FFFF00','00FFFF','FF00FF') ;   
var current=null    
function initcolor(evt,id)  {    
	var colorTable='';    
	for (i=0;i<2;i++){    
		for (j=0;j<6;j++){    
			colorTable=colorTable+'<tr height=15>';    
			colorTable=colorTable+'<td width=15 style="background-color:#000000">';  
			if (i==0){    
				colorTable=colorTable+'<td width=15 style="cursor:pointer;background-color:#'+ColorHex[j]+ColorHex[j]+ColorHex[j]+'" onclick="doclick(this.style.backgroundColor,'+id+')">';
			}else{    
				colorTable=colorTable+'<td width=15 style="cursor:pointer;background-color:#'+SpColorHex[j]+'" onclick="doclick(this.style.backgroundColor,'+id+')">';
			}    
			colorTable=colorTable+'<td width=15 style="background-color:#000000">';    
			for (k=0;k<3;k++){    
				for (l=0;l<6;l++){    
					colorTable=colorTable+'<td width=15 style="cursor:pointer;background-color:#'+ColorHex[k+i*3]+ColorHex[l]+ColorHex[j]+'" onclick="doclick(this.style.backgroundColor,'+id+')">';    
				}    
			}   
		}    
	}    
	colorTable='<table border="0" cellspacing="0" cellpadding="0" style="border:1px #000000 solid;border-bottom:none;border-collapse: collapse;width:337px;" bordercolor="000000">'    
	+'<tr height=20><td colspan=21 bgcolor=#ffffff style="font:12px tahoma;padding-left:2px;">'    
	+'<span style="float:left;color:#999999;">颜色选择</span>'    
	+'<span style="float:right;padding-right:3px;cursor:pointer;" onclick="colorclose()">×关闭</span>'    
	+'</td></table>'    
	+'<table border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="000000" style="cursor:pointer;">'    
	+colorTable+'</table>'; 
	document.getElementById("colorpane").innerHTML = colorTable;    
	var current_x = document.getElementById("Color_"+id).offsetLeft;    
	var current_y = document.getElementById("Color_"+id).offsetTop;   
	document.getElementById("colorpane").style.left = current_x + "px";    
	document.getElementById("colorpane").style.top = current_y + "px";    
}    
function doclick(obj,id){    
    var rgbcolor = "";   
    if(obj.length>7){   
        rgbcolor=rgb(trim(obj.substring(obj.indexOf('(')+1,obj.indexOf(')')).split(',')[0]),trim(obj.substring(obj.indexOf('(')+1,obj.indexOf(')')).split(',')[1]),trim(obj.substring(obj.indexOf('(')+1,obj.indexOf(')')).split(',')[2]));   
        document.getElementById("Color_"+id).value=rgbcolor;   
        document.getElementById("Color_"+id).style.backgroundColor=rgbcolor;   
    }else{   
        rgbcolor=obj;   
        document.getElementById("Color_"+id).value=rgbcolor;   
        document.getElementById("Color_"+id).style.backgroundColor=rgbcolor;   
    }   
    document.getElementById("colorpane").style.display = "none";    
}      
function colorclose(){    
	document.getElementById("colorpane").style.display = "none";    
}    
function coloropen(event,id){   
	initcolor(event,id); 
	document.getElementById("colorpane").style.display = "";    
}    
