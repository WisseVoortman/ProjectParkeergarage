public class Testor {
    private Simulator simulator;

    public Testor() {
        System.out.println( "Hier, kijk deze shit! Bam!" );
        //- Actually DO something
        this.init();
    }

    private void init() {
        this.simulator = new Simulator();
        this.simulator.run();
    }
}