package org.freshtuna.effective_java.Chap3_Methods_Common_to_All_Objects.Item_10_Obey_the_general_contract_when_overriding_equals;

import java.net.MalformedURLException;
import java.net.URL;

public class ViolateConsistency {
    public static void main(String[] args) throws MalformedURLException {
        URL a = new URL("naver.com");
        URL b = new URL("naver.com");

        /**
         * URL equals comparing ip address
         * so network connection is required
         * 그래서 항상 같은 값이라는것 보장할수가 없다.
         */
        a.equals(b);
    }
}
