package engineer.echo.bigandroid;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void nine() {
        String str = "";
        for (int i = 1, j = 1; j< 10; i++) {
            str += "\t" + i+ "*" + j + "=" + i * j;
            if (i == j) {
                i = 0;
                j++;
                str += "\n";
            }
        }
        System.out.print(str);
    }
}