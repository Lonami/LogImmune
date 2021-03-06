LogImmune
=========

Minecraft 1.12.2 mod for not-so-good computers that take years to login to local
worlds or multiplayer servers.

This mod will grant you immunity on login (thus the name, "log immune")
for thirty seconds until you move (i.e. when the game has loaded
and you can actually do something), similar to games like
[Path of Exile](https://www.pathofexile.com).

* GitHub: https://github.com/Lonami/LogImmune
* CurseForge: https://www.curseforge.com/minecraft/mc-mods/logimmune

Configuration
-------------

The following parameters are configurable:

* Buff duration.
* Buff amplifier.
* Buff to grant.

Incompatible Mods
-----------------

[EpicSiegeMod](https://github.com/Funwayguy/Epic-Siege-Mod) has its own
invulnerability on login. Unfortunately, it is not removed when the player
moves, so it can be heavily abused.

To make it compatible, modify it's `config/epicsiegemod.cfg` and set
the following value to zero:

    # Temporary invulnerability in ticks when respawning and teleporting [range: 0 ~ 2147483647, default: 200]
    I:"Resistance Cooldown"=0

Building
--------

Once you have the Java 8 JDK installed, simply run the following command:

    ./gradlew build

This will generate a `.jar` file under `./build/libs/`.
If you prefer to build and test the mod, use the following command instead:

    ./gradlew runClient

(This can be tried in multiplayer by using `runServer` as well).

Contributing
------------

Please refer to
[Minecraft Forge's documentation for developers](https://mcforge.readthedocs.io)
in order to learn the basics of mod developments, and it may also help you in
making your own.

I do not have any intention to test or update this mod to work on any other
Forge versions, but if you do let me know and open an issue or send a pull
request and I may look into it.
