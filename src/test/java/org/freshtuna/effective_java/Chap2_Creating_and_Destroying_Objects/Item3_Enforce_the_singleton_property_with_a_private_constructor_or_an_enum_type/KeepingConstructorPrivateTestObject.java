package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item3_Enforce_the_singleton_property_with_a_private_constructor_or_an_enum_type;

public class KeepingConstructorPrivateTestObject {
    /**
     * 생성자를 감춘다.
     */
    private KeepingConstructorPrivateTestObject() {
    }

    public static final KeepingConstructorPrivateTestObject data = new KeepingConstructorPrivateTestObject();
}
