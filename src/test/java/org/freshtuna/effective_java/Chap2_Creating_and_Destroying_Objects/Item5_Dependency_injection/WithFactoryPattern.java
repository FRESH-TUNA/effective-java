package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item5_Dependency_injection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WithFactoryPattern {
    /**
     * chickens
     */
    private static abstract class Chicken { };
    private static class KyochonHoneyWithoutBornChicken extends Chicken {};
    private static class KyochonRedWithoutBornChicken extends Chicken {};
    private static class PericanaHoneyWithoutBornChicken extends Chicken {};
    private static class PericanaRedWithoutBornChicken extends Chicken {};

    /**
     * chicken factory
     */
    private interface ChickenFactory {
        Chicken createChicken(String name);
    }
    private static class KyochonChickenFactory implements ChickenFactory {
        @Override
        public Chicken createChicken(String name) {
            if(name.equals("honey")) return new KyochonHoneyWithoutBornChicken();
            else if(name.equals("red")) return new KyochonRedWithoutBornChicken();
            else return null;
        }
    }
    private static class PericanaChickenFactory implements ChickenFactory {
        @Override
        public Chicken createChicken(String name) {
            if(name.equals("honey")) return new PericanaHoneyWithoutBornChicken();
            else if(name.equals("red")) return new PericanaRedWithoutBornChicken();
            else return null;
        }
    }

    /**
     * KyochonChickenStore
     */
    private static class ChickenStore {
        ChickenFactory chickenFactory;

        ChickenStore(ChickenFactory chickenFactory) {
            this.chickenFactory = chickenFactory;
        }

        public Chicken orderChicken(String name) {
            return chickenFactory.createChicken("name");
        }
    }


    @Test
    @DisplayName("어떤 팩토리를 넣는냐에 따라 나올수 있는 치킨이 달라진다.")
    public void test() {
        ChickenStore kyochonChickenStore = new ChickenStore(new KyochonChickenFactory());
        ChickenStore pericanaChickenFactory = new PericanaChickenFactory(new KyochonChickenFactory());
    }
}
