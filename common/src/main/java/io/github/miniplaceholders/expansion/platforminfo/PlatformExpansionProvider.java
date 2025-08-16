package io.github.miniplaceholders.expansion.platforminfo;

import io.github.miniplaceholders.api.Expansion;

public abstract class PlatformExpansionProvider<T> {
    protected T platformInstance;
    
    protected PlatformExpansionProvider(T platformInstance) {
        this.platformInstance = platformInstance;
    }
    
    protected abstract Expansion.Builder provideBuilder();
}
