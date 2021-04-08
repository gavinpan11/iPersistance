package com.gavin11.ipersistance.test.io;

import com.gavin11.ipersistance.io.Resources;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 对ipersistance中的Resources类进行测试
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.test.io
 * @description ResourcesTest <br>
 * @date 2021/4/2 7:23 <br>
 */
public class ResourcesTest {

    private static final int BUF_SIZE = 1024;

    @Test
    public void test0() throws IOException {
        try (
                InputStream is = Resources.getResourceAsStream("db-config.xml");
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr)
                ) {
            char[] buf = new char[BUF_SIZE];
            StringBuilder builder = new StringBuilder();
            while (br.read(buf) != -1) {
                for (char c : buf) {
                    if (c != '\0') {
                        builder.append(c);
                    }
                }
                buf = new char[BUF_SIZE];
            }
            System.out.println(builder.toString());
        }
    }
}
