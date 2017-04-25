package src;

import java.util.LinkedList;

public class Listexp {
    LinkedList<Aexp> exprList;

    public Listexp(Aexp e){
        exprList = new LinkedList<Aexp>();
        exprList.add(e);
    }

    public Listexp(Listexp l, Aexp e){
        exprList = l.exprList;
        exprList.add(e);
    }
    
    public String getExp() {
        String s = "";
        for (Aexp e : exprList) {
            s += e.getexp();
        }
        return s;
    }

    public Aexp getAexp(int index) {
        return exprList.get(index);
    }
    
    public int size() {
        return exprList.size();
    }
   
    public void clear() {
        exprList.clear();
    }
}
