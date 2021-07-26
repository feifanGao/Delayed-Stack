Delayed Stack
A stack is a data structure where the element removed is always the most recently added out of the remaining elements. See: Stack (abstract data type) - Wikipedia. The two main operations are push(E) which adds an element and pop() which removes an element.

A DelayedStack works like a normal stack except has a restriction ('delay condition') that prohibits elements from being removed until a certain number of push operations have occurred. Once the required number of push operations occur, any number of elements may be removed, however the moment another element is added, the delay condition comes back into force. 

Your task is to create a class called MyStack that implements the generic DelayedStack interface according to the specification written in the docstrings for each method. Your class should be able to be instantiated with

DelayedStack<...> s = new MyStack<...>(9);
where the ... can be replaced by any object, and the int parameter for the constructor represents the max delay value (ie. number of push operations that must occur before pop operations can start to occur).

If the max delay value is changed, the change does not take effect until the next time the delay is reset to the maximum (ie. when push occurs after a sequence of pop operations).
