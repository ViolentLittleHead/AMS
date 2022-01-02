import com.landian.util.VCodeUtil;
import org.junit.Test;

public class testCode {

    @Test
    public void testVode(){
        String VCode = VCodeUtil.verifyCode(5);
        System.out.println(VCode);
    }

}
