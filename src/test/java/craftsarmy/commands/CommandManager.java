package craftsarmy.commands;

import craftsarmy.utils.Touch;
import craftsarmy.Main;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CommandManager {

    private static final Touch<ICommand> touch = new Touch<>();
    private static final ConcurrentHashMap<String, ICommand> commands = new ConcurrentHashMap<>();

    public void register(String name, Class<? extends ICommand> clazz) {
        try {
            commands.put(name, touch.touch(clazz));
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void update() throws Exception {
        ConcurrentLinkedQueue<CommandData> update = new ConcurrentLinkedQueue<>();
        for(String s : commands.keySet())
            update.add(commands.get(s).update());
        Main.instance.jda.updateCommands().addCommands(update).queue();
    }

    public static void perform(SlashCommandInteractionEvent event) {
        try {
            Main.instance.jda.awaitReady();
            if (commands.containsKey(event.getInteraction().getName().toLowerCase()))
                commands.get(event.getInteraction().getName().toLowerCase()).perform(event);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
