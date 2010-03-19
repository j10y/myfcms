/*
 *  函数名：  String.Format()
 *  用途：    字符串格式化
 *  调用方法：alert(String.Format("我们都很喜欢使用如{0}中提供的{1}方法，但是在js中却没有{1}，通过此方法，我们可以为其扩展一个，效果还算不错，希望大家喜欢。{2}{3}", "C#", "string.Format()", "谢谢大家！", "\n\nby gaoming \nmail:Lonsan21@163.com" ));
 */
String.Format = function()
{
  
  if(arguments.length == 0)
    return "";
  
  if(arguments.length == 1)
    return arguments[0];
  
  var reg = /{(\d+)?}/g;
  var args = arguments;
  var result = arguments[0].replace(
    reg,
    function($0, $1) {
      return  args[parseInt($1)+1];
    }
  )

  return result;
}


/*
 *  函数名：  checkbox_selectAll()
 *  用途：    根据名称选择CheckBox
 *  调用方法：checkbox_selectAll('id')
 */
function checkbox_selectAll(name)
{
  var obj=document.getElementsByName(name);
  for (var i = 0 ; i < obj.length; i++)
  {
    obj[i].checked = true;
  }
}

/*
 *  函数名：  checkbox_unselect()
 *  用途：    根据名称反选CheckBox
 *  调用方法：checkbox_unselect('id')
 */
function checkbox_unselect(name)
{
  var obj=document.getElementsByName(name);
  for (var i = 0 ; i < obj.length; i++)
  {
    obj[i].checked = !obj[i].checked;
  }
}

/*
 *  函数名：  select_clear()
 *  用途：    清除select中的所有项
 *  调用方法：select_clear('cbxCity')
 */
function select_clear(select)
{
  for (var i = (select.length - 1) ; i >= 0; i--)
  {
    select.remove(i);
  }
}

/*
 *  函数名：  select_add()
 *  用途：    添加新项到select
 *  调用方法：select_add('cbxCity','beijing','1')
 */
function select_add(select, itemname, itemvalue)
{
  var opt=document.createElement('option');
  opt.text=itemname;
  opt.value=itemvalue;
  
  try{
    select.add(opt);
  }catch(ex){
    select.add(opt, null);
  }
}

/*
 *  函数名：  select_move()
 *  用途：    在两个select中移动option
 *  调用方法：select_move(select1, select2, true)
 */
function select_move(select1, select2, isAll)
{
  for (var i = (select1.length - 1) ; i >= 0; i--)
  {
    if (isAll || select1.options[i].selected)
    {
      select_add(select2, select1.options[i].text, select1.options[i].value);
      select1.remove(i);
    }
  }
}

	
function layer_Show(layer, show)
{
  //var obj=document.getElementById(layId);
  if (show)
  {
    layer.style.left=(document.body.clientWidth-layer.clientWidth) / 2;
    layer.style.top=(document.body.clientHeight-layer.clientHeight) / 2;
    layer.style.visibility = "visible";
  }
  else
    layer.style.visibility = "hidden";
}
