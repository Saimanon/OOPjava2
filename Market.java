import java.util.ArrayList;
import java.util.List;

public class Market implements MarketBehaviour, QueueBehaviour {

    private List<Actor> actors = new ArrayList<>();
    private List<Actor> queue = new ArrayList<>();

    @Override
    public void acceptToMarket(Actor actor) {
        System.out.println(actor.getName() + " вошел в маркет.");
        actors.add(actor);
    }

    @Override
    public void releaseFromMarket(List<Actor> actors) {
        for (Actor actor : actors) {
            System.out.println(actor.getName() + " покинул маркет.");
            this.actors.remove(actor);
        }
    }

    @Override
    public void update(int x) {
        for (int i = 0; i < x; i++) {
            if (!queue.isEmpty()) {
                takeOrders();
                giveOrders();
                releaseFromQueue();
            }
        }
    }

    @Override
    public void giveOrders() {
        for (Actor actor : queue) {
            if (actor.isMakeOrder()) {
                System.out.println("Заказ отдан" + actor.getName());
                actor.setTakeOrder(true);
                actor.setMakeOrder(false);
            }
        }
    }

    @Override
    public void releaseFromQueue() {
        if (!queue.isEmpty()) {
            Actor actor = queue.remove(0);
            System.out.println(actor.getName() + " покинул очередь.");
        }
    }

    @Override
    public void takeInQueue(Actor actor) {
        System.out.println(actor.getName() + " встал в очередь.");
        queue.add(actor);
    }

    @Override
    public void takeOrders() {
        for (Actor actor : queue) {
            if (!actor.isMakeOrder()) {
                System.out.println(actor.getName() + " сделал заказ.");
                actor.setMakeOrder(true);
            }
        }
    }
}