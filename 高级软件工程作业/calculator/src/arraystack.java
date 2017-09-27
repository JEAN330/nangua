
public class arraystack<T> implements stack<T> {
	private Object[] objs = new Object[30];  
    private int size = 0;
	@Override
	public T pop() {
		if (size == 0) {  
            return null;  
        }  
        return (T) objs[--size];
	}
	
	@Override
	public void push(T data) {
		if (size >= objs.length) {   //ÅÐ¶ÏÕ»ÊÇ·ñÒÑÂú
            resize();  
        }  
        objs[size++] = data;   
	}
	private void resize() {   //À©ÈÝ
        Object[] temp = new Object[objs.length * 3 / 2 + 1];  
        for (int i = 0; i < size; i++) {  
            temp[i] = objs[i];  
            objs[i] = null;  
        }  
        objs = temp;  
    }  
	
	@Override
	public T peek() {
		if(size==0)
			return null;
		else
			return (T) objs[size-1];
	}
	@Override
	public boolean isEmpty() {
		return size==0;
	}
	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {  
            objs[size] = null;  
        }  
        size = 0; 
	}
	@Override
	public int length() {
		return size;
	}    
  
}
