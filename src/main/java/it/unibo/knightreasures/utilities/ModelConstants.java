package it.unibo.knightreasures.utilities;

/**
 * This class contains various constant values used throughout the game model.
 */
public final class ModelConstants {

    /**
     * Constants related to the application settings.
     */
    public static class Application {

        /**
         * The animation speed setting.
         */
        public static final int ANI_SPEED = 15;
    }

    /**
     * Constants related to the game loop and frame updates.
     */
    public static class GameLoop {

        /**
         * The target frames per second (FPS).
         */
        public static final int FPS_SET = 120;

        /**
         * The target updates per second (UPS).
         */
        public static final int UPS_SET = 200;

        /**
         * The conversion factor for nanoseconds to seconds.
         *
         */
        public static final double NANOSECOND = 1_000_000_000.0;
        /**
         * Default frame counter value.
         *
         */
        public static final int FRAME_DEFAULT = 0;

        /**
         * Default delta frame value.
         */
        public static final int DELTAF_DEFAULT = 0;

        /**
         * Default delta update value.
         */
        public static final int DELTAU_DEFAULT = 0;

        /**
         * Minimum delta frame value.
         */
        public static final int DELTAF_MIN = 1;

        /**
         * Minimum delta update value.
         */
        public static final int DELTAU_MIN = 1;
    }

    /**
     * Constants defining player attributes and behaviors.
     */
    public static class PlayerValues {

        /**
         * Player idle state index.
         */
        public static final int IDLE = 0;

        /**
         * Player running state index.
         */
        public static final int RUN = 1;

        /**
         * Player jumping state index.
         */
        public static final int JUMP = 2;

        /**
         * Player attacking state index.
         */
        public static final int ATTACK = 3;

        /**
         * The number of sprites used in player animations.
         */
        public static final int INDEX_SPRITE = 6;

        /**
         * The default number of lives for the player.
         */
        public static final int NUM_LIVES = 3;

        /**
         * The default flip X value for player sprite.
         */
        public static final int FLIPX_DEFAULT = 0;

        /**
         * The default flip width value for player sprite.
         */
        public static final int FLIPW_DEFAULT = 1;

        /**
         * The default offset for player's X position.
         */
        public static final int OFFSET_X_DEFAULT = 0;

        /**
         * The default air speed when falling.
         */
        public static final int AIR_SPEED_DEFAULT = 0;

        /**
         * The number of sprite rows in the player's animation sheet.
         */
        public static final int SPRITES_ROWS = 4;

        /**
         * The number of sprite columns in the player's animation sheet.
         */
        public static final int SPRITES_COLUMNS = 6;

        /**
         * The amount of damage dealt by the player.
         */
        public static final int DAMAGE = 1;

        /**
         * The animation index for player attack.
         */
        public static final int ATTACK_INDEX = 5;

        /**
         * The number of lives when the player has no health left.
         */
        public static final int NO_LIVES = 0;
    }

    /**
     * Constants defining skeleton enemy attributes and behaviors.
     */
    public static class SkeletonsValues {

        /**
         * Identifier for the skeleton entity.
         */
        public static final int SKELETON = 0;

        /**
         * Skeleton idle state index.
         */
        public static final int IDLE = 0;

        /**
         * Skeleton running state index.
         */
        public static final int RUN = 1;

        /**
         * Skeleton attacking state index.
         */
        public static final int ATTACK = 2;

        /**
         * Skeleton hurt state index.
         */
        public static final int HURT = 3;

        /**
         * Skeleton dying state index.
         */
        public static final int DIE = 4;

        /**
         * The default number of lives for a skeleton.
         */
        public static final int NUM_LIVES = 2;

        /**
         * The amount of damage dealt by a skeleton.
         */
        public static final int DAMAGE = 1;

        /**
         * The default flip X value for skeleton sprite.
         */
        public static final int FLIPX_DEFAULT = 0;

        /**
         * The default flip width value for skeleton sprite.
         */
        public static final int FLIPW_DEFAULT = 1;

        /**
         * The number of sprite rows in the skeleton's animation sheet.
         */
        public static final int SPRITES_ROWS = 5;

        /**
         * The number of sprite columns in the skeleton's animation sheet.
         */
        public static final int SPRITES_COLUMNS = 11;

        /**
         * The animation index for skeleton attack.
         */
        public static final int ATTACK_INDEX = 5;

        /**
         * The number of sprite frames for the idle animation.
         */
        public static final int IDLE_SPRITES = 8;

        /**
         * The number of sprite frames for the running animation.
         */
        public static final int RUN_SPRITES = 10;

        /**
         * The number of sprite frames for the attack animation.
         */
        public static final int ATTACK_SPRITES = 10;

        /**
         * The number of sprite frames for the hurt animation.
         */
        public static final int HURT_SPRITES = 5;

        /**
         * The number of sprite frames for the dying animation.
         */
        public static final int DIE_SPRITES = 11;

        /**
         * The range for the skeleton to see the player.
         */
        public static final int RANGE_TO_SEE_PLAYER = 5;

        /**
         * Number of possible states a skeleton enemy can have. These states may
         * include idle, running, attacking, hurt, and dying.
         */
        public static final int SKELETON_STATES = 5;

        /**
         * Number of animation frames used for the skeleton's movement and
         * actions. This determines the smoothness of the animation cycle.
         */
        public static final int SKELETON_ANIMATION_FRAMES = 11;

    }

    /**
     * Constants defining object attributes and behaviors in the game.
     */
    public static class ObjectsValues {

        /**
         * Identifier for the ring treasure.
         */
        public static final int RING = 0;

        /**
         * Identifier for the cup treasure.
         */
        public static final int CUP = 1;

        /**
         * Identifier for the crown treasure.
         */
        public static final int CROWN = 2;

        /**
         * Identifier for the chest.
         */
        public static final int CHEST = 3;

        /**
         * Identifier for the spike trap.
         */
        public static final int SPIKE = 4;

        /**
         * Array defining the level treasures.
         */
        public static final int[] LEVEL_TREASURES = {CROWN, RING, CUP};

        /**
         * The total number of treasures in a level.
         */
        public static final int TOT_TREASURES = 3;

        /**
         * Number of sprite rows in the chest animation sheet.
         */
        public static final int CHEST_SPRITES_ROWS = 1;

        /**
         * Number of sprite columns in the chest animation sheet.
         */
        public static final int CHEST_SPRITES_COLUMNS = 5;

        /**
         * Number of sprite rows in the treasure animation sheet.
         */
        public static final int TREASURE_SPRITES_ROWS = 3;

        /**
         * Number of sprite columns in the treasure animation sheet.
         */
        public static final int TREASURE_SPRITES_COLUMNS = 7;

        /**
         * The initial image index for a chest animation.
         */
        public static final int CHEST_IMAGES_INDEX = 0;

        /**
         * The initial count of collected treasures at the start of the game.
         */
        public static final int INITIAL_COLLECTED_TREASURES = 0;

        /**
         * The chest sprite animation state index.
         */
        public static final int CHEST_SPRITE_ANI = 1;

        /**
         * Direction value used for hover animation effects.
         */
        public static final int HOVER_DIRECTION = 1;

        /**
         * The amount of damage caused by spikes.
         */
        public static final int SPIKE_DAMAGE = 3;

        /**
         * Number of sprite frames for chest animation.
         */
        public static final int CHEST_SPRITES = 5;

        /**
         * Number of sprite frames for object animations (ring, cup, crown).
         */
        public static final int OBJECTS_SPRITES = 7;

    }

    /**
     * Defines constants for movement directions in the game.
     */
    public static class Directions {

        /**
         * Left direction identifier.
         */
        public static final int LEFT = 0;

        /**
         * Up direction identifier.
         */
        public static final int UP = 1;

        /**
         * Right direction identifier.
         */
        public static final int RIGHT = 2;

        /**
         * Down direction identifier.
         */
        public static final int DOWN = 3;
    }

    /**
     * Constants defining level attributes and game environment settings.
     */
    public static class LevelsValues {

        /**
         * Index for the current level.
         */
        public static final int LVL_INDEX = 0;

        /**
         * Index for level data array.
         */
        public static final int LVL_DATA_INDEX = 0;

        /**
         * Default value when no level files are found.
         */
        public static final int NO_LEVEL_FILES_LENGHT = 0;

        /**
         * Position index for the first level.
         */
        public static final int FIRST_LEVEL_POSITION = 0;

        /**
         * Total number of sprites used for level representation.
         */
        public static final int SPRITES = 48;

        /**
         * Number of columns in the sprite sheet for level elements.
         */
        public static final int SPRITES_COLUMNS = 12;

        /**
         * Number of rows in the sprite sheet for level elements.
         */
        public static final int SPRITES_ROWS = 4;

        /**
         * Size (in pixels) of each sprite in the level.
         */
        public static final int SPRITES_SIZE = 32;

        /**
         * Number of stars awarded per level.
         */
        public static final int NUM_STARS = 3;

        /**
         * Index of the first star.
         */
        public static final int FIRST_STARS = 0;

        /**
         * Index of the second star.
         */
        public static final int SECOND_STARS = 1;

        /**
         * Index of the third star.
         */
        public static final int THIRD_STARS = 2;

        /**
         * Identifier for the first type of non-solid tile.
         */
        public static final int TILE_1_NOT_SOLID = 48;

        /**
         * Identifier for the second type of non-solid tile.
         */
        public static final int TILE_2_NOT_SOLID = 0;

        /**
         * Identifier for the third type of non-solid tile.
         */
        public static final int TILE_3_NOT_SOLID = 11;

        /**
         * Default identifier for a level tile.
         */
        public static final int LEVEL = 48;

        /**
         * Identifier for the player spawn position in the level.
         */
        public static final int SPAWN = 100;

        /**
         * Background color value used for greyed-out areas.
         */
        public static final int GREY_BACKGROUND = 200;

        /**
         * Offset value for floor positioning in collision detection.
         */
        public static final int FLOOR_OFFSET = 1;
    }

    /**
     * Constants defining button attributes and configurations for the game UI.
     */
    public static class ButtonsValues {

        /**
         * Number of buttons for audio settings.
         */
        public static final int AUDIO_NUM_BUTTONS = 2;

        /**
         * Size index for audio buttons.
         */
        public static final int AUDIO_SIZE = 3;

        /**
         * Number of buttons in the Resume, Restart, Home (RRH) menu.
         */
        public static final int RRH_NUM_BUTTONS = 3;

        /**
         * Number of buttons in the game over screen.
         */
        public static final int GAME_OVER_NUM_BUTTONS = 2;

        /**
         * Number of buttons in the level completion screen.
         */
        public static final int LVL_COMPLETE_NUM_BUTTONS = 2;

        /**
         * Number of buttons in the main menu.
         */
        public static final int MENU_NUM_BUTTONS = 3;

        /**
         * Identifier for volume off state.
         */
        public static final int VOLUME_OFF = 0;

        /**
         * Identifier for volume on state.
         */
        public static final int VOLUME_ON = 1;

        /**
         * Identifier for the play button in the main menu.
         */
        public static final int PLAY_BUTTON = 0;

        /**
         * Identifier for the settings button in the main menu.
         */
        public static final int SETTINGS_BUTTON = 1;

        /**
         * Identifier for the quit button in the main menu.
         */
        public static final int QUIT_BUTTON = 2;

        /**
         * Identifier for the resume button in the pause menu.
         */
        public static final int RESUME_BUTTON = 0;

        /**
         * Identifier for the restart button in the pause menu.
         */
        public static final int RESTART_BUTTON = 1;

        /**
         * Identifier for the home button in the pause menu.
         */
        public static final int HOME_BUTTON = 2;

        /**
         * Index of the first row of buttons.
         */
        public static final int FIRST_ROW_INDEX = 0;

        /**
         * Index of the second row of buttons.
         */
        public static final int SECOND_ROW_INDEX = 1;

        /**
         * Index of the third row of buttons.
         */
        public static final int THIRD_ROW_INDEX = 2;
    }

    /**
     * Constants related to game music and audio settings.
     */
    public static class SongGame {

        /**
         * Volume level for muted audio.
         */
        public static final float VOLUME_OFF = 0.0f;

        /**
         * Default volume level for game audio.
         */
        public static final float VOLUME_BASE = 1.0f;

        /**
         * Identifier for level background music.
         */
        public static final int LEVEL_SONG = 0;

        /**
         * Identifier for main menu background music.
         */
        public static final int MENU_SONG = 1;
    }
}
