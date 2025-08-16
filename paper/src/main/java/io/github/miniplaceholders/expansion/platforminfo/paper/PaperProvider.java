package io.github.miniplaceholders.expansion.platforminfo.paper;

import io.github.miniplaceholders.api.Expansion;
import io.github.miniplaceholders.expansion.platforminfo.PlatformExpansionProvider;
import io.papermc.paper.datapack.Datapack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.tag.Tag;
import org.bukkit.Server;
import org.bukkit.World;

public final class PaperProvider extends PlatformExpansionProvider<Server> {

    public PaperProvider(Object platformInstance) {
        super((Server) platformInstance);
    }

    @Override
    protected Expansion.Builder provideBuilder() {
        return  Expansion.builder("platforminfo")
                .globalPlaceholder("name", Tag.preProcessParsed(platformInstance.getName()))
                .globalPlaceholder("online", (queue, ctx) -> Tag.preProcessParsed(Integer.toString(platformInstance.getOnlinePlayers().size())))
                .globalPlaceholder("version", Tag.preProcessParsed(platformInstance.getVersion()))
                .globalPlaceholder("max_players", (queue, ctx) -> Tag.preProcessParsed(Integer.toString(platformInstance.getMaxPlayers())))
                .globalPlaceholder("unique_joins", (queue, ctx) -> Tag.preProcessParsed(Integer.toString(platformInstance.getOfflinePlayers().length)))
                .globalPlaceholder("has_whitelist", (queue, ctx) -> Tag.preProcessParsed(Boolean.toString(platformInstance.hasWhitelist())))
                .globalPlaceholder("total_chunks", (queue, ctx) -> {
                    int chunkCount = 0;
                    for (World world : platformInstance.getWorlds()){
                        chunkCount += world.getLoadedChunks().length;
                    }
                    return Tag.preProcessParsed(Integer.toString(chunkCount));
                })
                .globalPlaceholder("total_entities", (queue, ctx) -> {
                    int entityCount = 0;
                    for (World world : platformInstance.getWorlds()){
                        entityCount += world.getEntityCount();
                    }
                    return Tag.preProcessParsed(Integer.toString(entityCount));
                })
                .globalPlaceholder("datapack_list", (queue, ctx) -> {
                    final TextComponent.Builder builder = Component.text();
                    for (final Datapack datapack : platformInstance.getDatapackManager().getEnabledPacks()) {
                        builder.append(Component.text("[").append(Component.text(datapack.getName()).append(Component.text("] "))));
                    }
                    return Tag.selfClosingInserting(builder.build());
                })
                .globalPlaceholder("datapack_count", (queue, ctx) -> Tag.preProcessParsed(Integer.toString(platformInstance.getDatapackManager().getEnabledPacks().size())));
    }
}
