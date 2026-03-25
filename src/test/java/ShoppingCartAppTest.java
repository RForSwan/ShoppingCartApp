import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartAppTest {

    @DisplayName("Calculator test")
    @Test
    public void calculatorTest() {
        String test = "1\n1\n1,25\n";
        InputStream in = new ByteArrayInputStream(test.getBytes());
        System.setIn(in);
        ShoppingCartApp sca = new ShoppingCartApp("en", "US");
        sca.calculateCost();
    }

}