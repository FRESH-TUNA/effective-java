package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 참고자료
 * https://devyongsik.tistory.com/294
 */
public class SeperatesImplementations {
    /**
     * driver interface
     * Service Provider Interface
     */
    public interface Driver {
        Connection getConnection();
    }

    /**
     * driver manager
     */
    public static class DriverManager {
        /**
         * 생성자 감추기
         */
        private DriverManager() {}

        private static final Map<String, Driver> drivers = new ConcurrentHashMap<String,Driver>();
        public static final String DEFAULT_DRIVER_NAME = "default";

        public static void registerDefaultPrivider(Driver d) {
            System.out.println("Driver registred!!");
            registerDriver(DEFAULT_DRIVER_NAME, d);
        }

        public static void registerDriver(String name, Driver d) {
            drivers.put(name,d);
        }

        public static Connection getConnection() {
            return getConnection(DEFAULT_DRIVER_NAME);
        }

        public static Connection getConnection(String name) {
            Driver d = drivers.get(name);
            if(d==null)
                throw new IllegalArgumentException();
            return d.getConnection();
        }
    }

    /**
     * custom driver
     */
    public static class TunaDriver implements Driver {
        private static Driver defaultDriver;
        static {
            defaultDriver = new TunaDriver();
            DriverManager.registerDefaultPrivider(defaultDriver);
        }

        @Override
        public Connection getConnection() {
            System.out.println("TunaDriver 연결 반환");
            return null;
        }
    }

    /**
     * Driver registred!! 출력
     * TunaDriver 연결 반환 출력
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.SeperatesImplementations$TunaDriver");
        Connection connection = DriverManager.getConnection();
    }
}

