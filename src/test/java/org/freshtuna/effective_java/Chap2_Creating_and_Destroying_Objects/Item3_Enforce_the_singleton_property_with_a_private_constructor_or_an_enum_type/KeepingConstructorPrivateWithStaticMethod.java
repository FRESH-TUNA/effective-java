package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item3_Enforce_the_singleton_property_with_a_private_constructor_or_an_enum_type;

public class KeepingConstructorPrivateWithStaticMethod {
    /**
     * 팩토리 메소드를 통해 생성자를 호출한다.
     * 싱글톤에서 변경시 유지보수에 유리하다.
     */
    private KeepingConstructorPrivateWithStaticMethod() {}

    private static final KeepingConstructorPrivateWithStaticMethod data = new KeepingConstructorPrivateWithStaticMethod();

    public static KeepingConstructorPrivateWithStaticMethod getData() {
        return data;
    }
}
