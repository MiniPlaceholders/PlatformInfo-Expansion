package io.github.miniplaceholders.expansion.platforminfo.velocity;

import com.velocitypowered.api.proxy.ProxyServer;
import io.github.miniplaceholders.api.Expansion;
import io.github.miniplaceholders.api.utils.Components;
import io.github.miniplaceholders.expansion.platforminfo.PlatformExpansionProvider;
import net.kyori.adventure.text.minimessage.tag.Tag;

public final class VelocityProvider extends PlatformExpansionProvider<ProxyServer> {
    public VelocityProvider(Object platformInstance) {
        super((ProxyServer) platformInstance);
    }

    @Override
    public Expansion.Builder provideBuilder() {
        return Expansion.builder("platforminfo")
                .author("MiniPlaceholders Contributors")
                .globalPlaceholder("online_players", (queue, ctx) -> {
                    if (queue.hasNext()) {
                        String server = queue.pop().value();
                        return Tag.preProcessParsed(platformInstance.getServer(server)
                                .map(sv -> sv.getPlayersConnected().size())
                                .map(size -> Integer.toString(size))
                                .orElse("0"));
                    }
                    return Tag.preProcessParsed(Integer.toString(platformInstance.getPlayerCount()));
                })
                .globalPlaceholder("server_count", (queue, ctx) -> Tag.preProcessParsed(Integer.toString(platformInstance.getAllServers().size())))
                .globalPlaceholder("is_player_online", (queue, ctx) -> {
                    String playerName = queue.popOr(() -> "you need to introduce an argument").value();
                    return Tag.selfClosingInserting(platformInstance.getPlayer(playerName).isPresent() ? Components.YES_COMPONENT : Components.NO_COMPONENT);
                })
                .globalPlaceholder("version", Tag.preProcessParsed(platformInstance.getVersion().getVersion()));
    }
}
