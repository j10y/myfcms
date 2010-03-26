sliders.names = new Array();

function sliders()
{
	this.id = sliders.names.length;
	sliders.names[this.id] = this;
	this.target	 = document.getElementById(arguments[0]);	//��һ��������������div��id
	this.direction = arguments[1];//�ڶ���������div�����ķ���
	this.height = arguments[2];//������������div�ĸ߶�
	this.width = arguments[3];//���ĸ�������div�Ŀ��
	this.step = arguments[4];//�����������ϣ�������ֽ�Ϊ�������
	this.timer = 10 * arguments[5];//������������ÿ�������ļ��ʱ�䣬10msΪһ����λ
	this.startopa = arguments[6];//���߸�������div��ʼ��͸����
	this.sparent = this.target.parentNode;//��ȡ����div�ĸ�����
	this.intervalid = null;//ѭ����ʱ��id
	this.i = 0;//ѭ���ļ�����
	this.status = 0;//sliders���״̬��0-����չ����1-������չ��
	this.target.style.display = "none";//�Ƚ�div��ȥ
	return this;
}

sliders.prototype.initialize = function()
{
	this.sparent.style.overflow = "hidden";//���ø�����overflow
	this.target.style.width = Number(this.width) + 'px';//����Ŀ��div�Ŀ��
	this.target.style.height = Number(this.height) + 'px';//����Ŀ��div�ĸ߶�
	this.target.style.position = "";//����Ŀ��div�Ķ�λ��ʽ
	this.target.style.display = "";//����Ŀ��div����ʾ��ʽ
	this.target.style.filter = 'Alpha(opacity=' + Number(this.startopa) + ')';//����Ŀ��div��͸����Ϊ��ʼ͸����
	this.target.style.overflow = "hidden";//����overflow
	switch(this.direction)//���ݵ��������趨div��margin
	{
		case 1://left to right
			this.target.style.marginLeft = "-" + this.width + "px";
			break;
		case 2://top to bottom
			this.target.style.marginTop = "-" + this.height + "px";
			break;
		case 3://right to left
			this.target.style.marginRight = "-" + this.width + "px";
			break;
	}
}

sliders.prototype.shows = function()
{
	
	if (this.status==0)//���״̬�Ƿ��Ѿ�չ��
	{
		this.initialize();//����div���丸�����ĳ�ʼ��
		this.intervalid = window.setInterval("sliders.names["+this.id+"].cycle()",this.timer);//���ö���ѭ��
	}
}

sliders.prototype.hides = function()
{
	if (this.status==1)//���״̬�Ƿ��Ѿ�չ��
	{
		this.intervalid = window.setInterval("sliders.names["+this.id+"].decycle()",this.timer);//���ö���ѭ��
	}
}

sliders.prototype.cycle = function()	//����ѭ������
{
	var opa = this.target.style.filter.split("=")[1].split(")")[0]//��ȡĿ��div��͸������ֵ	
	var opastep = Math.round(((100 - Number(opa)) / this.step)+2.5);//����ÿ�����ӵ�͸����
	var nopa = Number(opa) + Number(opastep);//��ǰ͸����
	if (nopa>100){this.target.style.filter = 'Alpha(opacity=100)';}else{this.target.style.filter = 'Alpha(opacity=' + String(nopa) + ')';}//��div͸���ȸ�ֵ
	switch(this.direction)//���ݵ������������趨div�Ķ���
	{
		case 1:		//left to right
			var opx = this.target.style.marginLeft.split("px")[0];
			var pxstep = Math.round((this.width / this.step)+0.5);
			var npx = Number(opx) + Number(pxstep);
			if (npx>0){this.target.style.marginLeft = '0px';}else{this.target.style.marginLeft = String(npx) + 'px';}
			break;
		case 2:		//top to bottom
			var opx = this.target.style.marginTop.split("px")[0];
			var pxstep = Math.round((this.height / this.step)+0.5);
			var npx = Number(opx) + Number(pxstep);
			if (npx>0){this.target.style.marginTop = '0px';}else{this.target.style.marginTop = String(npx) + 'px';}
			break;
		case 3:		//right to left
			var opx = this.target.style.marginRight.split("px")[0];
			var pxstep = Math.round((this.width / this.step)+0.5);
			var npx = Number(opx) + Number(pxstep);
			if (npx>0){this.target.style.marginRight = '0px';}else{this.target.style.marginRight = String(npx) + 'px';}
			break;
	}
	this.i++	//������+1
	if (this.i>(this.step-1)){window.clearInterval(this.intervalid);this.i=0;this.status=1;this.target.style.display = "block"}	//ѭ����ϣ����ѭ����ʱ
}

sliders.prototype.decycle = function()	//����ѭ������
{
	var opa = this.target.style.filter.split("=")[1].split(")")[0]//��ȡĿ��div��͸������ֵ	
	var opastep = Math.round(((100 - Number(opa)) / this.step)+2.5)*2;//����ÿ�����ӵ�͸����
	var nopa = Number(opa) - Number(opastep);//��ǰ͸����
	if (nopa<this.startopa){this.target.style.filter = 'Alpha(opacity=' + this.startopa + ')';}else{this.target.style.filter = 'Alpha(opacity=' + String(nopa) + ')';}//��div͸���ȸ�ֵ
	
	switch(this.direction)//���ݵ������������趨div�Ķ���
	{
		case 1:		//left to right
			var opx = this.target.style.marginLeft.split("px")[0];
			var pxstep = Math.round((this.width / Math.round(this.step*0.5))+0.5);
			var npx = Number(opx) - Number(pxstep);
			if (Math.abs(npx)>this.width+2){this.target.style.marginLeft = '-' + this.width + 'px';}else{this.target.style.marginLeft = String(npx) + 'px';}
			break;
		case 2:		//top to bottom
			var opx = this.target.style.marginTop.split("px")[0];
			var pxstep = Math.round((this.height / Math.round(this.step*0.5))+0.5);
			var npx = Number(opx) - Number(pxstep);
			if (Math.abs(npx)>this.height+2){this.target.style.marginTop = '-' + this.height + 'px';}else{this.target.style.marginTop = String(npx) + 'px';}
			break;
		case 3:		//right to left
			var opx = this.target.style.marginRight.split("px")[0];
			var pxstep = Math.round((this.width / Math.round(this.step*0.5))+0.5);
			var npx = Number(opx) - Number(pxstep);
			if (Math.abs(npx)>this.width+2){this.target.style.marginRight = '-' + this.width + 'px';}else{this.target.style.marginRight = String(npx) + 'px';}
			break;
	}
	this.i++	//������+1
	if (this.i>(Math.round(this.step*0.5)-1)){window.clearInterval(this.intervalid);this.i=0;this.status=0;this.target.style.display = "none";}	//ѭ����ϣ����ѭ����ʱ
}

//�������ʹ����������˵����
//����Ĵ�����Է�װ��Ϊһ��������js�ļ���Ȼ�������ҳ�浱��
//Ȼ��ʹ������Ĵ������slider��ʼ����һ��ҳ�����ʹ�ö��slider������Ĵ��������ҳ��ײ�</body>֮ǰ��������ܻᱨ��
//var topslider = new slider('topcontainer',2,80,734,20,2,20);
//var leftslider = new slider('leftcontainer',1,398,120,20,1,20);
//�����ֱ�������������ڴ��붥�˿���
//����slider��������ʹ�����ַ�ʽ
//һ����ʹ���������������ֵ��ã�����topslider.show(),topslider.hide()
//��һ�ֿ���ʹ��slider.names[0].show(),slider.names[0].hide()���±�ȡ����slider��ʼ����˳��
//�����ֱ�������������ڴ��붥�˿���
//����slider��������ʹ�����ַ�ʽ
//һ����ʹ���������������ֵ��ã�����topslider.show(),topslider.hide()
//��һ�ֿ���ʹ��slider.names[0].show(),slider.names[0].hide()���±�ȡ����slider��ʼ����˳��