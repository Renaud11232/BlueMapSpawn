package be.renaud11232.bluemapspawn.icon;

import com.flowpowered.math.vector.Vector2i;

public class SimpleIcon implements Icon {
    private final String src;
    private final Vector2i anchor;

    public SimpleIcon(String src, Vector2i anchor) {
        this.src = src;
        this.anchor = anchor;
    }

    @Override
    public String getSrc() {
        return src;
    }

    @Override
    public Vector2i getAnchor() {
        return anchor;
    }
}
