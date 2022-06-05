package edu.hitsz.prop;

import edu.hitsz.Events.Events;
import edu.hitsz.application.Game;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicThread;
import edu.hitsz.application.VideoManager;
import edu.hitsz.publish.BombPublisher;

public class BombProp extends AbstractProp{
    public BombProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void propActive(Game game)
    {
        System.out.println("炸弹道具生效!");
        if (Events.musicType.equals(Events.MusicType.OPEN))
        {
            new MusicThread(VideoManager.BGM_BOMB_EXPLOSION_WAV).start();
        }

        BombPublisher bombPublisher = new BombPublisher();
        bombPublisher.addObserversList(game.getEnemyAircrafts());
        bombPublisher.notifyAllObservers();

        for (int i=0;i<game.getEnemyBullets().size();i++)
        {
            game.getEnemyBullets().get(i).vanish();
        }
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }
}
