import java.util.*;
public class primwork {

	 public static int[] fraction() {    //定义一个真分数
		 int x=(int)(Math.random()*10)+1;  //随机生成10以内的不为0的分子分母
		 int y=(int)(Math.random()*10)+1;  
		 if(x<=y){
			 return gcd(x,y);	
		 }
		 else{
			 return gcd(y,x);	
		 }
	 }
	 public static int[] gcd(int x,int y){   //辗转相除得到最简分数
		 int temp,x1=x,y1=y;
		 if(x>=y)         //假分数化简
		 {
			 while(x!=y){
				 temp=y;
				 if((x-y)<=temp){    //比较减数与被减数的大小，将大的值赋予x,小的值赋予y
					 y=x-y;
					 x=temp;
				 }
				 else{
					 x=x-y;
					 y=temp;
				 }
			 }
		 }
		 else             //真分数化简
		 {
			 while(x!=y){
				 temp=x;
				 if((y-x)<=temp){    
					 x=y-x;
					 y=temp;
				 }
				 else{
					 y=y-x;
					 x=temp;
				 }
			 } 
		 }
		int[] array={x1/x,y1/y}; 
		return array;
	 }
	 public static int[] cal(int a,int b,int c,int d,int op){ //真分数之间的四则运算规则
		 int[] arr=new int[2];
		 switch(op)
		 {
		 	case 0:arr[0]=a*d+b*c;arr[1]=b*d; break;
		 	case 1:arr[0]=a*d-b*c;arr[1]=b*d;break;
		 	case 2:arr[0]=a*c;arr[1]=b*d;break;
		 	case 3:arr[0]=a*d;arr[1]=b*c;break;
		 }
		 return arr; 
	 }
	 public static int[] display(int n,int m,int[] a,int[][] b,int[] op){
		 int i,j,k,l;
		 int[] order=new int[20];  //为整数和真分数排序
		 for(i=0,j=0,k=0,l=0;k<m+n;)  //随机排列整数和真分数
		 {
			 int x=(int)(Math.random()*2);
			 if(x==0&&i<n)
			 {
				 order[k]=0;
				 System.out.print(a[i]);
				 if(k<m+n-1)
				 {
					 switch(op[l])
					 {
					 	case 0:System.out.print("+");break;
					 	case 1:System.out.print("-");break;
					 	case 2:System.out.print("*");break;
					 	case 3:System.out.print("÷");break;
					 }
					 l++;
				 }
				 k++;
				 i++;
			 }
			 if(x==1&&j<m){
				 order[k]=1;
				 System.out.print(b[j][0]+"/"+b[j][1]);
			 	 if(k!=m+n-1)
				 {
					 switch(op[l])
					 {
					 	case 0:System.out.print("+");break;
					 	case 1:System.out.print("-");break;
					 	case 2:System.out.print("*");break;
					 	case 3:System.out.print("÷");break;
					 }
					 l++;
				 }
			 	 j++;
			 	 k++;
			 }
		 }
				System.out.print("=");
				return order;
	 }
	 public static int[] res(int[] a,int b[][],int[] op,int[] order,int n,int m){ //将中缀表达式转换为后缀表达式
		stack<Integer> stack = new arraystack<Integer>();
		int i=0,j=0,o,k,l;
		int[] arr=new int[2];
		int[][] c=new int[30][2];
		stack.push(op[0]);
		if(order[0]==0)  //将整数以整数/1的形式存入数组，便于之后的计算
		{
			c[0][0]=a[i];
			c[0][1]=1;
			i++;
		}
		else{
			c[0][0]=b[0][0];
			c[0][1]=b[0][1];
			j++;
		}
		for(k=1,l=1,o=1;k<n+m;k++){  //将中缀表达式按照逆波兰表达的顺序存入二维数组c
			if(order[k]==0){
				c[o][0]=a[i++];
				c[o][1]=1;
				o++;
			}
			else{
				c[o][0]=b[j][0];
				c[o++][1]=b[j++][1];
			}
			if(l<(m+n-1)){
				while(!stack.isEmpty()&&precedence(stack.peek(),op[l])>=0){
					switch(stack.pop()){
						case 0:c[o][0]=0;c[o++][1]=0;break;
						case 1:c[o][0]=0;c[o++][1]=1;break;
						case 2:c[o][0]=0;c[o++][1]=2;break;
						case 3:c[o][0]=0;c[o++][1]=3;break;
					}
				}
				stack.push(op[l++]);
			}	
			
		}
		if(!stack.isEmpty()){
			int length=stack.length();
			for(i=0;i<length;i++){
				switch(stack.pop()){
				case 0:c[o][0]=0;c[o++][1]=0;break;
				case 1:c[o][0]=0;c[o++][1]=1;break;
				case 2:c[o][0]=0;c[o++][1]=2;break;
				case 3:c[o][0]=0;c[o++][1]=3;break;
				}
			}
		}
		arr=calculate(c,n,m);
		return arr;
	 }
	 public static int[] calculate(int[][] c,int n,int m){  //c为存储逆波兰表达式的二维数组，根据逆波兰表达式计算结果
		 int i,w,x,y,z;
		 int[] arr=new int[2];
		 stack<Integer> stack = new arraystack<Integer>();
		 for(i=0;i<2*m+2*n-1;i++){
			 if(c[i][0]==0){
				z=stack.pop();y=stack.pop();x=stack.pop();w=stack.pop();
				switch(c[i][1]){
				case 0:arr=cal(w,x,y,z,0);stack.push(arr[0]);stack.push(arr[1]);break;
				case 1:arr=cal(w,x,y,z,1);stack.push(arr[0]);stack.push(arr[1]);break;
				case 2:arr=cal(w,x,y,z,2);stack.push(arr[0]);stack.push(arr[1]);break;
				case 3:arr=cal(w,x,y,z,3);stack.push(arr[0]);stack.push(arr[1]);break;
				}
			 }
			 else{
				 stack.push(c[i][0]);
				 stack.push(c[i][1]);
			 }	 
		 }
		 arr[1]=stack.pop();
		 arr[0]=stack.pop();
		 return arr;
		 
	 }
	 public static int precedence (int op1,int op2){  //判断运算符优先级
		 if(op1==0||op1==1) //0、1 分别代表"+"、"-"
		 {
			 if(op2==2||op2==3)  //2、3分别代表"*"、"÷"
				 return -1;
			 else
				 return 0;
		 }
		 else
		 {
			 if(op2==0||op2==1)
				 return 1;
			 else
				 return 0;
		 }
	 }
	 public static void main(String[] args)
	 {
		 Scanner input=new Scanner(System.in);
		 int i,k,p,flag=0;
		 int n=(int)(Math.random()*3)+2;   //整数个数至少为2个最多为5个
		 int m=(int)(Math.random()*6);  //真分数个数最多为5个
		 int[] arr=new int[2];
		 int[] a=new int[5];  //最多存放5个整数
		 int[][] b=new int[5][2]; //最多存放5个真分数
		 int[] op=new int[10]; //最多存放10个运算符
		 p=Integer.parseInt(args[0]); //取参数值
		 for(k=0;k<p;k++){
			 for(i=0;i<n;i++)  //随机生成n个100以内的整数
				 a[i]=(int)(Math.random()*100)+1;
			 for(i=0;i<m;i++){  //随机生成m个最简真分数
				 int[] c=fraction();
				 b[i][0]=c[0];
				 b[i][1]=c[1];	 
			 }
			 for(i=0;i<m+n-1;i++)  //随机生成m+n-1个运算符，其中0代表"+"，1代表"-"，2代表"*"，3代表"÷"
				 op[i]=(int)(Math.random()*4);
			 arr=res(a,b,op, display(n,m,a,b,op),n,m);
			 String s = input.next();
			 //String s=0+""; //测试时默认输入值为0
			 if(arr[0]>0){
				 arr=gcd(arr[0],arr[1]);
				 if(arr[1]==1){
					 if(s.equals(String.valueOf(arr[0]))){
						 System.out.println("正确");
						 flag++;
					}
					 else
						 System.out.println("错误，正确答案为"+arr[0]);
				 }
				 else{
					 if(s.equals(String.valueOf(arr[0]+"/"+arr[1]))){
						 System.out.println("正确");
						 flag++;
					 }
					 else
						 System.out.println("错误，正确答案为"+arr[0]+"/"+arr[1]);
				 }	
			 }
			 else{
				arr=gcd(Math.abs(arr[0]),arr[1]);
				if(arr[1]==1){
					if(s.equals(String.valueOf("-"+arr[0]))){
						 System.out.println("正确");
						 flag++;
					}
					 else
						 System.out.println("错误，正确答案为-"+arr[0]);
				}
				else{
					if(s.equals(String.valueOf("-"+arr[0]+"/"+arr[1]))){
						 System.out.println("正确");
						 flag++;
					}
					 else
						 System.out.println("错误，正确答案为-"+arr[0]+"/"+arr[1]);
				}	 
			}
		 }
		 System.out.println("本次共"+p+"题,您答对了"+flag+"题,正确率为"+flag*100/p+"%");
		// if(k==p)  //测试随机生成1000个算式时间
		//	 return 1;
		 //else
		//	 return 0;  
		 
	 }
	 
}
