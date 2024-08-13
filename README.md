# HSMPCore 1.7.10
A small Forge mod that uses mixins to add various fixes/tweaks to the game. This mod was made because I was tired of having tons of small mods that only change 1 thing each.

🔗 [HSMP Discord](https://discord.gg/4ySWkM2)

## Features
Mostly everything can be configured under `Mod Options`.

| General Settings         | Description                                         |
|--------------------------|-----------------------------------------------------|
| `alwaysSprint`           | Automatically sprint while moving forward           |
| `blockBreakingParticles` | Can be used to disable block breaking particles     |
| `centerInventoryEffects` | Centers inventory if the player has potion effects  |
| `entityJitterFix`        | Tweaks LivingEntity position to appear less jittery |
| `flySpeed`               | Adjust flight speed while holding SPRINT key        |
| `transparentConfigs`     | Makes all Forge config GUIs transparent in-game     |

| Sidebar Settings          | Description                                        |
|---------------------------|----------------------------------------------------|
| `enableScoreboard`        | Toggle sidebar ON/OFF                              |
| `hideScoreboardInF3`      | Automatically hide sidebar if debug screen is open |
| `scoreboardColor`         | Choose the color of the sidebar numbers            |
| `scoreboardHighlightName` | Makes your own name bold in the sidebar            |
| `scoreboardLimit`         | Adjust the sidebar size (1-50 lines)               |
| `scoreboardShadow`        | Renders text with shadow in the sidebar            |
| `scoreboardTotal`         | Shows total score at the top of the sidebar        |

| Free Cam Settings          | Description                                                        |
|----------------------------|--------------------------------------------------------------------|
| `freeCamFlyAcceleration`   | Acceleration speed while flying in free cam mode                   |
| `freeCamFlyMaxSpeed`       | Max speed while flying in free cam mode (multiplied by `flySpeed`) |
| `freeCamFlySlowdownFactor` | Slowdown/slipperiness while flying in free cam mode                |
| `freeCamInteract`          | Enables interacting with the world while in free cam mode          |

## Dependency
> [!IMPORTANT]
> This mod needs a Mixin bootstrap mod to work.

My recommendation is [UniMixins](https://modrinth.com/mod/unimixins). Other Mixin bootstrap mods will work too.

## Credits
Free cam is a port of `FreeCam` by Zergatul. All credits to the author.
<br>🔗 https://www.curseforge.com/minecraft/mc-mods/freecam-by-zergatul
<br>🔗 https://github.com/Zergatul/freecam/tree/1.8.9-forge