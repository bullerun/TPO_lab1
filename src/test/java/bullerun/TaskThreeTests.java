package bullerun;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskThreeTests {


    // Артур старается запереть кабину, но дверь плохо подогнана
    @Test
    void testLockCabinDoor() {
        TaskThree.Cabin cabin = new TaskThree.Cabin();
        TaskThree.Arthur arthur = new TaskThree.Arthur();
        assertFalse(arthur.lock(cabin));
    }

    // Тоненькие голоса безумно верещат
    @Test
    void testVoicesCrazyScream() {
        TaskThree.Cabin cabin = new TaskThree.Cabin();
        TaskThree.Arthur arthur = new TaskThree.Arthur();
        List<TaskThree.Scream> screams = arthur.listen(cabin);
        assertTrue(screams.stream().allMatch(scream -> scream == TaskThree.Scream.CRAZY));
    }

    // Дверь плохо подогнана
    @Test
    void testDoorCalibratedBad() {
        TaskThree.Door door = new TaskThree.Door();
        assertEquals(TaskThree.DoorCalibrationState.BAD, door.getCalibrationState());
    }

    // У плохо подогнанной двери есть щели
    @Test
    void testBadCalibratedDoorHasGaps() {
        TaskThree.Door door = new TaskThree.Door();
        assertFalse(door.getGaps().isEmpty());
    }

    // У хорошо подогнанной двкри нет щелей
    @Test
    void testGoodCalibratedDoorHasNoGaps() {
        TaskThree.Door door = new TaskThree.Door(TaskThree.DoorCalibrationState.GOOD);
        assertTrue(door.getGaps().isEmpty());
    }

    // У плохо подогнанной двери 3 щели
    @Test
    void testBadCalibratedDoorHasThreeGaps() {
        TaskThree.Door door = new TaskThree.Door();
        assertEquals(3, door.getGaps().size());
    }

    // Хорошо подогнанную дверь можно запереть
    @Test
    void testGoodCalibratedDoorIsLockable() {
        TaskThree.Cabin cabin = new TaskThree.Cabin(new TaskThree.Door(TaskThree.DoorCalibrationState.GOOD));
        TaskThree.Arthur arthur = new TaskThree.Arthur();
        assertTrue(arthur.lock(cabin));
    }

    // Когда дверь заперта, голосов нет
    @Test
    void testDoorLockedNoVoices() {
        TaskThree.Cabin cabin = new TaskThree.Cabin(new TaskThree.Door(TaskThree.DoorCalibrationState.GOOD));
        TaskThree.Arthur arthur = new TaskThree.Arthur();
        arthur.lock(cabin);
        assertTrue(cabin.getVoices().isEmpty());
    }

    // Во всех щелях есть маленькие мохнатые ручки
    @Test
    void testThereAreHandsInGaps() {
        TaskThree.Door door = new TaskThree.Door();
        for (TaskThree.Gap gap : door.getGaps()) {
            assertNotNull(gap.getSmallFurryHand());
        }
    }

    // У рук 5 пальцев
    @Test
    void testHandHasFiveFingers() {
        TaskThree.SmallFurryHand smallFurryHand = new TaskThree.SmallFurryHand();
        assertEquals(5, smallFurryHand.getFingers().size());
    }

    // Пальцы перепачканы чернилами
    @Test
    void testHandFingersStainedWithInk() {
        TaskThree.SmallFurryHand smallFurryHand = new TaskThree.SmallFurryHand();
        assertTrue(smallFurryHand.getFingers().stream().allMatch(finger ->
                finger.getCondition() == TaskThree.FingerCondition.STAINED_WITH_INK));
    }

}
