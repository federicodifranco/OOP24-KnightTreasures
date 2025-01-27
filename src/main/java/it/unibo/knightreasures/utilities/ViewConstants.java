package it.unibo.knightreasures.utilities;

public class ViewConstants {

    public static class Window {

        public static final float SCALE = 1.5f;
        public static final int TILES_DEFAULT_SIZE = 32;
        public static final int TILES_IN_WIDTH = 26;
        public static final int TILES_IN_HEIGHT = 14;
        public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
        public static final int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
        public static final int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    }

    public static class Player {

        public static final int PLAYER_WIDTH = 64;
        public static final int PLAYER_HEIGHT = 40;
        public static final int WIDTH = (int) (64 * Window.SCALE);
        public static final int HEIGHT = (int) (40 * Window.SCALE);
        public static final int INIT_X = (int) (50 * Window.SCALE);
        public static final int INIT_Y = (int) (80 * Window.SCALE);
        public static final int HITBOX_X_OFFSET = (int) (30 * Window.SCALE);
        public static final int HITBOX_WIDTH = 20;
        public static final int HITBOX_HEIGHT = 27;
        public static final float X_DRAW_OFFSET = 10 * Window.SCALE;
        public static final float Y_DRAW_OFFSET = 4 * Window.SCALE;
        public static final int ATTACKBOX_WIDTH = (int) (20 * Window.SCALE);
        public static final int ATTACKBOX_HEIGHT = (int) (17 * Window.SCALE);
        public static final int ATTACKBOX_OFFSET = (int) (10 * Window.SCALE);
    }

    public static class Skeletons {

        public static final int WIDTH_DEFAULT = 64;
        public static final int HEIGHT_DEFAULT = 32;
        public static final int WIDTH = (int) (WIDTH_DEFAULT * Window.SCALE);
        public static final int HEIGHT = (int) (HEIGHT_DEFAULT * Window.SCALE);
        public static final int HITBOX_WIDTH = 15;
        public static final int HITBOX_HEIGHT = 25;
        public static final int ATTACKBOX_WIDTH = (int) (55 * Window.SCALE);
        public static final int ATTACKBOX_HEIGHT = (int) (25 * Window.SCALE);
        public static final int ATTACKBOX_OFFSET_X = (int) (20 * Window.SCALE);
        public static final int DRAW_OFFSET_X = (int) (24 * Window.SCALE);
        public static final int DRAW_OFFSET_Y = (int) (7 * Window.SCALE);
    }

    public static class ObjectConstants {

        public static final int RING = 0;
        public static final int CUP = 1;
        public static final int CROWN = 2;
        public static final int CHEST = 3;
        public static final int SPIKE = 4;

        public static final int[] LEVEL_TREASURES = {
            CROWN,
            RING,
            CUP,};

        public static final int TOT_TREASURES = 3;

        public static final int MAX_HOVER_OFFSET = (int) (10 * Window.SCALE);

        public static final int CHEST_SPRITES_ROWS = 1;
        public static final int CHEST_SPRITES_COLUMNS = 5;
        public static final int TREASURE_SPRITES_ROWS = 3;
        public static final int TREASURE_SPRITES_COLUMNS = 7;

        public static final int SPIKE_WIDTH_DEFAULT = 32;
        public static final int SPIKE_HEIGHT_DEFAULT = 32;
        public static final int SPIKE_WIDTH = (int) (Window.SCALE * SPIKE_WIDTH_DEFAULT);
        public static final int SPIKE_HEIGHT = (int) (Window.SCALE * SPIKE_HEIGHT_DEFAULT);

        public static final int CHEST_WIDTH_DEFAULT = 60;
        public static final int CHEST_HEIGHT_DEFAULT = 60;
        public static final int CHEST_WIDTH = (int) (Window.SCALE * CHEST_WIDTH_DEFAULT);
        public static final int CHEST_HEIGHT = (int) (Window.SCALE * CHEST_HEIGHT_DEFAULT);

        public static final int TREASURE_WIDTH_DEFAULT = 12;
        public static final int TREASURE_HEIGHT_DEFAULT = 16;
        public static final int TREASURE_WIDTH = (int) (Window.SCALE * TREASURE_WIDTH_DEFAULT);
        public static final int TREASURE_HEIGHT = (int) (Window.SCALE * TREASURE_HEIGHT_DEFAULT);

        public static final int CHEST_HITBOX_WIDTH = (int) (20 * Window.SCALE);
        public static final int CHEST_HITBOX_HEIGHT = (int) (16 * Window.SCALE);
        public static final int CHEST_X_OFFSET = (int) (12 * Window.SCALE);
        public static final int CHEST_Y_OFFSET = (int) (16 * Window.SCALE);
        public static final int CHEST_X_SHIFT = (int) (8 * Window.SCALE);
        public static final int CHEST_Y_SHIFT = (int) (2 * Window.SCALE);

        public static final int TREASURE_HITBOX_WIDTH = (int) (8 * Window.SCALE);
        public static final int TREASURE_HITBOX_HEIGHT = (int) (8 * Window.SCALE);
        public static final int TREASURE_X_OFFSET = (int) (3 * Window.SCALE);
        public static final int TREASURE_Y_OFFSET = (int) (2 * Window.SCALE);
        public static final int TREASURE_X_SHIFT = (int) (5 * Window.SCALE);
        public static final int TREASURE_Y_SHIFT = (int) (40 * Window.SCALE);

        public static final int SPIKE_HITBOX_WIDTH = (int) (32 * Window.SCALE);
        public static final int SPIKE_HITBOX_HEIGHT = (int) (16 * Window.SCALE);
        public static final int SPIKE_OFFSET = (int) (28 * Window.SCALE);

        public static final float HOVER_OFFSET = 0.075f * Window.SCALE;

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

    public static class Images {

        public static final String PLAYER = "knight";
        public static final String MENU_PANEL = "menu_panel";
        public static final String PAUSE_PANEL = "pause_panel";
        public static final String SETTINGS_PANEL = "settings_panel";
        public static final String ENVIRONMENT = "environment";
        public static final String MENU_BUTTONS = "menu_buttons";
        public static final String BACKGROUND = "background";
        public static final String GAMEOVER_PANEL = "gameover_panel";
        public static final String VOLUME_BUTTONS = "volume_buttons";
        public static final String RRH_BUTTONS = "rrh_buttons";
        public static final String HOME_BACKGROUND = "home_background";
        public static final String SKELETON = "skeleton";
        public static final String HEARTS = "heart";
        public static final String STARS = "star";
        public static final String LVL_COMPLETE = "lvl_complete";
        public static final String TREASURE = "treasure";
        public static final String CHEST = "chest_treasure";
        public static final String SPIKE = "spikes";
        public static final String LEGEND = "legend";
    }

    public static class Buttons {

        public static final int BUTTON_WIDTH_DEFAULT = 140;
        public static final int BUTTON_HEIGHT_DEFAULT = 56;
        public static final int BUTTON_WIDTH = (int) (BUTTON_WIDTH_DEFAULT * Window.SCALE);
        public static final int BUTTON_HEIGHT = (int) (BUTTON_HEIGHT_DEFAULT * Window.SCALE);
        public static final int X_OFFSET_CENTER = BUTTON_WIDTH / 2;
    }

    public static class AudioButtons {

        public static final int SOUND_SIZE_DEFAULT = 42;
        public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Window.SCALE);
        public static final int VOLUME_ON_X = (int) (450 * Window.SCALE);
        public static final int VOLUME_OFF_X = (int) (340 * Window.SCALE);
        public static final int VOLUME_Y = (int) (170 * Window.SCALE);
    }

    public static class RRHButtons {

        public static final int RRH_SIZE_DEFAULT = 56;
        public static final int RRH_SIZE = (int) (RRH_SIZE_DEFAULT * Window.SCALE);
        public static final int RESUME_X = (int) (310 * Window.SCALE);
        public static final int RESTART_X = (int) (385 * Window.SCALE);
        public static final int HOME_X = (int) (460 * Window.SCALE);
        public static final int RRH_Y = (int) (360 * Window.SCALE);
    }

    public static class SettingsButtons {

        public static final int HOME_X = (int) (385 * Window.SCALE);
        public static final int HOME_Y = (int) (360 * Window.SCALE);
    }

    public static class GameOverButtons {

        public static final int RESTART_X = (int) (460 * Window.SCALE);
        public static final int HOME_X = (int) (310 * Window.SCALE);
        public static final int BTN_Y = (int) (350 * Window.SCALE);
    }

    public static class LvlCompletedButtons {

        public static final int NEXT_X = (int) (430 * Window.SCALE);
        public static final int HOME_X = (int) (340 * Window.SCALE);
        public static final int BTN_Y = (int) (205 * Window.SCALE);
    }

    public static class LegendConstants {

        public static final int LEGEND_Y = (int) (260 * Window.SCALE);
        public static final int LEGEND_X_OFFSET = (int) (40 * Window.SCALE);
    }

    public static class MenuButtons {

        public static final int PLAY_Y = (int) (150 * Window.SCALE);
        public static final int SETTINGS_Y = (int) (200 * Window.SCALE);
        public static final int QUIT_Y = (int) (320 * Window.SCALE);
    }

    public static class LevelOffset {

        public static final int LEFT_BORDER = (int) (0.2 * Window.GAME_WIDTH);
        public static final int RIGHT_BORDER = (int) (0.8 * Window.GAME_HEIGHT);
    }

    public static class Heart {

        public static final int HEART_SIZE_DEFAULT = 30;
        public static final int HEART_SIZE = (int) (HEART_SIZE_DEFAULT * Window.SCALE);
        public static final int SPACING = (int) (20 * Window.SCALE);
        public static final int INIT_X = (int) (30 * Window.SCALE);
        public static final int INIT_Y = (int) (20 * Window.SCALE);
    }

    public static class Star {

        public static final int STAR_SIZE_DEFAULT = 50;
        public static final int STAR_SIZE = (int) (STAR_SIZE_DEFAULT * Window.SCALE);
        public static final int SPACING = (int) (17 * Window.SCALE);
        public static final int INIT_X = (int) (320 * Window.SCALE);
        public static final int INIT_Y = (int) (20 * Window.SCALE);
    }

    public static class PanelSize {

        public static final int GAMEOVER_Y = (int) (40 * Window.SCALE);
        public static final int SETTINGS_Y = (int) (36 * Window.SCALE);
        public static final int MENU_Y = (int) (25 * Window.SCALE);
        public static final int PAUSE_Y = (int) (40 * Window.SCALE);
        public static final int LVL_COMPLETED_Y = (int) (75 * Window.SCALE);
    }
}
