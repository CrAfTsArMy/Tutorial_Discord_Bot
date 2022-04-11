package craftsarmy.commands;

import craftsarmy.Main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.*;

public class SlashCommandBuilder {

    private  SlashCommandData data;

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

    public void addOption(OptionType type, String name, String description) {
        data.addOption(type, name, description);
    }

    public void addOption(OptionType type, String name, String description, boolean required) {
        data.addOption(type, name, description, required);
    }

    public void addOption(OptionType type, String name, String description, boolean required, boolean autocomplete) {
        data.addOption(type, name, description, required, autocomplete);
    }

    public void addOptions(OptionData... options) {
        data.addOptions(options);
    }

    public void addSupcommands(SubcommandData subcommand) {
        data.addSubcommands(subcommand);
    }

    public void addSubcommandGroups(SubcommandGroupData subcommandGroup) {
        data.addSubcommandGroups(subcommandGroup);
    }

    public SlashCommandData build() {
        assert data != null;
        return data;
    }

}
