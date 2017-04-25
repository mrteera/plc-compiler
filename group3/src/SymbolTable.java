package src;

import java.util.Hashtable;

public class SymbolTable extends Hashtable<String,Object>{

    static SymbolTable globalTable;
    static SymbolTable localTable;
    ArrayList<SymbolTable> myArrList = new ArrayList<DataType>();

    
    static {
        globalTable = new SymbolTable();
    }
    
    static void setValue(String id, Variable value){
        if(localTable != null)
            localTable.put(id,value);
        else
            globalTable.put(id,value);
    }

    static Variable getValue(String id){
        if(localTable != null){
            if (localTable.get(id).getClass() == Variable.class )
                return (Variable) localTable.get(id);
            else {
                if ((int)localTable.get(id) == 1)
                    return new Variable(1);
                else if((int)localTable.get(id) == 2)
                    return new Variable(true);
                else if((int)localTable.get(id) == 3)
                    return new Variable(1.0);
            }
        } else {
            if (globalTable.get(id).getClass() == Variable.class )
                return (Variable) globalTable.get(id);
            else {
                if ((int)globalTable.get(id) == 1)
                    return new Variable(1);
                else if((int)globalTable.get(id) == 2)
                    return new Variable(true);
                else if((int)globalTable.get(id) == 3)
                    return new Variable(1.0);
            }
        }
        return null;
    }
    
    static Integer getType(String id) {
        if(localTable != null)
            return (Integer) localTable.get(id);
        return (Integer) globalTable.get(id);
    }
    
    static boolean isExistID(String id) {
        if(localTable != null)
            return localTable.containsKey(id);
        return globalTable.containsKey(id);
    }
    
    static void setType(int type, String id) {
        if(localTable != null)
            localTable.put(id ,type);
        else
            globalTable.put(id ,type);
    }
}
