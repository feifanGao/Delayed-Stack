import junit.framework.TestCase;
import org.junit.Test;

public class MyStackTest extends TestCase {
    
    /////////////////////////////////////////////////////////////////////////////
    //Push & Size
    /////////////////////////////////////////////////////////////////////////////
 
    @Test
    public void testSizeAndPushBasic() {
        DelayedStack<String> s = new MyStack<String>(3);
        assertEquals(3,s.getMaximumDelay());
        assertEquals(3,s.getDelay());

        s.push("alfa");
        assertEquals(1,s.size());
        s.push("beta");
        assertEquals(2,s.size());

        assertEquals(1,s.getDelay());

        assertNull(s.pop());
        s.push("cat");

        assertEquals("cat",s.pop());
        assertEquals("beta",s.pop());
        assertEquals("alfa",s.pop());
    }

    @Test
    public void testPushExpandSize() {
        DelayedStack<String> s = new MyStack<String>(3);

        s.push("alfa");
        s.push("beta");
        s.push("cat");
        s.push("alfa");
        s.push("beta");
        s.push("cat");
        s.push("alfa");
        s.push("beta");
        s.push("cat");
        s.push("alfa");
        s.push("beta");
        s.push("cat");

        assertEquals("cat",s.pop());
        assertEquals("beta",s.pop());
        assertEquals("alfa",s.pop());
        assertEquals("cat",s.pop());
        assertEquals("beta",s.pop());
        assertEquals("alfa",s.pop());
        assertEquals("cat",s.pop());
        assertEquals("beta",s.pop());
        assertEquals("alfa",s.pop());
        assertEquals("cat",s.pop());
        assertEquals("beta",s.pop());
        assertEquals("alfa",s.pop());
    }
    
    @Test
    public void testPushDelayReset() {
        DelayedStack<String> s = new MyStack<String>(3);
        assertEquals(3,s.getMaximumDelay());
        assertEquals(3,s.getDelay());

        s.push("alfa");
        s.push("beta");
        s.push("cat");

        assertEquals(0,s.getDelay());

        assertEquals("cat",s.pop());
        assertEquals(0,s.getDelay());

        s.push("delta");
        assertEquals(2,s.getDelay());
    }
    /////////////////////////////////////////////////////////////////////////////
    //Pop
    /////////////////////////////////////////////////////////////////////////////

    @Test
    public void testPopUnderflow() {
        DelayedStack<String> s = new MyStack<String>(3);
        try{
            s.pop();
        }catch(IllegalStateException e){
            return;
        }
        fail("Exception was not thrown");
    }

    @Test
    public void testPopLocked() {
        DelayedStack<String> s = new MyStack<String>(3);
        s.push("alfa");
        assertNull(s.pop());

        s.push("beta");
        assertNull(s.pop());
        
        s.push("cat");
        assertEquals("cat",s.pop());
        assertEquals("beta",s.pop());
        assertEquals("alfa",s.pop());
    }

    @Test
    public void testPopNullElem() {
        DelayedStack<String> s = new MyStack<String>(3);
        s.push(null);
        s.push("beta");
        s.push("cat");
        s.push(null);
        s.push("beta");

        assertEquals("beta",s.pop());
        assertNull(s.pop());
        assertEquals("cat",s.pop());
    }

    @Test
    public void testPopNullElem2() {
        DelayedStack<String> s = new MyStack<String>(3);
        s.push(null);
        assertNull(s.pop());
        assertEquals(1,s.size());

        s.push(null);
        assertNull(s.pop());
        assertEquals(2,s.size());

        s.push(null);
        assertEquals(3,s.size());

        assertNull(s.pop());
        assertEquals(2,s.size());
    }

    /////////////////////////////////////////////////////////////////////////////
    //Peek
    /////////////////////////////////////////////////////////////////////////////

    @Test
    public void testPeekBasic() {
        DelayedStack<String> s = new MyStack<String>(5);
        s.push("alfa");
        assertEquals("alfa",s.peek());

        s.push("beta");
        assertEquals("beta",s.peek());

        s.push("cat");
        assertEquals("cat",s.peek());
    }

    @Test
    public void testPeekException() {
        DelayedStack<String> s = new MyStack<String>(5);
        try{
            s.peek();
        }catch(IllegalStateException e){
            return;
        }
        fail("Exception was not thrown");
    }

    @Test
    public void testPeekException2() {
        DelayedStack<String> s = new MyStack<String>(5);
        s.push("alfa");
        s.push("beta");
        s.push("cat");
        s.push("alfa");
        s.push("beta");
        s.push("cat");

        s.pop();
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        s.pop();

        try{
            s.peek();
        }catch(IllegalStateException e){
            return;
        }
        fail("Exception was not thrown");
    }

    /////////////////////////////////////////////////////////////////////////////
    //getDelay
    /////////////////////////////////////////////////////////////////////////////

    @Test
    public void testgetDelay() {
        DelayedStack<String> s = new MyStack<String>(5);
        assertEquals(5,s.getDelay());
        s.push("alfa");
        assertEquals(4,s.getDelay());
        s.push("beta");

        s.pop();
        assertEquals(3,s.getDelay());

        assertEquals(3,s.getDelay());
        s.push("cat");
        assertEquals(2,s.getDelay());
        s.push("alfa");
        assertEquals(1,s.getDelay());
        s.push("beta");
        assertEquals(0,s.getDelay());
        s.push("cat");
        assertEquals(0,s.getDelay());

        s.pop();
        assertEquals(0,s.getDelay());

        s.push("alfa");
        assertEquals(4,s.getDelay());
        s.push("beta");
        assertEquals(3,s.getDelay());
    }

    /////////////////////////////////////////////////////////////////////////////
    /////setMaximumDelay & getMaximumDelay
    /////////////////////////////////////////////////////////////////////////////

    @Test
    public void testSetandGetMaximumDelay() {
        DelayedStack<String> s = new MyStack<String>(3);
        assertEquals(3,s.getMaximumDelay());
        assertEquals(3,s.getDelay());

        s.push("alfa");
        s.push("beta");
        s.setMaximumDelay(5);
        assertEquals(5,s.getMaximumDelay());
        assertEquals(1,s.getDelay());
        
        s.push("cat");
        assertEquals(5,s.getMaximumDelay());
        assertEquals(0,s.getDelay());

        assertEquals("cat",s.pop());
        assertEquals(5,s.getMaximumDelay());
        assertEquals(0,s.getDelay());

        s.push("dogge");
        assertEquals(5,s.getMaximumDelay());
        assertEquals(4,s.getDelay());
    }

    @Test
    public void testSetandGetMaximumDelay2() {
        DelayedStack<String> s = new MyStack<String>(3);
        assertEquals(3,s.getMaximumDelay());
        assertEquals(3,s.getDelay());

        s.push("alfa");
        s.push("beta");
        s.setMaximumDelay(5);

        assertEquals(5,s.getMaximumDelay());
        assertEquals(1,s.getDelay());
        
        s.push("cat");
        assertEquals(5,s.getMaximumDelay());
        assertEquals(0,s.getDelay());

        assertEquals("cat",s.pop());
        assertEquals(5,s.getMaximumDelay());
        assertEquals(0,s.getDelay());

        s.setMaximumDelay(10);

        s.push("dogge");
        assertEquals(10,s.getMaximumDelay());
        assertEquals(9,s.getDelay());
    }

    @Test
    public void testSetandGetMaximumDelay3() {
        DelayedStack<String> s = new MyStack<String>(3);
        assertEquals(3,s.getMaximumDelay());
        assertEquals(3,s.getDelay());

        s.push("alfa");
        s.push("beta");
        s.setMaximumDelay(5);

        assertEquals(5,s.getMaximumDelay());
        assertEquals(1,s.getDelay());
        
        s.push("cat");
        assertEquals(5,s.getMaximumDelay());
        assertEquals(0,s.getDelay());

        s.setMaximumDelay(15);

        assertEquals("cat",s.pop());
        assertEquals(15,s.getMaximumDelay());
        assertEquals(0,s.getDelay());

        s.push("dogge");
        assertEquals(15,s.getMaximumDelay());
        assertEquals(14,s.getDelay());
    }

    /////////////////////////////////////////////////////////////////////////////
    //clear
    /////////////////////////////////////////////////////////////////////////////

    @Test
    public void testClearBasic() {
        DelayedStack<String> s = new MyStack<String>(3);
        assertTrue(s.clear());
        s.push("alfa");
        assertFalse(s.clear());
        s.push("beta");
        assertFalse(s.clear());
        s.push("cat");
        assertEquals(3,s.size());
        assertTrue(s.clear());
        assertEquals(0,s.size());
    }

    /////////////////////////////////////////////////////////////////////////////
    //contains
    /////////////////////////////////////////////////////////////////////////////
    
    @Test
    public void testContainsBasic(){
        DelayedStack<String> s = new MyStack<String>(3);
        s.push("alfa");
        s.push("beta");
        s.push("cat");
        assertTrue(s.contains("alfa"));
        assertTrue(s.contains("beta"));
        assertFalse(s.contains("dogge"));
        assertFalse(s.contains(null));
    }

    @Test
    public void testContainsNull(){
        DelayedStack<String> s = new MyStack<String>(3);
        assertFalse(s.contains("alfa"));
        assertFalse(s.contains(null));

        s.push("alfa");
        assertTrue(s.contains("alfa"));

        s.push(null);
        assertTrue(s.contains(null));
        s.push(null);

        s.pop();
        assertTrue(s.contains(null));
        s.pop();
        assertFalse(s.contains(null));
    }
}

