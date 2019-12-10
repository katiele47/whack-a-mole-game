package lab05.whackamole;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class WhackAMoleModelTest {

	private WhackAMoleModel w1;
    
    @Before
    public void setUp() throws Exception { 
        w1 = new WhackAMoleModel();
    }

	@Test
	public void testConstructor() {
		assertEquals("wrong rows", 4, w1.getRows());
		assertEquals("wrong cols", 4, w1.getCols());
		assertEquals("wrong score", 0, w1.getScore());
	}
	
	@Test
	public void testWhack() {
		w1.whack(2,3);
		if (w1.getMoleRow() == 2 && w1.getMoleCol() == 3) {
			assertEquals("Scores increased", 10, w1.getScore());
		} else {
			assertEquals("Score decreased", -5, w1.getScore());
		}	
	}

}
