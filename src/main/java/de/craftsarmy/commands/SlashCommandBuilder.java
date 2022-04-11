package de.craftsarmy.commands;

import de.craftsarmy.Main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandGroupData;

public class SlashCommandBuilder {

    private SlashCommandData data;

    public SlashCommandBuilder(String name, String description) {
        try {
            JDA jda = Main.instance.jda;
            jda.awaitReady();
            Command command = jda.upsertCommand(name, description).complete();
            data = SlashCommandData.fromCommand(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SlashCommandBuilder addOption(OptionType type, String name, String description) {
        data.addOption(type, name, description);
        return this;
    }

    public SlashCommandBuilder addOption(OptionType type, String name, String description, boolean required) {
        data.addOption(type, name, description, required);
        return this;
    }

    public SlashCommandBuilder addOption(OptionType type, String name, String description, boolean required, boolean autocomplete) {
        data.addOption(type, name, description, required, autocomplete);
        return this;
    }

    public SlashCommandBuilder addSupcommand(SubcommandData subcommand) {
        data.addSubcommands(subcommand);
        return this;
    }

    public SlashCommandBuilder addSupcommandGroup(SubcommandGroupData subcommandGroup) {
        data.addSubcommandGroups(subcommandGroup);
        return this;
    }

    public SlashCommandData build() {
        assert data != null;
        return data;
    }

    public static SlashCommandBuilder create(String name, String description) {
        return new SlashCommandBuilder(name, description);
    }

}
