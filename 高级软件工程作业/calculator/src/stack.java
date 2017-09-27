
public interface stack<T> {
	 public T pop();   //元素出栈  
     
	 public void push(T element);  //元素入栈
	     
	 public T peek();   //获取栈顶元素     
	       
	 public boolean isEmpty();  //判断栈是否为空  
	      
	 public void clear();   //清空栈
	 
	 public int length(); //获取栈的长度
	 
}
