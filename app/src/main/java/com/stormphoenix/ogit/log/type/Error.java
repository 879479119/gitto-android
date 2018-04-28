package com.stormphoenix.ogit.log.type;

import com.stormphoenix.ogit.log.subType.Image;
import com.stormphoenix.ogit.log.subType.Network;
import com.stormphoenix.ogit.log.subType.Script;

public class Error {

    private Network network;

    private Script script;

    private Image image;

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
