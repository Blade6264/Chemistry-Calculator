package Stoichiometry;

public class Element {

    private String chemical;
    private double moles;

    public Element(String symbol, double number) {
        chemical = symbol;
        moles = number;
    }

    public void set(String symbol, double number) {
        chemical = symbol;
        moles = number;
    }

    public String getSymbol() {
        return chemical;
    }

    public double getMoles() {
        return moles;
    }

    private int atomicnumber;
    private String[] symbolArray = List.getSymbolArray();
    private String[] nameArray = List.getNameArray();
    private double[] massArray = List.getMassArray();

    public int getAtomicNumber() {

        atomicnumber = findIndex(symbolArray, getSymbol());

        return atomicnumber;
    }

    public double getMolarMass() {

        return massArray[getAtomicNumber()];
    }

    public String getAtomName() {

        return nameArray[getAtomicNumber()];
    }

    public double getTotalMass() {
        return Math.round(getMolarMass() * getMoles() * Math.pow(10, 4)) / Math.pow(10, 4);
    }

    public Element combineElements(Element other) {
        if (other == null || !other.getSymbol().equals(getSymbol())) {
            return null;
        }

        double combinedMoles = getMoles() + other.getMoles();
        return new Element(getSymbol(), combinedMoles);
    }

    public static int findIndex(String arr[], String t) {
        if (arr == null) {
            return -1;
        }

        int len = arr.length;
        int i = 0;

        while (i < len) {
            if (arr[i].equals(t)) {
                return i;
            } else {
                i = i + 1;
            }
        }
        return -1;
    }

}
