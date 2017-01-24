public class Testor {
    private Simulator simulator;

    public Testor() {
        //- Actually DO something
        this.init();
    }

    private void init() {
        this.simulator = new Simulator();
        this.simulator.run();
    }
}