package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item9_Prefer_try_with_resources_to_try_finally;

import java.io.*;

/**
 * TryWithResource 를 통해
 * 여러 리소스를 다룰때 try finally 지옥을 막을수 있다.
 * try에 자원 객체를 전달하면, try 코드 블록이 끝나면 자동으로 close 해준다.
 * AutoCloseable 인터페이스의 구현체로 한정된다.
 */
public class TryWithResourceCopy {
    static void copy(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[200];
            int n;
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        }
    }
}
