package de.craftsarmy.commands;

import de.craftsarmy.Main;
import de.craftsarmy.utils.Touch;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CommandManager {

    private static final Touch<ICommand> touch = new Touch<>();
    private static final ConcurrentHashMap<String, ICommand> commands = new ConcurrentHashMap<>();

    public static void register(Class<? extends ICommand> clazz) {
        try {
            ICommand data = touch.touch(clazz);
            commands.put(data.update().getName().trim().toLowerCase(), data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update() {
        try {
            Main.instance.jda.awaitReady();
            ConcurrentLinkedQueue<CommandData> update = new ConcurrentLinkedQueue<>();
            for (String s : commands.keySet())
                if (!update.contains(commands.get(s).update()))
                    update.add(commands.get(s).update());
            Main.instance.jda.updateCommands().addCommands(update).queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void perform(SlashCommandInteractionEvent event) {
        try {
            Main.instance.jda.awaitReady();
            if (commands.containsKey(event.getInteraction().getName().trim().toLowerCase()))
                commands.get(event.getInteraction().getName().trim().toLowerCase()).perform(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
