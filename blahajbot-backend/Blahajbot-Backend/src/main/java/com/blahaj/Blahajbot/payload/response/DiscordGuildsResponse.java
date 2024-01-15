package com.blahaj.Blahajbot.payload.response;

import com.blahaj.Blahajbot.util.DiscordAvatarUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscordGuildsResponse {

    private long id;
    private String name;
    private String icon;
    private boolean owner;
    private long permissions;

    public String getIcon() {
        if (icon == null) {
            return null;
        }

        return DiscordAvatarUtils.resolveGuildIconUrl(icon, String.valueOf(id));
    }

}