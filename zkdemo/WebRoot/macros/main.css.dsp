<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>
<%@ taglib uri="http://www.zkoss.org/dsp/zk/core" prefix="z" %>
${z:setCSSCacheControl()}

html {overflow:hidden;}
img { -ms-interpolation-mode:bicubic }
body {
	padding: 0 !important;
}
h4 {
	margin: 0;
	padding: 10px 0;
}
P {
	margin: 0;
	padding: 5px 0;
}
ul {
	margin-top: 5px;
	margin-bottom: 5px;
}
a, a:visited {
	color:#008bb6;
}
ul li	{list-style: url(${c:encodeURL(c:browser('ie6-') ? '/img/z-bullet1.gif' : '/img/Centigrade-Widget-Icons/Bullet-10x10.png')}) disc}
ul ul li	{list-style: url(${c:encodeURL('/img/z-bullet2.gif')}) circle}
ul ul ul li	{list-style: url(${c:encodeURL('/img/z-bullet3.gif')}) square}
.main-header .z-north-body {
	background:transparent url(${c:encodeURL( c:property('org.zkoss.zkdemo.theme.cookie') != 'silvergray' ? '/img/category-bg.png' : '/img/Centigrade-Widget-Icons/GradientGray.png')}) repeat-x scroll 0 0;
}
.main-categorybar {
	position: relative;
	overflow: hidden;
	${c:browser('ie6-') ? 'float: left;' : ''}
}
.main-categorybar-body {
	margin: 0px;
	width: 100%;
	overflow: hidden;
	zoom: 1;
}
.main-categorybar-body-scroll {
	position: relative;
	margin-left: 20px;
	margin-right: 20px;
}
.main-categorybar-right-scroll {
	background-color: transparent;
	background-image: url(${c:encodeURL( c:property('org.zkoss.zkdemo.theme.cookie') != 'silvergray' ? '/img/scroll-right.png' : '/img/g-scroll-right.png')});
	background-repeat: no-repeat;
	background-attachment: scroll;
	background-position: 0 0;
	border-bottom:1px solid ${c:property('org.zkoss.zkdemo.theme.cookie') != 'silvergray' ? '#8DB2E3' : '#B7B7B7'};
	cursor:pointer;
	position:absolute;
	right:0;
	top:37px;
	width:18px;
	z-index:10;
	height:25px;
}
.main-categorybar-right-scroll:hover {
	background-position:-18px 0;
}
.main-categorybar-left-scroll {
	background-color: transparent;
	background-image: url(${c:encodeURL( c:property('org.zkoss.zkdemo.theme.cookie') != 'silvergray' ? '/img/scroll-left.png' : '/img/g-scroll-left.png')});
	background-repeat: no-repeat;
	background-attachment: scroll;
	background-position: -18px 0;
	border-bottom:1px solid ${c:property('org.zkoss.zkdemo.theme.cookie') != 'silvergray' ? '#8DB2E3' : '#B7B7B7'};
	cursor:pointer;
	left:0;
	position:absolute;
	top:37px;
	width:18px;
	z-index:10;
	height:25px;
}
.main-categorybar-left-scroll:hover {
	background-position:0px 0;
}
.main-seld {
	background:transparent url(${c:encodeURL(c:property('org.zkoss.zkdemo.theme.cookie') != 'silvergray' ? '/img/category-seld.png' : '/img/g-category-seld.png')}) no-repeat scroll 0 0;
}
.main-over.main-seld {
	background:transparent url(${c:encodeURL(c:property('org.zkoss.zkdemo.theme.cookie') != 'silvergray' ? '/img/category-over-seld.png' : '/img/g-category-over-seld.png')}) no-repeat scroll 0 0;
}
.main-over {
	background:transparent url(${c:encodeURL(c:property('org.zkoss.zkdemo.theme.cookie') != 'silvergray' ? '/img/category-over.png' : '/img/g-category-over.png')}) no-repeat scroll 0 0;
}
.main-search-inp {
    padding: 2px 0 1px 18px;
	background: white url(${c:encodeURL(c:browser('ie6-') ? '/img/search.gif' : '/img/search.png')}) no-repeat scroll 0 0;
}
.main-category {
	margin-top: 10px; float:left; height: 80px; width: 90px;
	text-align:center;
	cursor: pointer;
}
.main-category-img {
	width: 48px;
	height: 48px;
}
.main-category-text {
	color: #1c5178; font-size:10px
}
.main-logo {
	padding: 10px 10px 0 10px;
	float:left;
}
div.main-items {
	border: 0; background: white;
}
.main-items .z-listbox-body {
	overflow-x: hidden;
}
.main-items .z-listcell-cnt img {
	width:24px;
	height:24px;
}
.main-items .z-listcell-cnt {
	padding-left: 5px;
}
.main-main-cnt {
	padding-left: 5px;
}
.main-main-desc {
	padding-bottom: 5px;
}
.pointer {
	cursor:pointer;
}
.edition-field, .edition-field:visited {
	color: #346B93;
	text-decoration: none;
	font-style: italic;
}
a.edition-field:hover {
	text-decoration: underline;
}