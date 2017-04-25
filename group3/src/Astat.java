package src;

public class Astat {
    int statementType;
    int assignmentStatementType = 999;
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
        statement.assignmentStatementType = assignment;
        statement.assVariable = Variable;
        // parser.print_error(expr.getValue().toString()); // true
        //System.out.println("assignemnt, value: " + expr.getValue().toString());
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
    
    
//    Integer varType;
//    String varID;
//    //Aexp varExpr;
//    public static Astat varDec(int type, String id) {
//        Astat statement = new Astat();
//        statement.statementType = varDeclaration;
//        statement.varType = type;
//        statement.varID = id;
//        return statement;
//    }
    
    Integer varType;
    Lstat varDeclList;
    public static Astat varDec(int type, Lstat l) {
        Astat statement = new Astat();
        statement.statementType = varDeclaration;
        statement.varType = type;
        // parser.print_error(l.statementList.getClass().getName());
        // parser.print_error((new Integer(l.statementList.size())).toString());
        //parser.print_error((new String(l.statementList.get(0).assVariable)).toString());
        //System.out.println("Varname: " + (new String(l.statementList.get(0).assVariable)).toString());
        statement.varDeclList = l;
        //parser.print_error((new String(statement.varDeclList.statementList.get(0).varID)).toString());
        // System.out.println("l: " + (new String(statement.varDeclList.statementList.get(0).varID)).toString());
        return statement;
    }
    
//    String varID;
    public static Astat oneVarDec(String id) {
        Astat statement = new Astat();
        statement.statementType = varDeclaration;
        statement.assVariable = id;
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
            return assVariable + "=" + assExpr.getexp();
        } else if (statementType == ifthen) {
            return "if " + ifcondition.getexp() + " " + ifbody.getstat();
        } else if (statementType == print) {
            return "print " + printE.getexp();
        } else if (statementType == whileloop) {
            return "while " + whileCondition.getexp() + " do " + whileBody.getstat();
        } else if (statementType == block) {
            return "block";
        } else {
            return "unknown";
        }
    }

    public void execute() {
        // parser.print_error(statementType);
        //System.out.println("statementType: " + statementType);
        // parser.print_error((new String(l.statementList.get(0).assVariable)).toString());

        if (statementType == assignment) {
//            System.out.println("Assvariable " + assVariable + " assignment " + assExpr.getValue().getType());
//            System.out.println("symbol" + SymbolTable.globalTable.get(assVariable) );
            if((assExpr.getValue().getType() == Variable.ValType.INT) && ((int)SymbolTable.globalTable.get(assVariable) == 1)){
                SymbolTable.setValue(assVariable, assExpr.getValue());
            }
            else if((assExpr.getValue().getType() == Variable.ValType.BOOL) && ((int)SymbolTable.globalTable.get(assVariable) == 2)){
                parser.print_error(assExpr.getValue().toString());
                SymbolTable.setValue(assVariable, assExpr.getValue());  
            }
            else if((assExpr.getValue().getType() == Variable.ValType.FLOAT) && ((int)SymbolTable.globalTable.get(assVariable) == 3)){
                SymbolTable.setValue(assVariable, assExpr.getValue());
            }
            else {
                parser.print_error("Variable Type mismatch");
            }
//            SymbolTable.setValue(assVariable, assExpr.getValue());
        } else if (statementType == varDeclaration) {
           // if ( !SymbolTable.isExistID(varID)) {
           // parser.print_error((new String(varDeclList.statementList.get(0).varID)).toString());

           for (Astat s : varDeclList.statementList) {
               // parser.print_error((new Integer(varType)).toString());
               // parser.print_error(assExpr.toString());
               // System.out.println("assignemntStatementType: " + s.assignmentStatementType);
               SymbolTable.setType(varType, s.assVariable);
               if (s.assignmentStatementType == assignment) {
                    //System.out.println("hello2");
                    //System.out.println("s.assVariable: " + s.assVariable);
                    //System.out.println("assExpr.getValue: " + s.assExpr.getValue());
                    SymbolTable.setValue(s.assVariable, s.assExpr.getValue());
               }
               //SymbolTable.setValue(assVariable, assExpr.getValue());
           }
          //  } else {
            //    System.err.println("Duplicate ID: " + this.varID);
           // }
        } else if (statementType == ifthen) {

            if (ifcondition.getValue() != null) {
                ifbody.execute();
            }

        } else if (statementType == whileloop) {

            for (;;) {

                if (whileCondition.getValue() != null) {
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
