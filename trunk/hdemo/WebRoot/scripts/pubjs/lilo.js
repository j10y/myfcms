/*
 *  ��������  String.Format()
 *  ��;��    �ַ�����ʽ��
 *  ���÷�����alert(String.Format("���Ƕ���ϲ��ʹ����{0}���ṩ��{1}������������js��ȴû��{1}��ͨ���˷��������ǿ���Ϊ����չһ����Ч�����㲻��ϣ�����ϲ����{2}{3}", "C#", "string.Format()", "лл��ң�", "\n\nby gaoming \nmail:Lonsan21@163.com" ));
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
 *  ��������  checkbox_selectAll()
 *  ��;��    ��������ѡ��CheckBox
 *  ���÷�����checkbox_selectAll('id')
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
 *  ��������  checkbox_unselect()
 *  ��;��    �������Ʒ�ѡCheckBox
 *  ���÷�����checkbox_unselect('id')
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
 *  ��������  select_clear()
 *  ��;��    ���select�е�������
 *  ���÷�����select_clear('cbxCity')
 */
function select_clear(select)
{
  for (var i = (select.length - 1) ; i >= 0; i--)
  {
    select.remove(i);
  }
}

/*
 *  ��������  select_add()
 *  ��;��    ������select
 *  ���÷�����select_add('cbxCity','beijing','1')
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
 *  ��������  select_move()
 *  ��;��    ������select���ƶ�option
 *  ���÷�����select_move(select1, select2, true)
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
