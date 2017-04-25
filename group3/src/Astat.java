package src;

import java.time.Clock;

public class Astat {

    int statementType;
    public static int assignment = 0;
    public static int varDeclaration = 1;
    public static int print = 2;
    public static int ifthen = 3;
    public static int block = 4;
    public static int whileloop = 5;
    public static int funDeclaration = 6;
    public static int returnStatement = 7;
    /*
     * assignment statement: variable = expr
     *
     */
    String assVariable;
    Aexp assExpr;
    public static Astat assignment(String Variable, Aexp expr) {
        Astat statement = new Astat();
        statement.statementType = assignment;
        statement.assVariable = Variable;
        statement.assExpr = expr;
        return statement;
    }

    /*
     * while loop: while whileCondition begin whileBody end
     *
     */
    Aexp whileCondition;
    Astat whileBody;

    public static Astat whileloop(Aexp condition, Astat WhileBody) {
        Astat statement = new Astat();
        statement.statementType = whileloop;
        statement.whileCondition = condition;
        statement.whileBody = WhileBody;
        return statement;

    }
    
    
    Integer varType;
    String varID;
    //Aexp varExpr;
    public static Astat varDec(int type, String id) {
        Astat statement = new Astat();
        statement.statementType = varDeclaration;
        statement.varType = type;
        statement.varID = id;
        return statement;
    }
    
    /*
     * if then statement: if ifcondition then ifbody
     *
     */
    Aexp ifcondition;
    Astat ifbody;

    public static Astat ifthen(Aexp Condition, Astat Ifbody) {
        Astat statement = new Astat();
        statement.statementType = ifthen;
        statement.ifcondition = Condition;
        statement.ifbody = Ifbody;

        return statement;
    }

    /*
     * print statement: print e
     */
    Aexp printE;

    public static Astat print(Aexp expr) {

        Astat statement = new Astat();
        statement.statementType = print;
        statement.printE = expr;
        return statement;

    }

    /*
     * block statement: begin statement_list end
     */
    Lstat blockBody;

    public static Astat block(Lstat l) {
        Astat statement = new Astat();
        statement.statementType = block;
        statement.blockBody = l;
        return statement;
    }

    public String getstat() {
        if (statementType == assignment) {
            return assVariable + ":=" + assExpr.getexp();
        } else if (statementType == ifthen) {
            return "if " + ifcondition.getexp() + " " + ifbody.getstat();
        } else if (statementType == print) {
            return "print " + printE.getexp();
        } else if (statementType == whileloop) {
            return "while (" + whileCondition.getexp() + ") " + whileBody.getstat();
        } else if (statementType == block) {
            return "{ " + blockBody.getstat() + " }";
        } else {
            return "unknown";
        }
    }

    public void execute() {

        if (statementType == assignment) {
//            System.out.println("Assvariable " + assVariable + " assignment " + assExpr.getValue().getType());
//            System.out.println("symbol" + SymbolTable.globalTable.get(assVariable) );
            if((assExpr.getValue().getType() == Variable.ValType.INT) && (((Variable)SymbolTable.globalTable.getValue(assVariable)).getType() == Variable.ValType.INT)){
                SymbolTable.setValue(assVariable, assExpr.getValue());
            }
            else if((assExpr.getValue().getType() == Variable.ValType.BOOL) && (((Variable)SymbolTable.globalTable.getValue(assVariable)).getType() == Variable.ValType.BOOL)){
                SymbolTable.setValue(assVariable, assExpr.getValue());  
            }
            else if((assExpr.getValue().getType() == Variable.ValType.FLOAT) && ((((Variable)SymbolTable.globalTable.getValue(assVariable)).getType() == Variable.ValType.FLOAT))){
                SymbolTable.setValue(assVariable, assExpr.getValue());
            }
            else {
                parser.print_error("Variable Type mismatch");
            }
//            SymbolTable.setValue(assVariable, assExpr.getValue());
        } else if (statementType == varDeclaration) {
            if ( !SymbolTable.isExistID(varID)) {
               SymbolTable.setType(this.varType, this.varID);
            } else {
                System.err.println("Duplicate ID: " + this.varID);
            }
        } else if (statementType == ifthen) {

            if (ifcondition.getValue() != null) {
                ifbody.execute();
            }

        } else if (statementType == whileloop) {

            for (;;) {

                if (whileCondition.getValue().getBoolVal() != false) {
                    whileBody.execute();
                } else {
                    break;
                }

            }

        } else if (statementType == print) {

            System.out.println(printE.getValue());

        } else if (statementType == block) {
            for (Astat s : blockBody.statementList) {
                s.execute();
            }
        }
    }
}
