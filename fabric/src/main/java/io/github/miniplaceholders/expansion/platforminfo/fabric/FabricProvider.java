package io.github.miniplaceholders.expansion.platforminfo.fabric;

import io.github.miniplaceholders.api.Expansion;
import io.github.miniplaceholders.expansion.platforminfo.PlatformExpansionProvider;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.packs.PackResources;
import net.minecraft.world.entity.Entity;

public final class FabricProvider extends PlatformExpansionProvider<MinecraftServer> {
    public FabricProvider(Object platformInstance) {
        super((MinecraftServer) platformInstance);
    }

    @Override
    protected Expansion.Builder provideBuilder() {
        return Expansion.builder("platforminfo")
                .globalPlaceholder("name", Tag.preProcessParsed(platformInstance.getServerModName()))
                .globalPlaceholder("online", (queue, ctx) -> Tag.preProcessParsed(Integer.toString(platformInstance.getPlayerCount())))
                .globalPlaceholder("version", (ctx, queue) -> Tag.preProcessParsed(platformInstance.getServerVersion()))
                .globalPlaceholder("max_players", (ctx, queue) -> Tag.preProcessParsed(Integer.toString(platformInstance.getMaxPlayers())))
                .globalPlaceholder("unique_joins", (queue, ctx) -> {
                    final var profileCache = platformInstance.getProfileCache();
                    if (profileCache == null) {
                        return Tag.preProcessParsed("0");
                    }
                    return Tag.preProcessParsed(Integer.toString(profileCache.load().size()));
                })
                .globalPlaceholder("tick_count", (queue, ctx) -> Tag.preProcessParsed(Integer.toString(platformInstance.getTickCount())))
                .globalPlaceholder("has_whitelist", (queue, ctx) -> Tag.preProcessParsed(Boolean.toString(platformInstance.getPlayerList().isUsingWhitelist())))
                .globalPlaceholder("total_chunks", (queue, ctx) -> {
                    int chunks = 0;
                    for (final ServerLevel level : platformInstance.getAllLevels()) {
                        chunks += level.getChunkSource().getLoadedChunksCount();
                    }
                    return Tag.preProcessParsed(Integer.toString(chunks));
                })
                .globalPlaceholder("total_entities", (queue, ctx) -> {
                    int entities = 0;
                    for (final ServerLevel level : platformInstance.getAllLevels()) {
                        for (final Entity ignored : level.getAllEntities()) {
                            entities++;
                        }
                    }
                    return Tag.preProcessParsed(Integer.toString(entities));
                })
                .globalPlaceholder("datapack_list", (queue, ctx) ->
                        Tag.selfClosingInserting(platformInstance.getResourceManager()
                                .listPacks()
                                .map(PackResources::packId)
                                .map(id -> Component.text()
                                        .content("[")
                                        .append(Component.text(id))
                                        .append(Component.text("]"))
                                        .build())
                                .collect(Component.toComponent(Component.space())))
                )
                .globalPlaceholder("datapack_count", (queue, ctx) ->
                        Tag.preProcessParsed(Long.toString(platformInstance.getResourceManager()
                                .listPacks()
                                .count()))
                );
    }
}
