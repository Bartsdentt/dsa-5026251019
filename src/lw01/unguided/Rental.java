package lw01.unguided;

public abstract class Rental implements Chargeable {
    private String id;
    private int days;

    public Rental(String id, int days) {
        this.id = id;
        this.days = days;

        if (days <= 0) {
            throw new IllegalArgumentException("Days must be positive");
        }
    }

    public String getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

    @Override
    public abstract int calculateCharge();

    public int calculateCharge(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("units must be positive");
        }
        return units * calculateCharge();
    }

    public String label() {
        return "Rental";
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}