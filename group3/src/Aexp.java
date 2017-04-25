package src;

public class Aexp {

    private enum AexpType {
        VALUE,
        ID,
        EXP
    }
    
    private final AexpType eType;
    private Integer inum;
    private Double fnum;
    private String id;
    private Args operands;
    private int operator;
    private Variable val;
    
    Aexp(Integer x) {
        eType = AexpType.VALUE;
        val = new Variable(x);
    }
    
    Aexp(Double x) {
        eType = AexpType.VALUE;
        val = new Variable(x);
    }

    public Aexp(Boolean x) {
        eType = AexpType.VALUE;
        val = new Variable(x);
    }
    
    
//   
//    Aexp(Integer x) {
//        eType = AexpType.INTEGER;
//        inum = x;        
//    }
//    
//    Aexp(Double x) {
//        eType = AexpType.FLOAT;
//        fnum = x;        
//    }

    Aexp(String x) {
        eType = AexpType.ID;
        id = x;        
    }

    Aexp(Args x, int op) {
        eType = AexpType.EXP;
        operands = x;
        operator = op;
    }

    public String getexp() {

        String s = "";
        switch (this.eType) {
            case VALUE: s = val.toString(); break;
//            case INTEGER: s = "" + inum; break;
//            case FLOAT: s = "" + fnum; break;
            case ID: s = id; break;
            case EXP:
                switch (operator) {
                    case sym.EQ: 
                        s = "EQ(" + operands.getfi().getexp() + ", " + operands.getfi().getexp() + ")"; 
                        break;
                    case sym.NE: 
                        s = "NE(" + operands.getfi().getexp() + ", " + operands.getfi().getexp() + ")"; 
                        break;
                    case sym.LT: 
                        s = "LT(" + operands.getfi().getexp() + ", " + operands.getfi().getexp() + ")"; 
                        break;
                    case sym.LE: 
                        s = "LE(" + operands.getfi().getexp() + ", " + operands.getfi().getexp() + ")"; 
                        break;
                    case sym.GT: 
                        s = "GT(" + operands.getfi().getexp() + ", " + operands.getfi().getexp() + ")"; 
                        break;
                    case sym.GE: 
                        s = "GE(" + operands.getfi().getexp() + ", " + operands.getfi().getexp() + ")"; 
                        break;
                    case sym.PLUS:
                        s = "PLUS(" + operands.getfi().getexp() + "," + operands.getse().getexp() + ")";
                        break;
                    case sym.MINUS:
                        s = "MINUS(" + operands.getfi().getexp() + "," + operands.getse().getexp() + ")";
                        break;
                    case sym.TIMES:
                        s = "TIMES(" + operands.getfi().getexp() + "," + operands.getse().getexp() + ")";
                        break;
                    case sym.DIVIDE:
                        s = "DIVIDE(" + operands.getfi().getexp() + "," + operands.getse().getexp() + ")";
                        break;
                    default: break;
                } break;
            default: break;
        }

        return s;
    }

    public Variable getValue() {
        //Integer val = 0;
        Variable val = null;
        switch (this.eType) {
            case VALUE:
                val = this.val;
//                if (val == null || val.getType() == Variable.ValType.VOID) {
//                    parser.print_error("Cannot get a value of type VOID");
//                }
                break;
//            case INTEGER:
//                // expression is a number
//                val = new Variable(this.inum); 
//                break;
//            case FLOAT:
//                val = new Variable(this.fnum); 
//                break;
            case ID:
                //expression is a variable
                val = SymbolTable.getValue(id);
                if (val == null) {
                    System.out.print(id + " was not declared");
                    System.exit(0);
                }   break;
//            case ID: val = SymbolTable.getAexp(id).getVal(); break;
            case EXP:
                //expression is a math expression
                switch (operator) {
                    //case sym.PLUS:
                        //val = operands.getfi().getValue() + operands.getse().getValue();
                        //break;
                    case sym.EQ: 
                        val = operands.getfi().getValue().eq(operands.getse().getValue()); 
                        break;
                    case sym.NE: 
                        val = operands.getfi().getValue().ne(operands.getse().getValue()); 
                        break;
                    case sym.LT: 
                        val = operands.getfi().getValue().lt(operands.getse().getValue()); 
                        break;
                    case sym.LE: 
                        val = operands.getfi().getValue().le(operands.getse().getValue()); 
                        break;
                    case sym.GT: 
                        val = operands.getfi().getValue().gt(operands.getse().getValue()); 
                        break;
                    case sym.GE: 
                        val = operands.getfi().getValue().ge(operands.getse().getValue()); 
                        break;
                    case sym.PLUS:
                        val = operands.getfi().getValue().plus(operands.getse().getValue());
                        break;
                    case sym.MINUS:
                        val = operands.getfi().getValue().minus(operands.getse().getValue());
                        break;
                    case sym.TIMES:
                        val = operands.getfi().getValue().times(operands.getse().getValue());
                        break;
                    case sym.DIVIDE:
                        val = operands.getfi().getValue().divide(operands.getse().getValue());
                        break;
                    default:
                        break;
                } break;
            default: break;
        }
        return val;
    }
}
