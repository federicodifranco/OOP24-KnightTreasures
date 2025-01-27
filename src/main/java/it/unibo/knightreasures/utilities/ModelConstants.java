package it.unibo.knightreasures.utilities;

import it.unibo.knightreasures.utilities.ViewConstants.Window;

public class ModelConstants {

    public static class Application {

        public static final int ANI_SPEED = 15;
    }

    public static class GameLoop {

        public static final int FPS_SET = 120;
        public static final int UPS_SET = 200;
        public static final double NANOSECOND = 1_000_000_000.0;
        public static final int FRAME_DEFAULT = 0;
        public static final int DELTAF_DEFAULT = 0;
        public static final int DELTAU_DEFAULT = 0;
        public static final int DELTAF_MIN = 1;
        public static final int DELTAU_MIN = 1;
    }

    public static class PlayerValues {

        public static final int IDLE = 0;
        public static final int RUN = 1;
        public static final int JUMP = 2;
        public static final int ATTACK = 3;
        public static final int INDEX_SPRITE = 6;
        public static final int NUM_LIVES = 3;
        public static final int FLIPX_DEFAULT = 0;
        public static final int FLIPW_DEFAULT = 1;
        public static final int OFFSET_X_DEFAULT = 0;
        public static final int AIR_SPEED_DEFAULT = 0;
        public static final int SPRITES_ROWS = 4;
        public static final int SPRITES_COLUMNS = 6;
        public static final int DAMAGE = 1;
        public static final int ATTACK_INDEX = 5;
        public static final int NO_LIVES = 0;
    }

    public static class SkeletonsValues {

        public static final int SKELETON = 0;
        public static final int IDLE = 0;
        public static final int RUN = 1;
        public static final int ATTACK = 2;
        public static final int HURT = 3;
        public static final int DIE = 4;
        public static final int NUM_LIVES = 2;
        public static final int DAMAGE = 1;
        public static final int FLIPX_DEFAULT = 0;
        public static final int FLIPW_DEFAULT = 1;
        public static final int SPRITES_ROWS = 5;
        public static final int SPRITES_COLUMNS = 11;
        public static final int ATTACK_INDEX = 5;
        public static final float SPEED = 0.3f * Window.SCALE;

        public static int getSpriteAmount(int enemy_state) {
            switch (enemy_state) {
                case IDLE -> {
                    return 8;
                }
                case RUN -> {
                    return 10;
                }
                case ATTACK -> {
                    return 10;
                }
                case HURT -> {
                    return 5;
                }
                case DIE -> {
                    return 11;
                }
            }
            return 0;
        }
    }

    public static class ObjectsValues {

        public static final int RING = 0;
        public static final int CUP = 1;
        public static final int CROWN = 2;
        public static final int CHEST = 3;
        public static final int SPIKE = 4;

        public static final int[] LEVEL_TREASURES = {
            CROWN,
            RING,
            CUP,
        };

        public static final int TOT_TREASURES = 3;
        public static final int CHEST_SPRITES_ROWS = 1;
        public static final int CHEST_SPRITES_COLUMNS = 5;
        public static final int TREASURE_SPRITES_ROWS = 3;
        public static final int TREASURE_SPRITES_COLUMNS = 7;
        public static final float HOVER_OFFSET = 0.075f * Window.SCALE;
        public static final int CHEST_IMAGES_INDEX = 0;
        public static final int INITIAL_COLLECTED_TREASURES = 0;
        public static final int CHEST_SPRITE_ANI = 1;
        public static final int HOVER_DIRECTION = 1;
        public static final int SPIKE_DAMAGE = 3;

        public static int getSpriteAmount(int object_type) {
            switch (object_type) {
                case RING, CUP, CROWN -> {
                    return 7;
                }
                case CHEST -> {
                    return 5;
                }
            }
            return 1;
        }
    }

    public static class Physics {

        public static final float SPEED = 1.0f * Window.SCALE;
        public static final float GRAVITY = 0.04f * Window.SCALE;
        public static final float JUMP_SPEED = -2.25f * Window.SCALE;
        public static final float FALL_SPEED = 0.5f * Window.SCALE;
    }

    public static class Directions {

        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class LevelsValues {

        public static final int LVL_INDEX = 0;
        public static final int LVL_DATA_INDEX = 0;
        public static final int NO_LEVEL_FILES_LENGHT = 0;
        public static final int FIRST_LEVEL_POSITION = 0;
        public static final int SPRITES = 48;
        public static final int SPRITES_COLUMNS = 12;
        public static final int SPRITES_ROWS = 4;
        public static final int SPRITES_SIZE = 32;
        public static final int NUM_STARS = 3;
        public static final int FIRST_STARS = 0;
        public static final int SECOND_STARS = 1;
        public static final int THIRD_STARS = 2;
        public static final int TILE_1_NOT_SOLID = 48;
        public static final int TILE_2_NOT_SOLID = 0;
        public static final int TILE_3_NOT_SOLID = 11;
        public static final int LEVEL = 48;
        public static final int SPAWN = 100;
        public static final int GREY_BACKGROUND = 200;
        public static final int FLOOR_OFFSET = 1;
    }

    public static class ButtonsValues {

        public static final int AUDIO_NUM_BUTTONS = 2;
        public static final int AUDIO_SIZE = 3;
        public static final int RRH_NUM_BUTTONS = 3;
        public static final int GAME_OVER_NUM_BUTTONS = 2;
        public static final int LVL_COMPLETE_NUM_BUTTONS = 2;
        public static final int MENU_NUM_BUTTONS = 3;
        public static final int VOLUME_OFF = 0;
        public static final int VOLUME_ON = 1;
        public static final int PLAY_BUTTON = 0;
        public static final int SETTINGS_BUTTON = 1;
        public static final int QUIT_BUTTON = 2;
        public static final int RESUME_BUTTON = 0;
        public static final int RESTART_BUTTON = 1;
        public static final int HOME_BUTTON = 2;
        public static final int FIRST_ROW_INDEX = 0;
        public static final int SECOND_ROW_INDEX = 1;
        public static final int THIRD_ROW_INDEX = 2;
    }

    public static class SongGame {

        public static final float VOLUME_OFF = 0.0f;
        public static final float VOLUME_BASE = 1.0f;
        public static final int LEVEL_SONG = 0;
        public static final int MENU_SONG = 1;
    }
}
