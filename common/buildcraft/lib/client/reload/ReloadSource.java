package buildcraft.lib.client.reload;

import java.util.Objects;

import net.minecraft.util.ResourceLocation;

/** A combination of a {@link ResourceLocation} (Which object changed) and {@link SourceType} (What about it
 * changed). */
public class ReloadSource {
    public final ResourceLocation location;
    public final SourceType type;
    private final int hash;

    public ReloadSource(ResourceLocation location, SourceType type) {
        this.location = location;
        this.type = type;
        hash = Objects.hash(location, type);
    }

    public ReloadSource(String loc, SourceType type) {
        this(new ResourceLocation(loc), type);
    }

    public ReloadSource(String domain, String path, SourceType type) {
        this(new ResourceLocation(domain, path), type);
    }

    @Override
    public String toString() {
        return type + " [ " + location + " ]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) {
            return false;
        }
        ReloadSource other = (ReloadSource) obj;
        return type == other.type//
            && location.equals(other.location);
    }

    @Override
    public int hashCode() {
        return hash;
    }
}
