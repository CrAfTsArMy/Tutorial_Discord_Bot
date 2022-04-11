package de.craftsarmy;

import de.craftsarmy.commands.CommandManager;
import de.craftsarmy.commands.user.InviteCommand;
import de.craftsarmy.listeners.SlashCommandInteractionListener;
import de.craftsarmy.secrets.BotUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

public class Main {

    public static Main instance;

    public static void main(String[] args) throws LoginException {
        new Main();
    }

    public final JDA jda;

    public Main() throws LoginException {
        instance = this;
        JDABuilder builder = JDABuilder.createDefault(BotUtils.token,
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_PRESENCES);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setStatus(OnlineStatus.IDLE);
        builder.setActivity(Activity.playing("kein Spiel!"));
        builder.addEventListeners(new SlashCommandInteractionListener());
        jda = builder.build();

        CommandManager.register(InviteCommand.class);
    }

}
