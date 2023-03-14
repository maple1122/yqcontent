package content;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/22 15:30
 */
public class DeleteRecycleTest {

    @Test
    public void testDeleteRecycle() throws InterruptedException {
        DeleteRecycle.deleteRecycle();
    }
}