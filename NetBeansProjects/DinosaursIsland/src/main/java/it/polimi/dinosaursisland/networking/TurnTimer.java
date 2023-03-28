package it.polimi.dinosaursisland.networking;

import java.util.TimerTask;
import java.util.Timer;

public class TurnTimer {

    private Timer timer;
    private GameServer gs;
    private int seconds;

    public TurnTimer(int seconds, GameServer gs) {
        this.seconds = seconds;
        this.gs = gs;
        timer = new Timer();
        if (seconds == 30) {
            System.out.println("Inizio timer 30 secondi...");
        }
        if (seconds == 120) {
            System.out.println("Inizio timer 120 secondi...");
        }
        timer.schedule(new TimerAction(), seconds * 1000L);

    }

    class TimerAction extends TimerTask {

        public void run() {
            gs.nextTurn();
            if (seconds == 30) {
            System.out.println("Scaduti 30 secondi");
            }
            if (seconds == 120) {
                System.out.println("Scaduti 120 secondi");
            }
        }
    }

    public void endTimer() {
        if (seconds == 30) {
            System.out.println("Timer da 30 secondi terminato");
        }
        if (seconds == 120) {
            System.out.println("Timer da 120 secondi terminato");
        }
        timer.cancel();
    }
}
