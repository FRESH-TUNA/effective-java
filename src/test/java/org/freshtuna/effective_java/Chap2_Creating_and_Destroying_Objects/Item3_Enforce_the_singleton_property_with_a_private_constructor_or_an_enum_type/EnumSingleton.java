package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item3_Enforce_the_singleton_property_with_a_private_constructor_or_an_enum_type;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;
public class EnumSingleton  {
    public enum MyEnumSingleton {
        INSTANCE("john");

        private final String name;

        private MyEnumSingleton(String name) {
            this.name = name;
        }

        /**
         * object method
         */
        public void method() {
            System.out.println("hohoho");
        }
    }

    /**
     * enum을 활용하여 싱글톤 객체를 만들수 있다.
     * reflection 공격도 막을수 있다.
     * It is a compile-time error to attempt to explicitly instantiate an enum type (§15.9.1).
     * The final clone method in Enum ensures that enum constants can never be cloned, and the special treatment by the serialization mechanism ensures
     * that duplicate instances are never created as a result of deserialization. Reflective instantiation of enum types is prohibited.
     *
     * serialable 하지만 안전하다.
     * Enum constants are serialized differently than ordinary serializable or externalizable objects.
     * The serialized form of an enum constant consists solely of its name;
     * field values of the constant are not present in the form. To serialize an enum constant,
     * ObjectOutputStream writes the value returned by the enum constant's name method.
     * To deserialize an enum constant, ObjectInputStream reads the constant name from the stream;
     * the deserialized constant is then obtained by calling the java.lang.Enum.valueOf method,
     * passing the constant's enum type along with the received constant name as arguments.
     * Like other serializable or externalizable objects,
     * enum constants can function as the targets of back references appearing subsequently in the serialization stream.
     *
     * 싱글톤 객체가 enum이 아닌 다른 객체의 상속을 필요로 하면 못쓴다.
     *
     *
     */
    @Test
    public void test() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        MyEnumSingleton object1 = MyEnumSingleton.INSTANCE;
        MyEnumSingleton object2 = MyEnumSingleton.INSTANCE;

        assertThat(object1).isSameAs(object2);
    }
}
