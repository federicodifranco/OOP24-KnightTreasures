package it.unibo.knightreasures.utilities;

/**
 * Defines various constants used for rendering and game physics.
 */
public class ViewConstants {

    /**
     * Constants related to the game window size and scaling.
     */
    public static class Window {

        /**
         * Scaling factor for the game.
         */
        public static final float SCALE = 1.5f;

        /**
         * Default size of a tile in pixels.
         */
        public static final int TILES_DEFAULT_SIZE = 32;

        /**
         * Number of tiles in width.
         */
        public static final int TILES_IN_WIDTH = 26;

        /**
         * Number of tiles in height.
         */
        public static final int TILES_IN_HEIGHT = 14;

        /**
         * Scaled tile size.
         */
        public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);

        /**
         * Total game width in pixels.
         */
        public static final int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;

        /**
         * Total game height in pixels.
         */
        public static final int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    }

    /**
     * Constants related to the player character's size, position, and hitboxes.
     */
    public static class Player {

        /**
         * Default width of the player sprite.
         */
        public static final int PLAYER_WIDTH = 64;

        /**
         * Default height of the player sprite.
         */
        public static final int PLAYER_HEIGHT = 40;

        /**
         * Scaled width of the player.
         */
        public static final int WIDTH = (int) (PLAYER_WIDTH * Window.SCALE);

        /**
         * Scaled height of the player.
         */
        public static final int HEIGHT = (int) (PLAYER_HEIGHT * Window.SCALE);

        /**
         * Initial X position of the player.
         */
        public static final int INIT_X = (int) (50 * Window.SCALE);

        /**
         * Initial Y position of the player.
         */
        public static final int INIT_Y = (int) (140 * Window.SCALE);

        /**
         * X-axis offset for the player's hitbox.
         */
        public static final int HITBOX_X_OFFSET = (int) (30 * Window.SCALE);

        /**
         * Width of the player's hitbox.
         */
        public static final int HITBOX_WIDTH = 20;

        /**
         * Height of the player's hitbox.
         */
        public static final int HITBOX_HEIGHT = 27;

        /**
         * X-axis offset for drawing the player sprite.
         */
        public static final float X_DRAW_OFFSET = 10 * Window.SCALE;

        /**
         * Y-axis offset for drawing the player sprite.
         */
        public static final float Y_DRAW_OFFSET = 4 * Window.SCALE;

        /**
         * Width of the player's attack box.
         */
        public static final int ATTACKBOX_WIDTH = (int) (20 * Window.SCALE);

        /**
         * Height of the player's attack box.
         */
        public static final int ATTACKBOX_HEIGHT = (int) (17 * Window.SCALE);

        /**
         * Offset for positioning the player's attack box.
         */
        public static final int ATTACKBOX_OFFSET = (int) (10 * Window.SCALE);
    }

    /**
     * Constants related to the skeleton enemies, including size, hitbox, and
     * attack box properties.
     */
    public static class Skeletons {

        /**
         * Default width of the skeleton sprite.
         */
        public static final int WIDTH_DEFAULT = 64;

        /**
         * Default height of the skeleton sprite.
         */
        public static final int HEIGHT_DEFAULT = 32;

        /**
         * Scaled width of the skeleton.
         */
        public static final int WIDTH = (int) (WIDTH_DEFAULT * Window.SCALE);

        /**
         * Scaled height of the skeleton.
         */
        public static final int HEIGHT = (int) (HEIGHT_DEFAULT * Window.SCALE);

        /**
         * Width of the skeleton's hitbox.
         */
        public static final int HITBOX_WIDTH = 15;

        /**
         * Height of the skeleton's hitbox.
         */
        public static final int HITBOX_HEIGHT = 25;

        /**
         * Width of the skeleton's attack box.
         */
        public static final int ATTACKBOX_WIDTH = (int) (55 * Window.SCALE);

        /**
         * Height of the skeleton's attack box.
         */
        public static final int ATTACKBOX_HEIGHT = (int) (25 * Window.SCALE);

        /**
         * X-axis offset for positioning the skeleton's attack box.
         */
        public static final int ATTACKBOX_OFFSET_X = (int) (20 * Window.SCALE);

        /**
         * X-axis offset for drawing the skeleton sprite.
         */
        public static final int DRAW_OFFSET_X = (int) (24 * Window.SCALE);

        /**
         * Y-axis offset for drawing the skeleton sprite.
         */
        public static final int DRAW_OFFSET_Y = (int) (7 * Window.SCALE);

        /**
         * Skeleton's speed.
         */
        public static final float SPEED = 0.3f * Window.SCALE;
    }

    /**
     * Defines constants related to game objects, such as treasures, chests, and
     * spikes.
     */
    public static class ObjectConstants {

        /**
         * Maximum hover offset for animated objects.
         */
        public static final int MAX_HOVER_OFFSET = (int) (10 * Window.SCALE);

        /**
         * Default width of a spike object.
         */
        public static final int SPIKE_WIDTH_DEFAULT = 32;

        /**
         * Default height of a spike object.
         */
        public static final int SPIKE_HEIGHT_DEFAULT = 32;

        /**
         * Scaled width of a spike object.
         */
        public static final int SPIKE_WIDTH = (int) (Window.SCALE * SPIKE_WIDTH_DEFAULT);

        /**
         * Scaled height of a spike object.
         */
        public static final int SPIKE_HEIGHT = (int) (Window.SCALE * SPIKE_HEIGHT_DEFAULT);

        /**
         * Default width of a chest object.
         */
        public static final int CHEST_WIDTH_DEFAULT = 60;

        /**
         * Default height of a chest object.
         */
        public static final int CHEST_HEIGHT_DEFAULT = 60;

        /**
         * Scaled width of a chest object.
         */
        public static final int CHEST_WIDTH = (int) (Window.SCALE * CHEST_WIDTH_DEFAULT);

        /**
         * Scaled height of a chest object.
         */
        public static final int CHEST_HEIGHT = (int) (Window.SCALE * CHEST_HEIGHT_DEFAULT);

        /**
         * Default width of a treasure object.
         */
        public static final int TREASURE_WIDTH_DEFAULT = 12;

        /**
         * Default height of a treasure object.
         */
        public static final int TREASURE_HEIGHT_DEFAULT = 16;

        /**
         * Scaled width of a treasure object.
         */
        public static final int TREASURE_WIDTH = (int) (Window.SCALE * TREASURE_WIDTH_DEFAULT);

        /**
         * Scaled height of a treasure object.
         */
        public static final int TREASURE_HEIGHT = (int) (Window.SCALE * TREASURE_HEIGHT_DEFAULT);

        /**
         * Width of the chest's hitbox.
         */
        public static final int CHEST_HITBOX_WIDTH = (int) (20 * Window.SCALE);

        /**
         * Height of the chest's hitbox.
         */
        public static final int CHEST_HITBOX_HEIGHT = (int) (16 * Window.SCALE);

        /**
         * X-axis offset for the chest's hitbox.
         */
        public static final int CHEST_X_OFFSET = (int) (12 * Window.SCALE);

        /**
         * Y-axis offset for the chest's hitbox.
         */
        public static final int CHEST_Y_OFFSET = (int) (16 * Window.SCALE);

        /**
         * X-axis shift for the chest's position.
         */
        public static final int CHEST_X_SHIFT = (int) (8 * Window.SCALE);

        /**
         * Y-axis shift for the chest's position.
         */
        public static final int CHEST_Y_SHIFT = (int) (2 * Window.SCALE);

        /**
         * Width of the treasure's hitbox.
         */
        public static final int TREASURE_HITBOX_WIDTH = (int) (8 * Window.SCALE);

        /**
         * Height of the treasure's hitbox.
         */
        public static final int TREASURE_HITBOX_HEIGHT = (int) (8 * Window.SCALE);

        /**
         * X-axis offset for the treasure's hitbox.
         */
        public static final int TREASURE_X_OFFSET = (int) (3 * Window.SCALE);

        /**
         * Y-axis offset for the treasure's hitbox.
         */
        public static final int TREASURE_Y_OFFSET = (int) (2 * Window.SCALE);

        /**
         * X-axis shift for the treasure's position.
         */
        public static final int TREASURE_X_SHIFT = (int) (5 * Window.SCALE);

        /**
         * Y-axis shift for the treasure's position.
         */
        public static final int TREASURE_Y_SHIFT = (int) (40 * Window.SCALE);

        /**
         * Width of the spike's hitbox.
         */
        public static final int SPIKE_HITBOX_WIDTH = (int) (32 * Window.SCALE);

        /**
         * Height of the spike's hitbox.
         */
        public static final int SPIKE_HITBOX_HEIGHT = (int) (16 * Window.SCALE);

        /**
         * Offset value for the spike object.
         */
        public static final int SPIKE_OFFSET = (int) (28 * Window.SCALE);

        /**
         * Offset value for object hover animations.
         */
        public static final float HOVER_OFFSET = 0.075f * Window.SCALE;
    }

    /**
     * Defines constants related to game physics, such as movement speed and
     * gravity.
     */
    public static class Physics {

        /**
         * Movement speed of the player.
         */
        public static final float SPEED = 1.0f * Window.SCALE;

        /**
         * Gravity force applied to the player and objects.
         */
        public static final float GRAVITY = 0.04f * Window.SCALE;

        /**
         * Initial speed when the player jumps.
         */
        public static final float JUMP_SPEED = -2.25f * Window.SCALE;

        /**
         * Speed at which the player falls when in the air.
         */
        public static final float FALL_SPEED = 0.5f * Window.SCALE;
    }

    /**
     * Contains constants for image file names used in the game.
     */
    public static class Images {

        /**
         * Image file name for the player character.
         */
        public static final String PLAYER = "knight";

        /**
         * Image file name for the menu panel.
         */
        public static final String MENU_PANEL = "menu_panel";

        /**
         * Image file name for the pause panel.
         */
        public static final String PAUSE_PANEL = "pause_panel";

        /**
         * Image file name for the settings panel.
         */
        public static final String SETTINGS_PANEL = "settings_panel";

        /**
         * Image file name for environmental elements.
         */
        public static final String ENVIRONMENT = "environment";

        /**
         * Image file name for menu buttons.
         */
        public static final String MENU_BUTTONS = "menu_buttons";

        /**
         * Image file name for the game background.
         */
        public static final String BACKGROUND = "background";

        /**
         * Image file name for the game-over panel.
         */
        public static final String GAMEOVER_PANEL = "gameover_panel";

        /**
         * Image file name for volume control buttons.
         */
        public static final String VOLUME_BUTTONS = "volume_buttons";

        /**
         * Image file name for resume, restart, and home buttons.
         */
        public static final String RRH_BUTTONS = "rrh_buttons";

        /**
         * Image file name for the home screen background.
         */
        public static final String HOME_BACKGROUND = "home_background";

        /**
         * Image file name for the skeleton enemy.
         */
        public static final String SKELETON = "skeleton";

        /**
         * Image file name for heart icons.
         */
        public static final String HEARTS = "heart";

        /**
         * Image file name for star icons.
         */
        public static final String STARS = "star";

        /**
         * Image file name for the level completion panel.
         */
        public static final String LVL_COMPLETE = "lvl_complete";

        /**
         * Image file name for treasure objects.
         */
        public static final String TREASURE = "treasure";

        /**
         * Image file name for chest objects.
         */
        public static final String CHEST = "chest_treasure";

        /**
         * Image file name for spike objects.
         */
        public static final String SPIKE = "spikes";

        /**
         * Image file name for the legend panel.
         */
        public static final String LEGEND = "legend";
    }

    /**
     * Contains constants for button sizes and positioning in the game UI.
     */
    public static class Buttons {

        /**
         * Default width of a button before scaling.
         */
        public static final int BUTTON_WIDTH_DEFAULT = 140;

        /**
         * Default height of a button before scaling.
         */
        public static final int BUTTON_HEIGHT_DEFAULT = 56;

        /**
         * Scaled width of a button.
         */
        public static final int BUTTON_WIDTH = (int) (BUTTON_WIDTH_DEFAULT * Window.SCALE);

        /**
         * Scaled height of a button.
         */
        public static final int BUTTON_HEIGHT = (int) (BUTTON_HEIGHT_DEFAULT * Window.SCALE);

        /**
         * Offset value to center a button horizontally.
         */
        public static final int X_OFFSET_CENTER = BUTTON_WIDTH / 2;
    }

    /**
     * Contains constants for audio control buttons in the game UI.
     */
    public static class AudioButtons {

        /**
         * Default size of an audio button before scaling.
         */
        public static final int SOUND_SIZE_DEFAULT = 42;

        /**
         * Scaled size of an audio button.
         */
        public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Window.SCALE);

        /**
         * X position of the volume-on button.
         */
        public static final int VOLUME_ON_X = (int) (450 * Window.SCALE);

        /**
         * X position of the volume-off button.
         */
        public static final int VOLUME_OFF_X = (int) (340 * Window.SCALE);

        /**
         * Y position of the volume buttons.
         */
        public static final int VOLUME_Y = (int) (170 * Window.SCALE);
    }

    /**
     * Contains constants for Resume, Restart, and Home (RRH) buttons in the
     * game UI.
     */
    public static class RRHButtons {

        /**
         * Default size of the RRH buttons before scaling.
         */
        public static final int RRH_SIZE_DEFAULT = 56;

        /**
         * Scaled size of the RRH buttons.
         */
        public static final int RRH_SIZE = (int) (RRH_SIZE_DEFAULT * Window.SCALE);

        /**
         * X position of the Resume button.
         */
        public static final int RESUME_X = (int) (310 * Window.SCALE);

        /**
         * X position of the Restart button.
         */
        public static final int RESTART_X = (int) (385 * Window.SCALE);

        /**
         * X position of the Home button.
         */
        public static final int HOME_X = (int) (460 * Window.SCALE);

        /**
         * Y position for all RRH buttons.
         */
        public static final int RRH_Y = (int) (360 * Window.SCALE);
    }

    /**
     * Contains constants for buttons in the settings menu.
     */
    public static class SettingsButtons {

        /**
         * X position of the Home button in the settings menu.
         */
        public static final int HOME_X = (int) (385 * Window.SCALE);

        /**
         * Y position of the Home button in the settings menu.
         */
        public static final int HOME_Y = (int) (360 * Window.SCALE);
    }

    /**
     * Contains constants for buttons displayed in the game-over screen.
     */
    public static class GameOverButtons {

        /**
         * X position of the Restart button on the game-over screen.
         */
        public static final int RESTART_X = (int) (460 * Window.SCALE);

        /**
         * X position of the Home button on the game-over screen.
         */
        public static final int HOME_X = (int) (310 * Window.SCALE);

        /**
         * Y position for all buttons on the game-over screen.
         */
        public static final int BTN_Y = (int) (350 * Window.SCALE);
    }

    /**
     * Contains constants for buttons displayed when a level is completed.
     */
    public static class LvlCompletedButtons {

        /**
         * X position of the Next Level button.
         */
        public static final int NEXT_X = (int) (430 * Window.SCALE);

        /**
         * X position of the Home button on the level completed screen.
         */
        public static final int HOME_X = (int) (340 * Window.SCALE);

        /**
         * Y position for all buttons on the level completed screen.
         */
        public static final int BTN_Y = (int) (205 * Window.SCALE);
    }

    /**
     * Contains constants related to the position of the legend panel in the
     * game UI.
     */
    public static class LegendConstants {

        /**
         * Y position of the legend panel.
         */
        public static final int LEGEND_Y = (int) (260 * Window.SCALE);

        /**
         * X offset for positioning elements within the legend panel.
         */
        public static final int LEGEND_X_OFFSET = (int) (40 * Window.SCALE);
    }

    /**
     * Contains constants for button positions in the main menu.
     */
    public static class MenuButtons {

        /**
         * Y position of the Play button in the main menu.
         */
        public static final int PLAY_Y = (int) (150 * Window.SCALE);

        /**
         * Y position of the Settings button in the main menu.
         */
        public static final int SETTINGS_Y = (int) (200 * Window.SCALE);

        /**
         * Y position of the Quit button in the main menu.
         */
        public static final int QUIT_Y = (int) (320 * Window.SCALE);
    }

    /**
     * Contains constants for level scrolling offsets.
     */
    public static class LevelOffset {

        /**
         * Left border limit before the camera starts moving.
         */
        public static final int LEFT_BORDER = (int) (0.2 * Window.GAME_WIDTH);

        /**
         * Right border limit before the camera starts moving.
         */
        public static final int RIGHT_BORDER = (int) (0.8 * Window.GAME_HEIGHT);
    }

    /**
     * Contains constants related to the player's health display (hearts).
     */
    public static class Heart {

        /**
         * Default size of a heart icon before scaling.
         */
        public static final int HEART_SIZE_DEFAULT = 30;

        /**
         * Scaled size of a heart icon.
         */
        public static final int HEART_SIZE = (int) (HEART_SIZE_DEFAULT * Window.SCALE);

        /**
         * Spacing between heart icons.
         */
        public static final int SPACING = (int) (20 * Window.SCALE);

        /**
         * Initial X position of the first heart.
         */
        public static final int INIT_X = (int) (30 * Window.SCALE);

        /**
         * Initial Y position of the first heart.
         */
        public static final int INIT_Y = (int) (20 * Window.SCALE);
    }

    /**
     * Contains constants related to the player's collected stars display.
     */
    public static class Star {

        /**
         * Default size of a star icon before scaling.
         */
        public static final int STAR_SIZE_DEFAULT = 50;

        /**
         * Scaled size of a star icon.
         */
        public static final int STAR_SIZE = (int) (STAR_SIZE_DEFAULT * Window.SCALE);

        /**
         * Spacing between star icons.
         */
        public static final int SPACING = (int) (17 * Window.SCALE);

        /**
         * Initial X position of the first star.
         */
        public static final int INIT_X = (int) (320 * Window.SCALE);

        /**
         * Initial Y position of the first star.
         */
        public static final int INIT_Y = (int) (20 * Window.SCALE);
    }

    /**
     * Contains constants for positioning various UI panels in the game.
     */
    public static class PanelSize {

        /**
         * Y position of the game-over panel.
         */
        public static final int GAMEOVER_Y = (int) (40 * Window.SCALE);

        /**
         * Y position of the settings panel.
         */
        public static final int SETTINGS_Y = (int) (36 * Window.SCALE);

        /**
         * Y position of the main menu panel.
         */
        public static final int MENU_Y = (int) (25 * Window.SCALE);

        /**
         * Y position of the pause panel.
         */
        public static final int PAUSE_Y = (int) (40 * Window.SCALE);

        /**
         * Y position of the level completed panel.
         */
        public static final int LVL_COMPLETED_Y = (int) (75 * Window.SCALE);
    }
}
