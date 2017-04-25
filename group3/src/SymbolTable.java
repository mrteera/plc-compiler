package src;

import java.util.Hashtable;

public class SymbolTable extends Hashtable<String,Object>{

    static SymbolTable globalTable;
    
    static {globalTable = new SymbolTable();}

    static void setValue(String id, Variable value){
//        System.out.println("From symboltable setvalue: " + id);
        //parser.print_error(value.toString);
        globalTable.put(id,value);
        // System.out.println("==============");
        // System.out.println(((Variable)globalTable.get(id)).getType());
    }
    
    static void setFunction(String id, Aexp value){
        globalTable.put(id, value);
    }
    
    static Aexp getFunction(String id){
        return (Aexp) globalTable.get(id);
    }

    static Variable getValue(String id){
        //return (Integer) globalTable.get(id);
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
        return null;
        //parser.print_error(id);
        // get type instead, int1, bool2
        // parser.print_error(id); // y
        // parser.print_error(globalTable.get(id).toString()); // 2
        // parser.print_error(globalTable.get(id).getClass().getName());
        //return new Variable(globalTable.get(id));
    }
    
    static Integer getType(String id) {
        return (Integer) globalTable.get(id);
    }
    
    static boolean isExistID(String id) {
        return globalTable.containsKey(id);
    }
    
    static void setType(int type, String id) {
        globalTable.put(id ,type);
    }
}
