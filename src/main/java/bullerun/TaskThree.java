package bullerun;

import java.util.ArrayList;
import java.util.List;

public class TaskThree {

    static class Arthur {

        public boolean lock(Cabin cabin) {
            cabin.getDoor().lock();
            boolean locked = cabin.getDoor().isLocked();
            if (locked) {
                cabin.getVoices().clear();
            }
            return locked;
        }

        public List<Scream> listen(Cabin cabin) {
            List<HighPitchedVoice> voices = cabin.getVoices();
            return voices.stream().map(HighPitchedVoice::getScream).toList();
        }

    }

    static class Cabin {

        private final Door door;
        private final List<HighPitchedVoice> voices;

        public Cabin(Door door) {
            this.door = door;
            voices = new ArrayList<>();
            voices.add(new HighPitchedVoice());
            voices.add(new HighPitchedVoice());
            voices.add(new HighPitchedVoice());
            voices.add(new HighPitchedVoice());
            voices.add(new HighPitchedVoice());
        }

        public Cabin() {
            this(new Door());
        }

        public Door getDoor() {
            return door;
        }

        public List<HighPitchedVoice> getVoices() {
            return voices;
        }

    }

    static class Door {

        private final List<Gap> gaps;
        private final DoorCalibrationState calibrationState;
        private boolean locked;


        public Door() {
            calibrationState = DoorCalibrationState.BAD;
            locked = false;
            gaps = new ArrayList<>();
            gaps.add(new Gap());
            gaps.add(new Gap());
            gaps.add(new Gap());
        }

        public Door(DoorCalibrationState calibrationState) {
            this.calibrationState = calibrationState;
            locked = false;
            gaps = new ArrayList<>();
            if (calibrationState == DoorCalibrationState.BAD) {
                gaps.add(new Gap());
                gaps.add(new Gap());
                gaps.add(new Gap());
            }
        }

        public void lock() {
            if (calibrationState == DoorCalibrationState.GOOD) {
                locked = true;
            }
        }

        public boolean isLocked() {
            return locked;
        }

        public List<Gap> getGaps() {
            return gaps;
        }

        public DoorCalibrationState getCalibrationState() {
            return calibrationState;
        }

    }

    enum DoorCalibrationState {
        GOOD, BAD
    }

    static class Gap {

        private final SmallFurryHand smallFurryHand;

        public Gap() {
            smallFurryHand = new SmallFurryHand();
        }

        public SmallFurryHand getSmallFurryHand() {
            return smallFurryHand;
        }

    }

    static class SmallFurryHand {

        private final List<Finger> fingers;

        public SmallFurryHand() {
            fingers = new ArrayList<>();
            fingers.add(new Finger());
            fingers.add(new Finger());
            fingers.add(new Finger());
            fingers.add(new Finger());
            fingers.add(new Finger());
        }

        public SmallFurryHand(int fingerCount) {
            fingers = new ArrayList<>(fingerCount);
            for (int i = 0; i < fingerCount; i++) {
                fingers.add(new Finger());
            }
        }

        public List<Finger> getFingers() {
            return fingers;
        }

    }

    static class Finger {

        private final FingerCondition condition;

        public Finger() {
            this.condition = FingerCondition.STAINED_WITH_INK;
        }

        public Finger(FingerCondition condition) {
            this.condition = condition;
        }

        public FingerCondition getCondition() {
            return condition;
        }

    }

    enum FingerCondition {
        CLEAN, STAINED_WITH_INK
    }

    static class HighPitchedVoice {

        private final Scream scream;

        public HighPitchedVoice() {
            scream = Scream.CRAZY;
        }

        public HighPitchedVoice(Scream scream) {
            this.scream = scream;
        }

        public Scream getScream() {
            return scream;
        }

    }

    enum Scream {
        CRAZY, SANE
    }

}

