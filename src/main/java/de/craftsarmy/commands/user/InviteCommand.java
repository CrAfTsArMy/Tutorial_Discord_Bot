package de.craftsarmy.commands.user;

import de.craftsarmy.commands.ICommand;
import de.craftsarmy.commands.SlashCommandBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class InviteCommand implements ICommand {

    @Override
    public void perform(SlashCommandInteractionEvent event) {
        event.reply("Link folgt").queue();
    }

    @Override
    public SlashCommandData update() {
        return SlashCommandBuilder.create("invite", "Lade den Bot auf deinen Server ein!").build();
    }

}
