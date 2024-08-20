package me.kimovoid.tweakimo.mixin.sidebar;

import me.kimovoid.tweakimo.Tweakimo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.scoreboard.*;
import net.minecraft.util.EnumChatFormatting;
import org.spongepowered.asm.mixin.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

@Mixin(value = GuiIngame.class, priority = 999)
public abstract class GuiIngameMixin extends Gui {

    @Shadow @Final protected Minecraft mc;

    /**
     * @author kimoVoid
     * @reason Default scoreboard is garbage you won't miss it
     */
    @Overwrite
    public void func_96136_a(ScoreObjective objective, int x, int y, FontRenderer fr) {
        if (!Tweakimo.CONFIG.enableScoreboard || (Tweakimo.CONFIG.hideScoreboardInF3 && this.mc.gameSettings.showDebugInfo)) {
            return;
        }

        Scoreboard scoreboard = objective.getScoreboard();
        Collection<Score> collection = scoreboard.func_96534_i(objective);

        if (Tweakimo.CONFIG.scoreboardTotal) {
            int total = 0;
            for (Score score : collection) {
                total += score.getScorePoints();
            }
            Score totalScore = new Score(scoreboard, scoreboard.func_96539_a(0), EnumChatFormatting.GRAY + "Total");
            totalScore.setScorePoints(total);
            collection.add(totalScore);
        }

        int limit = Tweakimo.CONFIG.scoreboardLimit + (Tweakimo.CONFIG.scoreboardTotal ? 1 : 0);
        if (collection.size() > limit) {
            collection = collection.stream().skip(Math.abs(collection.size() - limit)).collect(Collectors.toList());
        }

        int width = fr.getStringWidth(objective.getDisplayName());
        String s;
        for (Iterator<Score> iterator = collection.iterator(); iterator.hasNext(); width = Math.max(width, fr.getStringWidth(s))) {
            Score score = iterator.next();
            ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
            s = this.getPlayerName(scoreplayerteam, score.getPlayerName()) + EnumChatFormatting.RESET + ": " + EnumChatFormatting.RED + score.getScorePoints();
        }

        int k1 = collection.size() * fr.FONT_HEIGHT;
        int l1 = x / 2 + k1 / 3;
        int i2 = y - width - 3;
        int l = 0;

        for (Score score : collection) {
            ++l;
            ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score.getPlayerName());
            String s1 = this.getPlayerName(scoreplayerteam1, score.getPlayerName());
            String s2 = EnumChatFormatting.getValueByName(Tweakimo.CONFIG.scoreboardColor) + "" + score.getScorePoints();

            int i1 = l1 - l * fr.FONT_HEIGHT;
            int j1 = y - 3 + 2;
            drawRect(i2 - 2, i1, j1, i1 + fr.FONT_HEIGHT, 1342177280);

            this.renderText(fr, s1, i2, i1, 553648127);
            this.renderText(fr, s2, j1 - fr.getStringWidth(s2), i1, 553648127);

            if (l == collection.size()) {
                String title = objective.getDisplayName();
                drawRect(i2 - 2, i1 - fr.FONT_HEIGHT - 1, j1, i1 - 1, 1610612736);
                drawRect(i2 - 2, i1 - 1, j1, i1, 1342177280);
                this.renderText(fr, title, i2 + width / 2 - fr.getStringWidth(title) / 2, i1 - fr.FONT_HEIGHT, 553648127);
            }
        }
    }

    @Unique
    private void renderText(FontRenderer fr, String str, int x, int y, int color) {
        if (Tweakimo.CONFIG.scoreboardShadow) {
            fr.drawStringWithShadow(str, x, y, color);
        } else {
            fr.drawString(str, x, y, color);
        }
    }

    @Unique
    private String getPlayerName(Team team, String playerName) {
        return ScorePlayerTeam.formatPlayerName(team, (Tweakimo.CONFIG.scoreboardHighlightName && playerName.equals(this.mc.thePlayer.getDisplayName()) ? EnumChatFormatting.BOLD + playerName : playerName));
    }
}