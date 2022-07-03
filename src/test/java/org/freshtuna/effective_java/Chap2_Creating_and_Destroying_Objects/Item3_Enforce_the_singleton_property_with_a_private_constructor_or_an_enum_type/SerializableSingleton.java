package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item3_Enforce_the_singleton_property_with_a_private_constructor_or_an_enum_type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

public class SerializableSingleton {
    /**
     * Serializable 를 상속하고 아무처리를 안하면
     * 상글톤이 안된다.
     */
    private static class IsNotSingleton implements Serializable {
        private IsNotSingleton() {};
        private final static IsNotSingleton data = new IsNotSingleton();
        public static IsNotSingleton getData() { return data; }
    }

    /**
     * readResolve 메서드를 사용하면 된다.
     * readObject 메서드가 있더라도 readResolve 메서드에서 반환한 인스턴스로 대체된다.
     */
    private static class Singleton implements Serializable {
        private Singleton() {};
        private final static Singleton data = new Singleton();
        public static Singleton getData() { return data; }

        private Object readResolve() {
            return data;
        }
    }

    /**
     * methods
     */
    private byte[] serialize(Object instance) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (bos; ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(instance);
        } catch (Exception e) {
            // ... 구현 생략
        }
        return bos.toByteArray();
    }

    private Object deserialize(byte[] serializedData) {
        ByteArrayInputStream bis = new ByteArrayInputStream(serializedData);
        try (bis; ObjectInputStream ois = new ObjectInputStream(bis)) {
            return ois.readObject();
        } catch (Exception e) {
            // ... 구현 생략
        }
        return null;
    }

    @Test
    @DisplayName("Serializable 클래스의 경우 역직렬화시 별다른 조치가 없으면 한상 객체가 생성된다.")
    public void test() {
        IsNotSingleton data = IsNotSingleton.getData();

        byte[] databytes = serialize(data);

        IsNotSingleton data2 = (IsNotSingleton) deserialize(databytes);

        assertThat(data).isNotSameAs(data2);
    }

    @Test
    @DisplayName("readResolve를 이용하여 싱글톤문제를 해결한다.")
    public void test1() {
        Singleton data = Singleton.getData();

        byte[] databytes = serialize(data);

        Singleton data2 = (Singleton) deserialize(databytes);

        assertThat(data).isSameAs(data2);
    }
}
