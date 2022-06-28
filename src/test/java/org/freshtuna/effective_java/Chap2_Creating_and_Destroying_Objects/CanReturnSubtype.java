package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CanReturnSubtype {
    private static abstract class User {}
    private static class Admin extends User {}
    private static class Client extends User {}

    private enum Role {
        ADMIN, CLIENT
    }

    private static class UserFactory {
        public static User ofRole(Role role){
            switch (role){
                case ADMIN: return new Admin();
                case CLIENT: return new Client();
                default:
                    throw new IllegalArgumentException("Unsupported user. You input: " + role.name());
            }
        }
    }

    @DisplayName("정적 팩토리메소드는 객체의 서브타입을 반환할수 있다.")
    @Test
    public void test() {
        User admin = UserFactory.ofRole(Role.ADMIN);
        User client =UserFactory.ofRole(Role.CLIENT);
        assertThat(admin).isInstanceOf(Admin.class);
        assertThat(client).isInstanceOf(Client.class);
    }
}
