# Discord Rich Presence

To use the mod correctly you need to edit the config file (discordrichpresence-client.toml)

```TOML

#Discord Rich Presence Configuration File
["Configuration for Discord Application"]
	#Discord Rich Presence Application ID
	discordID = "573197802985881611"
	#Discord Rich Presence Details
	details = "Minecraft Server"
	#Discord Rich Presence Large Image Name
	large_image_name = "large_image"
	#Discord Rich Presence Large Image Text
	large_image_text = "Private Minecraft Server"
	#Discord Rich Presence Small Image Name
	small_image_name = "small_image"
	#Text displayed in SinglePlayer
	state_solo = "In Singleplayer"
	#Text displayed in SinglePlayer
	state_multi = "In Multiplayer"
	#Text displayed in Multiplayer
	max_players_number = 100
	#Text displayed for other state
	state_other = "In the Menu"

```

# Create a Rich Presence

Go to [discord](https://discordapp.com/developers/applications) and create a new Application

<img src="https://cdn.discordapp.com/attachments/625261173226471433/625261521961746432/general_infomations.png"/>

Copy the Client ID and replace ***573197802985881611*** of ```S:discordID=573197802985881611``` by the Client ID

### Adding image to the Rich Presence

Go to Rich Presence and add your image

<img src="https://cdn.discordapp.com/attachments/625261173226471433/625263927713988617/art_assets.png"/>

Replace ***large_image*** ```S:large_image_name=large_image``` or ***small_image***```S:small_image_name=small_image``` by the name of the assets

#### Enjoy DRP !
