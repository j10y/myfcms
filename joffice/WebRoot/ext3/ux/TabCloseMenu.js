/*
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.ux.TabCloseMenu=function(){var a,c,b;this.init=function(e){a=e;a.on("contextmenu",d);};function d(h,g,i){if(!c){c=new Ext.menu.Menu({items:[{id:a.id+"-close",text:"Close Tab",handler:function(){a.remove(b);}},{id:a.id+"-close-others",text:"Close Other Tabs",handler:function(){a.items.each(function(e){if(e.closable&&e!=b){a.remove(e);}});}}]});}b=g;var f=c.items;f.get(a.id+"-close").setDisabled(!g.closable);var j=true;a.items.each(function(){if(this!=g&&this.closable){j=false;return false;}});f.get(a.id+"-close-others").setDisabled(j);i.stopEvent();c.showAt(i.getPoint());}};Ext.preg("tabclosemenu",Ext.ux.TabCloseMenu);