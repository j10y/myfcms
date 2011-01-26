Ext.ns("NewsCommentView");var NewsCommentView=function(){return new Ext.Panel({id:"NewsCommentView",title:"评论列表",iconCls:"menu-info",autoScroll:true,items:[new Ext.FormPanel({height:40,frame:false,id:"NewsCommentSearchForm",layout:"hbox",layoutConfig:{padding:"5",align:"middle"},defaults:{xtype:"label",border:false,margins:{top:0,right:4,bottom:4,left:4}},items:[{text:"请输入查询条件:"},{text:"新闻标题"},{xtype:"textfield",width:120,name:"Q_news.subject_S_LK"},{text:"时间"},{xtype:"datefield",width:100,format:"Y-m-d",name:"Q_createtime_D_GE"},{text:"-"},{xtype:"datefield",width:100,format:"Y-m-d",name:"Q_createtime_D_LE"},{text:"评论人"},{xtype:"textfield",width:80,name:"Q_fullname_S_LK"},{xtype:"button",text:"查询",iconCls:"search",handler:function(){var a=Ext.getCmp("NewsCommentSearchForm");var b=Ext.getCmp("NewsCommentGrid");if(a.getForm().isValid()){a.getForm().submit({waitMsg:"正在提交查询",url:__ctxPath+"/info/listNewsComment.do",success:function(d,e){var c=Ext.util.JSON.decode(e.response.responseText);b.getStore().loadData(c);}});}}}]}),this.setup()]});};NewsCommentView.prototype.setup=function(){return this.grid();};NewsCommentView.prototype.grid=function(){var e=new Ext.ux.grid.RowExpander({tpl:new Ext.Template('<p style="padding:5px 5px 5px 62px;"><b>评论内容:</b> {content}</p>')});var d=new Ext.grid.CheckboxSelectionModel();var a=new Ext.grid.ColumnModel({columns:[d,new Ext.grid.RowNumberer(),e,{header:"commentId",dataIndex:"commentId",hidden:true},{header:"所属新闻",dataIndex:"subject",width:250},{header:"评论时间",dataIndex:"createtime",width:100,renderer:function(f){return f.substring(0,10);}},{header:"评论人",dataIndex:"fullname",width:60},{header:"管理",dataIndex:"commentId",width:50,sortable:false,renderer:function(i,h,f,l,g){var k=f.data.commentId;var j="";if(isGranted("_NewsCommentDel")){j='<button title="删除" value=" " class="btn-del" onclick="NewsCommentView.remove('+k+')">&nbsp;&nbsp;</button>';}return j;}}],defaults:{sortable:true,menuDisabled:false,width:100}});var b=this.store();b.load({params:{start:0,limit:25}});var c=new Ext.grid.GridPanel({id:"NewsCommentGrid",tbar:this.topbar(),store:b,trackMouseOver:true,disableSelection:false,loadMask:true,plugins:e,autoHeight:true,cm:a,sm:d,viewConfig:{forceFit:true,enableRowBody:false,showPreview:false},bbar:new Ext.PagingToolbar({pageSize:25,store:b,displayInfo:true,displayMsg:"当前显示{0}至{1}， 共{2}条记录",emptyMsg:"当前没有记录"})});c.addListener("rowdblclick",function(g,f,h){g.getSelectionModel().each(function(i){NewsCommentView.edit(i.data.commentId);});});return c;};NewsCommentView.prototype.store=function(){var a=new Ext.data.Store({proxy:new Ext.data.HttpProxy({url:__ctxPath+"/info/listNewsComment.do"}),reader:new Ext.data.JsonReader({root:"result",totalProperty:"totalCounts",id:"id",fields:[{name:"commentId",type:"int"},"subject","content","createtime","fullname","userId"]}),remoteSort:true});a.setDefaultSort("commentId","desc");return a;};NewsCommentView.prototype.topbar=function(){var a=new Ext.Toolbar({id:"NewsCommentFootBar",height:30,bodyStyle:"text-align:left",items:[]});if(isGranted("_NewsCommentDel")){a.add(new Ext.Button({iconCls:"btn-del",text:"删除评论",handler:function(){var d=Ext.getCmp("NewsCommentGrid");var b=d.getSelectionModel().getSelections();if(b.length==0){Ext.ux.Toast.msg("信息","请选择要删除的记录！");return;}var e=Array();for(var c=0;c<b.length;c++){e.push(b[c].data.commentId);}NewsCommentView.remove(e);}}));}return a;};NewsCommentView.remove=function(b){var a=Ext.getCmp("NewsCommentGrid");Ext.Msg.confirm("信息确认","您确认要删除该记录吗？",function(c){if(c=="yes"){Ext.Ajax.request({url:__ctxPath+"/info/multiDelNewsComment.do",params:{ids:b},method:"post",success:function(){Ext.ux.Toast.msg("信息提示","成功删除所选记录！");a.getStore().reload({params:{start:0,limit:25}});}});}});};NewsCommentView.edit=function(a){new NewsCommentForm(a);};