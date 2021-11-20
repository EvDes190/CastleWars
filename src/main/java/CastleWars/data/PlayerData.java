package CastleWars.data;

import static CastleWars.Bundle.findLocale;
import static CastleWars.Bundle.format;

import CastleWars.Main;
import CastleWars.logic.Room;
import CastleWars.logic.TurretRoom;
import arc.Events;
import arc.math.Mathf;
import arc.struct.IntMap;
import arc.util.Interval;
import arc.util.Strings;
import mindustry.Vars;
import mindustry.content.UnitTypes;
import mindustry.game.EventType;
import mindustry.gen.*;

public class PlayerData {

    private static final int defaultIncome = 15;

    public static IntMap<PlayerData> datas = new IntMap<>();
    public static float MoneyInterval = 60f;
    public static float LabelInterval = 3 * 60f;

    public final Player player;
    public boolean disabledHud = false;
    public int money = 0, income = defaultIncome;
    private float bonus = 1f;
    private final Interval interval;

    public PlayerData(Player player) {
        this.player = player;
        this.interval = new Interval(2);
    }

    public void update() {
        bonus = Math.max((float) Groups.player.size() / Groups.player.count(p -> p.team() == player.team()) / 2f, 1f);
        if (interval.get(0, MoneyInterval)) money += income * bonus;
        if (interval.get(1, LabelInterval)) labels(player);

        if (player.shooting && player.unit() != null) {
            Room.rooms.each(room -> !(room instanceof TurretRoom turretRoom && turretRoom.team != player.team()), room -> {
                if (room.check(player.unit().aimX, player.unit().aimY) && room.canBuy(this)) room.buy(this);
            });
        }

        if (player.unit().spawnedByCore && !(player.unit() instanceof WaterMovec) && player.team().core() != null) {
            Unit unit = UnitTypes.risso.spawn(player.team(), player.team().core().x + 30, player.team().core().y + Mathf.random(-40, 40));
            unit.spawnedByCore = true;
            player.unit(unit);
        }

        if (!disabledHud) {
            StringBuilder hud = new StringBuilder(format("commands.hud.display", findLocale(player), money, income));
            if (bonus > 1f) hud.append(Strings.format(" [lightgray]([accent]+@%[lightgray])", (bonus - 1) * 100));
            if (player.unit() != null && player.unit().isFlying() && !Main.logic.placeCheck(player)) hud.append(format("commands.hud.fly-warning", findLocale(player)));
            Call.setHudText(player.con, hud.toString());
        }
    }

    public static void init() {
        Events.on(EventType.PlayerJoin.class, event -> {
            datas.put(event.player.id, new PlayerData(event.player));
            Vars.netServer.assignTeam(event.player, Groups.player);
        });

        Events.on(EventType.PlayerLeave.class, event -> datas.remove(event.player.id));
    }

    public static void labels(Player player) {
        Room.rooms.each(room -> !(room instanceof TurretRoom turretRoom && turretRoom.team != player.team()), room -> {
            if (room.labelVisible) Call.label(player.con, room.label, LabelInterval / 60f, room.centreDrawx, room.centreDrawy - room.size * 2);
        });
    }

    public void reset() {
        income = defaultIncome;
        money = 0;
        bonus = 1f;
    }
}
