public abstract class SequenceModifier {
    protected int start;
    protected int step;

    public SequenceModifier() {
        this(0, 1);
    }

    public SequenceModifier(int start, int step) {
        this.start = start;
        this.step = step;
    }

    abstract void modify(Sequence sequence);
}
