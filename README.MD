# Discord Rich Presence

To use the mod correctly you need to edit the config file (DRP.cfg)

```CFG
# Configuration file

discord {
    # Add your Discord App ID [default: 0]
    S:DISCORD_ID=0

    # Add your Discord Details [default: Minecraft Server]
    S:DRP_DETAILS=Minecraft Server

    # Add your Discord Large Image Key [default: large_image]
    S:DRP_IMAGE_LARGE=large_image

    # Add your Discord Image Large Text [default: Private Minecraft Server]
    S:DRP_IMAGE_LARGE_TEXT=Private Minecraft Server

    # Add your Discord Small Image Key [default: small_image]
    S:DRP_IMAGE_SMALL=small_image

    # Add your Text for the MultiPlayer State [default: In Multiplayer]
    S:DRP_STATE_MULTIPLAYER=In Multiplayer

    # Add your Text for the Other State [default: In The Menu]
    S:DRP_STATE_OTHER=In The Menu

    # Add your Text for the Solo State [default: In SinglePlayer]
    S:DRP_STATE_SOLO=In SinglePlayer
}
```

# Create a Rich Presence

Go to [discord](https://discordapp.com/developers/applications) and create a new Application

<img src="https://cdn.discordapp.com/attachments/625261173226471433/625261521961746432/general_infomations.png"/>

Copy the Client ID and replace the ***0*** of ```S:DISCORD_ID=0``` by the Client ID

### Adding image to the Rich Presence

Go to Rich Presence and add your image

<img src="https://cdn.discordapp.com/attachments/625261173226471433/625263927713988617/art_assets.png"/>

Replace ***large_image*** ```S:DRP_IMAGE_LARGE=large_image``` or ***small_image***```S:DRP_IMAGE_SMALL=small_image``` by the name of the assets

#### Enjoy DRP !
