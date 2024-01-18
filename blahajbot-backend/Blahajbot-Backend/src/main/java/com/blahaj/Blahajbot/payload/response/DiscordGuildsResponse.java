package com.blahaj.Blahajbot.payload.response;

import com.blahaj.Blahajbot.util.DiscordAvatarUtils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiscordGuildsResponse {

    private String id;
    private String name;
    private String icon;
    private boolean owner;
    private long permissions;

    

    public DiscordGuildsResponse(String id, String name, String icon, boolean owner, long permissions) {
        this.id=id;
        this.name = name;
        this.icon = icon;
        this.owner = owner;
        this.permissions = permissions;
    }



    public String getIcon() {
        if (icon == null) {
            return null;
        }

        return DiscordAvatarUtils.resolveGuildIconUrl(icon, id);
    }

}