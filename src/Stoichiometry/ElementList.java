package Stoichiometry;

import java.util.ArrayList;

public class ElementList {
    private ArrayList<Element> myElements;

    // create 2 arraylist 1 element 1 mass/moles
    public ElementList() {
        myElements = new ArrayList<Element>();
    }

    public void addElement(Element element) {
        myElements.add(element);
    }

    public ArrayList<Element> getElementList() {
        return myElements;
    }

    public double getMolarMassEL() {
        double mass = 0.0;
        if (myElements.size() != 0) {
            for (int i = 0; i < myElements.size(); i++) {
                mass += myElements.get(i).getMolarMass() * myElements.get(i).getMoles();
            }
        }
        return mass;
    }

    public void remove(){
        getElementList().remove(getElementList().size()-1);
    }

    public int size(){
        return getElementList().size();
    }

}
