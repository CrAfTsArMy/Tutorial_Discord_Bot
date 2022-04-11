package de.craftsarmy.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public interface ICommand {

    void perform(SlashCommandInteractionEvent event);
    SlashCommandData update();

}
