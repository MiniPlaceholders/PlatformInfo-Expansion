package io.github.miniplaceholders.expansion.platforminfo.sponge;

import io.github.miniplaceholders.api.Expansion;
import io.github.miniplaceholders.expansion.platforminfo.PlatformExpansionProvider;
import net.kyori.adventure.text.minimessage.tag.Tag;
import org.spongepowered.api.Server;
import org.spongepowered.api.world.server.ServerWorld;

public final class SpongeProvider extends PlatformExpansionProvider<Server> {
    public SpongeProvider(Object platformInstance) {
        super((Server) platformInstance);
    }

    @Override
    protected Expansion.Builder provideBuilder() {
        return Expansion.builder("platforminfo")
                .author("MiniPlaceholders Contributors")
                .globalPlaceholder("name", Tag.preProcessParsed("Sponge"))
                .globalPlaceholder("online", (queue, ctx) -> Tag.preProcessParsed(Integer.toString(platformInstance.onlinePlayers().size())))
                .globalPlaceholder("max_players", (queue, ctx) -> Tag.preProcessParsed(Integer.toString(platformInstance.maxPlayers())))
                .globalPlaceholder("unique_joins", (queue, ctx) -> Tag.preProcessParsed(Long.toString(platformInstance.userManager().streamAll().count())))
                .globalPlaceholder("has_whitelist", (queue, ctx) -> Tag.preProcessParsed(Boolean.toString(platformInstance.isWhitelistEnabled())))
                .globalPlaceholder("total_chunks", (queue, ctx) -> {
                    int chunkCount = 0;
                    for (ServerWorld world : platformInstance.worldManager().worlds()){
                        chunkCount += world.entities().size();
                    }
                    return Tag.preProcessParsed(Integer.toString(chunkCount));
                })
                .globalPlaceholder("total_entities", (queue, ctx) -> {
                    int entityCount = 0;
                    for (ServerWorld world : platformInstance.worldManager().worlds()){
                        entityCount += world.entities().size();
                    }
                    return Tag.preProcessParsed(Integer.toString(entityCount));
                });
    }
}
