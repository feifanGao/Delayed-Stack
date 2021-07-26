public class MyStack<E> implements DelayedStack<E>{
    
	private E[] stack = (E[]) new Object[10];
	private int MaximumDelay;
	private int delay;
	private int size;
	private int pushTimes;
	private boolean flag = false;

	public MyStack(int Max){
		if (Max<1) Max=0;
		this.MaximumDelay = Max;
		delay = Max;
		size = 0;
		pushTimes = 0;
	}

	@Override
	public int size(){
		return size;
	}
	
	@Override
	public void push(E element){
		if(flag){
			flag=false;//lock
			pushTimes = 0;
			if(MaximumDelay<1) delay = 0;
			else delay = MaximumDelay;
        }
		if(size==stack.length){
			E[] newStack = (E[]) new Object[size*2];
			System.arraycopy(stack,0,newStack,0,size);
			stack = newStack;
		}
		stack[size] = element;
		size++;
		pushTimes++;
		delay--;
	}
	
	@Override
	public E pop() throws IllegalStateException{
		if(size()==0)
			throw new IllegalStateException();
		if(getDelay()==0 || flag){
			E e = stack[size-1];
			stack[size-1] = null;
			size--;
			//System.out.println("pop return value is "+e);
			flag = true;
			return e;
		}else{
			//System.out.println("return value is null, because so far only "+pushTimes+" elements have been pushed");
			return null;
		}
		
	}
	
	@Override
	public E peek() throws IllegalStateException{
		if(size()==0) 
			throw new IllegalStateException();
		E e = stack[size-1];
		return e;
	}
	
	@Override
	public int getDelay(){
		if(delay<0) delay = 0;
		return delay;
	}

	@Override
	public void setMaximumDelay(int d){
		MaximumDelay = d;
	}
	
	@Override
	public int getMaximumDelay(){
        return MaximumDelay;
	}
	
	@Override
	public boolean clear(){
		if(size == 0) return true;
		if(getDelay() == 0){
			for(int i=0;i<size;i++)
				stack[i] = null;
			size = 0;
			pushTimes = 0;
			flag = true;
			return true;
		}else{
			//System.out.println("clear failed, because so far only "+pushTimes+" elements have been pushed");
			return false;
		}
	}
	
	@Override
	public boolean contains(E elem){
		if(size==0) return false;
		for(int i=0;i<size();i++){
			if(elem==null && stack[i] == null) return true;
			else if(elem==null || stack[i] == null) continue;
			else if(elem.equals(stack[i])) return true;
		}
		return false;
	}

	// public void printall(){
	// 	System.out.print(size());
	// 	System.out.print(" (");
	// 	for(int i=0;i<size;i++){
	// 		System.out.print(stack[i]+" ");
	// 	}
	// 	System.out.println(")");
	// }

	public static void main(String[] args) {
		// DelayedStack<String> s = new MyStack<String>(0); //delay condition of 0 means that there is never a restriction. Same with negative values, or 1.
		// s.push(null);
		// s.pop(); //returns “hello”
		// s.setMaximumDelay(2);
		// s.getMaximumDelay(); //return value is 2
		// //s.pop(); //IllegalStateException is thrown, the stack is empty
		// s.push("X");
		// s.push("a");
		// s.push("b");
		// s.push("c");
		// s.printall();
		// s.pop(); //return value is “c”
		// s.pop(); //return value is “b”
		// s.clear(); 
		// s.printall();
		// s.setMaximumDelay(4);
		// s.getMaximumDelay(); //return value is 2
		// s.getDelay(); //return value is 0.
		// //s.pop(); //return value is “a” – delay is not set until the next push
		// s.push("Y"); s.push("Z");
		// s.setMaximumDelay(-1);
		// s.getDelay(); //return value is 2 – delay is not set yet
		// s.push("An");
		// s.printall();
		// s.clear();
		// s.printall();
		// s.getDelay(); //return value is 1
		// s.pop(); //return value is null
		// s.printall();
		// s.push("AX");
		// s.printall();
		// s.getDelay(); //return value is 0
		// s.pop(); //return value is “AX” 
		// s.printall();
		// s.clear();
		// s.printall();

    //    //例子1
    //    DelayedStack<String> s = new MyStack<String>(4); //delay condition of 4
    //    s.push("first element");
    //    s.push("something else");
    //    s.pop();//return value is null, because so far only 2 elements have been pushed

    //    s.push("third");
    //    s.push("fourth");//return value is “fourth”
    //    s.pop();

    //    s.push("another one");
    //    s.pop();//return value is null again, because the delay condition has been reset
    //    s.push("2");
    //    s.push("3");
    //    s.push("4");
    //    s.pop(); // return value is “4”
    //    s.pop(); // return value is “3”
    //    s.pop();
    //    s.pop();
    //    s.pop();
    //    s.pop();
	//    s.push("mmmm");
	//    s.pop();

    //    // 最大延迟测试
    //    DelayedStack<String> s0 = new MyStack<String>(3); //delay condition of 0 means that there is never a restriction. Same with negative values, or 1.
    //    s0.setMaximumDelay(2);
    //    s0.getMaximumDelay(); //return value is 2
    //    s0.push("hello");
    //    s0.push("hello");
    //    s0.push("hello");
    //    s0.pop(); //returns “hello”
	//    s0.getMaximumDelay();
    //    s0.push("hello");
    //    s0.getMaximumDelay(); //return value is 2

    //    // getDelay测试
    //     DelayedStack<String> s1 = new MyStack<String>(3);
    //     s1.getDelay();

    //     s1.push("1");
    //     s1.getDelay();

    //     s1.push("2");
    //     s1.getDelay();

    //     s1.push("3");
    //     s1.getDelay();

    //     s1.pop();
    //     s1.getDelay();

    //     s1.push("2");
    //     s1.getDelay();


    }
}

